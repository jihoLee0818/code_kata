class Solution {
    int count = 0;
    boolean found = false;
    String[] vowels = {"A", "E", "I", "O", "U"};

    public int solution(String word) {
        dfs("", word);
        return count;
    }

    private void dfs(String currentWord, String targetWord) {
        // 이미 목표 단어를 찾았으면 더 이상 탐색하지 않음
        if (found) {
            return;
        }

        // 현재 단어가 목표 단어와 일치하는지 확인
        if (currentWord.equals(targetWord)) {
            found = true;
            // count는 현재 단어의 순서이므로, 여기서 반환하지 않고 solution 메소드에서 최종 count를 반환
            return;
        }
        
        // 단어 길이가 5이면 더 이상 탐색하지 않음
        if (currentWord.length() >= 5) {
            return;
        }

        // A, E, I, O, U 순서로 다음 글자 추가
        for (int i = 0; i < 5; i++) {
            String nextWord = currentWord + vowels[i];
            count++; // 다음 단어를 만들었으므로 카운트 증가
            dfs(nextWord, targetWord);
            if (found) return; // 하위 탐색에서 찾았으면 즉시 종료
        }
    }
}