import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    public int solution(String[][] book_time) {
        // 1. 시간을 분으로 변환하고, 시작 시간 기준 정렬을 위해 2D int 배열 생성
        int[][] times = new int[book_time.length][2];
        for (int i = 0; i < book_time.length; i++) {
            times[i][0] = timeToMinutes(book_time[i][0]); // 시작 시간
            times[i][1] = timeToMinutes(book_time[i][1]); // 종료 시간
        }

        // 2. 시작 시간 기준 오름차순 정렬
        Arrays.sort(times, (a, b) -> a[0] - b[0]);

        // 3. 우선순위 큐 (Min-Heap) 준비: 객실의 "사용 가능 시간"을 저장
        PriorityQueue<Integer> roomEndTimes = new PriorityQueue<>();

        int maxRooms = 0;

        // 4. 시뮬레이션
        for (int[] time : times) {
            int start = time[0];
            int end = time[1];

            // 4a. 사용 가능한 객실 확인 및 재사용
            while (!roomEndTimes.isEmpty() && roomEndTimes.peek() <= start) {
                roomEndTimes.poll(); // 청소가 끝난 객실은 큐에서 제거 (재사용)
            }

            // 4b. 현재 예약을 위해 객실 배정 (퇴실 시간 + 청소 10분)
            roomEndTimes.offer(end + 10);

            // 4c. 현재 시점에 사용 중인 객실 수의 최댓값 갱신
            maxRooms = Math.max(maxRooms, roomEndTimes.size());
        }

        return maxRooms;
    }

    // "HH:MM"을 분으로 변환하는 헬퍼 메소드
    private int timeToMinutes(String timeStr) {
        String[] parts = timeStr.split(":");
        int hours = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return (hours * 60) + minutes;
    }
}