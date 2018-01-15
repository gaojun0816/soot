/* Soot - a J*va Optimization Framework
 * Copyright (C) 2003 Ondrej Lhotak
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

/* THIS FILE IS AUTO-GENERATED FROM soot_options.xml. DO NOT MODIFY. */

package soot.options;

import java.util.Map;

/** Option parser for Spark. */
public class SparkOptions {
  private Map<String, String> options;

  public SparkOptions(Map<String, String> options) {
    this.options = options;
  }

  /**
   * Enabled --
   *
   * <p>.
   */
  public boolean enabled() {
    return soot.PhaseOptions.getBoolean(options, "enabled");
  }

  /**
   * Verbose --
   *
   * <p>Print detailed information about the execution of Spark.
   *
   * <p>When this option is set to true, Spark prints detailed information about its execution.
   */
  public boolean verbose() {
    return soot.PhaseOptions.getBoolean(options, "verbose");
  }

  /**
   * Ignore Types Entirely --
   *
   * <p>Make Spark completely ignore declared types of variables.
   *
   * <p>When this option is set to true, all parts of Spark completely ignore declared types of
   * variables and casts.
   */
  public boolean ignore_types() {
    return soot.PhaseOptions.getBoolean(options, "ignore-types");
  }

  /**
   * Force Garbage Collections --
   *
   * <p>Force garbage collection for measuring memory usage.
   *
   * <p>When this option is set to true, calls to System.gc() will be made at various points to
   * allow memory usage to be measured.
   */
  public boolean force_gc() {
    return soot.PhaseOptions.getBoolean(options, "force-gc");
  }

  /**
   * Pre Jimplify --
   *
   * <p>Jimplify all methods before starting Spark.
   *
   * <p>When this option is set to true, Spark converts all available methods to Jimple before
   * starting the points-to analysis. This allows the Jimplification time to be separated from the
   * points-to time. However, it increases the total time and memory requirement, because all
   * methods are Jimplified, rather than only those deemed reachable by the points-to analysis.
   */
  public boolean pre_jimplify() {
    return soot.PhaseOptions.getBoolean(options, "pre-jimplify");
  }

  /**
   * AppOnly --
   *
   * <p>Consider only application classes.
   *
   * <p>Setting this option to true causes Soot to only consider application classes when building
   * the callgraph. The resulting callgraph will be inherently unsound. Still, this option can make
   * sense if performance optimization and memory reduction are your primary goal.
   */
  public boolean apponly() {
    return soot.PhaseOptions.getBoolean(options, "apponly");
  }

  /**
   * VTA --
   *
   * <p>Emulate Variable Type Analysis.
   *
   * <p>Setting VTA to true has the effect of setting field-based, types-for-sites, and
   * simplify-sccs to true, and on-fly-cg to false, to simulate Variable Type Analysis, described in
   * our OOPSLA 2000 paper. Note that the algorithm differs from the original VTA in that it handles
   * array elements more precisely.
   */
  public boolean vta() {
    return soot.PhaseOptions.getBoolean(options, "vta");
  }

  /**
   * RTA --
   *
   * <p>Emulate Rapid Type Analysis.
   *
   * <p>Setting RTA to true sets types-for-sites to true, and causes Spark to use a single points-to
   * set for all variables, giving Rapid Type Analysis.
   */
  public boolean rta() {
    return soot.PhaseOptions.getBoolean(options, "rta");
  }

  /**
   * Field Based --
   *
   * <p>Use a field-based rather than field-sensitive representation.
   *
   * <p>When this option is set to true, fields are represented by variable (Green) nodes, and the
   * object that the field belongs to is ignored (all objects are lumped together), giving a
   * field-based analysis. Otherwise, fields are represented by field reference (Red) nodes, and the
   * objects that they belong to are distinguished, giving a field-sensitive analysis.
   */
  public boolean field_based() {
    return soot.PhaseOptions.getBoolean(options, "field-based");
  }

  /**
   * Types For Sites --
   *
   * <p>Represent objects by their actual type rather than allocation site.
   *
   * <p>When this option is set to true, types rather than allocation sites are used as the elements
   * of the points-to sets.
   */
  public boolean types_for_sites() {
    return soot.PhaseOptions.getBoolean(options, "types-for-sites");
  }

  /**
   * Merge String Buffer --
   *
   * <p>Represent all StringBuffers as one object.
   *
   * <p>When this option is set to true, all allocation sites creating java.lang.StringBuffer
   * objects are grouped together as a single allocation site.
   */
  public boolean merge_stringbuffer() {
    return soot.PhaseOptions.getBoolean(options, "merge-stringbuffer");
  }

  /**
   * Propagate All String Constants --
   *
   * <p>Propagate all string constants, not just class names.
   *
   * <p>When this option is set to false, Spark only distinguishes string constants that may be the
   * name of a class loaded dynamically using reflection, and all other string constants are lumped
   * together into a single string constant node. Setting this option to true causes all string
   * constants to be propagated individually.
   */
  public boolean string_constants() {
    return soot.PhaseOptions.getBoolean(options, "string-constants");
  }

  /**
   * Simulate Natives --
   *
   * <p>Simulate effects of native methods in standard class library.
   *
   * <p>When this option is set to true, the effects of native methods in the standard Java class
   * library are simulated.
   */
  public boolean simulate_natives() {
    return soot.PhaseOptions.getBoolean(options, "simulate-natives");
  }

  /**
   * Treat EMPTY as Alloc --
   *
   * <p>Treat singletons for empty sets etc. as allocation sites.
   *
   * <p>When this option is set to true, Spark treats references to EMPTYSET, EMPTYMAP, and
   * EMPTYLIST as allocation sites for HashSet, HashMap and LinkedList objects respectively, and
   * references to Hashtable.emptyIterator as allocation sites for Hashtable.EmptyIterator. This
   * enables subsequent analyses to differentiate different uses of Java's immutable empty
   * collections.
   */
  public boolean empties_as_allocs() {
    return soot.PhaseOptions.getBoolean(options, "empties-as-allocs");
  }

  /**
   * Simple Edges Bidirectional --
   *
   * <p>Equality-based analysis between variable nodes.
   *
   * <p>When this option is set to true, all edges connecting variable (Green) nodes are made
   * bidirectional, as in Steensgaard's analysis.
   */
  public boolean simple_edges_bidirectional() {
    return soot.PhaseOptions.getBoolean(options, "simple-edges-bidirectional");
  }

  /**
   * On Fly Call Graph --
   *
   * <p>Build call graph as receiver types become known.
   *
   * <p>When this option is set to true, the call graph is computed on-the-fly as points-to
   * information is computed. Otherwise, an initial CHA approximation to the call graph is used.
   */
  public boolean on_fly_cg() {
    return soot.PhaseOptions.getBoolean(options, "on-fly-cg");
  }

  /**
   * Simplify Offline --
   *
   * <p>Collapse single-entry subgraphs of the PAG.
   *
   * <p>When this option is set to true, variable (Green) nodes which form single-entry subgraphs
   * (so they must have the same points-to set) are merged before propagation begins.
   */
  public boolean simplify_offline() {
    return soot.PhaseOptions.getBoolean(options, "simplify-offline");
  }

  /**
   * Simplify SCCs --
   *
   * <p>Collapse strongly-connected components of the PAG.
   *
   * <p>When this option is set to true, variable (Green) nodes which form strongly-connected
   * components (so they must have the same points-to set) are merged before propagation begins.
   */
  public boolean simplify_sccs() {
    return soot.PhaseOptions.getBoolean(options, "simplify-sccs");
  }

  /**
   * Ignore Types For SCCs --
   *
   * <p>Ignore declared types when determining node equivalence for SCCs.
   *
   * <p>When this option is set to true, when collapsing strongly-connected components, nodes
   * forming SCCs are collapsed regardless of their declared type. The collapsed SCC is given the
   * most general type of all the nodes in the component. When this option is set to false, only
   * edges connecting nodes of the same type are considered when detecting SCCs. This option has no
   * effect unless simplify-sccs is true.
   */
  public boolean ignore_types_for_sccs() {
    return soot.PhaseOptions.getBoolean(options, "ignore-types-for-sccs");
  }

  /**
   * Dump HTML --
   *
   * <p>Dump pointer assignment graph to HTML for debugging.
   *
   * <p>When this option is set to true, a browseable HTML representation of the pointer assignment
   * graph is output to a file called pag.jar after the analysis completes. Note that this
   * representation is typically very large.
   */
  public boolean dump_html() {
    return soot.PhaseOptions.getBoolean(options, "dump-html");
  }

  /**
   * Dump PAG --
   *
   * <p>Dump pointer assignment graph for other solvers.
   *
   * <p>When this option is set to true, a representation of the pointer assignment graph suitable
   * for processing with other solvers (such as the BDD-based solver) is output before the analysis
   * begins.
   */
  public boolean dump_pag() {
    return soot.PhaseOptions.getBoolean(options, "dump-pag");
  }

  /**
   * Dump Solution --
   *
   * <p>Dump final solution for comparison with other solvers.
   *
   * <p>When this option is set to true, a representation of the resulting points-to sets is dumped.
   * The format is similar to that of the Dump PAG option, and is therefore suitable for comparison
   * with the results of other solvers.
   */
  public boolean dump_solution() {
    return soot.PhaseOptions.getBoolean(options, "dump-solution");
  }

  /**
   * Topological Sort --
   *
   * <p>Sort variable nodes in dump.
   *
   * <p>When this option is set to true, the representation dumped by the Dump PAG option is dumped
   * with the variable (green) nodes in (pseudo-)topological order. This option has no effect unless
   * Dump PAG is true.
   */
  public boolean topo_sort() {
    return soot.PhaseOptions.getBoolean(options, "topo-sort");
  }

  /**
   * Dump Types --
   *
   * <p>Include declared types in dump.
   *
   * <p>When this option is set to true, the representation dumped by the Dump PAG option includes
   * type information for all nodes. This option has no effect unless Dump PAG is true.
   */
  public boolean dump_types() {
    return soot.PhaseOptions.getBoolean(options, "dump-types");
  }

  /**
   * Class Method Var --
   *
   * <p>In dump, label variables by class and method.
   *
   * <p>When this option is set to true, the representation dumped by the Dump PAG option represents
   * nodes by numbering each class, method, and variable within the method separately, rather than
   * assigning a single integer to each node. This option has no effect unless Dump PAG is true.
   * Setting Class Method Var to true has the effect of setting Topological Sort to false.
   */
  public boolean class_method_var() {
    return soot.PhaseOptions.getBoolean(options, "class-method-var");
  }

  /**
   * Dump Answer --
   *
   * <p>Dump computed reaching types for comparison with other solvers.
   *
   * <p>When this option is set to true, the computed reaching types for each variable are dumped to
   * a file, so that they can be compared with the results of other analyses (such as the old VTA).
   */
  public boolean dump_answer() {
    return soot.PhaseOptions.getBoolean(options, "dump-answer");
  }

  /**
   * Add Tags --
   *
   * <p>Output points-to results in tags for viewing with the Jimple.
   *
   * <p>When this option is set to true, the results of the analysis are encoded within tags and
   * printed with the resulting Jimple code.
   */
  public boolean add_tags() {
    return soot.PhaseOptions.getBoolean(options, "add-tags");
  }

  /**
   * Calculate Set Mass --
   *
   * <p>Calculate statistics about points-to set sizes.
   *
   * <p>When this option is set to true, Spark computes and prints various cryptic statistics about
   * the size of the points-to sets computed.
   */
  public boolean set_mass() {
    return soot.PhaseOptions.getBoolean(options, "set-mass");
  }

  /**
   * Demand-driven refinement-based context-sensitive points-to analysis --
   *
   * <p>After running Spark, refine points-to sets on demand with context information.
   *
   * <p>When this option is set to true, Manu Sridharan's demand-driven, refinement-based points-to
   * analysis (PLDI 06) is applied after Spark was run.
   */
  public boolean cs_demand() {
    return soot.PhaseOptions.getBoolean(options, "cs-demand");
  }

  /**
   * Create lazy points-to sets --
   *
   * <p>Create lazy points-to sets that create context information only when needed..
   *
   * <p>When this option is disabled, context information is computed for every query to the
   * reachingObjects method. When it is enabled, a call to reachingObjects returns a lazy wrapper
   * object that contains a context-insensitive points-to set. This set is then automatically
   * refined with context information when necessary, i.e. when we try to determine the intersection
   * with another points-to set and this intersection seems to be non-empty.
   */
  public boolean lazy_pts() {
    return soot.PhaseOptions.getBoolean(options, "lazy-pts");
  }

  /**
   * Geometric, context-sensitive points-to analysis --
   *
   * <p>This switch enables/disables the geometric analysis..
   *
   * <p>This switch enables/disables the geometric analysis.
   */
  public boolean geom_pta() {
    return soot.PhaseOptions.getBoolean(options, "geom-pta");
  }

  /**
   * Transform to context-insensitive result --
   *
   * <p>Transform to context-insensitive result.
   *
   * <p>If you stick to working with SPARK, you can use this option to transform the context
   * sensitive result to insensitive result. After the transformation, the context sensitive
   * points-to quries cannot be answered.
   */
  public boolean geom_trans() {
    return soot.PhaseOptions.getBoolean(options, "geom-trans");
  }

  /**
   * Blocking strategy for recursive calls --
   *
   * <p>Enable blocking strategy for recursive calls.
   *
   * <p>Blocking strategy is a 1CFA model for recursive calls. This model significantly improves the
   * precision.
   */
  public boolean geom_blocking() {
    return soot.PhaseOptions.getBoolean(options, "geom-blocking");
  }

  /**
   * Pointers processed by geomPTA --
   *
   * <p>Processing pointers that impact pointers in application code only.
   *
   * <p>When this option is true, geomPTA only processes the pointers in library functions ( java.*,
   * sun.*, and etc.) that potentially impact the points-to information of pointers in application
   * code, the pointers in application code, and the base pointers at virtual callsites.
   */
  public boolean geom_app_only() {
    return soot.PhaseOptions.getBoolean(options, "geom-app-only");
  }

  /**
   * Maximal traversal --
   *
   * <p>Make the analysis traverse at most this number of nodes per query..
   *
   * <p>Make the analysis traverse at most this number of nodes per query. This quota is evenly
   * shared between multiple passes (see next option).
   */
  public int traversal() {
    return soot.PhaseOptions.getInt(options, "traversal");
  }

  /**
   * Maximal number of passes --
   *
   * <p>Perform at most this number of refinement iterations..
   *
   * <p>Perform at most this number of refinement iterations. Each iteration traverses at most (
   * traverse / passes ) nodes.
   */
  public int passes() {
    return soot.PhaseOptions.getInt(options, "passes");
  }

  /**
   * Precision evaluation methodologies --
   *
   * <p>Precision evaluation methodologies.
   *
   * <p>We internally provide some precision evaluation methodologies and classify the evaluation
   * strength into three levels. If level is 0, we do nothing. If level is 1, we report the
   * statistical information about the points-to result. If level is 2, we perform the virtual
   * callsite resolution, static cast safety and all-pairs alias evaluations.
   */
  public int geom_eval() {
    return soot.PhaseOptions.getInt(options, "geom-eval");
  }

  /**
   * Fractional parameter --
   *
   * <p>Fractional parameter for precision/performance trade-off.
   *
   * <p>This option specifies the fractional parameter, which manually balances the precision and
   * the performance. Smaller value means better performance and worse precision.
   */
  public int geom_frac_base() {
    return soot.PhaseOptions.getInt(options, "geom-frac-base");
  }

  /**
   * Iterations --
   *
   * <p>Iterations of analysis.
   *
   * <p>We can run multiple times of the geometric analysis to continuously improve the analysis
   * precision.
   */
  public int geom_runs() {
    return soot.PhaseOptions.getInt(options, "geom-runs");
  }

  /**
   * Verbose dump file --
   *
   * <p>Filename for detailed execution log.
   *
   * <p>If you want to save the geomPTA analysis information for future analysis, please provide a
   * file name.
   */
  public String geom_dump_verbose() {
    return soot.PhaseOptions.getString(options, "geom-dump-verbose");
  }

  /**
   * Verification file --
   *
   * <p>Filename for verification file.
   *
   * <p>If you want to compare the precision of the points-to results with other solvers (e.g.
   * Paddle), you can use the 'verify-file' to specify the list of methods (soot method signature
   * format) that are reachable by that solver. During the internal evaluations (see the option
   * geom-eval), we only consider the methods that are common to both solvers.
   */
  public String geom_verify_name() {
    return soot.PhaseOptions.getString(options, "geom-verify-name");
  }

  public static final int propagator_iter = 1;
  public static final int propagator_worklist = 2;
  public static final int propagator_cycle = 3;
  public static final int propagator_merge = 4;
  public static final int propagator_alias = 5;
  public static final int propagator_none = 6;
  /**
   * Propagator --
   *
   * <p>Select propagation algorithm.
   *
   * <p>This option tells Spark which propagation algorithm to use.
   */
  public int propagator() {
    String s = soot.PhaseOptions.getString(options, "propagator");

    if (s.equalsIgnoreCase("iter")) return propagator_iter;

    if (s.equalsIgnoreCase("worklist")) return propagator_worklist;

    if (s.equalsIgnoreCase("cycle")) return propagator_cycle;

    if (s.equalsIgnoreCase("merge")) return propagator_merge;

    if (s.equalsIgnoreCase("alias")) return propagator_alias;

    if (s.equalsIgnoreCase("none")) return propagator_none;

    throw new RuntimeException("Invalid value " + s + " of phase option propagator");
  }

  public static final int set_impl_hash = 1;
  public static final int set_impl_bit = 2;
  public static final int set_impl_hybrid = 3;
  public static final int set_impl_array = 4;
  public static final int set_impl_heintze = 5;
  public static final int set_impl_sharedlist = 6;
  public static final int set_impl_double = 7;
  /**
   * Set Implementation --
   *
   * <p>Select points-to set implementation.
   *
   * <p>Select an implementation of points-to sets for Spark to use.
   */
  public int set_impl() {
    String s = soot.PhaseOptions.getString(options, "set-impl");

    if (s.equalsIgnoreCase("hash")) return set_impl_hash;

    if (s.equalsIgnoreCase("bit")) return set_impl_bit;

    if (s.equalsIgnoreCase("hybrid")) return set_impl_hybrid;

    if (s.equalsIgnoreCase("array")) return set_impl_array;

    if (s.equalsIgnoreCase("heintze")) return set_impl_heintze;

    if (s.equalsIgnoreCase("sharedlist")) return set_impl_sharedlist;

    if (s.equalsIgnoreCase("double")) return set_impl_double;

    throw new RuntimeException("Invalid value " + s + " of phase option set-impl");
  }

  public static final int double_set_old_hash = 1;
  public static final int double_set_old_bit = 2;
  public static final int double_set_old_hybrid = 3;
  public static final int double_set_old_array = 4;
  public static final int double_set_old_heintze = 5;
  public static final int double_set_old_sharedlist = 6;
  /**
   * Double Set Old --
   *
   * <p>Select implementation of points-to set for old part of double set.
   *
   * <p>Select an implementation for sets of old objects in the double points-to set implementation.
   * This option has no effect unless Set Implementation is set to double.
   */
  public int double_set_old() {
    String s = soot.PhaseOptions.getString(options, "double-set-old");

    if (s.equalsIgnoreCase("hash")) return double_set_old_hash;

    if (s.equalsIgnoreCase("bit")) return double_set_old_bit;

    if (s.equalsIgnoreCase("hybrid")) return double_set_old_hybrid;

    if (s.equalsIgnoreCase("array")) return double_set_old_array;

    if (s.equalsIgnoreCase("heintze")) return double_set_old_heintze;

    if (s.equalsIgnoreCase("sharedlist")) return double_set_old_sharedlist;

    throw new RuntimeException("Invalid value " + s + " of phase option double-set-old");
  }

  public static final int double_set_new_hash = 1;
  public static final int double_set_new_bit = 2;
  public static final int double_set_new_hybrid = 3;
  public static final int double_set_new_array = 4;
  public static final int double_set_new_heintze = 5;
  public static final int double_set_new_sharedlist = 6;
  /**
   * Double Set New --
   *
   * <p>Select implementation of points-to set for new part of double set.
   *
   * <p>Select an implementation for sets of new objects in the double points-to set implementation.
   * This option has no effect unless Set Implementation is set to double.
   */
  public int double_set_new() {
    String s = soot.PhaseOptions.getString(options, "double-set-new");

    if (s.equalsIgnoreCase("hash")) return double_set_new_hash;

    if (s.equalsIgnoreCase("bit")) return double_set_new_bit;

    if (s.equalsIgnoreCase("hybrid")) return double_set_new_hybrid;

    if (s.equalsIgnoreCase("array")) return double_set_new_array;

    if (s.equalsIgnoreCase("heintze")) return double_set_new_heintze;

    if (s.equalsIgnoreCase("sharedlist")) return double_set_new_sharedlist;

    throw new RuntimeException("Invalid value " + s + " of phase option double-set-new");
  }

  public static final int geom_encoding_Geom = 1;
  public static final int geom_encoding_HeapIns = 2;
  public static final int geom_encoding_PtIns = 3;
  /**
   * Encoding methodology used --
   *
   * <p>Encoding methodology.
   *
   * <p>This switch specifies the encoding methodology used in the analysis. All possible options
   * are: Geom, HeapIns, PtIns. The efficiency order is (from slow to fast) Geom - HeapIns - PtIns,
   * but the precision order is the reverse.
   */
  public int geom_encoding() {
    String s = soot.PhaseOptions.getString(options, "geom-encoding");

    if (s.equalsIgnoreCase("Geom")) return geom_encoding_Geom;

    if (s.equalsIgnoreCase("HeapIns")) return geom_encoding_HeapIns;

    if (s.equalsIgnoreCase("PtIns")) return geom_encoding_PtIns;

    throw new RuntimeException("Invalid value " + s + " of phase option geom-encoding");
  }

  public static final int geom_worklist_PQ = 1;
  public static final int geom_worklist_FIFO = 2;
  /**
   * Worklist type --
   *
   * <p>Worklist type.
   *
   * <p>Specifies the worklist used for selecting the next propagation pointer. All possible options
   * are: PQ, FIFO. They stand for the priority queue (sorted by the last fire time and topology
   * order) and FIFO queue.
   */
  public int geom_worklist() {
    String s = soot.PhaseOptions.getString(options, "geom-worklist");

    if (s.equalsIgnoreCase("PQ")) return geom_worklist_PQ;

    if (s.equalsIgnoreCase("FIFO")) return geom_worklist_FIFO;

    throw new RuntimeException("Invalid value " + s + " of phase option geom-worklist");
  }
}