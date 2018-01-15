/* Soot - a J*va Optimization Framework
 * Copyright (C) 1997-2000 Etienne Gagnon.  All rights reserved.
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

/*
 * Modified by the Sable Research Group and others 1997-1999.
 * See the 'credits' file distributed with Soot for the complete list of
 * contributors.  (Soot is distributed at http://www.sable.mcgill.ca/soot)
 */

package soot.jimple.toolkits.typing;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import soot.G;

class StronglyConnectedComponents {
  List<TypeVariable> variables;
  Set<TypeVariable> black;
  List<TypeVariable> finished;

  List<List<TypeVariable>> forest = new LinkedList<>();
  List<TypeVariable> current_tree;

  private static final boolean DEBUG = false;

  public static void merge(List<TypeVariable> typeVariableList) throws TypeException {
    new StronglyConnectedComponents(typeVariableList);
  }

  private StronglyConnectedComponents(List<TypeVariable> typeVariableList) throws TypeException {
    variables = typeVariableList;

    black = new TreeSet<>();
    finished = new LinkedList<>();

    for (TypeVariable var : variables) {
      if (!black.contains(var)) {
        black.add(var);
        dfsg_visit(var);
      }
    }

    black = new TreeSet<>();

    for (TypeVariable var : finished) {
      if (!black.contains(var)) {
        current_tree = new LinkedList<>();
        forest.add(current_tree);
        black.add(var);
        dfsgt_visit(var);
      }
    }

    for (List<TypeVariable> list : forest) {
      TypeVariable previous = null;
      StringBuffer s = null;
      if (DEBUG) {
        s = new StringBuffer("scc:\n");
      }

      for (TypeVariable current : list) {
        if (DEBUG) {
          s.append(" " + current + "\n");
        }

        if (previous == null) {
          previous = current;
        } else {
          try {
            previous = previous.union(current);
          } catch (TypeException e) {
            if (DEBUG) {
              G.v().out.println(s);
            }
            throw e;
          }
        }
      }
    }
  }

  private void dfsg_visit(TypeVariable var) {
    List<TypeVariable> parents = var.parents();

    for (TypeVariable parent : parents) {
      if (!black.contains(parent)) {
        black.add(parent);
        dfsg_visit(parent);
      }
    }

    finished.add(0, var);
  }

  private void dfsgt_visit(TypeVariable var) {
    current_tree.add(var);

    List<TypeVariable> children = var.children();

    for (TypeVariable child : children) {
      if (!black.contains(child)) {
        black.add(child);
        dfsgt_visit(child);
      }
    }
  }
}