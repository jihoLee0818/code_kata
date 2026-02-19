class Solution {
    public int solution(int[] num_list) {
        // 합을 저장할 변수는 0으로, 곱을 저장할 변수는 1로 초기화합니다.
        int sum = 0;
        int product = 1;
        
        // 배열의 모든 원소를 순회하며 합과 곱을 누적합니다.
        for (int num : num_list) {
            sum += num;
            product *= num;
        }
        
        // 원소들의 곱이 합의 제곱보다 작으면 1, 아니면 0을 반환합니다.
        if (product < (sum * sum)) {
            return 1;
        } else {
            return 0;
        }
    }
}