/*In Marketing Job, each agent will mentor atmost two sub-agents. 
At the end, all mentor agents and sub agents, will be treated as agents only.

Now, you are given the sales data of two months as a tree, tree contains the 
count of the items sold by each agent, few agents might join the job and 
few might left the job.

You are given the sales data as month-1 and month-2.
Your task is to find get the combined report of two months data.

Implement the class Solution:
   1. public TreeNode combinedReport(TreeNode root1, TreeNode root2): 
    returns the root node of the combined data.

NOTE:
    - In the tree '-1', indicates no sales(null).

Input Format:
-------------
Line-1: space separated integers, sales data of month-1
Line-2: space separated integers, sales data of month-2

Output Format:
--------------
Print list of integers, with combined sales data.


Sample Input-1:
---------------
2 5 2 -1 -1 -1 4
1 2 3 4 5

Sample Output-1:
----------------
3 7 5 4 5 4


Sample Input-2:
---------------
1 2 3 4
1

Sample Output-2:
----------------
2 2 3 4
In Marketing Job, each agent will mentor atmost two sub-agents. 
At the end, all mentor agents and sub agents, will be treated as agents only.

Now, you are given the sales data of two months as a tree, tree contains the 
count of the items sold by each agent, few agents might join the job and 
few might left the job.

You are given the sales data as month-1 and month-2.
Your task is to find get the combined report of two months data.

Implement the class Solution:
   1. public TreeNode combinedReport(TreeNode root1, TreeNode root2): 
    returns the root node of the combined data.

NOTE:
	- In the tree '-1', indicates no sales(null).

Input Format:
-------------
Line-1: space separated integers, sales data of month-1
Line-2: space separated integers, sales data of month-2

Output Format:
--------------
Print list of integers, with combined sales data.


Sample Input-1:
---------------
2 5 2 -1 -1 -1 4
1 2 3 4 5

Sample Output-1:
----------------
3 7 5 4 5 4


Sample Input-2:
---------------
1 2 3 4
1

Sample Output-2:
----------------
2 2 3 4
 */

import java.util.*;
TreeNode for Reference.

class TreeNode {
    Integer val;
    TreeNode left, right;
    
    TreeNode(Integer val) {
        this.val = val;
        this.left = this.right = null;
    }
}


class Solution{
    public TreeNode combinedReport(TreeNode root1, TreeNode root2){
                if(root1==null) return root2;
                if(root2==null) return root1;
                TreeNode root=new TreeNode(root1.val+root2.val);
                root.left=combinedReport(root1.left, root2.left);
                root.right=combinedReport(root1.right, root2.right);
                return root;
        }
        public List<Integer> levelOrder(TreeNode root) {
            List<Integer> result = new ArrayList<>();
            if (root == null) return result;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode node = queue.poll();
                    result.add(node.val);
                    if (node.left != null) queue.add(node.left);
                    if (node.right != null) queue.add(node.right);
                }
            }
            return result;
        }      
} 
