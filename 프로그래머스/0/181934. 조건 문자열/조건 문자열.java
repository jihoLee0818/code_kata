class Solution {
    public int solution(String ineq, String eq, int n, int m) {
        boolean result = false;

        if (ineq.equals(">")) {
            if (eq.equals("=")) {
                // ">=" 인 경우
                result = (n >= m);
            } else {
                // ">!" 인 경우
                result = (n > m);
            }
        } else { // ineq.equals("<")
            if (eq.equals("=")) {
                // "<=" 인 경우
                result = (n <= m);
            } else {
                // "<!" 인 경우
                result = (n < m);
            }
        }

        // 조건이 참이면 1, 거짓이면 0 반환
        return result ? 1 : 0;
    }
}