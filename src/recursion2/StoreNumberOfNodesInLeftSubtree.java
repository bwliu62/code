package recursion2;

/*
Given a binary tree, count the number of nodes in each node’s left subtree, and store it in the numNodesLeft field.

Examples

 

                  1(6)

               /          \

           2(3)        3(0)

          /      \

      4(1)     5(0)

    /        \        \

6(0)     7(0)   8(0)

The numNodesLeft is shown in parentheses.
* **/
class TreeNodeLeft {
    public int key;
    public TreeNodeLeft left;
    public TreeNodeLeft right;
    public int numNodesLeft;

    public TreeNodeLeft(int key) {
        this.key = key;
    }
}

public class StoreNumberOfNodesInLeftSubtree {
    public void numNodesLeft(TreeNodeLeft root) {
        // Write your solution here
        helper(root);
    }
    private int helper(TreeNodeLeft root) {
        if (root == null) {
            return 0;
        }
        int leftNum = helper(root.left);
        int rightNum = helper(root.right);
        root.numNodesLeft = leftNum ;
        return leftNum + rightNum + 1;
    }
}
