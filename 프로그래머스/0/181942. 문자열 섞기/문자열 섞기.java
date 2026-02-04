class Solution {
    public String solution(String str1, String str2) {
        // 문자열을 효율적으로 합치기 위해 StringBuilder 사용
        StringBuilder answer = new StringBuilder();
        
        // 두 문자열의 길이는 같으므로 str1의 길이만큼 반복
        for (int i = 0; i < str1.length(); i++) {
            answer.append(str1.charAt(i)); // str1의 i번째 글자 추가
            answer.append(str2.charAt(i)); // str2의 i번째 글자 추가
        }
        
        return answer.toString();
    }
}