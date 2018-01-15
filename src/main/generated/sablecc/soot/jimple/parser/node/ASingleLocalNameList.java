/* This file was generated by SableCC (http://www.sablecc.org/). */

package soot.jimple.parser.node;

import soot.jimple.parser.analysis.Analysis;

@SuppressWarnings("nls")
public final class ASingleLocalNameList extends PLocalNameList {
  private PLocalName _localName_;

  public ASingleLocalNameList() {
    // Constructor
  }

  public ASingleLocalNameList(@SuppressWarnings("hiding") PLocalName _localName_) {
    // Constructor
    setLocalName(_localName_);
  }

  @Override
  public Object clone() {
    return new ASingleLocalNameList(cloneNode(this._localName_));
  }

  @Override
  public void apply(Switch sw) {
    ((Analysis) sw).caseASingleLocalNameList(this);
  }

  public PLocalName getLocalName() {
    return this._localName_;
  }

  public void setLocalName(PLocalName node) {
    if (this._localName_ != null) {
      this._localName_.parent(null);
    }

    if (node != null) {
      if (node.parent() != null) {
        node.parent().removeChild(node);
      }

      node.parent(this);
    }

    this._localName_ = node;
  }

  @Override
  public String toString() {
    return "" + toString(this._localName_);
  }

  @Override
  void removeChild(@SuppressWarnings("unused") Node child) {
    // Remove child
    if (this._localName_ == child) {
      this._localName_ = null;
      return;
    }

    throw new RuntimeException("Not a child.");
  }

  @Override
  void replaceChild(
      @SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild) {
    // Replace child
    if (this._localName_ == oldChild) {
      setLocalName((PLocalName) newChild);
      return;
    }

    throw new RuntimeException("Not a child.");
  }
}