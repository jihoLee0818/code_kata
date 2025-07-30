import java.util.*;

class Solution {
    public String solution(String[] seoul) {
        String answer = "";
        List<String> list = new ArrayList<>();
        
        for(String s : seoul){
            list.add(s);
        }
        
        int location = list.indexOf("Kim");
        answer = "김서방은 " + location +"에 있다";
        
        return answer;
    }
}