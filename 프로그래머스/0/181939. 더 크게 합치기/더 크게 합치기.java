class Solution {
    public int solution(int a, int b) {
        // 1. 정수를 문자열로 변환
        String strA = String.valueOf(a);
        String strB = String.valueOf(b);
        
        // 2. 두 가지 순서로 합치기
        String combine1 = strA + strB;
        String combine2 = strB + strA;
        
        // 3. 다시 정수로 변환하여 크기 비교
        int value1 = Integer.parseInt(combine1);
        int value2 = Integer.parseInt(combine2);
        
        // 두 값 중 더 큰 값을 반환 (같으면 아무거나 반환해도 됨)
        return Math.max(value1, value2);
    }
}