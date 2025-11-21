import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        List<Point> points = new ArrayList<>();
        
        // 좌표의 범위가 매우 클 수 있으므로 long 타입 사용
        long minX = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE;
        long maxY = Long.MIN_VALUE;

        // 1. 모든 직선 쌍에 대해 교점 구하기
        for (int i = 0; i < line.length; i++) {
            for (int j = i + 1; j < line.length; j++) {
                
                // 직선 1: Ax + By + E = 0
                long A = line[i][0];
                long B = line[i][1];
                long E = line[i][2];
                
                // 직선 2: Cx + Dy + F = 0
                long C = line[j][0];
                long D = line[j][1];
                long F = line[j][2];

                // 분모 (AD - BC)
                long denominator = A * D - B * C;
                
                // 평행하거나 일치하는 경우 (분모가 0)
                if (denominator == 0) continue;

                // 분자 계산
                long numX = B * F - E * D;
                long numY = E * C - A * F;

                // 정수 좌표인지 확인 (나머지가 0이어야 함)
                if (numX % denominator == 0 && numY % denominator == 0) {
                    long x = numX / denominator;
                    long y = numY / denominator;
                    
                    points.add(new Point(x, y));
                    
                    // 2. 격자판의 범위 갱신
                    minX = Math.min(minX, x);
                    maxX = Math.max(maxX, x);
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);
                }
            }
        }

        // 3. 2차원 배열 생성 및 별 찍기
        int width = (int) (maxX - minX + 1);
        int height = (int) (maxY - minY + 1);

        char[][] board = new char[height][width];
        for (char[] row : board) {
            Arrays.fill(row, '.');
        }

        // 좌표 변환하여 별 찍기
        for (Point p : points) {
            // 일반 좌표계의 (x, y)를 배열 인덱스 (row, col)로 변환
            // y축은 방향이 반대이므로 maxY에서 빼준다.
            int row = (int) (maxY - p.y);
            int col = (int) (p.x - minX);
            board[row][col] = '*';
        }

        // 결과 반환
        String[] answer = new String[height];
        for (int i = 0; i < height; i++) {
            answer[i] = new String(board[i]);
        }
        
        return answer;
    }
    
    // 좌표 저장을 위한 클래스
    static class Point {
        long x, y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}