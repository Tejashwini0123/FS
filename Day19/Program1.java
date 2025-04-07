/*

"Emphatic Pronunciation" of a given word is where we take the word and
replicate some of the letter to emphasize their impact.

Instead of saying 'oh my god', someone may say "ohhh myyy goddd", 
We define emphatic pronunciation of a word, which is derived by replicating 
a group (or single) of letters in the original word. 

So that the replicated group is atleast 3 characters or more and 
greater than or equal to size of original group. 
For example Good -> Goood is an emphatic pronunciation,
but Goodd is not because in Goodd the 'd' are only occuring twice consecutively.
 
In the question you are given the "Emphatic pronunciation" word, 
you have to findout how many words can legal result in the 
"emphatic pronunciation" word.

Input Format:
-------------
Line-1 -> A String contains a single word, Emphatic Pronunciation word
Line-2 -> Space seperated word/s

Output Format:
--------------
Print an integer as your result


Sample Input-1:
---------------
goood
good goodd

Sample Output-1:
----------------
1

Sample Input-2:
---------------
heeelllooo
hello hi helo

Sample Output-2:
----------------
2
*/
import java.util.*;
class Solution{
    public static boolean check(String s,String emp){
        int i=0,j=0,n=s.length(),m=emp.length(),c1=0,c2=0;
        while(i<n && j<m){
            if(s.charAt(i)!=emp.charAt(j)) return false;
            char ch=s.charAt(i);
            c1=0;
            c2=0;
            while(i<n && s.charAt(i)==ch){
                i++;
                c1++;
            }
            while(j<m && emp.charAt(j)==ch){
                j++;
                c2++;
            }
            if(c1>c2) return false;
            if(c1<c2 && c2<3) return false;
        }
        return i==n && j==m;
}
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String emp=sc.nextLine();
        int c=0;
        String s[]=sc.nextLine().split(" ");
        for(String word: s){
            if(check(word,emp)){
                c++;
            }
        }
        System.out.println(c);
    }
} 
