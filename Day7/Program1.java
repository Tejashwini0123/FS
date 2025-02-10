/*In a distant future, humanity has begun interstellar colonization, establishing 
zones of habitation and control on a new planet. Scientists have recorded two 
types of data regarding how these zones were structured:

1. Survey Order (analogous to pre-order traversal) – This record details how 
the colonization started, with the first zone established and then expanding 
into new zones following a systematic approach.
2. Planetary Layout (analogous to in-order traversal) – This document shows 
how zones were positioned relative to each other on the map, based on 
territorial boundaries.

Using this information, scientists need to reconstruct the colonization hierarchy 
(binary tree of zones) and display them level wise.

Input Format:
--------------
An integer N representing the number of zones colonized.
A space-separated list of N integers representing the Planetary Layout Order (in-order).
A space-separated list of N integers representing the Survey Order ( pre-order ).

Output Format:
---------------
Print all zone IDs in level order:

Sample Input:
-------------
7
4 2 5 1 6 3 7
1 2 4 5 3 6 7

Sample Output:
---------------
3 2 4 5 6 7

Explanation:
The given Planetary Layout (in-order) and Survey Order (pre-order) correspond to the following colonization hierarchy:
        1
       / \
      2   3
     / \  / \
    4   5 6  7

Level Order: [1, 2, 3, 4, 5, 6, 7]
*/
import java.util.*;
class TreeNode {
    int val;
    TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}
class Solution {
    public static TreeNode createTree(List<Integer> in, List<Integer> pre) {
        if (in.isEmpty() || pre.isEmpty()) return null;
        int rootVal = pre.get(0);
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = in.indexOf(rootVal);
        List<Integer> leftIn = new ArrayList<>(in.subList(0, rootIndex));
        List<Integer> rightIn = new ArrayList<>(in.subList(rootIndex + 1, in.size()));
        List<Integer> leftPre = new ArrayList<>(pre.subList(1, 1 + leftIn.size()));
        List<Integer> rightPre = new ArrayList<>(pre.subList(1 + leftIn.size(), pre.size()));
        root.left = createTree(leftIn, leftPre);
        root.right = createTree(rightIn, rightPre);
        return root;
    }
    public static List<Integer> levelOrder(TreeNode root){
        List<Integer> result =new ArrayList<Integer>();
        if(root==null) return result;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node=queue.poll();
            result.add(node.val);
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }
        return result;
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> in = new ArrayList<>();
        ArrayList<Integer> pre = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            in.add(sc.nextInt());
        }
        for (int i = 0; i < n; i++) {
            pre.add(sc.nextInt());
        }
        TreeNode root = createTree(in, pre);
        List<Integer> res=levelOrder(root);
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i)+" ");
        }
    }
}
