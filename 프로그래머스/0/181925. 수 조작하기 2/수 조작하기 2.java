class Solution {
    public String solution(int[] numLog) {
        // 문자열을 효율적으로 이어 붙이기 위해 StringBuilder를 사용합니다.
        StringBuilder sb = new StringBuilder();
        
        // 인덱스 1부터 시작하여 이전 값과의 차이를 구합니다.
        for (int i = 1; i < numLog.length; i++) {
            int diff = numLog[i] - numLog[i - 1];
            
            // 변화량(diff)에 따라 알맞은 문자를 추가합니다.
            switch (diff) {
                case 1:
                    sb.append('w');
                    break;
                case -1:
                    sb.append('s');
                    break;
                case 10:
                    sb.append('d');
                    break;
                case -10:
                    sb.append('a');
                    break;
            }
        }
        
        // 완성된 결과를 문자열로 변환하여 반환합니다.
        return sb.toString();
    }
}