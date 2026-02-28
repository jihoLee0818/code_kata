import java.util.ArrayList;

class Solution {
    public int[] solution(int n) {
        // 결과를 담을 ArrayList 생성
        ArrayList<Integer> list = new ArrayList<>();
        
        // 초기값 n을 먼저 리스트에 추가
        list.add(n);
        
        // n이 1이 될 때까지 반복
        while (n != 1) {
            if (n % 2 == 0) {
                // 짝수인 경우
                n /= 2;
            } else {
                // 홀수인 경우
                n = 3 * n + 1;
            }
            
            // 계산된 n을 리스트에 추가
            list.add(n);
        }
        
        // ArrayList를 int[] 배열로 변환
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}