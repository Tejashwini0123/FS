
/*Given the preorder and postorder traversals of a binary tree, construct 
the original binary tree and print its level order traversal.

Input Format:
---------------
Space separated integers, pre order data
Space separated integers, post order data

Output Format:
-----------------
Print list of list of integers, the level-order data of the tree.


Sample Input:
----------------
1 2 4 5 3 6 7
4 5 2 6 7 3 1

Sample Output:
----------------
[[1], [2, 3], [4, 5, 6, 7]]

Explanation:
--------------
        1
       / \
      2   3
     / \  / \
    4   5 6  7


Sample Input:
----------------
1 2 3
2 3 1

Sample Output:
----------------
[[1], [2, 3]]

Explanation:
--------------
    1
   / \
  2  3
 */
import java.util.*;
class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int val) {
        this.val = val;
        this.left=null;
        this.right=null;
    }
}
public class Solution {
    static int preIndex=0;
    public static TreeNode createTree(int[] preorder, int[] postorder, int postStart, int postEnd,HashMap<Integer,Integer> postIndexMap) {
        if (preIndex >= preorder.length || postStart > postEnd) return null;
        TreeNode root = new TreeNode(preorder[preIndex]);
        preIndex++;
        if (postStart == postEnd || preIndex >= preorder.length) return root;
        int leftRootIndex = postIndexMap.get(preorder[preIndex]);
        if (leftRootIndex <= postEnd) {
            root.left = createTree(preorder, postorder, postStart, leftRootIndex,postIndexMap);
            root.right = createTree(preorder, postorder, leftRootIndex + 1, postEnd - 1,postIndexMap);
        }
        return root;
    }
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            result.add(level);
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] preStr = scanner.nextLine().split(" ");
        int[] preorder = Arrays.stream(preStr).mapToInt(Integer::parseInt).toArray();
        String[] postStr = scanner.nextLine().split(" ");
        int[] postorder = Arrays.stream(postStr).mapToInt(Integer::parseInt).toArray();
        HashMap<Integer,Integer> postIndexMap = new HashMap<>();
        for (int i = 0; i < postorder.length; i++) {
            postIndexMap.put(postorder[i], i);
        }
        TreeNode root = createTree(preorder, postorder, 0, postorder.length - 1,postIndexMap);
        System.out.println(levelOrder(root));
    }
}
