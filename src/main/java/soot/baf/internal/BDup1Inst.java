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

import java.util.ArrayList;
import java.util.List;

import soot.Type;
import soot.UnitPrinter;
import soot.baf.Baf;
import soot.baf.Dup1Inst;
import soot.baf.InstSwitch;
import soot.util.Switch;

public class BDup1Inst extends BDupInst implements Dup1Inst {

  private final Type mOpType;

  public BDup1Inst(Type aOpType) {
    mOpType = Baf.getDescriptorTypeOf(aOpType);
  }

  @Override
  public Type getOp1Type() {
    return mOpType;
  }

  @Override
  public List<Type> getOpTypes() {
    List<Type> res = new ArrayList<>();
    res.add(mOpType);
    return res;
  }

  @Override
  public List<Type> getUnderTypes() {
    return new ArrayList<>();
  }

  @Override
  public final String getName() {
    return "dup1";
  }

  @Override
  public void apply(Switch sw) {
    ((InstSwitch) sw).caseDup1Inst(this);
  }

  @Override
  public String toString() {
    return "dup1." + Baf.bafDescriptorOf(mOpType);
  }

  @Override
  public void toString(UnitPrinter up) {
    up.literal("dup1");
    up.literal(".");
    up.literal(Baf.bafDescriptorOf(mOpType));
  }
}