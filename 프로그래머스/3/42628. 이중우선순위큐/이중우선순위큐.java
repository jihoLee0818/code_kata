import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        // 1. 최소 힙 (기본)
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        // 2. 최대 힙 (Collections.reverseOrder() 사용)
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());

        for (String op : operations) {
            String[] split = op.split(" ");
            String command = split[0];
            int value = Integer.parseInt(split[1]);

            if (command.equals("I")) {
                // 삽입: 두 큐에 모두 넣음
                minPq.offer(value);
                maxPq.offer(value);
            } else {
                // 큐가 비어있으면 삭제 연산 무시
                if (minPq.isEmpty()) continue;

                if (value == 1) {
                    // D 1 : 최댓값 삭제
                    int max = maxPq.poll(); // 최대 힙에서 삭제
                    minPq.remove(max);      // 최소 힙에서도 동기화 삭제
                } else {
                    // D -1 : 최솟값 삭제
                    int min = minPq.poll(); // 최소 힙에서 삭제
                    maxPq.remove(min);      // 최대 힙에서도 동기화 삭제
                }
            }
        }

        // 결과 반환
        if (minPq.isEmpty()) {
            return new int[]{0, 0};
        } else {
            return new int[]{maxPq.peek(), minPq.peek()};
        }
    }
}