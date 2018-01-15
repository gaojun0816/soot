/* Soot - a J*va Optimization Framework
 * Copyright (C) 2005 Antoine Mine
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

/**
 * Implementation of the paper "A Combined Pointer and Purity Analysis for Java Programs" by
 * Alexandru Salcianu and Martin Rinard, within the Soot Optimization Framework.
 *
 * <p>by Antoine Mine, 2005/01/24
 */
package soot.jimple.toolkits.annotation.purity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import soot.G;
import soot.SootMethod;
import soot.jimple.toolkits.callgraph.CallGraph;
import soot.jimple.toolkits.callgraph.Edge;
import soot.toolkits.graph.DirectedGraph;
import soot.util.HashMultiMap;
import soot.util.MultiMap;

/**
 * Builds a DirectedGraph from a CallGraph and SootMethodFilter.
 *
 * <p>This is used in AbstractInterproceduralAnalysis to construct a reverse pseudo topological
 * order on which to iterate. You can specify a SootMethodFilter to trim the graph by cutting call
 * edges.
 *
 * <p>Methods filtered-out by the SootMethodFilter will not appear in the DirectedGraph!
 */
public class DirectedCallGraph implements DirectedGraph<SootMethod> {

  protected Set<SootMethod> nodes;
  protected Map<SootMethod, List<SootMethod>> succ;
  protected Map<SootMethod, List<SootMethod>> pred;
  protected List<SootMethod> heads;
  protected List<SootMethod> tails;
  protected int size;

  /**
   * The constructor does all the work here. After constructed, you can safely use all interface
   * methods. Moreover, these methods should perform very fast...
   *
   * <p>The DirectedGraph will only contain methods in call paths from a method in head and
   * comprising only methods wanted by filter. Moreover, only concrete methods are put in the
   * graph...
   *
   * @param cg
   * @param filter
   * @param heads is a List of SootMethod
   * @param verbose
   */
  public DirectedCallGraph(
      CallGraph cg, SootMethodFilter filter, Iterator<SootMethod> heads, boolean verbose) {
    // filter heads by filter
    List<SootMethod> filteredHeads = new LinkedList<>();
    while (heads.hasNext()) {
      SootMethod m = heads.next();
      if (m.isConcrete() && filter.want(m)) {
        filteredHeads.add(m);
      }
    }

    this.nodes = new HashSet<>(filteredHeads);

    MultiMap<SootMethod, SootMethod> s = new HashMultiMap<>();
    MultiMap<SootMethod, SootMethod> p = new HashMultiMap<>();

    // simple breadth-first visit
    Set<SootMethod> remain = new HashSet<>(filteredHeads);
    int nb = 0;
    if (verbose) {
      G.v().out.println("[AM] dumping method dependencies");
    }
    while (!remain.isEmpty()) {
      Set<SootMethod> newRemain = new HashSet<>();
      for (SootMethod m : remain) {
        if (verbose) {
          G.v().out.println(" |- " + m.toString() + " calls");
        }

        for (Iterator<Edge> itt = cg.edgesOutOf(m); itt.hasNext(); ) {
          Edge edge = itt.next();
          SootMethod mm = edge.tgt();
          boolean keep = mm.isConcrete() && filter.want(mm);
          if (verbose) {
            G.v().out.println(" |  |- " + mm.toString() + (keep ? "" : " (filtered out)"));
          }
          if (keep) {
            if (this.nodes.add(mm)) {
              newRemain.add(mm);
            }
            s.put(m, mm);
            p.put(mm, m);
          }
        }
        nb++;
      }
      remain = newRemain;
    }
    G.v().out.println("[AM] number of methods to be analysed: " + nb);

    // MultiMap -> Map of List
    this.succ = new HashMap<>();
    this.pred = new HashMap<>();
    this.tails = new LinkedList<>();
    this.heads = new LinkedList<>();
    for (SootMethod x : this.nodes) {
      Set<SootMethod> ss = s.get(x);
      Set<SootMethod> pp = p.get(x);
      this.succ.put(x, new LinkedList<>(ss));
      this.pred.put(x, new LinkedList<>(pp));
      if (ss.isEmpty()) {
        this.tails.add(x);
      }
      if (pp.isEmpty()) {
        this.heads.add(x);
      }
    }

    this.size = this.nodes.size();
  }

  /**
   * You get a List of SootMethod.
   *
   * @return
   */
  @Override
  public List<SootMethod> getHeads() {
    return heads;
  }

  /**
   * You get a List of SootMethod.
   *
   * @return
   */
  @Override
  public List<SootMethod> getTails() {
    return tails;
  }

  /**
   * You get an Iterator on SootMethod.
   *
   * @return
   */
  @Override
  public Iterator<SootMethod> iterator() {
    return nodes.iterator();
  }

  /** @return */
  @Override
  public int size() {
    return size;
  }

  /**
   * You get a List of SootMethod.
   *
   * @param s
   * @return
   */
  @Override
  public List<SootMethod> getSuccsOf(SootMethod s) {
    return succ.get(s);
  }

  /**
   * You get a List of SootMethod.
   *
   * @param s
   * @return
   */
  @Override
  public List<SootMethod> getPredsOf(SootMethod s) {
    return pred.get(s);
  }
}