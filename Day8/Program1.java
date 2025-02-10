/* Mr. Rakesh is interested in working with Data Structures.

He has constructed a Binary Tree (BT) and asked his friend 
Anil to check whether the BT is a self-mirror tree or not.

Can you help Anil determine whether the given BT is a self-mirror tree?
Return true if it is a self-mirror tree; otherwise, return false.

Note:
------
In the tree, '-1' indicates an empty (null) node.

Input Format:
-------------
A single line of space separated integers, values at the treenode

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
2 1 1 2 3 3 2

Sample Output-1:
----------------
true


Sample Input-2:
---------------
2 1 1 -1 3 -1 3

Sample Output-2:
----------------
false
 */
import java.util.*;
class TreeNode{
    int val;
    TreeNode left,right;
    TreeNode(int val){
        this.val=val;
        this.left=null;
        this.right=null;
    }
}
class Solution{
    public static TreeNode createTree(int l[]){
        if(l.length==0 || l[0]==-1) return null;
        Queue<TreeNode> queue=new LinkedList<>();
        TreeNode root=new TreeNode(l[0]);
        queue.add(root);
        int i=1;
        while(!queue.isEmpty() && i<l.length){
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
    public static boolean isMirror(TreeNode root1, TreeNode root2){
        if(root1==null && root2==null) return true;
        if(root1==null || root2==null) return false;
        if(root1.val!=root2.val) return false;
        return isMirror(root1.left,root2.right) && isMirror(root1.right,root2.left);
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String s[]=sc.nextLine().split("\\s+");
        int a[]=new int[s.length];
        for(int i=0;i<s.length;i++){
            a[i]=Integer.parseInt(s[i]);
        }
        TreeNode root=createTree(a);
        if(root==null){
            System.out.println(false);
            return;
        }
        System.out.println(isMirror(root.left,root.right));
    }
}
