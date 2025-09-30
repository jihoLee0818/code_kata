import java.util.Arrays;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        // 1. citations 배열을 오름차순으로 정렬
        Arrays.sort(citations);
        
        int n = citations.length;
        
        // 2. 배열을 순회하며 h의 최댓값을 찾는다.
        for (int i = 0; i < n; i++) {
            // h는 'h번 이상 인용된 논문의 수'를 의미할 수 있다.
            // 현재 논문(citations[i])보다 많이 인용된 논문의 수는 (n - i)개.
            int h = n - i;
            
            // 현재 논문의 인용 횟수(citations[i])가 h 이상이라면
            // (n-i)개의 논문이 모두 h번 이상 인용되었다는 의미이다.
            if (citations[i] >= h) {
                // 조건을 만족했으므로 h가 H-Index의 후보가 된다.
                // 반복문이 더 돌면 h는 작아지므로, 최초로 만족하는 h가 최댓값이다.
                answer = h;
                break;
            }
        }
        
        return answer;
    }
}