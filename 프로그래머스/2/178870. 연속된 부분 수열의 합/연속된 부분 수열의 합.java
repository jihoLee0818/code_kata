import java.util.*;

class Solution {
    public int[] solution(int[] sequence, int k) {
        int n = sequence.length;
        
        int start = 0; // 윈도우의 시작 포인터
        int currentSum = 0; // 윈도우의 현재 합계
        
        int minLength = Integer.MAX_VALUE; // 합이 k인 부분 수열의 최소 길이
        int[] answer = new int[2];        // 정답 [시작 인덱스, 끝 인덱스]

        // 1. end 포인터를 0부터 n-1까지 이동 (윈도우 확장)
        for (int end = 0; end < n; end++) {
            currentSum += sequence[end];
            
            // 2. 윈도우의 합이 k보다 크면, k보다 작거나 같아질 때까지 start 이동 (윈도우 축소)
            while (currentSum > k && start <= end) {
                currentSum -= sequence[start];
                start++;
            }
            
            // 3. 윈도우의 합이 k와 같은지 확인
            if (currentSum == k) {
                int currentLength = end - start + 1;
                
                // 4. 현재 찾은 수열이 기존의 최소 길이보다 짧으면 갱신
                if (currentLength < minLength) {
                    minLength = currentLength;
                    answer[0] = start;
                    answer[1] = end;
                }
            }
        }
        
        return answer;
    }
}