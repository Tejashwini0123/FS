/*
Neeraj has a task to complete. Given a number N, he has to make reductions 
to make it to 1 with the following rules:
    1. If N is odd then add 1 to it.
    2. If N is even then divide it by 2.
    
Neeraj is given the N in binary format as a string S. Neeraj always 
successful in making N to 1. 
Your task is to help Neeraj to find the number of steps required to make N  to 1.

Input Format:
-------------
A string S, represents the binary equivalent of N.

Output Format:
--------------
Print an integer as number of steps.


Sample Input-1:
---------------
110

Sample Output-1:
----------------
4

Explanation:
-------------
step-1: N=6, even, so 6/2=3
step-2: N=3, odd,  so 3+1=4
step-3: N=4, even, so 4/2=2
step-4: N=2, even, so 2/2=1
Total steps=4


Sample Input-2:
---------------
101

Sample Output-2:
----------------
5

Explanation:
------------
step-1: N=5, odd,  so 5+1=6
step-2: N=6, even, so 6/2=3
step-3: N=3, odd,  so 3+1=4
step-4: N=4, even, so 4/2=2
step-5: N=2, even, so 2/2=1
Total steps=5

*/
import java.util.*;
public class Solution{
    public static int reduceSteps(String S){
        int steps = 0;
        StringBuilder sb = new StringBuilder(S);
        while (!sb.toString().equals("1")){
            int len = sb.length();
            if (sb.charAt(len - 1) == '0'){
                sb.deleteCharAt(len - 1);
            } else {
                sb = addOne(sb);
            }
            steps++;
        }
        return steps;
}
    private static StringBuilder addOne(StringBuilder sb){
        int carry = 1;
        for (int i = sb.length() - 1; i >= 0; i--){
            if (sb.charAt(i) == '0') {
                sb.setCharAt(i, '1');
                carry = 0;
                break;
            } else {
                sb.setCharAt(i, '0');
                }
            }
        if (carry == 1) {
            sb.insert(0, '1');
        }
        return sb;
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String S = sc.next();
        System.out.println(reduceSteps(S));
        }
}
