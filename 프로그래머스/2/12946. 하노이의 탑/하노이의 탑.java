import java.util.ArrayList;
import java.util.List;

class Solution {
    
    // 이동 경로를 저장할 리스트
    List<int[]> moves;

    public int[][] solution(int n) {
        moves = new ArrayList<>();
        
        // 1번 기둥에서 3번 기둥으로 n개의 원판을 옮김 (2번은 보조)
        hanoi(n, 1, 3, 2);
        
        // List<int[]>를 int[][]로 변환
        return moves.toArray(new int[0][]);
    }

    /**
     * 하노이의 탑 재귀 함수
     * @param n     옮길 원판의 개수
     * @param start 출발 기둥
     * @param end   도착 기둥
     * @param aux   보조 기둥
     */
    private void hanoi(int n, int start, int end, int aux) {
        // 기저 조건 (Base Case): 옮길 원판이 1개일 때
        if (n == 1) {
            moves.add(new int[]{start, end});
            return;
        }
        
        // 1. n-1개를 start에서 aux로 옮긴다 (end를 보조로 사용)
        hanoi(n - 1, start, aux, end);
        
        // 2. 가장 큰 원판(n번째)을 start에서 end로 옮긴다
        moves.add(new int[]{start, end});
        
        // 3. n-1개를 aux에서 end로 옮긴다 (start를 보조로 사용)
        hanoi(n - 1, aux, end, start);
    }
}