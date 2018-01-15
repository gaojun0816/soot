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
import soot.baf.Baf;
import soot.baf.Dup2Inst;
import soot.baf.InstSwitch;
import soot.util.Switch;

public class BDup2Inst extends BDupInst implements Dup2Inst {

  private final Type mOp1Type;
  private final Type mOp2Type;

  public BDup2Inst(Type aOp1Type, Type aOp2Type) {
    mOp1Type = Baf.getDescriptorTypeOf(aOp1Type);
    mOp2Type = Baf.getDescriptorTypeOf(aOp2Type);
  }

  @Override
  public Type getOp1Type() {
    return mOp1Type;
  }

  @Override
  public Type getOp2Type() {
    return mOp2Type;
  }

  @Override
  public List<Type> getOpTypes() {
    List<Type> res = new ArrayList<>();
    res.add(mOp1Type);
    res.add(mOp2Type);
    return res;
  }

  @Override
  public List<Type> getUnderTypes() {
    return new ArrayList<>();
  }

  @Override
  public final String getName() {
    return "dup2";
  }

  @Override
  public void apply(Switch sw) {
    ((InstSwitch) sw).caseDup2Inst(this);
  }

  @Override
  public String toString() {
    return "dup2." + Baf.bafDescriptorOf(mOp1Type) + Baf.bafDescriptorOf(mOp2Type);
  }
}