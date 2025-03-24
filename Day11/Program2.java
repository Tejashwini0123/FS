/*
In a School, the math teacher has given a number N between 1000 to 9999.
He asked the students to create two numbers N1 and N2 using the digits of N,
where each digit must be used only once and N1 and N2 must be between 0 to 999 
(leading 0's are allowed). The sum of  N1 and N2 should be minimum.

Your task is to help the students to find the mimimum sum of N1 and N2 using N.

Input Format:
-------------
An integer N.

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
7512

Sample Output-1:
----------------
42

Explanation:
------------
The possible numbers of N1 and N2 are,
(125,7), (12,57), (157,2), (17,25) or (15,27),..etc.
Among these 17+25 or 15+27 will give the minimum sum.


Sample Input-2:
---------------
5004

Sample Output-2:
----------------
9

*/
import java.util.*;
public class Test {
 public static int minSum(int N) {
        int[] digits = new int[4];
        for (int i = 3; i >= 0; i--) {
            digits[i] = N % 10;
            N /= 10;
}
        Arrays.sort(digits);
        int N1 = 0, N2 = 0;
        for (int i = 0; i < 4; i++) {
            if (i % 2 == 0) {
                N1 = N1 * 10 + digits[i]; 
} else{
    N2 = N2 * 10 + digits[i];  
   }
}       return N1 + N2;
}
   public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n=sc.nextInt();
        System.out.println(minSum(n));
}
}
