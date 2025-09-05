class Solution {
    public int[] solution(int[] lottos, int[] win_nums) {
        int[] answer = new int[2];
        // 순위를 저장하는 배열 생성
        int[] rank = {6, 6, 5, 4, 3, 2, 1};
        
        // 반복문을 통해 선택한 로또 번호 확인
        for (int i = 0; i < lottos.length; i++) {
            // 0일 경우 최고 순위 +1
            if (lottos[i] == 0) {
                answer[0]++;
                continue;
            }
            
            // 정답 번호와 선택한 로또 번호 비교
            for (int j = 0; j < win_nums.length; j++) {
            	// 번호가 같을 경우 최고 순위, 최저 순위 +1
                if (lottos[i] == win_nums[j]) {
                    answer[0]++;
                    answer[1]++;
                }
            }
        }
        
        // 순위 배열에서 값 가져온다.
        answer[0] = rank[answer[0]];
        answer[1] = rank[answer[1]];
        
        return answer;
    }
}