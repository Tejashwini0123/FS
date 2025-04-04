/*
Imagine you are a librarian organizing books on vertical shelves in a grand library. The books are currently scattered across a tree-like structure, where each book (node) has a position determined by its shelf number (column) and row number (level).

Your task is to arrange the books on shelves so that:
1. Books are placed column by column from left to right.
2. Within the same column, books are arranged from top to bottom (i.e., by row).
3. If multiple books belong to the same shelf and row, they should be arranged from left to right, just as they appear in the original scattered arrangement.

Example 1:
Input:
3 9 20 -1 -1 15 7
Output: 
[[9],[3,15],[20],[7]]

Explanation:
         3
       /   \
      9     20
          /    \
         15     7

Shelf 1: [9]
Shelf 2: [3, 15]
Shelf 3: [20]
Shelf 4: [7]

Example 2:
Input:
3 9 8 4 0 1 7
Output: 
[[4],[9],[3,0,1],[8],[7]]

Explanation:
          3
       /     \
      9       8
    /   \   /   \
   4     0 1     7

Shelf 1: [4]
Shelf 2: [9]
Shelf 3: [3, 0, 1]
Shelf 4: [8]
Shelf 5: [7]

Library Organization Rules:
1. Each column represents a shelf from left to right.
2. Books on the same shelf are arranged from top to bottom.
3. If books share the same position, they are arranged left to right in order of appearance.

*/
import java.util.*;
class Treenode{
    int val;
    Treenode left,right;
    public Treenode(int val){
        this.val=val;
        this.left=this.right=null;
    }
}
class Pair{
    Treenode node;
    int level;
    Pair(Treenode node, int level){
        this.node=node;
        this.level=level;
    }
}
class Solution{
    public static Treenode buildTree(int[] levelorder){
        Treenode root = new Treenode(levelorder[0]);
        Queue<Treenode> queue = new LinkedList<Treenode>();
        queue.add(root);
        int i = 1;
        while(!queue.isEmpty() && i<levelorder.length){
            Treenode node = queue.poll();
            if(i<levelorder.length && levelorder[i]!=-1){
                node.left = new Treenode(levelorder[i]);
                queue.offer(node.left);
            }
            i++;
            if(i<levelorder.length && levelorder[i]!=-1){
                node.right = new Treenode(levelorder[i]);
                queue.offer(node.right);
            }
            i++;
        }
        return root;
    }
    public static void vertical(Treenode root, TreeMap<Integer,ArrayList<Integer>> map,int cur){
        if(root==null) return ;
        Queue<Pair> queue=new LinkedList<>();
        queue.add(new Pair(root,cur));
        while(!queue.isEmpty()){
            Pair node=queue.poll();
            Treenode newnode=node.node;
            cur = node.level;
            if(map.get(cur)==null){
                map.put(cur,new ArrayList<>());
            }
            map.get(cur).add(newnode.val);
            if(newnode.left!=null) queue.offer(new Pair(newnode.left,cur-1));
            if(newnode.right!=null) queue.offer(new Pair(newnode.right,cur+1));
        }
    }
    public static void solve(int a[]){
        TreeMap<Integer,ArrayList<Integer>> map=new TreeMap<>();
        Treenode root=buildTree(a);
        vertical(root,map,0);
        List<List<Integer>> res=new ArrayList<>();
        for(Map.Entry<Integer,ArrayList<Integer>> e : map.entrySet()){
            res.add(e.getValue());
        }
        System.out.println(res);
    }
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String[] n1 = sc.nextLine().split(" ");
        int[] levelorder = new int[n1.length];
        for(int i = 0;i<n1.length;i++){
            levelorder[i] = Integer.parseInt(n1[i]);
        }
        solve(levelorder);
    }
}
