package edu.grinnell.csc207.trees;

import java.util.List;

/**
 * A binary tree that satisifies the binary search tree invariant.
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

    ///// From the reading

    /**
     * A node of the binary search tree.
     */
    private static class Node<T> {
        T value;
        Node<T> left;
        Node<T> right;

        /**
         * @param value the value of the node
         * @param left  the left child of the node
         * @param right the right child of the node
         */
        Node(T value, Node<T> left, Node<T> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }

        /**
         * @param value the value of the node
         */
        Node(T value) {
            this(value, null, null);
        }
    }

    private Node<T> root;

    /**
     * Constructs a new empty binary search tree.
     */
    public BinarySearchTree() {
        this.root = null;
    }

    private int sizeH(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + sizeH(node.left) + sizeH(node.right);
        }
    }

    /**
     * @return the number of elements in the tree
     */
    public int size() {
        return sizeH(root);
    }

    private Node<T> insertH(T value, Node<T> root) {
        if (root == null) {
            return new Node<T>(value);
        } else {
            if (value.compareTo(root.value) < 0) {
                root.left = insertH(value, root.left);
            } else {
                root.right = insertH(value, root.right);
            }
            return root;
        }
    }

    /**
     * @param value the value to add to the tree
     */
    public void insert(T value) {
        root = insertH(value, root);
    }

    ///// Part 1: Traversals

    /**
     * @return the elements of this tree collected via an in-order traversal
     */
    public void toListInorderHelper(Node<T> tree, List<T> list) {
        if (tree == null) {
            return;
        } else {
            toListInorderHelper(tree.left, list);
            list.add(tree.value);
            toListInorderHelper(tree.right, list);
        }
    }

    public List<T> toListInorder() {
        List<T> result = new java.util.ArrayList<T>();
        toListInorderHelper(root, result);
        return result;
    }

    /**
     * @return the elements of this tree collected via a pre-order traversal
     */

    public void toListPreorderHelper(Node<T> tree, List<T> list) {
        if (tree == null) {
            return;
        } else {
            list.add(tree.value);
            toListPreorderHelper(tree.left, list);
            toListPreorderHelper(tree.right, list);
        }
    }

    public List<T> toListPreorder() {
        List<T> result = new java.util.ArrayList<T>();
        toListPreorderHelper(root, result);
        return result;
    }

    /**
     * @return the elements of this tree collected via a post-order traversal
     */
    public void toListPostorderHelper(Node<T> tree, List<T> list) {
        if (tree == null) {
            return;
        } else {
            toListPostorderHelper(tree.left, list);
            toListPostorderHelper(tree.right, list);
            list.add(tree.value);
        }
    }

    public List<T> toListPostorder() {
        List<T> result = new java.util.ArrayList<T>();
        toListPostorderHelper(root, result);
        return result;
    }

    ///// Part 2: Contains

    /**
     * @param value the value to search for
     * @return true iff the tree contains <code>value</code>
     */
    public boolean containsHelper(Node<T> tree, T value) {

        if (tree == null) {
            return false;
        } else {
            if (tree.value == value) {
                return true;
            } else {
                if (value.compareTo(tree.value) > 0) {
                    return containsHelper(tree.right, value);
                } else {
                    return containsHelper(tree.left, value);
                }
            }
        }
    }

    public boolean contains(T value) {
        return containsHelper(root, value);
    }

    ///// Part 3: Pretty Printing

    /**
     * @return a string representation of the tree obtained via an pre-order
     *         traversal in the
     *         form: "[v0, v1, ..., vn]"
     */
    public String toStringPreorder() {
        List<T> result = toListPreorder();
        if (result.size() == 0) {
            return "[]";
        }
        int i = 0;
        String str = "[";
        while (i < result.size() - 1) {
            str =  str + result.get(i) + ", "; 
             i++;
        }
        str =  str + result.get(result.size() - 1);
        str = str + "]";
        return str;
    }

    ///// Part 4: Deletion

    /*
     * The three cases of deletion are:
     * 1. (TODO: fill me in!)
     * 2. (TODO: fill me in!)
     * 3. (TOOD: fill me in!)
     */

    /**
     * Modifies the tree by deleting the first occurrence of <code>value</code>
     * found
     * in the tree.
     *
     * @param value the value to delete
     */
    public void delete(T value) {
        List<T> result = toListInorder();
        for(int i = 0; i < result.size(); i++) {
            if (result.get(i) == value) {
                result.remove(i);
                this.root = null;
                for(int j = 0; j < result.size(); j++){
                    insert(result.get(j));
                }
                break;
            }
        }
    }
}
