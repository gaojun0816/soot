/* Soot - a J*va Optimization Framework
 * Copyright (C) 2004 Navindra Umanee <navindra@cs.mcgill.ca>
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

package soot.shimple.internal;

import java.util.Collections;
import java.util.List;

import soot.Type;
import soot.Unit;
import soot.UnitBox;
import soot.UnitPrinter;
import soot.Value;
import soot.shimple.PiExpr;
import soot.shimple.Shimple;
import soot.toolkits.scalar.ValueUnitPair;
import soot.util.Switch;

/** @author Navindra Umanee */
public class SPiExpr implements PiExpr {
  protected ValueUnitPair argBox;
  protected Object targetKey;

  public SPiExpr(Value v, Unit u, Object o) {
    argBox = new SValueUnitPair(v, u);
    this.targetKey = o;
  }

  @Override
  public ValueUnitPair getArgBox() {
    return argBox;
  }

  @Override
  public Value getValue() {
    return argBox.getValue();
  }

  @Override
  public Unit getCondStmt() {
    return argBox.getUnit();
  }

  @Override
  public Object getTargetKey() {
    return targetKey;
  }

  @Override
  public void setValue(Value value) {
    argBox.setValue(value);
  }

  @Override
  public void setCondStmt(Unit pred) {
    argBox.setUnit(pred);
  }

  @Override
  public void setTargetKey(Object targetKey) {
    this.targetKey = targetKey;
  }

  @Override
  public List<UnitBox> getUnitBoxes() {
    return Collections.singletonList(argBox);
  }

  @Override
  public void clearUnitBoxes() {
    System.out.println("clear unit boxes");
    argBox.setUnit(null);
  }

  @Override
  public boolean equivTo(Object o) {
    if (!(o instanceof SPiExpr)) {
      return false;
    }

    return getArgBox().equivTo(((SPiExpr) o).getArgBox());
  }

  @Override
  public int equivHashCode() {
    return getArgBox().equivHashCode() * 17;
  }

  @Override
  public void apply(Switch sw) {
    // *** FIXME:
    throw new RuntimeException("Not Yet Implemented.");
  }

  @Override
  public Object clone() {
    return new SPiExpr(getValue(), getCondStmt(), getTargetKey());
  }

  @Override
  public String toString() {
    String s = Shimple.PI + "(" + getValue() + ")";
    return s;
  }

  @Override
  public void toString(UnitPrinter up) {
    up.literal(Shimple.PI);
    up.literal("(");
    argBox.toString(up);
    up.literal(" [");
    up.literal(targetKey.toString());
    up.literal("])");
  }

  @Override
  public Type getType() {
    return getValue().getType();
  }

  @Override
  public List getUseBoxes() {
    return Collections.singletonList(argBox);
  }
}