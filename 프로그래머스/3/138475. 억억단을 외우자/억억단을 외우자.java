class Solution {
    public int[] solution(int e, int[] starts) {
        // 1. 약수 개수를 저장할 배열 (인덱스 1 ~ e 사용)
        int[] divisorCounts = new int[e + 1];

        // 2. 약수 개수 구하기 (O(N log N))
        // i를 약수로 가지는 수(배수)들을 찾아 카운트 증가
        for (int i = 1; i <= e; i++) {
            for (int j = i; j <= e; j += i) {
                divisorCounts[j]++;
            }
        }

        // 3. 역방향으로 최댓값 갱신 (DP)
        // maxNumStore[i] : 범위 [i, e] 에서 약수 개수가 가장 많은 숫자
        int[] maxNumStore = new int[e + 1];
        
        // 초기값 설정: e부터 e까지의 범위에서 최댓값은 e 자신
        maxNumStore[e] = e;

        for (int i = e - 1; i >= 1; i--) {
            int prevMaxNum = maxNumStore[i + 1];
            
            // 현재 숫자(i)의 약수 개수가 오른쪽 범위의 챔피언(prevMaxNum)보다 크거나 같으면
            // 현재 숫자가 새로운 챔피언이 됨 (같을 경우 작은 수가 우선이므로 i 선택)
            if (divisorCounts[i] >= divisorCounts[prevMaxNum]) {
                maxNumStore[i] = i;
            } else {
                maxNumStore[i] = prevMaxNum;
            }
        }

        // 4. 쿼리 처리 (O(1))
        int[] answer = new int[starts.length];
        for (int i = 0; i < starts.length; i++) {
            answer[i] = maxNumStore[starts[i]];
        }

        return answer;
    }
}