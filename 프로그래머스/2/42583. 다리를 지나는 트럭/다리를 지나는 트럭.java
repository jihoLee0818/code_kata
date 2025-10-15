import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // 다리를 나타내는 큐
        Queue<Integer> bridge = new LinkedList<>();
        // 경과 시간
        int time = 0;
        // 현재 다리 위의 트럭 무게 합
        int currentWeight = 0;

        // 대기 중인 모든 트럭에 대해 반복
        for (int truck : truck_weights) {
            // 현재 트럭이 다리에 올라갈 때까지 시간을 진행
            while (true) {
                time++; // 1초 경과

                // 1. 트럭이 다리를 다 건넜는지 확인
                if (bridge.size() == bridge_length) {
                    currentWeight -= bridge.poll(); // 큐에서 제거하고 무게 차감
                }

                // 2. 새 트럭이 다리에 올라갈 수 있는지 확인
                if (currentWeight + truck <= weight) {
                    // 올라갈 수 있는 경우
                    bridge.offer(truck);
                    currentWeight += truck;
                    break; // 현재 트럭은 다리에 올랐으므로 다음 트럭으로 넘어감
                } else {
                    // 올라갈 수 없는 경우
                    // 시간이 흘렀음을 표시하기 위해 무게 0인 공간을 추가
                    bridge.offer(0);
                }
            }
        }
        
        // 마지막 트럭이 다리에 올라간 시간에 + 다리 길이를 더해야 최종 시간
        return time + bridge_length;
    }
}