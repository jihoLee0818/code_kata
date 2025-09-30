import java.util.HashSet;
import java.util.Set;

class Solution {
    public int solution(int[] elements) {
        // 3. 중복을 제거하며 합계를 저장할 Set
        Set<Integer> set = new HashSet<>();
        int n = elements.length;

        // 1. 배열을 2배로 늘려 원형 수열 처리
        int[] extended = new int[n * 2];
        for (int i = 0; i < n; i++) {
            extended[i] = elements[i];
            extended[i + n] = elements[i];
        }

        // 2. 모든 연속 부분 수열의 합 구하기
        // i: 부분 수열의 시작점
        for (int i = 0; i < n; i++) {
            int sum = 0;
            // j: 부분 수열의 길이 (1부터 n까지)
            for (int j = 0; j < n; j++) {
                // extended[i+j]를 더함으로써 시작점 i에서 길이가 j+1인 수열의 합을 만듦
                sum += extended[i + j];
                set.add(sum);
            }
        }

        // 4. Set의 크기(유일한 합계의 개수)를 반환
        return set.size();
    }
}