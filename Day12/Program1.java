/*Mr. Kejriwal purchased a digital clock, it shows the time in "hh:mm" 24 hr format.
Due to technical issue, in the place of some digits of displays '#' symbol.

As Mr Kejriwal is an IIT student also, he got an idea to find the number of 
valid times by replacing '#' with valid digits between 0-9.

You are given the time as a string T.
Your task is to help Mr Kejriwal to find the number of possible valid times.

NOTE:
-----
The valid time is in the range of 00:00 to 23:59.


Input Format:
-------------
A string T, the time in the (24-hr) format as "hh:mm" 

Output Format:
--------------
Print an integer result.


Sample Input-1:
---------------
#6:00

Sample Output-1:
----------------
2

Explanation:
------------
The valid times after replacing # with 0 or 1, are "06:00", "16:00". 


Sample Input-2:
---------------
0#:0#

Sample Output-2:
----------------
100

Explanation:
------------
To make the given time valid, replace 1st # with 0-9 digits and 2nd with the same.
So, totally we have 100 ways.
*/

import java.util.*;
class Solution{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        String s=sc.next();
        String k[]=s.split(":");
        String s1=k[0];
        String s2=k[1];
        int c=0,l=0;
        if(s1.charAt(0)=='#' && s1.charAt(1)=='#') c=24;
        else if(s1.charAt(0)=='#' && s1.charAt(1)<'4') c=3;
        else if(s1.charAt(0)=='#' && s1.charAt(1)>='4') c=2;
        else if(s1.charAt(0)=='0' && s1.charAt(1)=='#' ) c=10;
        else if(s1.charAt(0)=='1' && s1.charAt(1)=='#') c=10;
        else if(s1.charAt(0)=='2' && s1.charAt(1)=='#') c=4;
        if(s2.charAt(0)=='#' && s2.charAt(1)=='#')l=60;
        else if(s2.charAt(0)=='#' && s2.charAt(1)!='#') l=6;
        else if(s2.charAt(1)=='#' && s2.charAt(0)!='#') l=10;
        if(c==0 || l==0)
        System.out.println(c+l);
        else
        System.out.println(c*l);
    }
}
