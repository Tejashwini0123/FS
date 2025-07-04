/*
Given a integer value N, indicates number of bits in a binary number.

Your task is to design a Binary Code System, where two consecutive 
values in BCS having N bits, must have one bit difference only. 
For example refer the sample testcases.

Find and print the integer values of BCS, starting from 0.


Input Format:
-------------
A integer N, number of bits in BCS

Output Format:
--------------
Print the list of integer values, in BCS form. 


Sample Input-1:
---------------
2

Sample Output-1:
----------------
[0, 1, 3, 2]

Explanation:
------------
00 - 0
01 - 1
11 - 3
10 - 2

Sample Input-2:
---------------
3

Sample Output-2:
----------------
[0, 1, 3, 2, 6, 7, 5, 4]

Explanation:
------------
000 - 0
001 - 1
011 - 3
010 - 2
110 - 6
111 - 7
101 - 5
100 - 4

*/
import java.util.*;
class Solution {
    public static void backtrack(String s, List<Integer> res, Set<Integer> visited, int n) {
        if (res.size() == (1 << n)) return; 
        for (int i = n - 1; i >= 0; i--) {
            char[] arr = s.toCharArray();
            arr[i] = arr[i] == '0' ? '1' : '0';
            String flipped = new String(arr);
            int val = Integer.parseInt(flipped, 2);
            if (!visited.contains(val)) {
                visited.add(val);
                res.add(val);
                backtrack(flipped, res, visited, n);
                return;
            }
        }
    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String s = "0".repeat(n);
        List<Integer> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        res.add(0);
        visited.add(0);
        backtrack(s, res, visited, n);
        System.out.println(res);
    }
}
