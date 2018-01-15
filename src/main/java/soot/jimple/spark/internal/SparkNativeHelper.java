/* Soot - a J*va Optimization Framework
 * Copyright (C) 2002 Ondrej Lhotak
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

package soot.jimple.spark.internal;

import soot.G;
import soot.RefType;
import soot.SootClass;
import soot.SootField;
import soot.SootMethod;
import soot.jimple.spark.pag.AllocNode;
import soot.jimple.spark.pag.ArrayElement;
import soot.jimple.spark.pag.FieldRefNode;
import soot.jimple.spark.pag.Node;
import soot.jimple.spark.pag.PAG;
import soot.jimple.spark.pag.VarNode;
import soot.jimple.toolkits.pointer.representations.AbstractObject;
import soot.jimple.toolkits.pointer.representations.ReferenceVariable;
import soot.jimple.toolkits.pointer.util.NativeHelper;
import soot.toolkits.scalar.Pair;

public class SparkNativeHelper extends NativeHelper {
  protected PAG pag;

  public SparkNativeHelper(PAG pag) {
    this.pag = pag;
  }

  @Override
  protected void assignImpl(ReferenceVariable lhs, ReferenceVariable rhs) {
    pag.addEdge((Node) rhs, (Node) lhs);
  }

  @Override
  protected void assignObjectToImpl(ReferenceVariable lhs, AbstractObject obj) {
    AllocNode objNode =
        pag.makeAllocNode(new Pair("AbstractObject", obj.getType()), obj.getType(), null);

    VarNode var;
    if (lhs instanceof FieldRefNode) {
      var = pag.makeGlobalVarNode(objNode, objNode.getType());
      pag.addEdge((Node) lhs, var);
    } else {
      var = (VarNode) lhs;
    }
    pag.addEdge(objNode, var);
  }

  @Override
  protected void throwExceptionImpl(AbstractObject obj) {
    AllocNode objNode =
        pag.makeAllocNode(new Pair("AbstractObject", obj.getType()), obj.getType(), null);
    pag.addEdge(objNode, pag.nodeFactory().caseThrow());
  }

  @Override
  protected ReferenceVariable arrayElementOfImpl(ReferenceVariable base) {
    VarNode l;
    if (base instanceof VarNode) {
      l = (VarNode) base;
    } else {
      FieldRefNode b = (FieldRefNode) base;
      l = pag.makeGlobalVarNode(b, b.getType());
      pag.addEdge(b, l);
    }
    return pag.makeFieldRefNode(l, ArrayElement.v());
  }

  @Override
  protected ReferenceVariable cloneObjectImpl(ReferenceVariable source) {
    return source;
  }

  @Override
  protected ReferenceVariable newInstanceOfImpl(ReferenceVariable cls) {
    return pag.nodeFactory().caseNewInstance((VarNode) cls);
  }

  @Override
  protected ReferenceVariable staticFieldImpl(String className, String fieldName) {
    SootClass c = RefType.v(className).getSootClass();
    SootField f = c.getFieldByName(fieldName);
    return pag.makeGlobalVarNode(f, f.getType());
  }

  @Override
  protected ReferenceVariable tempFieldImpl(String fieldsig) {
    return pag.makeGlobalVarNode(new Pair("tempField", fieldsig), RefType.v("java.lang.Object"));
  }

  @Override
  protected ReferenceVariable tempVariableImpl() {
    return pag.makeGlobalVarNode(
        new Pair("TempVar", new Integer(++G.v().SparkNativeHelper_tempVar)),
        RefType.v("java.lang.Object"));
  }

  @Override
  protected ReferenceVariable tempLocalVariableImpl(SootMethod method) {
    return pag.makeLocalVarNode(
        new Pair("TempVar", new Integer(++G.v().SparkNativeHelper_tempVar)),
        RefType.v("java.lang.Object"),
        method);
  }
}