class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        // 시간을 초 단위로 변환
        int start = toSeconds(h1, m1, s1);
        int end = toSeconds(h2, m2, s2);

        // (종료 시간까지의 알람 수) - (시작 시간까지의 알람 수)
        int answer = countAlarms(end) - countAlarms(start);

        // 시작 시간 정각에 알람이 울리는 경우 포함 (+1)
        if (isAlarmAt(start)) {
            answer++;
        }

        return answer;
    }

    // 0시 0분 0초부터 time(초)까지 울린 알람 횟수 계산
    private int countAlarms(int time) {
        // 초침과 분침이 겹치는 횟수 계산 (주기: 3600/59 초)
        int smAlarms = (int) ((long) time * 59 / 3600);
        
        // 초침과 시침이 겹치는 횟수 계산 (주기: 43200/719 초)
        int shAlarms = (int) ((long) time * 719 / 43200);

        // 12시 0분 0초(43200초) 이상이면, 
        // 12시에 시침, 분침, 초침이 모두 겹치는 케이스가 중복(2회) 카운트되므로 1회 차감
        int penalty = (time >= 43200) ? 1 : 0;

        return smAlarms + shAlarms - penalty;
    }

    // 해당 시간에 알람이 울리는지 확인 (시작 시간 보정용)
    private boolean isAlarmAt(int time) {
        // 초침과 분침이 겹치거나, 초침과 시침이 겹치면 true
        // (time * 59) % 3600 == 0 은 초침과 분침이 정확히 겹치는 조건
        // (time * 719) % 43200 == 0 은 초침과 시침이 정확히 겹치는 조건
        return (long) time * 59 % 3600 == 0 || (long) time * 719 % 43200 == 0;
    }

    // 시:분:초를 초 단위 정수로 변환
    private int toSeconds(int h, int m, int s) {
        return h * 3600 + m * 60 + s;
    }
}