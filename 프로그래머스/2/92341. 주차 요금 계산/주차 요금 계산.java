import java.util.*;

class Solution {
    public int[] solution(int[] fees, String[] records) {
        // Key: 차량번호, Value: 입차시간 (분 단위)
        Map<String, Integer> parkingStatus = new HashMap<>();
        // Key: 차량번호, Value: 누적 주차시간 (분 단위)
        Map<String, Integer> totalParkingTime = new TreeMap<>(); // TreeMap으로 자동 정렬

        // 1. 입출차 기록 처리
        for (String record : records) {
            String[] info = record.split(" ");
            int time = timeToMinutes(info[0]);
            String carNumber = info[1];
            String status = info[2];

            if (status.equals("IN")) {
                parkingStatus.put(carNumber, time);
            } else { // "OUT"
                int inTime = parkingStatus.remove(carNumber);
                int parkedTime = time - inTime;
                totalParkingTime.put(carNumber, totalParkingTime.getOrDefault(carNumber, 0) + parkedTime);
            }
        }

        // 2. 출차 기록 없는 차량 처리 (23:59 출차)
        int endOfDay = timeToMinutes("23:59");
        for (String carNumber : parkingStatus.keySet()) {
            int inTime = parkingStatus.get(carNumber);
            int parkedTime = endOfDay - inTime;
            totalParkingTime.put(carNumber, totalParkingTime.getOrDefault(carNumber, 0) + parkedTime);
        }

        // 3. 요금 계산
        List<Integer> answerList = new ArrayList<>();
        for (int time : totalParkingTime.values()) {
            answerList.add(calculateFee(time, fees));
        }

        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }

    // "HH:MM" 형식의 시간을 분으로 변환하는 헬퍼 메소드
    private int timeToMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    // 누적 시간을 바탕으로 요금을 계산하는 헬퍼 메소드
    private int calculateFee(int totalMinutes, int[] fees) {
        int baseTime = fees[0];
        int baseFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];

        if (totalMinutes <= baseTime) {
            return baseFee;
        }

        // 기본 요금 + 추가 요금 (초과 시간에 대해 올림 처리)
        int extraTime = totalMinutes - baseTime;
        int extraFee = (int) Math.ceil((double) extraTime / unitTime) * unitFee;
        
        return baseFee + extraFee;
    }
}