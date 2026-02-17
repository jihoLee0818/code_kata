class Solution {
    public String solution(String code) {
        // 문자열 연결을 효율적으로 하기 위해 StringBuilder 사용
        StringBuilder ret = new StringBuilder();
        int mode = 0; // 초기 모드는 0
        
        for (int idx = 0; idx < code.length(); idx++) {
            char c = code.charAt(idx);
            
            // 1. 문자가 '1'인 경우: 모드 변경 (0 -> 1, 1 -> 0)
            if (c == '1') {
                mode = 1 - mode;
            } 
            // 2. 문자가 '1'이 아닌 경우: 모드에 따라 처리
            else {
                if (mode == 0) {
                    // mode가 0일 때는 짝수 인덱스만 추가
                    if (idx % 2 == 0) {
                        ret.append(c);
                    }
                } else { // mode == 1
                    // mode가 1일 때는 홀수 인덱스만 추가
                    if (idx % 2 == 1) {
                        ret.append(c);
                    }
                }
            }
        }
        
        // 3. 결과 문자열이 비어있으면 "EMPTY" 반환
        if (ret.length() == 0) {
            return "EMPTY";
        }
        
        return ret.toString();
    }
}