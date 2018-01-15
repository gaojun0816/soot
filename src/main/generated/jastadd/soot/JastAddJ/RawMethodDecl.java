/* This file was generated with JastAdd2 (http://jastadd.org) version R20130212 (r1031) */
package soot.JastAddJ;

/**
 * @production RawMethodDecl : {@link ParMethodDecl};
 * @ast node
 * @declaredat
 *     /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/GenericMethods.ast:8
 */
public class RawMethodDecl extends ParMethodDecl implements Cloneable {
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
  public RawMethodDecl clone() throws CloneNotSupportedException {
    RawMethodDecl node = (RawMethodDecl) super.clone();
    node.in$Circle(false);
    node.is$Final(false);
    return node;
  }
  /** @apilevel internal */
  @SuppressWarnings({"unchecked", "cast"})
  public RawMethodDecl copy() {
    try {
      RawMethodDecl node = clone();
      node.parent = null;
      if (children != null) node.children = children.clone();
      return node;
    } catch (CloneNotSupportedException e) {
      throw new Error("Error: clone not supported for " + getClass().getName());
    }
  }
  /**
   * Create a deep copy of the AST subtree at this node. The copy is dangling, i.e. has no parent.
   *
   * @return dangling copy of the subtree at this node
   * @apilevel low-level
   */
  @SuppressWarnings({"unchecked", "cast"})
  public RawMethodDecl fullCopy() {
    RawMethodDecl tree = copy();
    if (children != null) {
      for (int i = 0; i < children.length; ++i) {
        ASTNode child = children[i];
        if (child != null) {
          child = child.fullCopy();
          tree.setChild(child, i);
        }
      }
    }
    return tree;
  }
  /**
   * @ast method
   * @aspect LookupParTypeDecl
   * @declaredat
   *     /Users/eric/Documents/workspaces/clara-soot/JastAddJ/Java1.5Frontend/Generics.jrag:863
   */
  public boolean isRawType() {
    return true;
  }
  /** @ast method */
  public RawMethodDecl() {
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
    children = new ASTNode[6];
    setChild(new List(), 2);
    setChild(new List(), 3);
    setChild(new Opt(), 4);
    setChild(new List(), 5);
  }
  /** @ast method */
  public RawMethodDecl(
      Modifiers p0,
      Access p1,
      String p2,
      List<ParameterDeclaration> p3,
      List<Access> p4,
      Opt<Block> p5,
      List<Access> p6,
      GenericMethodDecl p7) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
    setChild(p6, 5);
    setGenericMethodDecl(p7);
  }
  /** @ast method */
  public RawMethodDecl(
      Modifiers p0,
      Access p1,
      beaver.Symbol p2,
      List<ParameterDeclaration> p3,
      List<Access> p4,
      Opt<Block> p5,
      List<Access> p6,
      GenericMethodDecl p7) {
    setChild(p0, 0);
    setChild(p1, 1);
    setID(p2);
    setChild(p3, 2);
    setChild(p4, 3);
    setChild(p5, 4);
    setChild(p6, 5);
    setGenericMethodDecl(p7);
  }
  /**
   * @apilevel low-level
   * @ast method
   */
  protected int numChildren() {
    return 6;
  }
  /**
   * @apilevel internal
   * @ast method
   */
  public boolean mayHaveRewrite() {
    return false;
  }
  /**
   * Replaces the Modifiers child.
   *
   * @param node The new node to replace the Modifiers child.
   * @apilevel high-level
   * @ast method
   */
  public void setModifiers(Modifiers node) {
    setChild(node, 0);
  }
  /**
   * Retrieves the Modifiers child.
   *
   * @return The current node used as the Modifiers child.
   * @apilevel high-level
   * @ast method
   */
  public Modifiers getModifiers() {
    return (Modifiers) getChild(0);
  }
  /**
   * Retrieves the Modifiers child.
   *
   * <p><em>This method does not invoke AST transformations.</em>
   *
   * @return The current node used as the Modifiers child.
   * @apilevel low-level
   * @ast method
   */
  public Modifiers getModifiersNoTransform() {
    return (Modifiers) getChildNoTransform(0);
  }
  /**
   * Replaces the TypeAccess child.
   *
   * @param node The new node to replace the TypeAccess child.
   * @apilevel high-level
   * @ast method
   */
  public void setTypeAccess(Access node) {
    setChild(node, 1);
  }
  /**
   * Retrieves the TypeAccess child.
   *
   * @return The current node used as the TypeAccess child.
   * @apilevel high-level
   * @ast method
   */
  public Access getTypeAccess() {
    return (Access) getChild(1);
  }
  /**
   * Retrieves the TypeAccess child.
   *
   * <p><em>This method does not invoke AST transformations.</em>
   *
   * @return The current node used as the TypeAccess child.
   * @apilevel low-level
   * @ast method
   */
  public Access getTypeAccessNoTransform() {
    return (Access) getChildNoTransform(1);
  }
  /**
   * Replaces the lexeme ID.
   *
   * @param value The new value for the lexeme ID.
   * @apilevel high-level
   * @ast method
   */
  public void setID(String value) {
    tokenString_ID = value;
  }
  /**
   * JastAdd-internal setter for lexeme ID using the Beaver parser.
   *
   * @apilevel internal
   * @ast method
   */
  public void setID(beaver.Symbol symbol) {
    if (symbol.value != null && !(symbol.value instanceof String))
      throw new UnsupportedOperationException("setID is only valid for String lexemes");
    tokenString_ID = (String) symbol.value;
    IDstart = symbol.getStart();
    IDend = symbol.getEnd();
  }
  /**
   * Retrieves the value for the lexeme ID.
   *
   * @return The value for the lexeme ID.
   * @apilevel high-level
   * @ast method
   */
  public String getID() {
    return tokenString_ID != null ? tokenString_ID : "";
  }
  /**
   * Replaces the Parameter list.
   *
   * @param list The new list node to be used as the Parameter list.
   * @apilevel high-level
   * @ast method
   */
  public void setParameterList(List<ParameterDeclaration> list) {
    setChild(list, 2);
  }
  /**
   * Retrieves the number of children in the Parameter list.
   *
   * @return Number of children in the Parameter list.
   * @apilevel high-level
   * @ast method
   */
  public int getNumParameter() {
    return getParameterList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Parameter list. Calling this method will not trigger
   * rewrites..
   *
   * @return Number of children in the Parameter list.
   * @apilevel low-level
   * @ast method
   */
  public int getNumParameterNoTransform() {
    return getParameterListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Parameter list..
   *
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Parameter list.
   * @apilevel high-level
   * @ast method
   */
  @SuppressWarnings({"unchecked", "cast"})
  public ParameterDeclaration getParameter(int i) {
    return getParameterList().getChild(i);
  }
  /**
   * Append an element to the Parameter list.
   *
   * @param node The element to append to the Parameter list.
   * @apilevel high-level
   * @ast method
   */
  public void addParameter(ParameterDeclaration node) {
    List<ParameterDeclaration> list =
        (parent == null || state == null) ? getParameterListNoTransform() : getParameterList();
    list.addChild(node);
  }
  /**
   * @apilevel low-level
   * @ast method
   */
  public void addParameterNoTransform(ParameterDeclaration node) {
    List<ParameterDeclaration> list = getParameterListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the Parameter list element at index {@code i} with the new node {@code node}.
   *
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   * @ast method
   */
  public void setParameter(ParameterDeclaration node, int i) {
    List<ParameterDeclaration> list = getParameterList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the Parameter list.
   *
   * @return The node representing the Parameter list.
   * @apilevel high-level
   * @ast method
   */
  public List<ParameterDeclaration> getParameters() {
    return getParameterList();
  }
  /**
   * Retrieves the Parameter list.
   *
   * <p><em>This method does not invoke AST transformations.</em>
   *
   * @return The node representing the Parameter list.
   * @apilevel low-level
   * @ast method
   */
  public List<ParameterDeclaration> getParametersNoTransform() {
    return getParameterListNoTransform();
  }
  /**
   * Retrieves the Parameter list.
   *
   * @return The node representing the Parameter list.
   * @apilevel high-level
   * @ast method
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ParameterDeclaration> getParameterList() {
    List<ParameterDeclaration> list = (List<ParameterDeclaration>) getChild(2);
    list.getNumChild();
    return list;
  }
  /**
   * Retrieves the Parameter list.
   *
   * <p><em>This method does not invoke AST transformations.</em>
   *
   * @return The node representing the Parameter list.
   * @apilevel low-level
   * @ast method
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<ParameterDeclaration> getParameterListNoTransform() {
    return (List<ParameterDeclaration>) getChildNoTransform(2);
  }
  /**
   * Replaces the Exception list.
   *
   * @param list The new list node to be used as the Exception list.
   * @apilevel high-level
   * @ast method
   */
  public void setExceptionList(List<Access> list) {
    setChild(list, 3);
  }
  /**
   * Retrieves the number of children in the Exception list.
   *
   * @return Number of children in the Exception list.
   * @apilevel high-level
   * @ast method
   */
  public int getNumException() {
    return getExceptionList().getNumChild();
  }
  /**
   * Retrieves the number of children in the Exception list. Calling this method will not trigger
   * rewrites..
   *
   * @return Number of children in the Exception list.
   * @apilevel low-level
   * @ast method
   */
  public int getNumExceptionNoTransform() {
    return getExceptionListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the Exception list..
   *
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the Exception list.
   * @apilevel high-level
   * @ast method
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Access getException(int i) {
    return getExceptionList().getChild(i);
  }
  /**
   * Append an element to the Exception list.
   *
   * @param node The element to append to the Exception list.
   * @apilevel high-level
   * @ast method
   */
  public void addException(Access node) {
    List<Access> list =
        (parent == null || state == null) ? getExceptionListNoTransform() : getExceptionList();
    list.addChild(node);
  }
  /**
   * @apilevel low-level
   * @ast method
   */
  public void addExceptionNoTransform(Access node) {
    List<Access> list = getExceptionListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the Exception list element at index {@code i} with the new node {@code node}.
   *
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   * @ast method
   */
  public void setException(Access node, int i) {
    List<Access> list = getExceptionList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the Exception list.
   *
   * @return The node representing the Exception list.
   * @apilevel high-level
   * @ast method
   */
  public List<Access> getExceptions() {
    return getExceptionList();
  }
  /**
   * Retrieves the Exception list.
   *
   * <p><em>This method does not invoke AST transformations.</em>
   *
   * @return The node representing the Exception list.
   * @apilevel low-level
   * @ast method
   */
  public List<Access> getExceptionsNoTransform() {
    return getExceptionListNoTransform();
  }
  /**
   * Retrieves the Exception list.
   *
   * @return The node representing the Exception list.
   * @apilevel high-level
   * @ast method
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Access> getExceptionList() {
    List<Access> list = (List<Access>) getChild(3);
    list.getNumChild();
    return list;
  }
  /**
   * Retrieves the Exception list.
   *
   * <p><em>This method does not invoke AST transformations.</em>
   *
   * @return The node representing the Exception list.
   * @apilevel low-level
   * @ast method
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Access> getExceptionListNoTransform() {
    return (List<Access>) getChildNoTransform(3);
  }
  /**
   * Replaces the optional node for the Block child. This is the {@code Opt} node containing the
   * child Block, not the actual child!
   *
   * @param opt The new node to be used as the optional node for the Block child.
   * @apilevel low-level
   * @ast method
   */
  public void setBlockOpt(Opt<Block> opt) {
    setChild(opt, 4);
  }
  /**
   * Check whether the optional Block child exists.
   *
   * @return {@code true} if the optional Block child exists, {@code false} if it does not.
   * @apilevel high-level
   * @ast method
   */
  public boolean hasBlock() {
    return getBlockOpt().getNumChild() != 0;
  }
  /**
   * Retrieves the (optional) Block child.
   *
   * @return The Block child, if it exists. Returns {@code null} otherwise.
   * @apilevel low-level
   * @ast method
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Block getBlock() {
    return getBlockOpt().getChild(0);
  }
  /**
   * Replaces the (optional) Block child.
   *
   * @param node The new node to be used as the Block child.
   * @apilevel high-level
   * @ast method
   */
  public void setBlock(Block node) {
    getBlockOpt().setChild(node, 0);
  }
  /**
   * @apilevel low-level
   * @ast method
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Block> getBlockOpt() {
    return (Opt<Block>) getChild(4);
  }
  /**
   * Retrieves the optional node for child Block. This is the {@code Opt} node containing the child
   * Block, not the actual child!
   *
   * <p><em>This method does not invoke AST transformations.</em>
   *
   * @return The optional node for child Block.
   * @apilevel low-level
   * @ast method
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Opt<Block> getBlockOptNoTransform() {
    return (Opt<Block>) getChildNoTransform(4);
  }
  /**
   * Replaces the TypeArgument list.
   *
   * @param list The new list node to be used as the TypeArgument list.
   * @apilevel high-level
   * @ast method
   */
  public void setTypeArgumentList(List<Access> list) {
    setChild(list, 5);
  }
  /**
   * Retrieves the number of children in the TypeArgument list.
   *
   * @return Number of children in the TypeArgument list.
   * @apilevel high-level
   * @ast method
   */
  public int getNumTypeArgument() {
    return getTypeArgumentList().getNumChild();
  }
  /**
   * Retrieves the number of children in the TypeArgument list. Calling this method will not trigger
   * rewrites..
   *
   * @return Number of children in the TypeArgument list.
   * @apilevel low-level
   * @ast method
   */
  public int getNumTypeArgumentNoTransform() {
    return getTypeArgumentListNoTransform().getNumChildNoTransform();
  }
  /**
   * Retrieves the element at index {@code i} in the TypeArgument list..
   *
   * @param i Index of the element to return.
   * @return The element at position {@code i} in the TypeArgument list.
   * @apilevel high-level
   * @ast method
   */
  @SuppressWarnings({"unchecked", "cast"})
  public Access getTypeArgument(int i) {
    return getTypeArgumentList().getChild(i);
  }
  /**
   * Append an element to the TypeArgument list.
   *
   * @param node The element to append to the TypeArgument list.
   * @apilevel high-level
   * @ast method
   */
  public void addTypeArgument(Access node) {
    List<Access> list =
        (parent == null || state == null)
            ? getTypeArgumentListNoTransform()
            : getTypeArgumentList();
    list.addChild(node);
  }
  /**
   * @apilevel low-level
   * @ast method
   */
  public void addTypeArgumentNoTransform(Access node) {
    List<Access> list = getTypeArgumentListNoTransform();
    list.addChild(node);
  }
  /**
   * Replaces the TypeArgument list element at index {@code i} with the new node {@code node}.
   *
   * @param node The new node to replace the old list element.
   * @param i The list index of the node to be replaced.
   * @apilevel high-level
   * @ast method
   */
  public void setTypeArgument(Access node, int i) {
    List<Access> list = getTypeArgumentList();
    list.setChild(node, i);
  }
  /**
   * Retrieves the TypeArgument list.
   *
   * @return The node representing the TypeArgument list.
   * @apilevel high-level
   * @ast method
   */
  public List<Access> getTypeArguments() {
    return getTypeArgumentList();
  }
  /**
   * Retrieves the TypeArgument list.
   *
   * <p><em>This method does not invoke AST transformations.</em>
   *
   * @return The node representing the TypeArgument list.
   * @apilevel low-level
   * @ast method
   */
  public List<Access> getTypeArgumentsNoTransform() {
    return getTypeArgumentListNoTransform();
  }
  /**
   * Retrieves the TypeArgument list.
   *
   * @return The node representing the TypeArgument list.
   * @apilevel high-level
   * @ast method
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Access> getTypeArgumentList() {
    List<Access> list = (List<Access>) getChild(5);
    list.getNumChild();
    return list;
  }
  /**
   * Retrieves the TypeArgument list.
   *
   * <p><em>This method does not invoke AST transformations.</em>
   *
   * @return The node representing the TypeArgument list.
   * @apilevel low-level
   * @ast method
   */
  @SuppressWarnings({"unchecked", "cast"})
  public List<Access> getTypeArgumentListNoTransform() {
    return (List<Access>) getChildNoTransform(5);
  }
  /**
   * Replaces the lexeme GenericMethodDecl.
   *
   * @param value The new value for the lexeme GenericMethodDecl.
   * @apilevel high-level
   * @ast method
   */
  public void setGenericMethodDecl(GenericMethodDecl value) {
    tokenGenericMethodDecl_GenericMethodDecl = value;
  }
  /**
   * Retrieves the value for the lexeme GenericMethodDecl.
   *
   * @return The value for the lexeme GenericMethodDecl.
   * @apilevel high-level
   * @ast method
   */
  public GenericMethodDecl getGenericMethodDecl() {
    return tokenGenericMethodDecl_GenericMethodDecl;
  }
  /** @apilevel internal */
  public ASTNode rewriteTo() {
    return super.rewriteTo();
  }
}