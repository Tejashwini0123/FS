/*
You can call two strings X and Y are friendly strings, 
if you can swap two letters in X, so the result is equal to Y.

The condition to swap the letters is as follows:
	Swapping letters is defined as taking two indices i and j (0-indexed) 
	such that i != j and swapping the characters at A[i] and A[j] . 
	For example, swapping at indices 0 and 2 in "abcd" results in "cbad" .

You are given two strings X and Y of lowercase letters, 
return true if X and Y are friendly strings, otherwise return false.

Input Format:
-------------
Two space separated Strings X and Y

Output Format:
--------------
Print a boolean value


Sample Input-1:
---------------
abcde bacde

Sample Output-1:
----------------
true

Sample Input-2:
---------------
abcde abcde

Sample Output-2:
----------------
false

*/
import java.util.*;
class Solution{
    public static String swap(String s,int n,int k){
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(i==n){
                sb.append(s.charAt(k));
            }
            else if(i==k){
                sb.append(s.charAt(n));
            }
            else{
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    public static boolean similar(String s){
        for(int i=1;i<s.length();i++){
            if(s.charAt(i-1)!=s.charAt(i)){
                return false;
            }
        }
        return true;
    }
    public static boolean hasDuplicate(String s){
        Set<Character> set = new HashSet<>();
        for (char ch : s.toCharArray()) {
            if (set.contains(ch)) return true;
            set.add(ch);
        }
        return false;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String s1=sc.next();
        String s2=sc.next();
        String s="";
        if(s1.length()!=s2.length()){
            System.out.println(false);
            return;
        }
        if(similar(s1)){
            if(s1.equals(s2)){ System.out.println(true);
            return;
            }
        }
        if (s1.equals(s2)){
            if (hasDuplicate(s1)) {
                System.out.println(true);
            } else {
                System.out.println(false);
            }
            return;
        }
        int i=0,j=0,first=-1,sec=-1;
        while(i<s1.length()){
            if (s1.charAt(i) != s2.charAt(i)) {
                if (first == -1) {
                    first = i;
                } 
                else if (sec == -1) {
                    sec = i;
                } 
                else {
                    System.out.println(false);
                    return;
                }
            }
            i++;
        }
        if(first!=-1 && sec!=-1){
            s=swap(s1,first,sec);
            if(s.equals(s2)) System.out.println(true);
        }
        else System.out.println(false);
    }
}
