class Solution {
    public int solution(int[] a) {
        int answer = 0;
        
        // 1. 각 숫자의 등장 횟수(빈도) 계산
        // a의 원소는 0 이상 a.length 미만이므로 배열로 카운트 가능
        int[] count = new int[a.length];
        for (int num : a) {
            count[num]++;
        }
        
        // 2. 각 숫자를 '스타 수열의 교집합 원소(Anchor)'로 가정하고 탐색
        for (int i = 0; i < count.length; i++) {
            // 해당 숫자가 한 번도 안 나왔으면 패스
            if (count[i] == 0) continue;
            
            // [핵심 가지치기] 
            // 현재 숫자의 등장 횟수 * 2 가 이미 구한 정답보다 작거나 같다면,
            // 이 숫자로 만들 수 있는 스타 수열은 절대 answer를 넘을 수 없음.
            if (count[i] * 2 <= answer) continue;
            
            int common = i; // 이번 턴의 교집합 원소
            int len = 0;
            
            // 3. 그리디 탐색: 배열을 훑으며 쌍을 만듦
            for (int j = 0; j < a.length - 1; j++) {
                // 조건 1: 두 원소 중 하나는 교집합 원소여야 함
                // 조건 2: 두 원소는 서로 달라야 함
                if ((a[j] == common || a[j+1] == common) && (a[j] != a[j+1])) {
                    len += 2; // 스타 수열 길이 2 증가
                    j++;      // 두 원소(j, j+1)를 모두 사용했으므로 인덱스 추가 증가
                }
            }
            
            answer = Math.max(answer, len);
        }
        
        return answer;
    }
}