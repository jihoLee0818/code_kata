import java.math.BigInteger;

class Solution {
    public long solution(int w, int h) {
        // int 범위를 초과할 수 있으므로 long으로 형변환
        long longW = (long)w;
        long longH = (long)h;

        // 1. 전체 사각형 수
        long totalSquares = longW * longH;
        
        // 2. 잘린 사각형 수 = W + H - GCD(W, H)
        long gcd = getGcd(longW, longH);
        long cutSquares = longW + longH - gcd;

        // 3. 멀쩡한 사각형 = 전체 - 잘린
        return totalSquares - cutSquares;
    }

    // 최대공약수(GCD)를 구하는 함수 (유클리드 호제법)
    private long getGcd(long a, long b) {
        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}