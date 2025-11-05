import java.util.Arrays;

class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        
        // 1. 1단계: 정렬
        // col은 1-based index이므로, 배열 접근 시 col-1을 사용
        int sortColIdx = col - 1;
        
        Arrays.sort(data, (a, b) -> {
            // 1순위: col번째 열 기준 오름차순
            if (a[sortColIdx] != b[sortColIdx]) {
                return a[sortColIdx] - b[sortColIdx];
            } else {
                // 2순위: 1번째 열(PK) 기준 내림차순
                return b[0] - a[0];
            }
        });

        // 3. 3단계: 해시 (XOR)
        int hashCode = 0;
        
        // 2. 2단계: S_i 계산
        // row_begin, row_end는 1-based
        for (int i = row_begin; i <= row_end; i++) {
            
            // data 배열 인덱스는 0-based
            int[] currentRow = data[i - 1]; 
            int s_i = 0;
            
            for (int value : currentRow) {
                s_i += (value % i);
            }
            
            // S_i가 만들어질 때마다 XOR 누적
            hashCode ^= s_i;
        }

        return hashCode;
    }
}