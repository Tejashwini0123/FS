import java.util.*;
public class Main{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int[] arr=new int[n];
        for(int i=0;i<n;i++){
            arr[i]=sc.nextInt();
        }
        while(!function(arr)){
            int[] temp=arr.clone();
            for(int i=1;i<n-1;i++){
                if(arr[i]>arr[i-1] && arr[i]>arr[i+1])temp[i]=arr[i]-1;
                if(arr[i]<arr[i-1] && arr[i]<arr[i+1])temp[i]=arr[i]+1;
            }
            arr=temp;
        }
        System.out.print(Arrays.toString(arr));
    }
    public static boolean function(int[] arr){
        for(int i=1;i<arr.length-1;i++){
            if(arr[i]>arr[i-1] && arr[i]>arr[i+1]){
                return false;
            }
            if(arr[i]<arr[i-1] && arr[i]<arr[i+1]){
                return false;
            }
        }
        return true;
    }
}
