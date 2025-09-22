class Solution {

    public int[] solution(String s) {
        String binary = s;
        int count = 0, zeroCount = 0;

        // 문자열이 "1"이 될 때까지 반복
        while (binary.length() > 1) {
            int len = binary.length();

            // 현재 문자열에서 '0'의 개수 세기
            int zero = (int) binary.chars().filter(i -> i == '0').count();

            // 제거된 0의 개수 누적
            zeroCount += zero;

            // 변환 횟수 증가
            count++;

            // 0 제거 후 남은 1의 개수를 이진 문자열로 변환
            binary = Integer.toBinaryString(len - zero);
        }

        // 변환 횟수와 제거된 0의 개수를 배열로 반환
        return new int[] {count, zeroCount};
    }

}