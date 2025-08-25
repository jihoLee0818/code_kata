class Solution {
    public int[] solution(String s) {
        int[] answer = new int[s.length()];

        for (int i = 0; i < s.length(); i++) {
            int index = 0;
            boolean check = false;
            for (int j = i-1; j >= 0; j--) {           
                index++;
                if (s.charAt(i) == s.charAt(j)){
                    check = true;
                    break;
                }
            }
            if (check == true) {
                answer[i] = index;
            } else {
                answer[i] = -1;
            }
        }
        return answer;
    }
}