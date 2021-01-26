package BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TreeNode{
    public int key;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int key) {
        this.key = key;
    }
}

public class ConstructTree {

//    public static void main(String args[])
//    {
//        int[] level = {5,3,8,1,4,11};
//        int[] inOrder = {1,3,4,5,8,11};
//        TreeNode root = constructBST(level,inOrder);
//        inOrderTraversal(root);
//    }
    // 给一个level order 的数组，和一个in-order的数组来构建一颗BST
    public static TreeNode constructBST(int[] level, int[] in){
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < in.length; i++){
            inMap.put(in[i], i);
        }
        List<Integer> lList = new ArrayList<>();
        for (int num : level) {
            lList.add(num);
        }
        return helper(lList, inMap);
    }
    private static TreeNode helper(List<Integer> level, Map<Integer,Integer> inMap){
        if(level.isEmpty()) return null;

        TreeNode root = new TreeNode(level.remove(0));
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int num : level) {
            if (inMap.get(num) < inMap.get(root.key)){
                left.add(num);
            } else {
                right.add(num);
            }
        }
        root.left = helper(left, inMap);
        root.right = helper(right, inMap);
        return root;

    }

    public static void inOrderTraversal(TreeNode root){
        if (root == null) return ;

        inOrderTraversal(root.left);
        System.out.println(root.key);
        inOrderTraversal(root.right);
    }
}



