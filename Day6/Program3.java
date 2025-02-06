/*In a distant future, humanity has begun interstellar colonization, establishing 
zones of habitation and control on a new planet. Scientists have recorded two 
types of data regarding how these zones were structured:

1. Survey Order (analogous to pre-order traversal) – This record details how 
the colonization started, with the first zone established and then expanding 
into new zones following a systematic approach.
2. Planetary Layout (analogous to in-order traversal) – This document shows 
how zones were positioned relative to each other on the map, based on 
territorial boundaries.

Using this information, scientists need to reconstruct the colonization hierarchy 
(binary tree of zones) and analyze areas within a specific range of levels. 
However, due to security concerns, patrol teams will scan these zones in a 
zigzag pattern:
    - Odd levels (starting from 1) should be inspected from left to right.
    - Even levels should be inspected from right to left.

Input Format:
-------------
An integer N representing the number of zones colonized.
N space-separated integers representing the Planetary Layout Order (in-order).
N space-separated integers representing the Survey Order (pre-order).
Two space sepaarted integers,Lower Level (L), Upper Level (U)

Output Format:
--------------
Print all zone IDs within the specified levels, but in spiral order:
    - Odd levels → Left to Right.
    - Even levels → Right to Left.

Sample Input:
-------------
7
4 2 5 1 6 3 7
1 2 4 5 3 6 7
2 3

Sample Output:
--------------
3 2 4 5 6 7

Explanation:
------------
The given Planetary Layout (in-order) and Survey Order (pre-order) correspond 
to the following colonization hierarchy:

        1
       / \
      2   3
     / \  / \
    4   5 6  7

Levels 2 to 3 in Regular Order:
Level 2 → 2 3
Level 3 → 4 5 6 7 

Spiral Order:
Level 2 (Even) → 3 2 (Right to Left)
Level 3 (Odd) → 4 5 6 7 (Left to Right)

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
    // Function to construct the tree from Inorder and Preorder
    public static TreeNode createTree(List<Integer> in, List<Integer> pre) {
        if (in.isEmpty() || pre.isEmpty()) return null;
        
        int rootVal = pre.get(0);
        TreeNode root = new TreeNode(rootVal);
        int rootIndex = in.indexOf(rootVal);

        List<Integer> leftIn = new ArrayList<>(in.subList(0, rootIndex));
        List<Integer> rightIn = new ArrayList<>(in.subList(rootIndex + 1, in.size()));

        List<Integer> leftPre = new ArrayList<>(pre.subList(1, 1 + leftIn.size()));
        List<Integer> rightPre = new ArrayList<>(pre.subList(1 + leftIn.size(), pre.size()));

        root.left = createTree(leftIn, leftPre);
        root.right = createTree(rightIn, rightPre);

        return root;
    }

    // Function to perform zigzag level order traversal within range L to U
    public static List<Integer> traverse(TreeNode root, int L, int U) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 1;
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            if (!leftToRight) {
                Collections.reverse(currentLevel);
            }

            if (level >= L && level <= U) {
                result.addAll(currentLevel);
            }

            leftToRight = !leftToRight;
            level++;
        }
        return result;
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<Integer> in = new ArrayList<>();
        ArrayList<Integer> pre = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            in.add(sc.nextInt());
        }
        for (int i = 0; i < n; i++) {
            pre.add(sc.nextInt());
        }

        int l = sc.nextInt();
        int u = sc.nextInt();
        sc.close();

        TreeNode root = createTree(in, pre);
        List<Integer> res = traverse(root, l, u);

        // Print result in a single line
        for (int i = 0; i < res.size(); i++) {
            System.out.print(res.get(i));
            if (i < res.size() - 1) System.out.print(" ");
        }
    }
}
