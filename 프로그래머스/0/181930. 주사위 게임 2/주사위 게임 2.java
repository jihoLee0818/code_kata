class Solution {
    public int solution(int a, int b, int c) {
        // 일단 기본 점수(모두 다른 경우)를 계산해 둡니다.
        int answer = a + b + c;
        
        // 1. 세 숫자가 모두 같은 경우 (가장 까다로운 조건부터 검사)
        if (a == b && b == c) {
            answer = answer * (a*a + b*b + c*c) * (a*a*a + b*b*b + c*c*c);
        }
        // 2. 두 숫자만 같은 경우 (1번 조건이 거짓일 때만 실행됨)
        else if (a == b || a == c || b == c) {
            answer = answer * (a*a + b*b + c*c);
        }
        // 3. 모두 다른 경우: 위 if문들에 걸리지 않으므로 초기값 answer가 그대로 반환됨
        
        return answer;
    }
}