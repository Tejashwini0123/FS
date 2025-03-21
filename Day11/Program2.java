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
