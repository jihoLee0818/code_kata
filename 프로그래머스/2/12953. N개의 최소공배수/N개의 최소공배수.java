class Solution {

    public int solution(int[] arr) {
        int lcm = arr[0];
        for (int i = 1; i < arr.length; i++) {
            lcm = lcm(lcm, arr[i]); // 누적 LCM 계산
        }
        return lcm;
    }

    // 유클리드 호제법을 이용한 GCD 계산
    public int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    // 두 수의 LCM 계산
    public int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}