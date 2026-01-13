class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        // 1. 모든 시간을 초(second) 단위로 변환
        int totalLen = toSeconds(video_len);
        int currentPos = toSeconds(pos);
        int startOp = toSeconds(op_start);
        int endOp = toSeconds(op_end);

        // 2. 초기 상태에서도 오프닝 구간인지 체크 (명령어 수행 전)
        if (currentPos >= startOp && currentPos <= endOp) {
            currentPos = endOp;
        }

        // 3. 명령어 처리
        for (String command : commands) {
            if (command.equals("prev")) {
                currentPos -= 10;
                if (currentPos < 0) currentPos = 0; // 0초 미만이면 0초로
            } else if (command.equals("next")) {
                currentPos += 10;
                if (currentPos > totalLen) currentPos = totalLen; // 영상 길이 초과하면 끝으로
            }

            // 4. 이동 후 오프닝 구간 체크 (오프닝 건너뛰기)
            if (currentPos >= startOp && currentPos <= endOp) {
                currentPos = endOp;
            }
        }

        // 5. 결과를 "mm:ss" 포맷으로 변환하여 반환
        return toTimeStr(currentPos);
    }

    // "mm:ss" -> 초 변환 메소드
    private int toSeconds(String time) {
        String[] parts = time.split(":");
        int min = Integer.parseInt(parts[0]);
        int sec = Integer.parseInt(parts[1]);
        return min * 60 + sec;
    }

    // 초 -> "mm:ss" 변환 메소드
    private String toTimeStr(int totalSeconds) {
        int min = totalSeconds / 60;
        int sec = totalSeconds % 60;
        // %02d: 2자리 정수로 포맷팅 (빈 자리는 0으로 채움)
        return String.format("%02d:%02d", min, sec);
    }
}