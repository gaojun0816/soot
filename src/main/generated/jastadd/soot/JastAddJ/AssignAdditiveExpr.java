/* This file was generated with JastAdd2 (http://jastadd.org) version R20130212 (r1031) */
package soot.JastAddJ;

/**
 * @production AssignAdditiveExpr : {@link AssignExpr};
 * @ast node
 * @declaredat /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.4Frontend/java.ast:115
 */
public abstract class AssignAdditiveExpr extends AssignExpr implements Cloneable {
  /** @apilevel low-level */
  public void flushCache() {
    super.flushCache();
  }
  /** @apilevel internal */
  public void flushCollectionCache() {
    super.flushCollectionCache();
  }
  /** @apilevel internal */
  @SuppressWarnings({"unchecked", "cast"})
  public AssignAdditiveExpr clone() throws CloneNotSupportedException {
    AssignAdditiveExpr node = (AssignAdditiveExpr) super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /** @ast method */
  public AssignAdditiveExpr() {
    super();
  }
  /**
   * Initializes the child array to the correct size. Initializes List and Opt nta children.
   *
   * @apilevel internal
   * @ast method
   * @ast method
   */
  public void init$Children() {
    children = new ASTNode[2];
  }
  /** @ast method */
  public AssignAdditiveExpr(Expr p0, Expr p1) {
    setChild(p0, 0);
    setChild(p1, 1);
  }
  /**
   * @apilevel low-level
   * @ast method
   */
  protected int numChildren() {
    return 2;
  }
  /**
   * @apilevel internal
   * @ast method
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Replaces the Dest child.
   *
   * @param node The new node to replace the Dest child.
   * @apilevel high-level
   * @ast method
   */
  public void setDest(Expr node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the Dest child.
   *
   * @return The current node used as the Dest child.
   * @apilevel high-level
   * @ast method
   */
  public Expr getDest() {
    return (Expr) getChild(0);
  }
  /**
   * Retrieves the Dest child.
   *
   * <p><em>This method does not invoke AST transformations.</em>
   *
   * @return The current node used as the Dest child.
   * @apilevel low-level
   * @ast method
   */
  public Expr getDestNoTransform() {
    return (Expr) getChildNoTransform(0);
  }
  /**
   * Replaces the Source child.
   *
   * @param node The new node to replace the Source child.
   * @apilevel high-level
   * @ast method
   */
  public void setSource(Expr node) {
    setChild(node, 1);
  }
  /**
   * Retrieves the Source child.
   *
   * @return The current node used as the Source child.
   * @apilevel high-level
   * @ast method
   */
  public Expr getSource() {
    return (Expr) getChild(1);
  }
  /**
   * Retrieves the Source child.
   *
   * <p><em>This method does not invoke AST transformations.</em>
   *
   * @return The current node used as the Source child.
   * @apilevel low-level
   * @ast method
   */
  public Expr getSourceNoTransform() {
    return (Expr) getChildNoTransform(1);
  }
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}