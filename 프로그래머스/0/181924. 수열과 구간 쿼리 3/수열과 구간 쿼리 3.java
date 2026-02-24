class Solution {
    public int[] solution(int[] arr, int[][] queries) {
        
        // 향상된 for문을 사용하여 queries의 각 쿼리 배열([i, j])을 꺼냅니다.
        for (int[] query : queries) {
            int i = query[0];
            int j = query[1];
            
            // 임시 변수 temp를 사용하여 arr[i]와 arr[j]의 값을 맞바꿉니다.
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        
        // 모든 교환이 끝난 최종 배열을 반환합니다.
        return arr;
    }
}