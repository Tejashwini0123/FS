  /*In an Intelligence Agency, each senior officer supervises either two junior officers 
or none. The senior officer is assigned a clearance level equal to the lowest clearance 
level of the two junior officers they supervise.

The clearance levels are represented as integer values in the range [1, 50], and multiple 
officers may have the same clearance level.

At the end, all officers (senior and junior) are collectively referred to as agents in the system.

You are provided with a hierarchical clearance level tree where each node represents 
an officer's clearance level. The tree structure follows these rules:
	- If a node has two children, its clearance level is the minimum of the two children's
	  clearance levels.
	- If a node has no children, it's clearance level is same as exists.
	- The value -1 indicates an empty (null) position.
Your task is to find the highest clearance level among all agents in the agency. 
If no such level exists, return -2.

Input Format:
-------------
A single line of space separated integers, clearance levels of each individual.

Output Format:
--------------
Print an integer, the highest clearance level.


Sample Input-1:
---------------
2 5 2 -1 -1 2 4

Sample Output-1:
----------------
5


Sample Input-2:
---------------
3 3 3 3 3

Sample Output-2:
----------------
3
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
    static int maxVal=Integer.MIN_VALUE;
    public static TreeNode createTree(int l[]){
        if(l.length==0 || l[0]==-1) return null;
        TreeNode root=new TreeNode(l[0]);
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int i=1;
        while(!queue.isEmpty() && i<l.length){
            TreeNode node=queue.poll();
            if(l[i]!=-1){
                node.left= new TreeNode(l[i]);
                queue.add(node.left);
            }
            i++;
            if(l[i]!=-1){
                node.right= new TreeNode(l[i]);
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
    public static int max(TreeNode root) {
        if (root == null) return Integer.MIN_VALUE; 
        int leftMax = max(root.left);
        int rightMax = max(root.right);
        return Math.max(root.val, Math.max(leftMax, rightMax));
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String s[]=sc.nextLine().split("\\s+");
        int a[]=new int[s.length];
        for(int i=0;i<a.length;i++){
            a[i]=Integer.parseInt(s[i]);
        }
        TreeNode root=createTree(a);
        System.out.println(max(root));
    }
} 
