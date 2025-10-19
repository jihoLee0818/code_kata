class Solution {
    // 0의 개수와 1의 개수를 저장할 배열
    int[] answer = new int[2];
    int[][] arr; // 입력 배열을 저장할 멤버 변수

    public int[] solution(int[][] arr) {
        this.arr = arr;
        
        // 전체 영역(0, 0)에서 arr.length 크기로 압축 시작
        compress(0, 0, arr.length);
        
        return answer;
    }

    /**
     * @param row  현재 영역의 시작 행
     * @param col  현재 영역의 시작 열
     * @param size 현재 영역의 한 변의 길이
     */
    private void compress(int row, int col, int size) {
        // 1. 영역 검사: 모든 숫자가 같은지 확인
        int firstVal = arr[row][col];
        boolean isMixed = false;
        
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (arr[i][j] != firstVal) {
                    isMixed = true;
                    break;
                }
            }
            if (isMixed) break;
        }

        // 2. 정복 (압축)
        if (!isMixed) {
            if (firstVal == 0) {
                answer[0]++;
            } else {
                answer[1]++;
            }
            return; // 압축 성공, 재귀 종료
        }
        
        // 3. 분할 (영역이 섞여있는 경우)
        int newSize = size / 2;
        
        compress(row, col, newSize);                         // 왼쪽 위
        compress(row, col + newSize, newSize);               // 오른쪽 위
        compress(row + newSize, col, newSize);               // 왼쪽 아래
        compress(row + newSize, col + newSize, newSize);     // 오른쪽 아래
    }
}