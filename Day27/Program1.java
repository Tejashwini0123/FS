/*
Ganesh has a habit of writing the words in backward order,
and writes the sentence without spaces.
 
Ganesh is given a sentence S (without spaces) and an integer C
His way of backward writing the sentence is as follows:
   - Break the sentence into 2C length words from begining to end.
   - Write the first C letters in backward direction of every 2C length word.
   - if there is a leftover word at the end of S with lenth lessthan 2C, then 
   - if length is lessthan C, write all the letters in backward direction.
   - if length is greater than or equal to C, write the first C letters
          in backward direction and keep the rest as it is.
           
 And return the sentence S after writing is completed.
 
Example of backward writing: 
Given sentence, S= "hellokmit" and C=2
Break the sentence into words: hell, okmi, t
Now apply backward writing on each word: ehll, komi, t
So, the final sentence is "ehllkomit"
 
 
Input Format:
-------------
Space separated string and integer, the word and C value
 
Output Format:
--------------
Print a string as result
 
 
Sample Input-1:
---------------
abcdefghijk 3

Sample Output-1:
----------------
cbadefihgjk
 

Sample Input-2:
---------------
appropriate 4
 
Sample Output-2:
----------------
rppaoprieta

*/
/*
import java.util.*;
class Solution{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        int c=sc.nextInt();
        StringBuilder res=new StringBuilder();
        int i=0;
        while(i<s.length()-((2*c))){
            System.out.println(i);
            StringBuilder sub=new StringBuilder(s.substring(i,(i+(2*c))));
            StringBuilder sub1=new StringBuilder(sub.subSequence(0, c));
            System.out.println(sub+" "+sub1);
            String sub2=(sub1.reverse())+(sub.substring(c,sub.length()));

            res.append(sub2);
            i=i+(2*c);
        }
        if(s.length()-i<c){
            StringBuilder sub=new StringBuilder(s.substring(i,s.length()));
            res.append(sub.reverse());
        }
        else{
            StringBuilder sub=new StringBuilder(s.substring(i,i+c));
            res.append(sub.reverse());
            res.append(s.substring(i+c,s.length()));
        }
        System.out.println(res);
    }
}
