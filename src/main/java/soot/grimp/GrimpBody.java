/* Soot - a J*va Optimization Framework
 * Copyright (C) 1999 Patrick Lam
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

package soot.grimp;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import soot.Body;
import soot.G;
import soot.Local;
import soot.PackManager;
import soot.SootMethod;
import soot.Trap;
import soot.Unit;
import soot.ValueBox;
import soot.jimple.AbstractStmtSwitch;
import soot.jimple.AssignStmt;
import soot.jimple.BreakpointStmt;
import soot.jimple.EnterMonitorStmt;
import soot.jimple.ExitMonitorStmt;
import soot.jimple.GotoStmt;
import soot.jimple.IdentityStmt;
import soot.jimple.IfStmt;
import soot.jimple.InvokeStmt;
import soot.jimple.JimpleBody;
import soot.jimple.LookupSwitchStmt;
import soot.jimple.NopStmt;
import soot.jimple.ReturnStmt;
import soot.jimple.ReturnVoidStmt;
import soot.jimple.Stmt;
import soot.jimple.StmtBody;
import soot.jimple.TableSwitchStmt;
import soot.jimple.ThrowStmt;
import soot.jimple.internal.StmtBox;
import soot.options.Options;

/** Implementation of the Body class for the Grimp IR. */
public class GrimpBody extends StmtBody {
  /** Construct an empty GrimpBody */
  GrimpBody(SootMethod m) {
    super(m);
  }

  @Override
  public Object clone() {
    Body b = Grimp.v().newBody(getMethod());
    b.importBodyContentsFrom(this);
    return b;
  }

  /** Constructs a GrimpBody from the given Body. */
  GrimpBody(Body body) {
    super(body.getMethod());

    if (Options.v().verbose()) {
      G.v().out.println("[" + getMethod().getName() + "] Constructing GrimpBody...");
    }

    JimpleBody jBody = null;

    if (body instanceof JimpleBody) {
      jBody = (JimpleBody) body;
    } else {
      throw new RuntimeException("Can only construct GrimpBody's from JimpleBody's (for now)");
    }

    Iterator<Local> localIt = jBody.getLocals().iterator();
    while (localIt.hasNext()) {
      getLocals().add(((localIt.next())));
      //            getLocals().add(((Local)(it.next())).clone());
    }

    Iterator<Unit> it = jBody.getUnits().iterator();

    final HashMap<Stmt, Stmt> oldToNew = new HashMap<>(getUnits().size() * 2 + 1, 0.7f);
    List<Unit> updates = new LinkedList<>();

    /* we should Grimpify the Stmt's here... */
    while (it.hasNext()) {
      Stmt oldStmt = (Stmt) (it.next());
      final StmtBox newStmtBox = (StmtBox) Grimp.v().newStmtBox(null);
      final StmtBox updateStmtBox = (StmtBox) Grimp.v().newStmtBox(null);

      /* we can't have a general StmtSwapper on Grimp.v() */
      /* because we need to collect a list of updates */
      oldStmt.apply(
          new AbstractStmtSwitch() {
            @Override
            public void caseAssignStmt(AssignStmt s) {
              newStmtBox.setUnit(Grimp.v().newAssignStmt(s));
            }

            @Override
            public void caseIdentityStmt(IdentityStmt s) {
              newStmtBox.setUnit(Grimp.v().newIdentityStmt(s));
            }

            @Override
            public void caseBreakpointStmt(BreakpointStmt s) {
              newStmtBox.setUnit(Grimp.v().newBreakpointStmt(s));
            }

            @Override
            public void caseInvokeStmt(InvokeStmt s) {
              newStmtBox.setUnit(Grimp.v().newInvokeStmt(s));
            }

            @Override
            public void caseEnterMonitorStmt(EnterMonitorStmt s) {
              newStmtBox.setUnit(Grimp.v().newEnterMonitorStmt(s));
            }

            @Override
            public void caseExitMonitorStmt(ExitMonitorStmt s) {
              newStmtBox.setUnit(Grimp.v().newExitMonitorStmt(s));
            }

            @Override
            public void caseGotoStmt(GotoStmt s) {
              newStmtBox.setUnit(Grimp.v().newGotoStmt(s));
              updateStmtBox.setUnit(s);
            }

            @Override
            public void caseIfStmt(IfStmt s) {
              newStmtBox.setUnit(Grimp.v().newIfStmt(s));
              updateStmtBox.setUnit(s);
            }

            @Override
            public void caseLookupSwitchStmt(LookupSwitchStmt s) {
              newStmtBox.setUnit(Grimp.v().newLookupSwitchStmt(s));
              updateStmtBox.setUnit(s);
            }

            @Override
            public void caseNopStmt(NopStmt s) {
              newStmtBox.setUnit(Grimp.v().newNopStmt(s));
            }

            @Override
            public void caseReturnStmt(ReturnStmt s) {
              newStmtBox.setUnit(Grimp.v().newReturnStmt(s));
            }

            @Override
            public void caseReturnVoidStmt(ReturnVoidStmt s) {
              newStmtBox.setUnit(Grimp.v().newReturnVoidStmt(s));
            }

            @Override
            public void caseTableSwitchStmt(TableSwitchStmt s) {
              newStmtBox.setUnit(Grimp.v().newTableSwitchStmt(s));
              updateStmtBox.setUnit(s);
            }

            @Override
            public void caseThrowStmt(ThrowStmt s) {
              newStmtBox.setUnit(Grimp.v().newThrowStmt(s));
            }
          });

      /* map old Expr's to new Expr's. */
      Stmt newStmt = (Stmt) (newStmtBox.getUnit());
      Iterator<ValueBox> useBoxesIt;
      useBoxesIt = newStmt.getUseBoxes().iterator();
      while (useBoxesIt.hasNext()) {
        ValueBox b = (useBoxesIt.next());
        b.setValue(Grimp.v().newExpr(b.getValue()));
      }
      useBoxesIt = newStmt.getDefBoxes().iterator();
      while (useBoxesIt.hasNext()) {
        ValueBox b = (useBoxesIt.next());
        b.setValue(Grimp.v().newExpr(b.getValue()));
      }

      getUnits().add(newStmt);
      oldToNew.put(oldStmt, newStmt);
      if (updateStmtBox.getUnit() != null) {
        updates.add(updateStmtBox.getUnit());
      }
      if (oldStmt.hasTag("LineNumberTag")) {
        newStmt.addTag(oldStmt.getTag("LineNumberTag"));
      }
      if (oldStmt.hasTag("SourceLnPosTag")) {
        newStmt.addTag(oldStmt.getTag("SourceLnPosTag"));
      }
    }

    /* fixup stmt's which have had moved targets */
    it = updates.iterator();
    while (it.hasNext()) {
      Stmt stmt = (Stmt) (it.next());

      stmt.apply(
          new AbstractStmtSwitch() {
            @Override
            public void caseGotoStmt(GotoStmt s) {
              GotoStmt newStmt = (GotoStmt) (oldToNew.get(s));
              newStmt.setTarget(oldToNew.get(newStmt.getTarget()));
            }

            @Override
            public void caseIfStmt(IfStmt s) {
              IfStmt newStmt = (IfStmt) (oldToNew.get(s));
              newStmt.setTarget(oldToNew.get(newStmt.getTarget()));
            }

            @Override
            public void caseLookupSwitchStmt(LookupSwitchStmt s) {
              LookupSwitchStmt newStmt = (LookupSwitchStmt) (oldToNew.get(s));
              newStmt.setDefaultTarget((oldToNew.get(newStmt.getDefaultTarget())));
              Unit[] newTargList = new Unit[newStmt.getTargetCount()];
              for (int i = 0; i < newStmt.getTargetCount(); i++) {
                newTargList[i] = (oldToNew.get(newStmt.getTarget(i)));
              }
              newStmt.setTargets(newTargList);
            }

            @Override
            public void caseTableSwitchStmt(TableSwitchStmt s) {
              TableSwitchStmt newStmt = (TableSwitchStmt) (oldToNew.get(s));
              newStmt.setDefaultTarget((oldToNew.get(newStmt.getDefaultTarget())));
              int tc = newStmt.getHighIndex() - newStmt.getLowIndex() + 1;
              LinkedList<Unit> newTargList = new LinkedList<>();
              for (int i = 0; i < tc; i++) {
                newTargList.add(oldToNew.get(newStmt.getTarget(i)));
              }
              newStmt.setTargets(newTargList);
            }
          });
    }

    Iterator<Trap> trapIt = jBody.getTraps().iterator();
    while (trapIt.hasNext()) {
      Trap oldTrap = trapIt.next();
      getTraps()
          .add(
              Grimp.v()
                  .newTrap(
                      oldTrap.getException(),
                      (oldToNew.get(oldTrap.getBeginUnit())),
                      (oldToNew.get(oldTrap.getEndUnit())),
                      (oldToNew.get(oldTrap.getHandlerUnit()))));
    }

    PackManager.v().getPack("gb").apply(this);
  }
}