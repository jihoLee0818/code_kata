class Solution {
    public int solution(int n) {
        int answer = 0;

        // n이 홀수인 경우
        if (n % 2 == 1) {
            // 1부터 n까지 2씩 증가 (1, 3, 5 ...)
            for (int i = 1; i <= n; i += 2) {
                answer += i;
            }
        } 
        // n이 짝수인 경우
        else {
            // 2부터 n까지 2씩 증가 (2, 4, 6 ...)
            for (int i = 2; i <= n; i += 2) {
                answer += i * i; // 제곱하여 더하기
            }
        }

        return answer;
    }
}