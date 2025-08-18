import java.util.*;
class Solution {
    public int solution(String t, String p) {
        int answer = 0;
        long subNum1 = Long.parseLong(p);
        
        for(int i = 0; i <= t.length()-p.length(); i++){
            String sub = t.substring(i, i+p.length());
            long subNum2 = Long.parseLong(sub);
            if(subNum1 >= subNum2){
                answer++;
            }
        }
        return answer;
    }
}