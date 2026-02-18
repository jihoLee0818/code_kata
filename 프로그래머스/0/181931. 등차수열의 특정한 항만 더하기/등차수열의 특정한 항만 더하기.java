class Solution {
    public int solution(int a, int d, boolean[] included) {
        int answer = 0;
        
        // 0부터 included 배열의 길이만큼 반복
        for (int i = 0; i < included.length; i++) {
            // included[i]가 true인 항만 더하기
            if (included[i]) {
                // 등차수열의 일반항: a + (d * i)
                answer += a + (d * i);
            }
        }
        
        return answer;
    }
}