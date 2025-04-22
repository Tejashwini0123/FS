import java.util.*;
class Test{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String s=sc.next();
        System.out.println(wins(s));
        
    }
     public static boolean wins(String s){
        for(int i=0;i<s.length()-1;i++){
            if(s.charAt(i)=='A' && s.charAt(i+1)=='A'){
            String next=s.substring(0, i) +"BB" +s.substring(i+2);
                if(!wins(next)){
                    return true;
                    }
                    }
        }
        return false;
}
    
}
