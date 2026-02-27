class Solution {
    public int[] solution(int start_num, int end_num) {
        // 1. 필요한 배열의 길이 계산 및 배열 생성
        int length = end_num - start_num + 1;
        int[] answer = new int[length];
        
        // 2. 반복문을 돌며 순서대로 값 채우기
        for (int i = 0; i < length; i++) {
            answer[i] = start_num + i;
        }
        
        return answer;
    }
}