package com.example.seminar_fx;

public class BSTWithHeight<E> extends BST<E> {
  public BSTWithHeight() {
    super();
  }

  /** Create a binary tree from an array of objects */
  public BSTWithHeight(E[] objects) {
    super(objects);
  }

  /** Returns the height of this binary tree. */
  public int height() {
    return height(root);
  }

  private int height(TreeNode<E> root) {
    if (root == null) {
      return -1; // Empty tree has height of -1
    }

    // Recursively calculate height of left and right subtrees
    int leftHeight = height(root.left);
    int rightHeight = height(root.right);

    // Return the larger height plus 1 (for the current node)
    return Math.max(leftHeight, rightHeight) + 1;
  }
}
