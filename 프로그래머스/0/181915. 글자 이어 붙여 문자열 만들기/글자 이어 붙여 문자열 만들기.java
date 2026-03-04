class Solution {
    public String solution(String my_string, int[] index_list) {
        // 문자열을 효율적으로 이어 붙이기 위해 StringBuilder 생성
        StringBuilder sb = new StringBuilder();
        
        // index_list 배열을 순회하며 인덱스 값을 하나씩 가져옴
        for (int idx : index_list) {
            // 원본 문자열에서 해당 인덱스의 문자를 추출하여 추가
            sb.append(my_string.charAt(idx));
        }
        
        // 완성된 결과를 문자열로 변환하여 반환
        return sb.toString();
    }
}