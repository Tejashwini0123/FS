/*In a distant galaxy, an ancient civilization built a hierarchical communication 
network of interconnected relay stations. The structure of this network can be 
reconstructed using two ancient data logs:
    - Beacon Activation Order (analogous to in-order traversal)
    - Final Signal Sent Order (analogous to post-order traversal)
    
Using these logs, we can reconstruct the original relay network and process q
ueries about signals reaching specific hierarchical levels.

Given the Beacon Activation Order and the Final Signal Sent Order of a galactic 
communication network, reconstruct the relay network. After reconstructing 
the hierarchy, and the output should list the relay stations in the order they 
appear in a level-wise transmission sequence.

Input Format:
-------------
- An integer N representing the number of relay stations in the network.
- A space-separated list of N integers representing the Beacon Activation Order 
    (similar to in-order traversal).
- A space-separated list of N integers representing the Final Signal Sent Order 
    (similar to post-order traversal).

Output Format:
--------------
A list of integers, level-wise transmission sequence.


Sample Input:
-------------
7
4 2 5 1 6 3 7
4 5 2 6 7 3 1
Sample Output:
---------------
[1, 2, 3, 4, 5, 6, 7]


Explanation:
The logs correspond to the following hierarchical relay network:
        1
       / \
      2   3
     / \  / \
    4   5 6  7
The level order is : 1 2 3 4 5 6 7 
 */
import java.util.*;
class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNode(int val) {
        this.val=val;
        this.left=null;
        this.right=null;
    }
    
}
class Solution{
    public static TreeNode createTree(List<Integer>in,List<Integer>po){
        if (in.isEmpty() || po.isEmpty()) return null;
        int rootVal=po.get(po.size()-1);
        TreeNode root=new TreeNode(rootVal);
        int rootIndex = in.indexOf(rootVal);
        List<Integer> leftIn = in.subList(0, rootIndex);
        List<Integer> rightIn = in.subList(rootIndex + 1, in.size());
        List<Integer> leftPost = po.subList(0, leftIn.size());
        List<Integer> rightPost = po.subList(leftIn.size(), po.size() - 1);
        root.left=createTree(leftIn,leftPost);
        root.right=createTree(rightIn,rightPost);
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
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        ArrayList<Integer> in=new ArrayList<>();
        ArrayList<Integer> po=new ArrayList<>();
        for(int i=0;i<n;i++){
            int l=sc.nextInt();
            in.add(l);
        }
        for(int i=0;i<n;i++){
            int l=sc.nextInt();
            po.add(l);
        }
        TreeNode root=createTree(in, po);
        System.out.println(levelOrder(root));
    }
}
