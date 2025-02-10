
/*In an Intelligence Agency, each senior officer supervises either two junior officers 
or none. The senior officer is assigned a clearance level equal to the higher clearance 
level of the two junior officers they supervise.

The clearance levels are represented as integer values in the range [1, 50], and 
multiple officers may have the same clearance level.

At the end, all officers (senior and junior) are collectively referred to as 
agents in the system.

You are provided with a hierarchical clearance level tree where each node represents 
an officer's clearance level. The tree structure follows these rules:
	- If a node has two children, its clearance level is the maximum of the two children's
	  clearance levels.
	- If a node has no children, it's clearance level is same as exists.
	- The value -1 indicates an empty (null) position.
Your task is to find the second highest clearance level among all agents in the agency. 
If no such level exists, return -2.

Input Format:
-------------
A single line of space separated integers, clearance levels of each individual.

Output Format:
--------------
Print an integer, second top agent based on rank.


Sample Input-1:
---------------
5 5 4 -1 -1 2 4

Sample Output-1:
----------------
4


Sample Input-2:
---------------
3 3 3 3 3

Sample Output-2:
----------------
-2
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
    static int maxVal1=Integer.MIN_VALUE;
    static int maxVal2=Integer.MIN_VALUE;
    public static TreeNode createTree(int l[]){
        if(l.length==0 || l[0]==-1) return null;
        TreeNode root=new TreeNode(l[0]);
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        int i=1;
        while(!queue.isEmpty() && i<l.length){
            TreeNode node=queue.poll();
            if(i<l.length && l[i]!=-1){
                node.left= new TreeNode(l[i]);
                queue.add(node.left);
            }
            i++;
            if(i<l.length && l[i]!=-1){
                node.right= new TreeNode(l[i]);
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }
    public static int secMax(TreeNode root){
        if(root==null) return -2;
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node=queue.poll();
            if (node.val > maxVal1) {
                maxVal2 = maxVal1;
                maxVal1 = node.val;
            } else if (node.val > maxVal2 && node.val < maxVal1) {
                maxVal2 = node.val;
            }
            if(node.left != null) queue.add(node.left);
            if(node.right != null) queue.add(node.right);
        }
        if(maxVal2==Integer.MIN_VALUE) return -2;
        return maxVal2;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String s[]=sc.nextLine().split("\\s+");
        int a[]=new int[s.length];
        for(int i=0;i<a.length;i++){
            a[i]=Integer.parseInt(s[i]);
        }
        TreeNode root=createTree(a);
        System.out.println(secMax(root));
    }
}
