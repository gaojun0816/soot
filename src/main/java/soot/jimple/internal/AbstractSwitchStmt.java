package soot.jimple.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import soot.Unit;
import soot.UnitBox;
import soot.Value;
import soot.ValueBox;
import soot.jimple.SwitchStmt;

@SuppressWarnings("serial")
public abstract class AbstractSwitchStmt extends AbstractStmt implements SwitchStmt {

  final UnitBox defaultTargetBox;

  final ValueBox keyBox;

  final List<UnitBox> stmtBoxes;

  protected final UnitBox[] targetBoxes;

  protected AbstractSwitchStmt(ValueBox keyBox, UnitBox defaultTargetBox, UnitBox... targetBoxes) {
    this.keyBox = keyBox;
    this.defaultTargetBox = defaultTargetBox;
    this.targetBoxes = targetBoxes;

    // Build up stmtBoxes
    List<UnitBox> list = new ArrayList<>();
    stmtBoxes = Collections.unmodifiableList(list);

    Collections.addAll(list, targetBoxes);
    list.add(defaultTargetBox);
  }

  @Override
  public final Unit getDefaultTarget() {
    return defaultTargetBox.getUnit();
  }

  @Override
  public final void setDefaultTarget(Unit defaultTarget) {
    defaultTargetBox.setUnit(defaultTarget);
  }

  @Override
  public final UnitBox getDefaultTargetBox() {
    return defaultTargetBox;
  }

  @Override
  public final Value getKey() {
    return keyBox.getValue();
  }

  @Override
  public final void setKey(Value key) {
    keyBox.setValue(key);
  }

  @Override
  public final ValueBox getKeyBox() {
    return keyBox;
  }

  @Override
  public final List<ValueBox> getUseBoxes() {
    List<ValueBox> list = new ArrayList<>();

    list.addAll(keyBox.getValue().getUseBoxes());
    list.add(keyBox);

    return list;
  }

  public final int getTargetCount() {
    return targetBoxes.length;
  }

  @Override
  public final Unit getTarget(int index) {
    return targetBoxes[index].getUnit();
  }

  @Override
  public final UnitBox getTargetBox(int index) {
    return targetBoxes[index];
  }

  @Override
  public final void setTarget(int index, Unit target) {
    targetBoxes[index].setUnit(target);
  }

  @Override
  public final List<Unit> getTargets() {
    List<Unit> targets = new ArrayList<>();

    for (UnitBox element : targetBoxes) {
      targets.add(element.getUnit());
    }

    return targets;
  }

  public final void setTargets(List<? extends Unit> targets) {
    for (int i = 0; i < targets.size(); i++) {
      targetBoxes[i].setUnit(targets.get(i));
    }
  }

  public final void setTargets(Unit[] targets) {
    for (int i = 0; i < targets.length; i++) {
      targetBoxes[i].setUnit(targets[i]);
    }
  }

  @Override
  public final List<UnitBox> getUnitBoxes() {
    return stmtBoxes;
  }

  @Override
  public final boolean fallsThrough() {
    return false;
  }

  @Override
  public final boolean branches() {
    return true;
  }
}