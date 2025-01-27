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
