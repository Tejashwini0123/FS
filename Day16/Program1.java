/*
In the context of linguistic harmony, we define a "harmonious string" as a string where 
every alphabet it contains appears both in uppercase and lowercase forms. For instance, 
a string like "pqQpP" is harmonious because it has both 'P' and 'p' as well as 'Q' and 'q'. 
Conversely, a string like "pqP" is not harmonious as it fails to meet this condition, 
with 'q' present while 'Q' is absent.

Your are given a string S, your task is  to return the longest harmonious substring in S. 
If there are multiple answers meeting this criterion, you should return the one that appears 
earliest in the string. If there is no harmonious substring, you should return an empty string.

Input Format:
-------------------
A string S

Output Format:
-------------------
Prin the longest harmonious string.


Sample Input:
--------------
QcvcCcq

Sample Output:
---------------
cCc


Sample Input:
--------------
pqrs

Sample Output:
--------------
""
*/
import java.util.*;
class Solution{
    public static boolean isharmonic(String s){
        List<Character> list=new ArrayList<>();
        for(int l=0;l<s.length();l++){
            list.add(s.charAt(l));
        }
        for(int k=0;k<s.length();k++){
            Character c=s.charAt(k);
            if(!list.contains(c.toLowerCase(c)) || !list.contains(c.toUpperCase(c))){
                return false;
            }
        }
        return true;
    }
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        String harmonic,maxhar="";
        for(int i=0;i<s.length();i++){
            for(int j=i+1;j<s.length();j++){
                if(isharmonic(s.substring(i,j+1))){
                    harmonic=s.substring(i,j+1);
                    if(harmonic.length()>maxhar.length()){
                        maxhar=harmonic;
                    }
                }
            }
        }
        System.out.print(maxhar);
    }
}
