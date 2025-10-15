class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            long num = numbers[i];

            if (num % 2 == 0) {
                // Case 1: 짝수인 경우
                answer[i] = num + 1;
            } else {
                // Case 2: 홀수인 경우
                // 1. 가장 오른쪽 '0'의 위치 찾기 (해당 비트가 1이 됨)
                long rightmostZeroBit = 1;
                while ((num & rightmostZeroBit) != 0) {
                    rightmostZeroBit <<= 1; // 왼쪽으로 1비트씩 이동
                }

                // 2. 그 바로 오른쪽 '1'의 위치 찾기 (해당 비트가 0이 됨)
                long bitToFlipOne = rightmostZeroBit >> 1;

                // 3. 원래 숫자에 두 비트의 차이만큼 더해준다
                answer[i] = num + bitToFlipOne;
            }
        }
        return answer;
    }
}