import java.util.ArrayList;

class Solution {
    public int[] solution(int l, int r) {
        ArrayList<Integer> list = new ArrayList<>();
        
        // 탐색 시작점을 5의 배수로 맞춥니다. (효율성 증가)
        int start = l;
        while (start % 5 != 0) {
            start++;
        }
        
        // start부터 r까지 5씩 증가하며 검사합니다.
        for (int i = start; i <= r; i += 5) {
            if (isOnlyZeroAndFive(i)) {
                list.add(i);
            }
        }
        
        // 조건을 만족하는 숫자가 없다면 -1이 담긴 배열을 반환합니다.
        if (list.isEmpty()) {
            return new int[]{-1};
        }
        
        // ArrayList를 int[] 배열로 변환하여 반환합니다.
        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
    
    // 숫자가 0과 5로만 이루어져 있는지 확인하는 별도의 메서드
    private boolean isOnlyZeroAndFive(int num) {
        while (num > 0) {
            int digit = num % 10; // 일의 자리 숫자 추출
            // 0도 아니고 5도 아니라면 즉시 false 반환
            if (digit != 0 && digit != 5) {
                return false;
            }
            num /= 10; // 일의 자리 숫자를 잘라냄
        }
        return true;
    }
}