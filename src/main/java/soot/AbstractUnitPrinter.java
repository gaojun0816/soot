/* Soot - a J*va Optimization Framework
 * Copyright (C) 2003, 2004 Ondrej Lhotak
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

package soot;

import java.util.HashSet;

import soot.jimple.Constant;
import soot.jimple.IdentityRef;
import soot.jimple.Jimple;

/** Partial default UnitPrinter implementation. */
public abstract class AbstractUnitPrinter implements UnitPrinter {
  @Override
  public void setPositionTagger(AttributesUnitPrinter pt) {
    this.pt = pt;
    pt.setUnitPrinter(this);
  }

  @Override
  public AttributesUnitPrinter getPositionTagger() {
    return pt;
  }

  @Override
  public void startUnit(Unit u) {
    handleIndent();
    if (pt != null) {
      pt.startUnit(u);
    }
  }

  @Override
  public void endUnit(Unit u) {
    if (pt != null) {
      pt.endUnit(u);
    }
  }

  @Override
  public void startUnitBox(UnitBox ub) {
    handleIndent();
  }

  @Override
  public void endUnitBox(UnitBox ub) {}

  @Override
  public void startValueBox(ValueBox vb) {
    handleIndent();
    if (pt != null) {
      pt.startValueBox(vb);
    }
  }

  @Override
  public void endValueBox(ValueBox vb) {
    if (pt != null) {
      pt.endValueBox(vb);
    }
  }

  @Override
  public void noIndent() {
    startOfLine = false;
  }

  @Override
  public void incIndent() {
    indent = indent + "    ";
  }

  @Override
  public void decIndent() {
    if (indent.length() >= 4) {
      indent = indent.substring(4);
    }
  }

  @Override
  public void setIndent(String indent) {
    this.indent = indent;
  }

  @Override
  public String getIndent() {
    return indent;
  }

  @Override
  public abstract void literal(String s);

  @Override
  public abstract void type(Type t);

  @Override
  public abstract void methodRef(SootMethodRef m);

  @Override
  public abstract void fieldRef(SootFieldRef f);

  @Override
  public abstract void identityRef(IdentityRef r);

  @Override
  public abstract void unitRef(Unit u, boolean branchTarget);

  @Override
  public void newline() {
    output.append("\n");
    startOfLine = true;
    if (pt != null) {
      pt.newline();
    }
  }

  @Override
  public void local(Local l) {
    handleIndent();
    if (quotableLocals == null) {
      initializeQuotableLocals();
    }
    if (quotableLocals.contains(l.getName())) {
      output.append("'" + l.getName() + "'");
    } else {
      output.append(l.getName());
    }
  }

  @Override
  public void constant(Constant c) {
    handleIndent();
    output.append(c.toString());
  }

  @Override
  public String toString() {
    String ret = output.toString();
    output = new StringBuffer();
    return ret;
  }

  @Override
  public StringBuffer output() {
    return output;
  }

  protected void handleIndent() {
    if (startOfLine) {
      output.append(indent);
    }
    startOfLine = false;
  }

  protected void initializeQuotableLocals() {
    quotableLocals = new HashSet<>();
    quotableLocals.addAll(Jimple.jimpleKeywordList());
  }

  protected boolean startOfLine = true;
  protected String indent = "        ";
  protected StringBuffer output = new StringBuffer();
  protected AttributesUnitPrinter pt;
  protected HashSet<String> quotableLocals;
}