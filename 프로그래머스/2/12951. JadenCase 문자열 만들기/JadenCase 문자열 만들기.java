class Solution {

    public String solution(String s) {
        // 전체 문자열을 소문자로 변환하여 기본 상태로 만듦
        StringBuilder sb = new StringBuilder(s.toLowerCase());

        boolean isFirst = true; // 단어의 첫 문자 여부 체크
        for (int i = 0; i < s.length(); i++) {
            char c = sb.charAt(i);

            if (c == ' ') {
                // 공백이 나오면 다음 문자는 단어의 첫 글자가 됨
                isFirst = true;
            } else if (isFirst) {
                // 단어의 첫 글자일 경우 대문자로 변환
                sb.setCharAt(i, Character.toUpperCase(c));
                isFirst = false;
            }
            // 단어의 첫 글자가 아니면 소문자 유지 (이미 처리됨)
        }

        // 변환된 문자열 반환
        return sb.toString();
    }
}