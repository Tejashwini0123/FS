/*
You are a student organizer, and you are given n students. Each student has two values:
    Student Name: A unique identifier for the student.
    Score: The score achieved by the student.

Your goal is to organize these students in the order of their scores (highest score first). 
If two students have the same score, order them alphabetically by their names. 

Write a program to simulate how the students are organized using a priority queue.

Input Format:
-------------
Line-1: An integer, N
Next N lines: space sepaarted string and integer, name and score of each student.

Output Format:
--------------
Organized students data as shown in samples.


Sample Input-1:
---------------
5
Alice 85
Bob 92
Charlie 78
Diana 95
Eve 88

Sample Output-1:
----------------
(Diana, 95)
(Bob, 92)
(Eve, 88)
(Alice, 85)
(Charlie, 78)


Sample Input-2:
---------------
4
Bob 90
Charlie 85
Diana 92
Alice 85

Sample Output-2:
----------------
(Diana, 92)
(Bob, 90)
(Alice, 85)
(Charlie, 85)

*/

import java.util.*;

class StudentOrganizer{
    static class Org{
    String name;
    int score;
    Org(String name, int score){
        this.name=name;
        this.score=score;
    }
    public String toString(){
        return "(" + name +", "+score+")";
    }
}
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        PriorityQueue<Org> pq=new PriorityQueue<>((st1,st2)->{
            int val=Integer.compare(st2.score,st1.score);
            if(val==0){
                return st1.name.compareTo(st2.name);
            }
            return val;
        });
         sc.nextLine();
        for(int i=0;i<n;i++){
            String s[]=sc.nextLine().split(" ");
            String k=s[0];
            int l=Integer.parseInt(s[1]);
            pq.add(new Org(k,l));
        }
        while(!pq.isEmpty()){
            System.out.println(pq.poll());
        }
    }
}
