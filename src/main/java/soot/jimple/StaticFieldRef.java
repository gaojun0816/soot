/* Soot - a J*va Optimization Framework
 * Copyright (C) 1997-1999 Raja Vallee-Rai
 * Copyright (C) 2004 Ondrej Lhotak
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

package soot.jimple;

import java.util.Collections;
import java.util.List;

import soot.SootField;
import soot.SootFieldRef;
import soot.Type;
import soot.Unit;
import soot.UnitPrinter;
import soot.ValueBox;
import soot.baf.Baf;
import soot.util.Switch;

public class StaticFieldRef implements FieldRef, ConvertToBaf {

  protected SootFieldRef fieldRef;

  protected StaticFieldRef(SootFieldRef fieldRef) {
    if (!fieldRef.isStatic()) {
      throw new RuntimeException("wrong static-ness");
    }
    this.fieldRef = fieldRef;
  }

  @Override
  public Object clone() {
    return new StaticFieldRef(fieldRef);
  }

  @Override
  public String toString() {
    return fieldRef.getSignature();
  }

  @Override
  public void toString(UnitPrinter up) {
    up.fieldRef(fieldRef);
  }

  @Override
  public SootFieldRef getFieldRef() {
    return fieldRef;
  }

  @Override
  public void setFieldRef(SootFieldRef fieldRef) {
    this.fieldRef = fieldRef;
  }

  @Override
  public SootField getField() {
    return fieldRef.resolve();
  }

  @Override
  public List<ValueBox> getUseBoxes() {
    return Collections.emptyList();
  }

  @Override
  public Type getType() {
    return fieldRef.type();
  }

  @Override
  public void apply(Switch sw) {
    ((RefSwitch) sw).caseStaticFieldRef(this);
  }

  @Override
  public boolean equivTo(Object o) {
    if (o instanceof StaticFieldRef) {
      return ((StaticFieldRef) o).getField().equals(getField());
    }

    return false;
  }

  @Override
  public int equivHashCode() {
    return getField().equivHashCode();
  }

  @Override
  public void convertToBaf(JimpleToBafContext context, List<Unit> out) {
    Unit u = Baf.v().newStaticGetInst(fieldRef);
    u.addAllTagsOf(context.getCurrentUnit());
    out.add(u);
  }
}