import java.util.*;

class Solution {
    public int solution(int N, int number) {
        // N과 number가 같으면 연산 없이 1개로 표현 가능
        if (N == number) {
            return 1;
        }

        // DP를 위한 리스트 (Set들의 모음)
        // dp.get(i) : N을 i번 사용해서 만들 수 있는 숫자들의 집합
        List<Set<Integer>> dp = new ArrayList<>();

        // 0번 인덱스는 사용하지 않고, 1~8번 인덱스 사용
        for (int i = 0; i <= 8; i++) {
            dp.add(new HashSet<>());
        }

        // 1번부터 8번까지 N 사용 횟수를 늘려가며 탐색
        for (int k = 1; k <= 8; k++) {
            
            // 1. N을 k번 이어 붙인 숫자 추가 (예: 5, 55, 555...)
            int repeatedN = Integer.parseInt(String.valueOf(N).repeat(k));
            dp.get(k).add(repeatedN);

            // 2. 이전 단계의 집합들을 조합하여 사칙연산 수행
            // S[k] = S[i] op S[k-i]  (1 <= i < k)
            for (int i = 1; i < k; i++) {
                Set<Integer> set1 = dp.get(i);
                Set<Integer> set2 = dp.get(k - i);

                for (int num1 : set1) {
                    for (int num2 : set2) {
                        dp.get(k).add(num1 + num2);
                        dp.get(k).add(num1 - num2);
                        dp.get(k).add(num1 * num2);
                        if (num2 != 0) {
                            dp.get(k).add(num1 / num2);
                        }
                    }
                }
            }

            // 3. 목표 숫자가 현재 집합에 있는지 확인
            if (dp.get(k).contains(number)) {
                return k;
            }
        }

        // 8번을 써도 못 만들면 -1 반환
        return -1;
    }
}