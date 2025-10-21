import java.util.Stack;

class Solution {
    public String solution(String number, int k) {
        // 1. 스택 준비
        Stack<Character> stack = new Stack<>();
        
        // 2. 숫자 순회
        for (char digit : number.toCharArray()) {
            
            // 3. 비교 및 제거 (핵심)
            while (!stack.isEmpty() && k > 0 && stack.peek() < digit) {
                stack.pop();
                k--;
            }
            
            // 4. 스택에 추가
            stack.push(digit);
        }
        
        // 5. 예외 처리 (k가 남은 경우, 예: "987")
        while (k > 0) {
            stack.pop();
            k--;
        }

        // 6. 결과 조합
        StringBuilder answer = new StringBuilder();
        for (char c : stack) {
            answer.append(c);
        }
        
        return answer.toString();
    }
}