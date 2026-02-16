class Solution {
    public int solution(int a, int b, boolean flag) {
        // flag가 true면 a + b, false면 a - b를 반환
        if (flag) {
            return a + b;
        } else {
            return a - b;
        }
    }
}