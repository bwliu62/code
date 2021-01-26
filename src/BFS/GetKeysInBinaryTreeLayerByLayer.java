package BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;


public class GetKeysInBinaryTreeLayerByLayer {

    public List<List<Integer>> layerByLayer(TreeNode root) {
        // Write your solution here
        if (root == null) {
            return new ArrayList<List<Integer>>();
        }
        // maintain a queue
        Queue<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> result = new ArrayList<>();
        queue.offer(root);
        int j = 0;
        while(!queue.isEmpty()){

            int size = queue.size();
            TreeNode cur = queue.poll();
            result.get(j).add(queue.poll().key);
            for (int i = 0; i < size - 1; i ++){
                result.get(j).add(queue.poll().key);
            }
            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
            j++;
        }
        return result;
    }

    public static void main(String[] args) {
        GetKeysInBinaryTreeLayerByLayer g = new GetKeysInBinaryTreeLayerByLayer();
        int[] level = {5,3,8,1,4,11};
        int[] inOrder = {1,3,4,5,8,11};
        TreeNode root = ConstructTree.constructBST(level,inOrder);
        List<List<Integer>> res = g.layerByLayer(root);

    }
}
