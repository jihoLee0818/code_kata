import java.util.*;

class Solution {

    // 문자열 → 숫자 (26진법)
    private long strToNum(String s) {
        long num = 0;
        for (int i = 0; i < s.length(); i++) {
            num = num * 26 + (s.charAt(i) - 'a' + 1);
        }
        return num;
    }

    // 숫자 → 문자열
    private String numToStr(long num) {
        StringBuilder sb = new StringBuilder();

        while (num > 0) {
            num--; // 1~26 → 0~25
            char c = (char) ('a' + (num % 26));
            sb.append(c);
            num /= 26;
        }

        return sb.reverse().toString();
    }

    public String solution(long n, String[] bans) {

        // 1. bans를 숫자로 변환
        long[] banNums = new long[bans.length];
        for (int i = 0; i < bans.length; i++) {
            banNums[i] = strToNum(bans[i]);
        }

        // 2. 정렬
        Arrays.sort(banNums);

        // 3. 금지 주문이 n보다 작거나 같으면 n 증가
        for (long ban : banNums) {
            if (ban <= n) {
                n++;
            } else {
                break;
            }
        }

        // 4. 최종 n을 문자열로 변환
        return numToStr(n);
    }
}
