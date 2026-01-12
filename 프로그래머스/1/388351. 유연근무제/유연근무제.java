class Solution {
    public int solution(int[] schedules, int[][] timelogs, int startday) {
        int answer = 0;
        int n = schedules.length; // 직원 수
        
        for (int i = 0; i < n; i++) {
            // 1. 인정 시각(limit) 계산
            int scheduledTime = schedules[i];
            int hour = scheduledTime / 100;
            int min = scheduledTime % 100;
            
            min += 10; // 10분 추가
            if (min >= 60) { // 60분이 넘어가면 시(hour) 올림 처리
                min -= 60;
                hour += 1;
            }
            int limitTime = hour * 100 + min;
            
            // 2. 7일간의 기록 확인
            boolean isSuccess = true;
            for (int day = 0; day < 7; day++) {
                // 현재 요일 계산 (1:월 ~ 7:일)
                // (시작요일 + 경과일 - 1) % 7 + 1 공식을 사용하면 1~7 사이로 순환됨
                int currentDay = (startday + day - 1) % 7 + 1;
                
                // 주말(6:토, 7:일)이면 체크 패스
                if (currentDay == 6 || currentDay == 7) {
                    continue;
                }
                
                // 평일인데 지각했다면 실패 처리
                if (timelogs[i][day] > limitTime) {
                    isSuccess = false;
                    break;
                }
            }
            
            // 한 번도 지각하지 않았다면 카운트 증가
            if (isSuccess) {
                answer++;
            }
        }
        
        return answer;
    }
}