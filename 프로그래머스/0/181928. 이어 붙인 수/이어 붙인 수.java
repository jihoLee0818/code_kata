class Solution {
    public int solution(int[] num_list) {
        int odd = 0;
        int even = 0;
        
        for (int num : num_list) {
            // 1. 홀수인 경우
            if (num % 2 == 1) {
                odd = odd * 10 + num;
            } 
            // 2. 짝수인 경우
            else {
                even = even * 10 + num;
            }
        }
        
        // 3. 두 수의 합 반환
        return odd + even;
    }
}