
public class Solution {

    public static void main(String args[]){
        /*
                            5

                          /    \

                        3        8

                      /   \        \

                    1      4        11
        **/
        int[] level = {5,3,8,1,4,11};
        int[] inOrder = {1,3,4,5,8,11};
        TreeNode root = ConstructTree.constructBST(level,inOrder);
//        ConstructTree.inOrderTraversal(root);
        TreeNode newRoot = delete(root,3);
        ConstructTree.inOrderTraversal(newRoot);
    }
    public static TreeNode delete(TreeNode root, int key) {  // given solution from lai_code
        // recursion base case
        if (root == null) {
            return null;
        }
        // 当找到待删除节点node时，
        if (key == root.key) {
            if (root.left == null) { //case1 & 2 删除节点没有孩子 或 没有 左孩子
                return root.right;
            }else if (root.right == null) { //case 3 删除节点没有右孩子，但是有左孩子
                return root.left;
            }else if (root.right.left == null) { //4.1 删除节点的右孩子没有左孩子，
                root.right.left = root.left;
                return root.right;
            }else{ // case 4.2 删除节点既有左孩子又有右孩子， 此时提右孩子中左子树下最小的值上来
                TreeNode newRoot = deleteSmallest(root.right);
                newRoot.left = root.left;
                newRoot.right = root.right;
                return newRoot;
            }
        }

        if(key < root.key){
            root.left = delete(root.left, key);
        }else if (key > root.key) {
            root.right = delete(root.right, key);
        }
        return root;
    }

    public static TreeNode deleteTree(TreeNode root, int key) {
        // Write your solution here
        if(root == null) return null;

        // step 1 find the target
        if(root.key > key) {
            root.left = deleteTree(root.left, key);
            return root;
        }else if(root.key < key) {
            root.right = deleteTree(root.right, key);
            return root;
        }

        // when
        //case 1 & 2
        if(root.left == null) {
            return root.right;

        }else if(root.right == null){ // case 3
            return root.left;
        }
        //case 4
        // case 4.1 node.right 没有 left child,
        if(root.right.left == null) {
            root.right.left = root.left;
            return root.right;
        }
        // 4.2 待删除节点node 的右孩子 node.right 有左孩子 node.right.left != null, 此时要先找到node.right中左子树
        //中最小点然后提上来

        TreeNode smallest = deleteSmallest(root.right);
        root.right = smallest;
        smallest.left = root.left;
        smallest.right = root.right;

        return smallest;

    }
    private static TreeNode deleteSmallest(TreeNode cur){
        TreeNode prev = cur;
        cur = cur.left;
        while( cur.left != null) {
            prev = cur;
            cur = cur.left;
        }
        prev.left = cur.right;
        return cur;
    }
}

