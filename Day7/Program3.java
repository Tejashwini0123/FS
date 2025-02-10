/*
Write a program to construct a binary tree from level-order input, while treating -1 
as a placeholder for missing nodes. The program reads input, constructs the tree, 
and provides an in-order traversal to verify correctness.

Input Format:
---------------
Space separated integers, level order data (where -1 indiactes null node).

Output Format:
-----------------
Print the in-order data of the tree.


Sample Input:
----------------
1 2 3 -1 -1 4 5

Sample Output:
----------------
2 1 4 3 5

Explanation:
--------------
    1
   / \
  2   3
     / \
    4   5


Sample Input:
----------------
1 2 3 4 5 6 7

Sample Output:
----------------
4 2 5 1 6 3 7

Explanation:
--------------
        1
       / \
      2   3
     / \  / \
    4  5 6  7

====================================
*/ 
import java.util.*;
class TreeNode{
    int val;
    TreeNode left,right;
    public TreeNode(int val){
        this.val=val;
        this.left=null;
        this.right=null;
    }
}
class Solution{
    public static TreeNode createTree(List<Integer> l) {
        if (l.isEmpty() || l.get(0) == -1) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(l.get(0));
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty() && i < l.size()) {
            TreeNode current = queue.poll();
            if (i < l.size() && l.get(i) != -1) {
                current.left = new TreeNode(l.get(i));
                queue.add(current.left);
            }
            i++;
            if (i < l.size() && l.get(i) != -1) {
                current.right = new TreeNode(l.get(i));
                queue.add(current.right);
            }
            i++;
        }
        return root;
    }
    public static List<Integer> inorder(TreeNode root,List<Integer> res){
        if (root == null ) return res;
        inorder(root.left,res);
        res.add(root.val);
        inorder(root.right,res);
        return res;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String s=sc.nextLine();
        String c[]=s.split("\\s+");
        List<Integer> l=new ArrayList<>();
        for(int i=0;i<c.length;i++){
            int k=Integer.parseInt(c[i]);
            l.add(k);
        }
        TreeNode root=createTree(l);
        List<Integer> res=new ArrayList<>();
        inorder(root,res);
        for(int i=0;i<res.size();i++){
        System.out.print(res.get(i)+" ");
        }
    }
} 
