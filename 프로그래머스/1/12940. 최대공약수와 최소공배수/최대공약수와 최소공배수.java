class Solution {

    public int[] solution(int n, int m) {
        int gcd = gcd(n, m);
        return new int[] {gcd, (int)((long) n * m / gcd)};
    }

    public int gcd(int a, int b) {
        return (b == 0) ? a : gcd(b, a % b);
    }

}