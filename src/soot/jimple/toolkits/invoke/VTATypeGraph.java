/* Soot - a J*va Optimization Framework
 * Copyright (C) 1999 Patrick Lam
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,
 * Boston, MA 02111-1307, USA.
 */

/*
 * Modified by the Sable Research Group and others 1997-1999.  
 * See the 'credits' file distributed with Soot for the complete list of
 * contributors.  (Soot is distributed at http://www.sable.mcgill.ca/soot)
 */

package soot.jimple.toolkits.invoke;

import java.util.*;
import soot.util.*;
import soot.*;
import soot.jimple.*;
import soot.toolkits.graph.*;

public class VTATypeGraph extends HashMutableDirectedGraph implements TypeGraph
{
    int state;
    Scene sc;

    private Type getBaseType(Type t)
    {
        if (t instanceof ArrayType)
            return ((ArrayType)t).baseType;
        else
            return t;
    }

    public VTATypeGraph(InvokeGraph ig)
    {
        this.sc = Scene.v();
        state = sc.getState();

        // Construct nodes of graph.
        {
            Chain allClasses = sc.getClasses();

            Iterator classesIt = allClasses.iterator();
            while (classesIt.hasNext())
            {
                SootClass c = (SootClass)classesIt.next();

                if (c.isInterface())
                    continue;

                // Add fields of c.
                Iterator fieldsIt = c.getFields().iterator();

                while (fieldsIt.hasNext())
                {
                    SootField f = (SootField)fieldsIt.next();

                    if (getBaseType(f.getType()) instanceof RefType)
                        addNode(f.getSignature());
                }

                // Add nodes for method contents.
                Iterator methodsIt = c.getMethods().iterator();

                while (methodsIt.hasNext())
                {
                    SootMethod m = (SootMethod)methodsIt.next();

                    if (!m.isConcrete())
                        continue;

                    String methodSig = m.getSignature();

                    // Add return node, if appropriate.
                    if (!m.isStatic())
                        addNode(methodSig+"$this");
                    if (getBaseType(m.getReturnType()) instanceof RefType)
                        addNode(methodSig + "$return");

                    // Add the parameters.
                    Iterator paramIt = m.getParameterTypes().iterator();
                    int paramCount = 0;
                    while (paramIt.hasNext())
                    {
                        Type t = (Type)paramIt.next();
                        if (getBaseType(t) instanceof RefType)
                            addNode(methodSig + "$p"+paramCount);
                        paramCount++;
                    }

                    // Get the body & add the locals.
                    Body b = m.getActiveBody();

                    Iterator localIt = b.getLocals().iterator();
                    while (paramIt.hasNext())
                    {
                        Local l = (Local)localIt.next();
                        Type t = l.getType();
                        if (getBaseType(t) instanceof RefType)
                            addNode(methodSig + "$$" + l.getName());
                    }
                }
            }
        }

        // Add edges.
        {
            Chain allClasses = sc.getClasses();
            HashMap methodToReturnStmts = new HashMap(allClasses.size() * 8 + 1, 0.7f);

            Iterator classesIt = allClasses.iterator();
            while (classesIt.hasNext())
            {
                SootClass c = (SootClass)classesIt.next();

                if (c.isInterface())
                    continue;

                // Add edges for method contents.
                Iterator methodsIt = c.getMethods().iterator();

                while (methodsIt.hasNext())
                {
                    SootMethod m = (SootMethod)methodsIt.next();
                    String methodSig = m.getSignature();
                    JimpleBody b = (JimpleBody)m.getActiveBody();
                    Type retType = getBaseType(m.getReturnType());

                    // Look for assignStmts and method calls.
                    Iterator unitsIt = b.getUnits().iterator();

                    while (unitsIt.hasNext())
                    {
                        Stmt s = (Stmt) unitsIt.next();

                        // Add an edge for method invocations.
                        if (s.containsInvokeExpr())
                        {
                            
                        }

                        // Add edges corresponding to identityStmts.
                        if (s instanceof IdentityStmt)
                        {
                            IdentityStmt is = (IdentityStmt)s;
                            Value lhs = is.getLeftOp(), rhs = is.getRightOp();
                            if (rhs instanceof ThisRef)
                                addEdge(methodSig+"$this", getVTALabel(m, lhs));
                            else if (rhs instanceof ParameterRef)
                                addEdge(methodSig+"$p"+((ParameterRef)rhs).getIndex(), getVTALabel(m, lhs));
                            else
                                /* ignore caughtexceptionrefs */;
                        }

                        // Add edges corresponding to assignStmts.
                        if (s instanceof AssignStmt)
                        {
                            AssignStmt as = (AssignStmt)s;
                            Value lhs = as.getLeftOp(), rhs = as.getRightOp();

                            if (rhs instanceof CastExpr)
                                rhs = ((CastExpr)rhs).getOp();

                            String lhsRep = getVTALabel(m, lhs), 
                                rhsRep = getVTALabel(m, rhs);

                            // check that we have the right form.
                            if (lhsRep == null || rhsRep == null)
                                continue;

                            addEdge(rhsRep, lhsRep);
                        }
                    }
                }
            }
        }
    }

    private String getVTALabel(SootMethod m, Value v)
    {
        // In Jimple, ArrayRef must have a Local base, so this is fine.
        if (v instanceof ArrayRef)
            return m.getSignature()+"$$"+((Local)((ArrayRef)v).getBase()).getName();
        else if (v instanceof Local)
            return m.getSignature()+"$$"+((Local)v).getName();
        else if (v instanceof FieldRef)
            return ((FieldRef)v).getField().getSignature();
        
        return null;
    }

    private void checkState()
    {
        if (state != sc.getState())
            throw new ConcurrentModificationException("Scene changed for VTATypeGraph!");
    }

}
