
    /*Given two IP addresses IP1 and IP2, and a subnet mask, your task is to check 
whether IP-1 and IP-2 belongs to same host range or not.

Input Format:
---------------
Two space separated strings, IP1 and IP2.
An integer, CIDR (subnet mask).

Output Format:
---------------
A boolean result.


Sample Input-1:
-----------------
192.168.1.10 192.168.1.20
24

Sample Output-1:
------------------
true


Sample Input-2:
-----------------
192.0.2.1 192.0.3.253
24

Sample Output-2:
------------------
false
*/
import java.util.*;
class Solution {
    public static int ipToInt(String ip) {
        String[] parts = ip.split("\\.");
        int result = 0;
        for (String part : parts) {
            result = (result << 8) | Integer.parseInt(part);
        }
        return result;
    }
    public static String intToIp(int num) {
        return String.format("%d.%d.%d.%d",
                (num >> 24) & 0xFF,
                (num >> 16) & 0xFF,
                (num >> 8) & 0xFF,
                num & 0xFF);
    }
    public static int subnetMask(int cidr) {
        return 0xFFFFFFFF << (32 - cidr);
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String ip1 = sc.next();
        String ip2=sc.next();
        int cidr = sc.nextInt();
        int ipInt1 = ipToInt(ip1);
        int ipInt2 = ipToInt(ip2);
        int mask = subnetMask(cidr);
        int network1 = ipInt1 & mask;
        int network2 = ipInt2 & mask;
        int broadcast1 = network1 | ~mask;
        int broadcast2 = network1 | ~mask;
        broadcast1 = broadcast1 & 0xFFFFFFFF;
        broadcast2 = broadcast2 & 0xFFFFFFFF;
        
        if(network1==network2 && broadcast1==broadcast2)
        System.out.println(true);
        else
        System.out.println(false);
    }
}
