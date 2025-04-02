
/*The Indian Army has established multiple military camps at strategic locations 
along the Line of Actual Control (LAC) in Galwan. These camps are connected in 
a hierarchical structure, with a main base camp acting as the root of the network.

To fortify national security, the Government of India has planned to deploy 
a protective S.H.I.E.L.D. that encloses all military camps forming the outer 
boundary of the network.

Structure of Military Camps:
    - Each military camp is uniquely identified by an integer ID.
    - A camp can have at most two direct connections:
        - Left connection → Represents a subordinate camp on the left.
        - Right connection → Represents a subordinate camp on the right.
    - If a military camp does not exist at a specific position, it is 
      represented by -1
	
Mission: Deploying the S.H.I.E.L.D.
Your task is to determine the list of military camp IDs forming the outer 
boundary of the military network. This boundary must be traversed in 
anti-clockwise order, starting from the main base camp (root).

The boundary consists of:
1. The main base camp (root).
2. The left boundary:
    - Starts from the root’s left child and follows the leftmost path downwards.
    - If a camp has a left connection, follow it.
    - If no left connection exists but a right connection does, follow the right connection.
    - The leftmost leaf camp is NOT included in this boundary.
3. The leaf camps (i.e., camps with no further connections), ordered from left to right.
4. The right boundary (in reverse order):
    - Starts from the root’s right child and follows the rightmost path downwards.
    - If a camp has a right connection, follow it.
    - If no right connection exists but a left connection does, follow the left connection.
    - The rightmost leaf camp is NOT included in this boundary.


Input Format:
-------------
space separated integers, military-camp IDs.

Output Format:
--------------
Print all the military-camp IDs, which are at the edge of S.H.I.E.L.D.


Sample Input-1:
---------------
5 2 4 7 9 8 1

Sample Output-1:
----------------
[5, 2, 7, 9, 8, 1, 4]


Sample Input-2:
---------------
11 2 13 4 25 6 -1 -1 -1 7 18 9 10

Sample Output-2:
----------------
[11, 2, 4, 7, 18, 9, 10, 6, 13]


Sample Input-3:
---------------
1 2 3 -1 -1 -1 5 -1 6 7

Sample Output-3:
----------------
[1, 2, 7, 6, 5, 3]

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
    public static TreeNode createTree(int l[]) {
        if (l.length == 0) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root = new TreeNode(l[0]);
        queue.add(root);
        int i = 1;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (i < l.length && l[i] != -1) {
                node.left = new TreeNode(l[i]);
                queue.add(node.left);
            }
            i++;
            if (i < l.length && l[i] != -1) {
                node.right = new TreeNode(l[i]);
                queue.add(node.right);
            }
            i++;
        }
        return root;
    }

    public static void leftBoundary(TreeNode root, List<Integer> res) {
        TreeNode node = root.left;
        while (node != null) {
            if (node.left != null || node.right != null) res.add(node.val);
            node = (node.left != null) ? node.left : node.right;
        }
    }

    public static void leafNodes(TreeNode root, List<Integer> res) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            res.add(root.val);
            return;
        }
        leafNodes(root.left, res);
        leafNodes(root.right, res);
    }

    public static void rightBoundary(TreeNode root, List<Integer> res) {
        TreeNode node = root.right;
        List<Integer> temp = new ArrayList<>();
        while (node != null) {
            if (node.left != null || node.right != null) temp.add(node.val);
            node = (node.right != null) ? node.right : node.left;
        }
        Collections.reverse(temp);
        res.addAll(temp);
    }

    public static List<Integer> shield(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        res.add(root.val); 
        leftBoundary(root, res);
        leafNodes(root, res);
        rightBoundary(root, res);
        return res;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String s[] = sc.nextLine().split("\\s+");
        int a[] = new int[s.length];
        for (int i = 0; i < s.length; i++) {
            a[i] = Integer.parseInt(s[i]);
        }
        TreeNode root = createTree(a);
        System.out.println(shield(root));
    }
}
