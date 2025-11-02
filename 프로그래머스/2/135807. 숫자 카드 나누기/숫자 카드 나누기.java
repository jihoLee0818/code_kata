class Solution {
    
    // 유클리드 호제법을 이용한 최대공약수 계산
    private int gcd(int a, int b) {
        while (b != 0) {
            int r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
    
    // 배열의 모든 원소의 최대공약수 계산
    private int getArrayGcd(int[] arr) {
        int result = arr[0];
        for (int i = 1; i < arr.length; i++) {
            result = gcd(result, arr[i]);
        }
        return result;
    }
    
    // 배열의 어떤 원소도 divisor로 나누어 떨어지지 않는지 확인
    // (하나라도 나누어 떨어지면 false 반환)
    private boolean dividesNone(int[] arr, int divisor) {
        for (int num : arr) {
            if (num % divisor == 0) {
                return false;
            }
        }
        return true;
    }

    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        // 1. arrayA의 최대공약수 구하기
        int gcdA = getArrayGcd(arrayA);
        // 2. arrayB의 최대공약수 구하기
        int gcdB = getArrayGcd(arrayB);
        
        int answer1 = 0;
        int answer2 = 0;
        
        // 3. 조건 1 확인: gcdA가 arrayB를 나누지 못하는가?
        if (dividesNone(arrayB, gcdA)) {
            answer1 = gcdA;
        }
        
        // 4. 조건 2 확인: gcdB가 arrayA를 나누지 못하는가?
        if (dividesNone(arrayA, gcdB)) {
            answer2 = gcdB;
        }
        
        // 5. 두 후보 중 더 큰 값을 정답으로 선택
        answer = Math.max(answer1, answer2);
        
        return answer;
    }
}