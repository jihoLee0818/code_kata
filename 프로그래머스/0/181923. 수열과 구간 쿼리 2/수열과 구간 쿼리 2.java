class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        // 1. 쿼리의 개수만큼 정답을 저장할 배열을 만듭니다.
        int[] answer = new int[queries.length];
        
        // 2. 모든 쿼리를 하나씩 확인합니다.
        for (int q = 0; q < queries.length; q++) {
            int s = queries[q][0];
            int e = queries[q][1];
            int k = queries[q][2];
            
            // 3. 최소값을 찾기 위해 임시 변수를 아주 큰 값으로 설정합니다.
            int minVal = Integer.MAX_VALUE;
            
            // 4. s번 인덱스부터 e번 인덱스까지 순회합니다.
            for (int i = s; i <= e; i++) {
                // k보다 크면서 동시에 현재까지 찾은 최소값보다 작을 때만 갱신합니다.
                if (arr[i] > k && arr[i] < minVal) {
                    minVal = arr[i];
                }
            }
            
            // 5. 만약 minVal이 처음 설정한 아주 큰 값 그대로라면, 조건에 맞는 원소가 없는 것입니다.
            if (minVal == Integer.MAX_VALUE) {
                answer[q] = -1;
            } else {
                answer[q] = minVal;
            }
        }
        
        return answer;
    }
}