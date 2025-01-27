/*
You are given an integer array nums and two integers l and r. Your task is to 
find the minimum positive energy produced by a sequence of operations. 
Each operation corresponds to selecting a contiguous subarray of nums 
whose length is between l and r (inclusive).

The energy of a sequence is defined as the product of all the numbers in 
the subarray. You need to find the sequence with the smallest positive energy.

If no such sequence exists, return -1.

Input Format:
---------------
Line-1: Three space separated integers, N, L and R.
Line-2: N space separated integers, array[].

Output Format:
-----------------
An integer result, smallest positive energy.

Sample Input-1:
-----------------
4 2 3
2 -1 3 4

Sample Output-1:
-------------------
12
  */
import java.util.*;
public class Test{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        List<Integer>ll=new ArrayList<>();

        int N=sc.nextInt();
        int l=sc.nextInt();
        int r=sc.nextInt();
        int a[]=new int[N];
        for(int j=0;j<N;j++)
            a[j]=sc.nextInt();
        int ans=Integer.MAX_VALUE;
        for(int k=0;k<(r-l+1);k++ ){
            int prod=1;
            for(int i=0;i<N;i++){
                prod*=a[i];
                if(i>=(l-1)){
                    if(prod >0){
                        ans=Math.min(ans,prod);
                    }
                
                if(a[i-l+1]==0) continue;
                prod=prod/a[i-l+1];
            }
        }
            l++;
        }
        System.out.println(ans==Integer.MAX_VALUE?-1:ans);
    }
}
