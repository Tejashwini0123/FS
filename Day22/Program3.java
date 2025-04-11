/*
Nehanth, a bubbly kid playing with digits and creating numbers using them.
The kid is creating the number called successive number. 
A number is called successive number, if and only if 
each digit in the number is one less than the next digit.

You are given two integers, start and end, both are inclusive.
Your task to find and print all the successive numbers in the given range (start, end).

Input Format:
-------------
Two space separated integers, start and end

Output Format:
--------------
Print the list of successive numbers in the range(start, end).


Sample Input-1:
---------------
50 150

Sample Output-1:
----------------
[56, 67, 78, 89, 123]


Sample Input-2:
---------------
100 600

Sample Output-2:
----------------
[123, 234, 345, 456, 567]

*/
import java.util.*;
class Solution{
    public static boolean check(int n){
        String s=String.valueOf(n);
        for(int i=0;i<s.length()-1;i++){
            if((s.charAt(i+1)-'0')-(s.charAt(i)-'0')!=1){
                return false;
            }
        }
        return true;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int start=sc.nextInt();
        int end=sc.nextInt();
        List<Integer> res=new ArrayList<>();
        while(start<=end){
            if(check(start)){
                res.add(start);
            }
            start++;
        }
        System.out.println(res);
    }
}
