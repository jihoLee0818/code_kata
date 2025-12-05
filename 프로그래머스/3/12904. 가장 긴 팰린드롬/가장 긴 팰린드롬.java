class Solution {
    public int solution(String s) {
        int maxLen = 0; // 가장 긴 팰린드롬의 길이

        // 문자열의 모든 위치를 중심점으로 시도
        for (int i = 0; i < s.length(); i++) {
            // 1. 홀수 길이 팰린드롬 체크 (중심이 i)
            int len1 = expandAroundCenter(s, i, i);
            
            // 2. 짝수 길이 팰린드롬 체크 (중심이 i와 i+1 사이)
            int len2 = expandAroundCenter(s, i, i + 1);
            
            // 더 긴 길이로 최댓값 갱신
            int len = Math.max(len1, len2);
            maxLen = Math.max(maxLen, len);
        }

        return maxLen;
    }

    /**
     * 중심(left, right)에서 시작하여 좌우로 확장하며 팰린드롬 길이를 구하는 함수
     */
    private int expandAroundCenter(String s, int left, int right) {
        // 범위 내에 있고, 좌우 문자가 같으면 계속 확장
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        
        // while문이 끝났을 때의 left와 right는 팰린드롬 범위를 벗어난 상태임.
        // 유효한 범위는 (left + 1) ~ (right - 1)
        // 길이 공식: (right - 1) - (left + 1) + 1 = right - left - 1
        return right - left - 1;
    }
}