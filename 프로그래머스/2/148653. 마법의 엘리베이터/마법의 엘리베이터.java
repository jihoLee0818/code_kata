class Solution {
    public int solution(int storey) {
        int answer = 0;

        while (storey > 0) {
            int digit = storey % 10;       // 현재 자릿수 (일의 자리)
            int nextDigit = (storey / 10) % 10; // 다음 자릿수 (십의 자리)

            if (digit > 5) {
                // 규칙 2: 6~9는 올림
                answer += (10 - digit);
                storey += (10 - digit); // (e.g., 27 -> 30)
            
            } else if (digit < 5) {
                // 규칙 1: 1~4는 내림
                answer += digit;
                storey -= digit; // (e.g., 23 -> 20)
            
            } else { // digit == 5
                // 규칙 3: 5는 다음 자릿수에 따라 결정
                answer += 5;
                if (nextDigit >= 5) {
                    storey += 5; // 올림 (e.g., 155 -> 160)
                } else {
                    storey -= 5; // 내림 (e.g., 145 -> 140)
                }
            }
            
            // 다음 자릿수 처리를 위해 10으로 나눔
            storey /= 10;
        }

        return answer;
    }
}