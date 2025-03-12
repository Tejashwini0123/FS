
/*Balbir Singh is working with Binary Trees.
The elements of the tree are given in level-order format.

Balbir is observing the tree from the right side, meaning he 
can only see the rightmost nodes (one node per level).

You are given the root of a binary tree. Your task is to determine 
the nodes visible from the right side and return them in top-to-bottom order.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
A list of integers representing the node values visible from the right side


Sample Input-1:
---------------
1 2 3 4 -1 -1 5

Sample Output-1:
----------------
[1, 3, 5]



Sample Input-2:
---------------
3 1 4 5 2

Sample Output-2:
----------------
[3, 4, 2]

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
    public static TreeNode createTree(int l[]){
        if(l.length==0) return null;
        Queue<TreeNode> queue=new LinkedList<>();
        TreeNode root=new TreeNode(l[0]);
        queue.add(root);
        int i=1;
        while(!queue.isEmpty()){
            TreeNode node=queue.poll();
            if(i<l.length && l[i]!=-1){
                node.left=new TreeNode(l[i]);
                queue.add(node.left);
            } 
            i++;
            if(i<l.length && l[i]!=-1){
                node.right=new TreeNode(l[i]);
                queue.add(node.right);
            } 
            i++;
        }
        return root;
    }
    public static List<Integer> rightRoot(TreeNode root, List<Integer> res){
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int cur=-1;
        while(!queue.isEmpty()){
            int size=queue.size();
            for(int i=0;i<size;i++){
                TreeNode node=queue.poll();
                cur=node.val;
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }
            res.add(cur);
        }
        return res;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String s[]=sc.nextLine().split("\\s+");
        int a[]=new int[s.length];
        for(int i=0;i<s.length;i++){
            a[i]=Integer.parseInt(s[i]);
        }
        TreeNode root=createTree(a);
        List<Integer> res=new ArrayList<>();
        System.out.println(rightRoot(root,res));
    }
} 
