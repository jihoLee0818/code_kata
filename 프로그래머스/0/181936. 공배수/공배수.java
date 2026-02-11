class Solution {
    public int solution(int number, int n, int m) {
        // number가 n의 배수이고(AND), m의 배수인지 확인
        if (number % n == 0 && number % m == 0) {
            return 1;
        } else {
            return 0;
        }
    }
}