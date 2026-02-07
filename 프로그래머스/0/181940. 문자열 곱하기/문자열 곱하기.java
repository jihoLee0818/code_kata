class Solution {
    public String solution(String my_string, int k) {
        // 문자열 연결을 위한 StringBuilder 생성
        StringBuilder sb = new StringBuilder();
        
        // k번 반복하며 문자열 추가
        for (int i = 0; i < k; i++) {
            sb.append(my_string);
        }
        
        // 결과 반환
        return sb.toString();
    }
}