;import java.util.*;

class Solution {
    public String solution(int[] food) {
        String answer = "";
        
        for(int i=1; i < food.length; i++){
        if(food[i] / 2 >= 1){
        for(int j=1; j <= (food[i]/2); j++){
        answer += i;
        }
        }
        }
        
        StringBuilder sb = new StringBuilder(answer);
        String answer2 = sb.reverse().toString();
        
        if(food[0] == 1){
        answer += 0;
        }
        return answer + answer2;
    }
}