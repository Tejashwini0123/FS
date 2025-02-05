/*Write a program that takes an IP address and subnet mask (in CIDR notation, 
e.g., 192.168.1.1/24) as input and calculates the network and broadcast addresses.

Input Format:
---------------
A String, IPAddress
An integer, CIDR

Output Format:
---------------
Space separated IP addresses, netwrok IP and broadcast IP.


Sample Input-1:
-----------------
192.168.1.10
24

Sample Output-1:
------------------
192.168.1.0 192.168.1.255


Sample Input-2:
-----------------
192.0.2.1
24

Sample Output-2:
------------------
192.0.2.0 192.0.2.255

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
        String ip = sc.next();
        int cidr = sc.nextInt();
        int ipInt = ipToInt(ip);
        int mask = subnetMask(cidr);
        int network = ipInt & mask;
        int broadcast = network | ~mask;
        broadcast = broadcast & 0xFFFFFFFF;
        System.out.println(intToIp(network));
        System.out.println(intToIp(broadcast));
    }
}
