import java.util.*;

class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        for (int i = 0; i < s.length; i++) {
            answer[i] = process(s[i]);
        }
        
        return answer;
    }
    
    private String process(String str) {
        // 1. "110"을 모두 추출하기 위한 StringBuilder (스택처럼 사용)
        StringBuilder sb = new StringBuilder();
        int count = 0; // 추출한 110의 개수
        
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            sb.append(c);
            
            // sb의 길이가 3 이상이고, 끝부분이 "110"인지 확인
            int len = sb.length();
            if (len >= 3 && 
                sb.charAt(len - 3) == '1' && 
                sb.charAt(len - 2) == '1' && 
                sb.charAt(len - 1) == '0') {
                
                // "110" 제거
                sb.delete(len - 3, len);
                count++;
            }
        }
        
        // 2. 남은 문자열에서 "110"을 삽입할 위치(마지막 '0'의 뒤) 찾기
        // 110이 없었다면 원본 그대로 반환
        if (count == 0) {
            return str;
        }
        
        // 남은 문자열(sb)에서 마지막 '0'의 인덱스를 찾음
        int lastZeroIdx = sb.lastIndexOf("0");
        
        // 3. 결과 문자열 만들기
        StringBuilder result = new StringBuilder();
        
        // "110" 문자열 미리 생성 (반복문 최소화)
        StringBuilder ones = new StringBuilder();
        for (int k = 0; k < count; k++) {
            ones.append("110");
        }
        
        if (lastZeroIdx == -1) {
            // '0'이 없으면 가장 앞에 삽입
            result.append(ones);
            result.append(sb);
        } else {
            // 마지막 '0' 바로 뒤에 삽입
            // (0까지의 부분 문자열) + (110 뭉치들) + (나머지 뒷부분)
            result.append(sb.substring(0, lastZeroIdx + 1));
            result.append(ones);
            result.append(sb.substring(lastZeroIdx + 1));
        }
        
        return result.toString();
    }
}