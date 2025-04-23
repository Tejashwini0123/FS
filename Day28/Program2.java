/*You are a database integrity engineer working for a global cloud company. 
Your team maintains a distributed database network, where each server either:
    - Stores equivalent data to another server (serverX == serverY).
    - Stores different data from another server (serverX != serverY).

The transitive consistency rule must be followed:
    - If A == B and B == C, then A == C must be true.
    - If A == B and B != C, then A != C must be true.

Your task is to analyze the given constraints and determine whether they 
follow transitive consistency. If all relations are consistent, return true; 
otherwise, return false

Input Format:
-------------
Space separated strnigs, list of relations

Output Format:
--------------
Print a boolean value, whether transitive law is obeyed or not.


Sample Input-1:
---------------
a==b c==d c!=e e==f

Sample Output-1:
----------------
true

Sample Input-2:
---------------
a==b b!=c c==a

Sample Output-2:
----------------
false

Explanation:
------------
{a, b} form one equivalence group.
{c} is declared equal to {a} (c == a), which means {a, b, c} should be equivalent.
However, b != c contradicts b == a and c == a.

Sample Input-3:
---------------
a==b b==c c!=d d!=e f==g g!=d

Sample Output-3:
----------------
true
*/
import java.util.*;
class Solution{
    static class DSU{
        int parent[];
        public DSU(int n){
        parent =new int[n];
        for(int i=0;i<n;i++){
            parent[i]=i;
        }
    }
        public int find(int x){
            if(parent[x]!=x){
                parent[x]=find(parent[x]);
            }
            return parent[x];
        }
        public boolean union(int a,int b){
            int roota=find(a);
            int rootb=find(b);
            if(roota==rootb) return false;
            if(roota<rootb){
                parent[rootb]=roota;
            }
            else {
                parent[roota]=rootb;
            }
            return true;
        }
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String s[]=sc.nextLine().split(" ");
        DSU dsu = new DSU(26);
        for (String str:s) {
            if(str.charAt(1)=='='){
                int c1=str.charAt(0)-'a';
                int c2=str.charAt(3)-'a';
                dsu.union(c1,c2);
            }
        }
        for(String str: s){
            int c1=str.charAt(0)-'a';
            int c2=str.charAt(3)-'a';
            if(str.charAt(1)=='='){
                if(dsu.find(c1)!=dsu.find(c2)){
                    System.out.println(false);
                    return ;
                }
            }
            else if(str.charAt(1)=='!'){
                if(dsu.find(c1)==dsu.find(c2)){
                    System.out.println(false);
                    return ;
                }
            }
        }
       System.out.println(true);
    }
}
