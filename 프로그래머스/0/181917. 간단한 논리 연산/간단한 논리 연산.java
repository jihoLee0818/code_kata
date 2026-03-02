class Solution {
    public boolean solution(boolean x1, boolean x2, boolean x3, boolean x4) {
        // (x1 OR x2) AND (x3 OR x4) 식을 그대로 자바 문법으로 구현
        boolean answer = (x1 || x2) && (x3 || x4);
        
        return answer;
    }
}