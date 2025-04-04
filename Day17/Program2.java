/*program-2
You are given two words W1 and W2.
You need find all the mapping of W2 in W1, and 
return all the statrting indices of the mappings.

The mapping of the words w2 and w1 is as follows:
	- A shuffled word contains all the characters as original word.
	The length of the words and occurrence count of each character are same.
	- find shuffled word of W2 as a substring in W1, and 
	return the starting index of substring.


Input Format:
-------------
Single line space separated strings, two words.

Output Format:
--------------
Print the list of integers, indices.


Sample Input-1:
---------------
abcabcabc abc
 
Sample Output-1:
----------------
[0, 1, 2, 3, 4, 5, 6]



Sample Input-2:
---------------
bacacbacdcab cab

Sample Output-2:
----------------
[0, 3, 4, 5, 9]

*/
import java.util.*;
public class Solution {
    public static boolean isPermutation(String s, List<Character> l) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map1.put(ch, map1.getOrDefault(ch, 0) + 1);
        }
        for (char ch : l) {
            map2.put(ch, map2.getOrDefault(ch, 0) + 1);
        }
        return map1.equals(map2);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s1 = sc.next();  
        String s2 = sc.next();  
        List<Character> l = new ArrayList<>();
        for (int i = 0; i < s2.length(); i++) {
            l.add(s2.charAt(i));
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i <= s1.length() - s2.length(); i++) {
            String sub = s1.substring(i, i + s2.length());
            if (isPermutation(sub, l)) {
                res.add(i);
            }
        }
        System.out.println(res);
    }
}
