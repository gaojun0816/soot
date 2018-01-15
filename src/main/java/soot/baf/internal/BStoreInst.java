/* Soot - a J*va Optimization Framework
 * Copyright (C) 1999 Patrick Lam, Patrick Pominville and Raja Vallee-Rai
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

package soot.baf.internal;

import java.util.Collections;
import java.util.List;

import soot.AbstractJasminClass;
import soot.Local;
import soot.Type;
import soot.UnitPrinter;
import soot.ValueBox;
import soot.baf.InstSwitch;
import soot.baf.StoreInst;
import soot.util.Switch;

public class BStoreInst extends AbstractOpTypeInst implements StoreInst {

  ValueBox localBox;
  List defBoxes;

  public BStoreInst(Type opType, Local local) {
    super(opType);
    localBox = new BafLocalBox(local);
    defBoxes = Collections.singletonList(localBox);
  }

  @Override
  public int getInCount() {
    return 1;
  }

  @Override
  public Object clone() {
    return new BStoreInst(getOpType(), getLocal());
  }

  @Override
  public int getInMachineCount() {
    return AbstractJasminClass.sizeOfType(getOpType());
  }

  @Override
  public int getOutCount() {
    return 0;
  }

  @Override
  public int getOutMachineCount() {
    return 0;
  }

  @Override
  public final String getName() {
    return "store";
  }

  @Override
  final String getParameters() {
    return " " + localBox.getValue().toString();
  }

  @Override
  protected void getParameters(UnitPrinter up) {
    up.literal(" ");
    localBox.toString(up);
  }

  @Override
  public void apply(Switch sw) {
    ((InstSwitch) sw).caseStoreInst(this);
  }

  @Override
  public void setLocal(Local l) {
    localBox.setValue(l);
  }

  @Override
  public Local getLocal() {
    return (Local) localBox.getValue();
  }

  @Override
  public List getDefBoxes() {
    return defBoxes;
  }
}