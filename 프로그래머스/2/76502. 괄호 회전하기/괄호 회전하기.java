import java.util.*;
class Solution {
    public int solution(String s) {
        int answer = 0;
        ArrayDeque<Character> deque = new ArrayDeque<>();
        for(int i = 0 ; i < s.length();i++){
            for(int j = 0 ; j < s.length();j++){
                if(deque.isEmpty()){
                    deque.addLast(s.charAt((i+j)%s.length()));
                } else if(s.charAt((i+j)%s.length()) == ')' && deque.peekLast() == '('){
                    deque.pollLast();
                } else if(s.charAt((i+j)%s.length()) == ']' && deque.peekLast() == '['){
                    deque.pollLast();
                } else if(s.charAt((i+j)%s.length()) == '}' && deque.peekLast() == '{'){
                    deque.pollLast();
                } else{
                    deque.addLast(s.charAt((i+j)%s.length()));
                }    
            }
            if(deque.isEmpty()) answer++;
            else deque.clear();
        }
        return answer;
    }
}