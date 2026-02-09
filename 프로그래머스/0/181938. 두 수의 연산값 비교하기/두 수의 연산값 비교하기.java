class Solution {
    public int solution(int a, int b) {
        // 1. a (+) b 계산 (문자열 연결 후 정수 변환)
        // "" + a + b 는 문자열 "a" + "b"가 되어 연결됩니다.
        int atb = Integer.parseInt("" + a + b);
        
        // 2. 2 * a * b 계산
        int twoab = 2 * a * b;
        
        // 3. 두 값 중 더 큰 값 반환
        // Math.max는 두 값이 같을 경우 그 값을 그대로 반환하므로 문제 조건 충족
        return Math.max(atb, twoab);
    }
}