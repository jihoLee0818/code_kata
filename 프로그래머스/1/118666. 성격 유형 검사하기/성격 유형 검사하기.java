class Solution {

    char[][] categories = {{'R', 'T'}, {'C', 'F'}, {'J', 'M'}, {'A', 'N'}};

    public String solution(String[] survey, int[] choices) {
        int[] score = new int['Z' - 'A' + 1];

        for (int i = 0; i < choices.length; i++) {
            // 선택 점수가 4보다 크면 동의 쪽에 점수
            if (choices[i] > 4) score[survey[i].charAt(1) - 'A'] += choices[i] - 4;
            // 선택 점수가 4보다 작으면 비동의 쪽에 점수
            else if (choices[i] < 4) score[survey[i].charAt(0) - 'A'] += 4 - choices[i];
        }

        StringBuilder sb = new StringBuilder();

        for (char[] category : categories) {
            // 점수가 같을 경우 사전 순으로 빠른 쪽 선택
            sb.append((score[category[0] - 'A'] >= score[category[1] - 'A']) ? category[0] : category[1]);
        }

        return sb.toString();
    }
}