/*
There are some pages in a website, each page links with atmost two other pages.
Each page displays a number on it. The complete website is given as binary tree 
using the level order insertion technique.

You need to return the number of pages where the number in the page is equal to 
the sum of the numbers of its descendants. A descendant refers to any page that 
is linked but lower down the tree stucture of the website, no matter how many 
levels lower.

Input Format:
-------------
Single line of comma separated integers, numbers displayed in web-pages as Tree.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
11 3 5 2 1

Sample Output-1:
----------------
2


Sample Input-2:
---------------
3 2 1 0 0

Sample Output-2:
----------------
3

Explanation:
------------
For the pages diplaying the number 0: The sum of descendants is 0,
since they have no descendant pages.

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
class Solution{
    static int cnt=0;
    public static TreeNode createTree(List<Integer> l,int ind,int n) {
        if (ind>=n) return null;
        TreeNode root=new TreeNode(l.get(ind));
        root.left = createTree(l,2*ind+1,n);
        root.right = createTree(l,2*ind+2,n);
        return root;
    }
    public static int pages(TreeNode root){
        if (root == null) return 0;
        int c1=pages(root.left);
        int c2=pages(root.right);
        if(root.val==c1+c2 ) cnt++;
        return c1+c2+root.val;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String s[]=sc.nextLine().split("\\s+");
        List<Integer>a =new ArrayList<>();
        for(int i=0;i<s.length;i++){
            a.add(Integer.parseInt(s[i]));
        }
        TreeNode root=createTree(a, 0, a.size()); 
        cnt=0;
        int k=pages(root);
        System.out.println(cnt);
    }
}
  
