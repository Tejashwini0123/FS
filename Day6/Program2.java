*/
    /*You are developing an application for a garden management system where each tree 
in the garden is represented as a binary tree structure. The system needs to 
allow users to plant new trees in a systematic way, ensuring that each tree is 
filled level by level.

A gardener wants to:
 - Plant trees based on user input.
 - Ensure trees grow in a balanced way by filling nodes level by level.
 - Inspect the garden layout by performing an in-order traversal, which helps 
   analyze the natural arrangement of trees.

Your task is to implement a program that:
    - Accepts a list of N tree species (as integers).
    - Builds a binary tree using level-order insertion.
    - Displays the in-order traversal of the tree.

Input Format:
-------------
- An integer N representing the number of tree plants.
- A space-separated list of N integers representing tree species.

Output Format:
--------------
A list of integers, in-order traversal of tree.


Sample Input:
-------------
7
1 2 3 4 5 6 7

Sample Output:
--------------
4 2 5 1 6 3 7


Explanation:
------------
The tree looks like this:

        1
       / \
      2   3
     / \  / \
    4   5 6  7
The in order is : 4 2 5 1 6 3 7


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
class Solution{
    public static TreeNode createTree(List<Integer> l,int ind,int n) {
        if (ind>=n) return null;
        TreeNode root=new TreeNode(l.get(ind));
        root.left = createTree(l,2*ind+1,n);
        root.right = createTree(l,2*ind+2,n);
        return root;
    }
    public static List<Integer> inorder(TreeNode root,List<Integer> res){
        if (root == null) return res;
        inorder(root.left,res);
        res.add(root.val);
        inorder(root.right,res);
        return res;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        List<Integer> l=new ArrayList<>();
        for(int i=0;i<n;i++){
            int k=sc.nextInt();
            l.add(k);
        }
        TreeNode root=createTree(l, 0, l.size()); 
        List<Integer>res=new ArrayList<>();
        inorder(root,res);
        for(int i=0;i<res.size();i++){
            System.out.print(res.get(i)+" ");
        }
    }
}
