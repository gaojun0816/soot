/* Soot - a J*va Optimization Framework
 * Copyright (C) 2000 Feng Qian
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

package soot.jimple.toolkits.annotation.arraycheck;

import soot.Local;
import soot.SootMethod;

class MethodLocal {
  private final SootMethod m;
  private final Local l;

  public MethodLocal(SootMethod method, Local local) {
    m = method;
    l = local;
  }

  public SootMethod getMethod() {
    return m;
  }

  public Local getLocal() {
    return l;
  }

  @Override
  public int hashCode() {
    return m.hashCode() + l.hashCode();
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof MethodLocal) {
      MethodLocal another = (MethodLocal) other;
      return m.equals(another.getMethod()) && l.equals(another.getLocal());
    }

    return false;
  }

  @Override
  public String toString() {
    return "[" + m.getSignature() + " : " + l.toString() + "]";
  }
}