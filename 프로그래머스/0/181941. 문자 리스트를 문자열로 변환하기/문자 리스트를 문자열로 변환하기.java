class Solution {
    public String solution(String[] arr) {
        // 문자열을 이어 붙이기 위한 StringBuilder 생성
        StringBuilder sb = new StringBuilder();
        
        // 배열의 모든 요소를 순회하며 이어 붙임
        for (String s : arr) {
            sb.append(s);
        }
        
        // 최종 문자열로 변환하여 반환
        return sb.toString();
    }
}