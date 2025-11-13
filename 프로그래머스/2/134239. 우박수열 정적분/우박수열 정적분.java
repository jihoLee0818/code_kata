import java.util.ArrayList;
import java.util.List;

class Solution {
    public double[] solution(int k, int[][] ranges) {
        // 1. 우박수열 생성 (y좌표들)
        List<Integer> yCoords = new ArrayList<>();
        yCoords.add(k);
        
        while (k > 1) {
            if (k % 2 == 0) {
                k /= 2;
            } else {
                k = (k * 3) + 1;
            }
            yCoords.add(k);
        }

        int n = yCoords.size() - 1; // 전체 구간의 수 (x좌표의 끝)

        // 2. 단위 구간(길이 1)별 넓이 미리 계산
        double[] unitAreas = new double[n];
        for (int i = 0; i < n; i++) {
            double y1 = yCoords.get(i);
            double y2 = yCoords.get(i + 1);
            // 사다리꼴 넓이: (윗변 + 아랫변) * 높이 / 2
            unitAreas[i] = (y1 + y2) / 2.0;
        }

        // 3. 주어진 범위(ranges)에 대해 정적분 계산
        double[] answer = new double[ranges.length];
        
        for (int i = 0; i < ranges.length; i++) {
            int start = ranges[i][0];       // a
            int end = n + ranges[i][1];     // n + b (b는 음수 또는 0)

            // 유효하지 않은 구간
            if (start > end) {
                answer[i] = -1.0;
            } 
            // 시작점과 끝점이 같은 경우 (넓이 0)
            else if (start == end) {
                answer[i] = 0.0;
            } 
            // 유효한 구간: 해당 범위의 단위 넓이들을 합산
            else {
                double sum = 0.0;
                for (int j = start; j < end; j++) {
                    sum += unitAreas[j];
                }
                answer[i] = sum;
            }
        }
        
        return answer;
    }
}