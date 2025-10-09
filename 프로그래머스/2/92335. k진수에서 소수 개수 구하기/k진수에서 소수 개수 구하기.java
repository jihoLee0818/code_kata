class Solution {
    public int solution(int n, int k) {
        int answer = 0;

        // 1. n을 k진수로 변환
        String baseKString = Integer.toString(n, k);

        // 2. '0'을 기준으로 문자열을 잘라 소수 후보들을 찾는다.
        // "0"이 연속으로 나올 경우 빈 문자열이 생길 수 있으므로 처리 필요.
        String[] candidates = baseKString.split("0");

        // 3. 각 후보가 소수인지 판별
        for (String s : candidates) {
            // 빈 문자열이거나 공백이면 건너뛴다.
            if (s.isEmpty()) {
                continue;
            }
            
            // 문자열을 long 타입의 숫자로 변환
            long num = Long.parseLong(s);
            
            // 소수이면 정답 카운트 증가
            if (isPrime(num)) {
                answer++;
            }
        }

        return answer;
    }

    /**
     * 숫자가 소수인지 판별하는 헬퍼 메소드
     */
    private boolean isPrime(long n) {
        // 1은 소수가 아니다.
        if (n <= 1) {
            return false;
        }
        
        // 2부터 n의 제곱근까지 나누어 떨어지는 수가 있으면 소수가 아니다.
        for (long i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        
        // 나누어 떨어지는 수가 없으면 소수이다.
        return true;
    }
}