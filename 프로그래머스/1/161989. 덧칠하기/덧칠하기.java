class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int max = 0;  // 롤러가 마지막으로 칠한 구역
        
        // 반복문을 통해 section에 저장된 구역 하나씩 순회
        for (int sec : section) {
            // max보다 sec이 크거나 같을 경우 실행
            if (max <= sec) {
                max = sec + m;
                answer++;  // answer 1 증가
            }
        }
        return answer;  // answer 반환
    }
}