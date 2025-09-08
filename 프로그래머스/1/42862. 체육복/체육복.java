import java.util.*;

class Solution {
    public int solution(int n, int[] lost, int[] reserve) {
        // 초기 answer에 체육복을 도난당하지 않은 학생 수를 구한다.
        int answer = n - lost.length;
        
        // reserve 배열을 정렬한다.
        Arrays.sort(reserve);
        // lost 배열을 정렬한다.
        Arrays.sort(lost);
        
        // 반복문을 통해 여벌의 옷을 가져왔지만 도난당한 학생 수를 계산한다.
        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (lost[i] == reserve[j]) {
                    answer++;
                    lost[i] = -1;
                    reserve[j] = -1;
                    break;
                }
            }
        }
        
        // 반복문을 통해 도난당했지만 여벌의 체육복을 빌릴 수 있는 학생 수를 계산한다.
        for (int i = 0; i < lost.length; i++) {
            for (int j = 0; j < reserve.length; j++) {
                if (lost[i] - 1 == reserve[j] || lost[i] + 1 == reserve[j]) {
                    answer++;
                    reserve[j] = -1;
                    break;
                }
            }
        }		
        
        return answer;
    }
}