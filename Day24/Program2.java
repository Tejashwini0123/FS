/*
Arjun wants to build a swimming pool, in the backyard of his farm-house.
The backyard has an empty land of size m*n. 
Some part of the backyard is used to build the swimming pool
You will be given the m*n grid values (0's and 1's). 
where 1 indicates swimming pool, and 0 indiactes empty land.

Your task to find the perimeter of the swimming pool.

Note: There is only one swimming pool.

Input Format:
-------------
Line-1: Two integers M and N, size of the backyard.
Next M lines: N space separated integers, either 0 or 1
0- represents empty land and 1- represents the swimming pool 

Output Format:
--------------
Print an integer, the perimeter of the swimming pool


Sample Input-1:
---------------	
4 4
0 1 0 0
1 1 1 0
0 1 0 0
1 1 0 0
 
Sample Output-1:
----------------
16


Sample Input-2:
---------------
1 2
1 0
 
Sample Output-2:
----------------
4
*/
import java.util.*;
class Solution{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int m=sc.nextInt();
        int n=sc.nextInt();
        int s=0;
        int a[][]=new int[m][n];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                a[i][j]=sc.nextInt();
            }
        }
        Queue<int []> qu = new LinkedList<>();
            for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(a[i][j]==1){
                    qu.offer(new int[] {i,j});
                }
            }
        }
        int directions[][]={{0,1},{0,-1},{1,0},{-1,0}};
        while(!qu.isEmpty()){
            int cell[]=qu.poll();
            int x=cell[0];
            int y=cell[1];
            int c=0;
            for(int[] dir : directions ){
                int nx= x+dir[0];
                int ny = y+dir[1];
            if(nx>=0 && nx<m && ny>=0 && ny<n && a[nx][ny]==1){
                c++;
            }
            }
        switch(c){
            case 0:
                s+=4;
                break;
            case 1:
                s+=3;
                break;
            case 2:
                s+=2;
                break;
            case 3:
                s+=1;
                break;
            default:
                s+=0;
            }
        }
        System.out.println(s);
    }

}
