class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        
        // 1. 모든 쿼리를 순서대로 확인합니다.
        for (int[] query : queries) {
            int s = query[0];
            int e = query[1];
            int k = query[2];
            
            // 2. s번 인덱스부터 e번 인덱스까지 반복합니다.
            for (int i = s; i <= e; i++) {
                
                // 3. 인덱스 i가 k의 배수인지 확인합니다.
                if (i % k == 0) {
                    // 조건에 맞으면 해당 위치의 값을 1 증가시킵니다.
                    arr[i]++;
                }
            }
        }
        
        // 4. 모든 조작이 끝난 배열을 반환합니다.
        return arr;
    }
}