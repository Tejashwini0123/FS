/*There are N people in a private party. Initially all are strangers to each other,
and the people are identified by unique ID from 0 to N-1.

In the party, whenever two persons (person-A and person-B) become friends, they 
took a photo. Each of the photo has some information, photos[i]=[T-i, P-j,P-k],
here T-i indicates time of the photo taken, P-j person with ID 'j', and 
P-k indicates person with ID 'k'.

Friendship is symmetric[i.e., If P-j is friend of P-k, then P-k is a friend of P-j].
Additionally, if person-A is "a friend of person-B OR a friend of someone who is 
friend of person-B", then person-A is friend of person-B.

You are given L photos information, Your task is to find the earliest time 
for which every person became friend with every other person in the party.
If there is no such earliest time, return -1.


Input Format:
-------------
Line-1: Two space separated integers, N and L.
Next L lines: Three space separated integers, log[i], 0<=i<L.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
6 8
5 0 1
7 3 4
12 2 3
21 1 5
34 2 4
37 0 3
42 1 2
93 4 5

Sample Output-1:
----------------
37


Sample Input-2:
---------------
7 6
2 0 3
5 1 5
8 2 5
7 3 6
9 4 6
6 4 5

Sample Output-2:
----------------
9
*/
import java.util.*;
class Solution {
    static class DSU {
        int[] parent, rank;
        int count;
        public DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            count = n;
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }
        public boolean union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX == rootY) return false;
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } 
            else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } 
            else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            count--;
            return true;
        }
        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();  
        int l = sc.nextInt(); 
        int[][] logs = new int[l][3];
        for (int i = 0; i < l; i++) {
            logs[i][0] = sc.nextInt(); 
            logs[i][1] = sc.nextInt();
            logs[i][2] = sc.nextInt(); 
        }
        Arrays.sort(logs, Comparator.comparingInt(a -> a[0]));
        DSU dsu = new DSU(n);
        for (int[] log : logs) {
            dsu.union(log[1], log[2]);
            if (dsu.getCount() == 1) {
                System.out.println(log[0]);
                return;
            }
        }
        System.out.println(-1);
    }
}
