import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        // 1. 숫자를 문자열로 변환
        String[] strNumbers = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            strNumbers[i] = String.valueOf(numbers[i]);
        }

        // 2. 커스텀 정렬 (람다식 사용)
        // (b + a)가 (a + b)보다 크면 b를 앞으로 보낸다 (내림차순 정렬)
        Arrays.sort(strNumbers, (a, b) -> (b + a).compareTo(a + b));

        // 3. 예외 처리 (모든 숫자가 0인 경우)
        if (strNumbers[0].equals("0")) {
            return "0";
        }

        // 4. 문자열 조합
        StringBuilder answer = new StringBuilder();
        for (String s : strNumbers) {
            answer.append(s);
        }

        return answer.toString();
    }
}