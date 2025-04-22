
/* 
Naresh is working on expression of words.
If you give him an expression like, [p,q,r]s[t,u],
Naresh will form the words like as follows : [pst, psu, qst,qsu, rst, rsu]
Another example, [a,b]c[d,e] will be converted as: [acd, ace, bcd, bce].

Naresh will be given an expression as a string EXP, like the above format.
He needs to return all words that can be formed in like mentioned above, 
Can you help Naresh to convert iven expression into a list of words, in lexicographical order.

NOTE: 
Expression consist of lowercase alphabets, comma, and square brackets only.

Input Format:
-------------
A string EXP, expression.

Output Format:
--------------
Print list of words, formed from the expression.


Sample Input-1:
---------------
[b]c[e,g]k

Sample Output-1:
----------------
[bcek, bcgk]


Sample Input-2:
---------------
[a,b][c,d]

Sample Output-2:
----------------
[ac, ad, bc, bd]


Sample Input-3:
---------------
[xyz]a[b,c]

Sample Output-3:
----------------
[xyzab, xyzac]

*/
import java.util.*;
class Solution {
    public static void backtrack(List<List<String>> list, int depth, String current, List<String> result) {
        if (depth == list.size()) {
            result.add(current);
            return;
        }
        for (String s : list.get(depth)) {
            backtrack(list, depth + 1, current + s, result);
        }
    }
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int i = 0;
        List<List<String>> list = new ArrayList<>();

        while (i < s.length()) {
            if (s.charAt(i) == '[') {
                i++;
                StringBuilder temp = new StringBuilder();
                List<String> l = new ArrayList<>();
                while (i < s.length() && s.charAt(i) != ']') {
                    if (s.charAt(i) == ',') {
                        l.add(temp.toString());
                        temp=new StringBuilder(); 
                    } else {
                        temp.append(s.charAt(i));
                    }
                    i++;
                }
                if (temp.length() > 0) {
                    l.add(temp.toString());
                }
                list.add(l);
            } else {
                StringBuilder temp = new StringBuilder();
                while (i < s.length() && s.charAt(i) != '[') {
                    temp.append(s.charAt(i));
                    i++;
                }
                List<String> l = new ArrayList<>();
                if (temp.length() > 0) {
                    l.add(temp.toString());
                    list.add(l);
                }
                continue;
            }
            i++;
        }
        System.out.println(list);
        List<String> result = new ArrayList<>();
        backtrack(list, 0, "", result);
        System.out.println(result);

    }
}
