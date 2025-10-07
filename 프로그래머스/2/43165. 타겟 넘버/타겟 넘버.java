class Solution {
    int answer = 0; // 타겟 넘버를 만드는 방법의 수를 저장할 변수
    int[] numbers;
    int target;

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        
        // DFS 시작: 첫 번째 숫자부터 시작하므로 인덱스는 0, 초기 합계는 0
        dfs(0, 0);
        
        return answer;
    }

    /**
     * @param index 현재 계산할 숫자의 인덱스
     * @param sum   이전 인덱스까지 계산된 중간 합계
     */
    public void dfs(int index, int sum) {
        // 3. 종료 조건: 모든 숫자를 다 사용했을 경우
        if (index == numbers.length) {
            // 계산된 합계가 타겟과 일치하는지 확인
            if (sum == target) {
                answer++;
            }
            return; // 재귀 종료
        }
        
        // 2. 탐색: 현재 숫자를 더하는 경우와 빼는 경우로 재귀 호출
        // 현재 숫자를 더하는 경로
        dfs(index + 1, sum + numbers[index]);
        
        // 현재 숫자를 빼는 경로
        dfs(index + 1, sum - numbers[index]);
    }
}