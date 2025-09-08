class Solution {

    public String solution(String X, String Y) {
        StringBuilder sb = new StringBuilder();
        int[][] count = new int[2][10];  // X와 Y에서 각 숫자의 개수를 세기 위한 배열

        // X와 Y의 각 숫자 등장 횟수 카운팅
        for (char c : X.toCharArray()) count[0][c - '0']++;
        for (char c : Y.toCharArray()) count[1][c - '0']++;

        // 9부터 0까지 공통으로 등장한 숫자를 가능한 만큼 StringBuilder에 추가
        for (int i = 9; i >= 0; i--) {
            int pair = Math.min(count[0][i], count[1][i]);
            for (int j = 0; j < pair; j++) sb.append(i);
        }

        // 짝꿍이 존재하지 않는 경우
        if (sb.length() == 0) return "-1";

        // 모든 짝꿍 숫자가 0인 경우
        if (sb.charAt(0) == '0') return "0";

        return sb.toString();
    }
}