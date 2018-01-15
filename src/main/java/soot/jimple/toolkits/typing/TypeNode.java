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

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import soot.ArrayType;
import soot.G;
import soot.NullType;
import soot.PrimType;
import soot.RefType;
import soot.SootClass;
import soot.Type;
import soot.options.Options;
import soot.util.BitVector;

/** Each instance of this class represents one type in the class hierarchy (or basic types). */
class TypeNode {
  private static final boolean DEBUG = false;

  private final int id;
  private final Type type;
  private final ClassHierarchy hierarchy;

  private TypeNode parentClass;
  private TypeNode element;
  private TypeNode array;

  private List<TypeNode> parents = Collections.emptyList();
  private final BitVector ancestors = new BitVector(0);
  private final BitVector descendants = new BitVector(0);

  public TypeNode(int id, Type type, ClassHierarchy hierarchy) {
    if (type == null || hierarchy == null) {
      throw new InternalTypingException();
    }

    if (!((type instanceof PrimType)
        || (type instanceof RefType)
        || (type instanceof ArrayType)
        || (type instanceof NullType))) {
      G.v().out.println("Unhandled type: " + type);
      throw new InternalTypingException();
    }

    this.id = id;
    this.type = type;
    this.hierarchy = hierarchy;

    if (DEBUG) {
      G.v().out.println("creating node " + this);
    }
  }

  public TypeNode(int id, RefType type, ClassHierarchy hierarchy) {
    this(id, (Type) type, hierarchy);

    {
      SootClass sClass = type.getSootClass();
      if (sClass == null) {
        throw new RuntimeException("Oops, forgot to load " + type);
      }
      if (sClass.isPhantomClass()) {
        throw new RuntimeException(
            "Jimplification requires " + sClass + ", but it is a phantom ref.");
      }
      List<TypeNode> plist = new LinkedList<>();

      if (sClass.hasSuperclass() && !sClass.getName().equals("java.lang.Object")) {
        TypeNode parent = hierarchy.typeNode(RefType.v(sClass.getSuperclass().getName()));
        plist.add(parent);
        parentClass = parent;
      }

      for (SootClass sootClass : sClass.getInterfaces()) {
        TypeNode parent = hierarchy.typeNode(RefType.v((sootClass).getName()));
        plist.add(parent);
      }

      parents = Collections.unmodifiableList(plist);
    }

    descendants.set(hierarchy.NULL.id);
    hierarchy.NULL.ancestors.set(id);

    for (TypeNode parent : parents) {

      ancestors.set(parent.id);
      ancestors.or(parent.ancestors);
      parent.fixDescendants(id);
    }
  }

  public TypeNode(int id, ArrayType type, ClassHierarchy hierarchy) {
    this(id, (Type) type, hierarchy);

    if (type.numDimensions < 1) {
      throw new InternalTypingException();
    }

    if (type.numDimensions == 1) {
      element = hierarchy.typeNode(type.baseType);
    } else {
      element = hierarchy.typeNode(ArrayType.v(type.baseType, type.numDimensions - 1));
    }

    if (element != hierarchy.INT) {
      if (element.array != null) {
        throw new InternalTypingException();
      }

      element.array = this;
    }

    {
      List<TypeNode> plist = new LinkedList<>();
      if (type.baseType instanceof RefType) {
        RefType baseType = (RefType) type.baseType;
        SootClass sClass = baseType.getSootClass();
        if (sClass.hasSuperclass() && !sClass.getName().equals("java.lang.Object")) {
          TypeNode parent =
              hierarchy.typeNode(
                  ArrayType.v(RefType.v(sClass.getSuperclass().getName()), type.numDimensions));
          plist.add(parent);
          parentClass = parent;
        } else if (type.numDimensions == 1) {
          plist.add(hierarchy.OBJECT);

          // hack for J2ME library, reported by Stephen Cheng
          if (!Options.v().j2me()) {
            plist.add(hierarchy.CLONEABLE);
            plist.add(hierarchy.SERIALIZABLE);
          }

          parentClass = hierarchy.OBJECT;
        } else {
          plist.add(
              hierarchy.typeNode(ArrayType.v(hierarchy.OBJECT.type(), type.numDimensions - 1)));

          // hack for J2ME library, reported by Stephen Cheng
          if (!Options.v().j2me()) {
            plist.add(
                hierarchy.typeNode(
                    ArrayType.v(hierarchy.CLONEABLE.type(), type.numDimensions - 1)));
            plist.add(
                hierarchy.typeNode(
                    ArrayType.v(hierarchy.SERIALIZABLE.type(), type.numDimensions - 1)));
          }

          parentClass =
              hierarchy.typeNode(ArrayType.v(hierarchy.OBJECT.type(), type.numDimensions - 1));
        }

        for (SootClass sootClass : sClass.getInterfaces()) {
          TypeNode parent =
              hierarchy.typeNode(ArrayType.v(RefType.v((sootClass).getName()), type.numDimensions));
          plist.add(parent);
        }
      } else if (type.numDimensions == 1) {
        plist.add(hierarchy.OBJECT);

        // hack for J2ME library, reported by Stephen Cheng
        if (!Options.v().j2me()) {
          plist.add(hierarchy.CLONEABLE);
          plist.add(hierarchy.SERIALIZABLE);
        }

        parentClass = hierarchy.OBJECT;
      } else {
        plist.add(hierarchy.typeNode(ArrayType.v(hierarchy.OBJECT.type(), type.numDimensions - 1)));
        // hack for J2ME library, reported by Stephen Cheng
        if (!Options.v().j2me()) {
          plist.add(
              hierarchy.typeNode(ArrayType.v(hierarchy.CLONEABLE.type(), type.numDimensions - 1)));
          plist.add(
              hierarchy.typeNode(
                  ArrayType.v(hierarchy.SERIALIZABLE.type(), type.numDimensions - 1)));
        }

        parentClass =
            hierarchy.typeNode(ArrayType.v(hierarchy.OBJECT.type(), type.numDimensions - 1));
      }

      parents = Collections.unmodifiableList(plist);
    }

    descendants.set(hierarchy.NULL.id);
    hierarchy.NULL.ancestors.set(id);

    for (TypeNode parent : parents) {

      ancestors.set(parent.id);
      ancestors.or(parent.ancestors);
      parent.fixDescendants(id);
    }
  }

  /** Adds the given node to the list of descendants of this node and its ancestors. * */
  private void fixDescendants(int id) {
    if (descendants.get(id)) {
      return;
    }

    for (TypeNode parent : parents) {

      parent.fixDescendants(id);
    }

    descendants.set(id);
  }

  /** Returns the unique id of this type node. * */
  public int id() {
    return id;
  }

  /** Returns the type represented by this type node. * */
  public Type type() {
    return type;
  }

  public boolean hasAncestor(TypeNode typeNode) {
    return ancestors.get(typeNode.id);
  }

  public boolean hasAncestorOrSelf(TypeNode typeNode) {
    if (typeNode == this) {
      return true;
    }

    return ancestors.get(typeNode.id);
  }

  public boolean hasDescendant(TypeNode typeNode) {
    return descendants.get(typeNode.id);
  }

  public boolean hasDescendantOrSelf(TypeNode typeNode) {
    if (typeNode == this) {
      return true;
    }

    return descendants.get(typeNode.id);
  }

  public List<TypeNode> parents() {
    return parents;
  }

  public TypeNode parentClass() {
    return parentClass;
  }

  @Override
  public String toString() {
    return type.toString() + "(" + id + ")";
  }

  public TypeNode lca(TypeNode type) throws TypeException {
    if (type == null) {
      throw new InternalTypingException();
    }

    if (type == this) {
      return this;
    }

    if (hasAncestor(type)) {
      return type;
    }

    if (hasDescendant(type)) {
      return this;
    }

    do {
      type = type.parentClass;

      if (type == null) {
        try {
          TypeVariable.error("Type Error(12)");
        } catch (TypeException e) {
          if (DEBUG) {
            e.printStackTrace();
          }
          throw e;
        }
      }
    } while (!hasAncestor(type));

    return type;
  }

  public TypeNode lcaIfUnique(TypeNode type) throws TypeException {
    TypeNode initial = type;

    if (type == null) {
      throw new InternalTypingException();
    }

    if (type == this) {
      return this;
    }

    if (hasAncestor(type)) {
      return type;
    }

    if (hasDescendant(type)) {
      return this;
    }

    do {
      if (type.parents.size() == 1) {
        type = type.parents.get(0);
      } else {
        if (DEBUG) {
          G.v().out.println("lca " + initial + " (" + type + ") & " + this + " =");
          for (TypeNode typeNode : type.parents) {
            G.v().out.println("  " + typeNode);
          }
        }
        return null;
      }
    } while (!hasAncestor(type));

    return type;
  }

  public boolean hasElement() {
    return element != null;
  }

  public TypeNode element() {
    if (element == null) {
      throw new InternalTypingException();
    }

    return element;
  }

  public TypeNode array() {
    if (array != null) {
      return array;
    }

    if (type instanceof ArrayType) {
      ArrayType atype = (ArrayType) type;
      array = hierarchy.typeNode(ArrayType.v(atype.baseType, atype.numDimensions + 1));
      return array;
    }

    if (type instanceof PrimType || type instanceof RefType) {
      array = hierarchy.typeNode(ArrayType.v(type, 1));
      return array;
    }

    throw new InternalTypingException();
  }

  public boolean isNull() {
    return type instanceof NullType;
  }

  public boolean isClass() {
    return type instanceof ArrayType
        || type instanceof NullType
        || (type instanceof RefType && !((RefType) type).getSootClass().isInterface());
  }

  public boolean isClassOrInterface() {
    return type instanceof ArrayType || type instanceof NullType || type instanceof RefType;
  }

  public boolean isArray() {
    return type instanceof ArrayType || type instanceof NullType;
  }
}