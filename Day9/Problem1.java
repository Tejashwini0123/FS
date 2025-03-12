/*A security team is setting up surveillance cameras in a multi-floor building. 
Each floor has a certain number of cameras, and every camera is assigned 
a resolution value (in megapixels). The placement follows a hierarchical 
structure, similar to a tree:
	- Floor 0 (Ground Floor) has a single main camera (root camera).
	- From the next floor onward, each camera can have at most two sub-cameras, 
	one on the left side and one on the right side.
	- If a camera does not have a sub-camera at a position, it is represented as -1.
	
The goal is to identify the camera with the highest resolution on each floor to 
ensure optimal security coverage.

Input Format:
-------------
A single line of space separated integers, the resolution values of cameras

Output Format:
--------------
A list of integers, where eech integer represents the maximum resolution camera 
on that floor.


Sample Input-1:
---------------
2 4 3 6 4 -1 9

Sample Output-1:
----------------
[2, 4, 9]


Sample Input-2:
---------------
3 4 7 7 3 8 4 

Sample Output-2:
----------------
[3, 4, 8]
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
        int rootVal=l[0];
        TreeNode  root=new TreeNode(rootVal);
        Queue<TreeNode> queue=new LinkedList<>();
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
    public static List<Integer> LevelMax(TreeNode root,List<Integer> res){
        Queue<TreeNode> queue=new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            int size=queue.size();
            int max=Integer.MIN_VALUE;
            for(int i=0;i<size;i++){
                TreeNode node=queue.poll();
                if(node.val>max){
                    max=node.val;
                }
                if(node.left!=null) queue.add(node.left);
                if(node.right!=null) queue.add(node.right);
            }
            res.add(max);
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
        System.out.println(LevelMax(root,res));
    }
}
