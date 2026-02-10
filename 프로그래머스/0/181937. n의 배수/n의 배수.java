class Solution {
    public int solution(int num, int n) {
        // num을 n으로 나누었을 때 나머지가 0이면 배수
        if (num % n == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}