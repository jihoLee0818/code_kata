class Solution {
    public int solution(String number) {
        int sum = 0;
        
        // 문자열의 길이만큼 반복하며 각 자리의 숫자를 가져옵니다.
        for (int i = 0; i < number.length(); i++) {
            // 문자를 숫자로 변환하여 누적합을 구합니다.
            sum += number.charAt(i) - '0';
        }
        
        // 각 자리 숫자의 합을 9로 나눈 나머지를 반환합니다.
        return sum % 9;
    }
}