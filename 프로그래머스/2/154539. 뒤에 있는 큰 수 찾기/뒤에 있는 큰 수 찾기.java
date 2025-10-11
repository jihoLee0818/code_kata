import java.util.Stack;
import java.util.Arrays;

class Solution {
    public int[] solution(int[] numbers) {
        int n = numbers.length;
        int[] answer = new int[n];
        
        // 1. 결과 배열을 -1로 초기화
        Arrays.fill(answer, -1);
        
        // 아직 뒷 큰 수를 찾지 못한 숫자들의 '인덱스'를 저장할 스택
        Stack<Integer> stack = new Stack<>();

        // 2. 배열을 순회
        for (int i = 0; i < n; i++) {
            int currentNum = numbers[i];
            
            // 3. 스택에 값이 있고, 스택 맨 위 인덱스의 값이 현재 값보다 작으면
            while (!stack.isEmpty() && numbers[stack.peek()] < currentNum) {
                // 스택에서 인덱스를 꺼내고, 해당 위치의 정답을 현재 값으로 설정
                answer[stack.pop()] = currentNum;
            }
            
            // 4. 현재 인덱스를 스택에 추가
            stack.push(i);
        }
        
        return answer;
    }
}