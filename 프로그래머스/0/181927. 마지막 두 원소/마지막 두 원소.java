import java.util.Arrays;

class Solution {
    public int[] solution(int[] num_list) {
        int len = num_list.length;
        
        // 1. 기존 배열보다 크기가 1 더 큰 새로운 배열 생성 및 복사
        int[] answer = Arrays.copyOf(num_list, len + 1);
        
        // 2. 마지막 원소와 그 전 원소 가져오기
        int last = num_list[len - 1];
        int beforeLast = num_list[len - 2];
        
        // 3. 조건에 따라 새로운 값 계산하여 배열 마지막에 추가
        if (last > beforeLast) {
            answer[len] = last - beforeLast;
        } else {
            answer[len] = last * 2;
        }
        
        return answer;
    }
}