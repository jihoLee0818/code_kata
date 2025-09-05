class Solution {
    public int solution(String[] babbling) {
        int answer = 0;
        
        // 가능한 발음을 저장한 possible 배열
        String[] possible = {"aya", "ye", "woo", "ma"};
        // 연속된 발음을 저장한 impossible 배열
        String[] impossible = {"ayaaya", "yeye", "woowoo", "mama"};
        
        // 반복문을 통해 babbling 배열 순회
        for (String element : babbling) {
            // 연속된 발음은 "X"로 변환
            for (String check : impossible) {
                element = element.replace(check, "X");
            }
            
            // 가능한 발음을 "O"로 변환
            for (String check : possible) {
                element = element.replace(check, "O");
            }
            
            int check = 0;
            // 불가능한 발음 존재시 check +1
            for (int i = 0; i < element.length(); i++) {
                if (element.charAt(i) != 'O') {
                    check++;
                    break;
                }
            }
            
            // check가 0이면 가능한 발음으로 answer +1
            if (check == 0) answer++;
        }
        
        return answer;
    }
}