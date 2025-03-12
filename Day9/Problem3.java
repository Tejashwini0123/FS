
/*A software development company is designing a smart home automation 
system that uses sensor networks to monitor and control different devices 
in a house. The sensors are organized in a hierarchical structure, where each 
sensor node has a unique ID and can have up to two child nodes (left and right).

The company wants to analyze the left-most sensors in the system to determine
which ones are critical for detecting environmental changes. The hierarchy of 
the sensors is provided as a level-order input, where missing sensors are 
represented as -1.

Your task is to build the sensor network as a binary tree and then determine 
the left-most sensor IDs at each level.

Input Format:
-------------
Space separated integers, elements of the tree.

Output Format:
--------------
A list of integers representing the left-most sensor IDs at each level


Sample Input-1:
---------------
1 2 3 4 -1 -1 5

Sample Output-1:
----------------
[1, 2, 4]



Sample Input-2:
---------------
3 2 4 1 5

Sample Output-2:
----------------
[3, 2, 1]
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
    public static List<Integer> leftRoot(TreeNode root,TreeNode left,TreeNode right, List<Integer> res){
        if(root==null) return res;
        if(root.left!=null){
            res.add(root.left.val);
            leftRoot(root.left,left,right,res);
        }
        else if(root.right!=null){
            res.add(root.right.val);
            leftRoot(root.right,left,right,res);
        }
        else if(right!=null){
            leftRoot(right,right.left,right.right,res);
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
        res.add(root.val);
        System.out.println(leftRoot(root,root.left,root.right,res));
    }
}
