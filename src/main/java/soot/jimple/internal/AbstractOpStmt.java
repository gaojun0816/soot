package soot.jimple.internal;

import java.util.ArrayList;
import java.util.List;

import soot.Value;
import soot.ValueBox;

@SuppressWarnings("serial")
public abstract class AbstractOpStmt extends AbstractStmt {

  final ValueBox opBox;

  protected AbstractOpStmt(ValueBox opBox) {
    this.opBox = opBox;
  }

  public final Value getOp() {
    return opBox.getValue();
  }

  public final void setOp(Value op) {
    opBox.setValue(op);
  }

  public final ValueBox getOpBox() {
    return opBox;
  }

  @Override
  public final List<ValueBox> getUseBoxes() {
    List<ValueBox> list = new ArrayList<>();

    list.addAll(opBox.getValue().getUseBoxes());
    list.add(opBox);

    return list;
  }
}