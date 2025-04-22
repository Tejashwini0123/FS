/*
Given two strings S1 and S2, find if S2 can match S1 or not.

A match that is both one-to-one (an injection) and onto (a surjection), 
i.e. a function which relates each letter in string S1 to a separate and 
distinct non-empty substring in S2, where each non-empty substring in S2
also has a corresponding letter in S1.

Return true,if S2 can match S1.
Otherwise false.

Input Format:
-------------
Line-1 -> Two strings S1 and S2

Output Format:
--------------
Print a boolean value as result.


Sample Input-1:
---------------
abab kmitngitkmitngit

Sample Output-1:
----------------
true


Sample Input-2:
---------------
aaaa kmjckmjckmjckmjc

Sample Output-2:
----------------
true

Sample Input-3:
---------------
mmnn pqrxyzpqrxyz

Sample Output-3:
----------------
false
*/
import java.util.*;
class Solution{
    public static boolean backtrack(String s1,String s2,HashMap<Character,String>h,int i,int j){
        if(i==s1.length() && j==s2.length()) return true;
        if(i>=s1.length() || j>=s2.length()) return false;
        if(h.containsKey(s1.charAt(i))){
            String m=h.get(s1.charAt(i));
            if (j + m.length() > s2.length() || !s2.substring(j, j + m.length()).equals(m)) {
                return false;
            }
            backtrack(s1,s2,h,i+1,j+m.length());
        }
            for (int k = 1; k + j <= s2.length(); k++) {
                String candidate = s2.substring(j, j + k);
                if (h.containsValue(candidate)) continue;
    
                h.put(s1.charAt(i), candidate);
                if (backtrack(s1, s2, h, i + 1, j + k)) return true; 
                h.remove(s1.charAt(i)); 
        }
        return false;
}
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String s1=sc.next();
        String s2=sc.next();
        HashMap<Character,String> h=new HashMap<>();
        System.out.println(backtrack(s1,s2,h,0,0));
    }
}
