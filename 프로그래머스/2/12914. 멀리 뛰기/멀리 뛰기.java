class Solution {
    public long solution(int n) {
        // n이 1이나 2일 경우, 경우의 수는 n과 동일합니다.
        if (n <= 2) {
            return n;
        }

        // 피보나치 수열의 (i-2)번째와 (i-1)번째 값을 저장할 변수입니다.
        // n이 커질 수 있으므로 long 타입을 사용해 오버플로우를 방지합니다.
        long a = 1; // Ways(1)
        long b = 2; // Ways(2)

        // 3부터 n까지 반복하여 n번째 값을 구합니다.
        for (int i = 3; i <= n; i++) {
            // (i)번째 값 = (i-1)번째 값 + (i-2)번째 값
            long nextVal = (a + b) % 1234567;
            
            // 다음 반복을 위해 값을 한 칸씩 이동시킵니다.
            a = b;
            b = nextVal;
        }

        // 최종적으로 n번째 값이 저장된 b를 반환합니다.
        return b;
    }
}