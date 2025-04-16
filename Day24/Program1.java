/*
Bablu is working in a construction field.
He has N number of building blocks, where the height and width of all the blocks are same.
And the length of each block is given in an array, blocks[].

Bablu is planned to build a wall in the form of a square.
The rules to cunstruct the wall are as follows:
	- He should use all the building blocks.
	- He should not break any building block, but you can attach them with other.
	- Each building-block must be used only once.
	
Your task is to check whether Bablu can cunstruct the wall as a square
with the given rules or not. If possible, print true. Otherwise, print false.

Input Format:
-------------
Line-1: An integer N, number of BuildingBlocks.
Line-2: N space separated integers, length of each block.

Output Format:
--------------
Print a boolean value.


Sample Input-1:
---------------
6
1 2 6 4 5 6

Sample Output-1:
----------------
true


Sample Input-2:
---------------
6
5 3 2 5 5 6

Sample Output-2:
----------------
false
*/
import java.util.*;
class Solution{
    public static boolean backtrack(int a[],boolean visited[],int index,int side,int sides_built,int target){
        if(sides_built==4){
            return true;
        }
        for (int i = index; i < a.length; i++){
        if (!visited[i]) {
            if (side + a[i] > target) continue;
            visited[i] = true;
            if (side + a[i] == target) {
                if (backtrack(a, visited, 0, 0, sides_built + 1, target)) return true;
            } 
            else {
                if (backtrack(a, visited, i + 1, side + a[i], sides_built, target)) return true;
            }
            visited[i] = false;
        }
    }
        return false;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int a[]=new int[n];
        int sum=0;
        for(int i=0;i<n;i++){
            a[i]=sc.nextInt();
            sum+=a[i];
        }
        if(sum%4!=0 || n<4){
            System.out.println(false);
            return;
        }
        int target=sum/4;
        System.out.println(backtrack(a,new boolean[n],0,0,0,target));
    }
}
