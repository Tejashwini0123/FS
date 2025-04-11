/*
Rohan has a habit of writing a word in reverse as R and then checks whether R is same as Original word W. If R is not same as W, he can remove only one character from R to make it as W.
Print 'true', if Rohan makes R equals W, Otherwise print 'false'.

Input Format:
-------------
Line-1: A string represents a word.

Output Format:
--------------
return a boolean result.

Sample Input-1:
---------------
bcba

Sample Output-1:
----------------
true

Explanation:
------------
By removing charcter 'a', we will get the word in reverse is also same.

Sample Input-2:
---------------
abcd

Sample Output-2:
----------------
false

Explanation:
-------------
There is no possibility to make the reverse is also same.

*/
import java.util.*;
class Solution{
    public static boolean palindrome(StringBuilder s){
        StringBuilder sb=new StringBuilder(s);
        return (s.toString().equals(sb.reverse().toString()));
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        for(int i=0;i<s.length();i++){
            StringBuilder sb=new StringBuilder(s);
            sb.deleteCharAt(i);
            if(palindrome(sb)){
                System.out.println(true);
                return;
            }
        }
        System.out.println(false);
    }
}
