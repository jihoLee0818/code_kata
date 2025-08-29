import java.util.Arrays;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "";
        int cards1Index = 0;
        int cards2Index = 0;

        for(int i = 0; i < goal.length; i++){
            if(cards1[cards1Index].equals(goal[i])){
                answer += cards1[cards1Index];
                answer += ",";
                if(cards1Index+1 < cards1.length){
                    cards1Index++;
                }
            }else if(cards2[cards2Index].equals(goal[i])){
                answer += cards2[cards2Index];
                answer += ",";
                if(cards2Index+1 < cards2.length){
                    cards2Index++;
                }
            }
        }

        String[] str = answer.split(",");

        if(Arrays.equals(str,goal)){
            answer = "Yes";
        }else {
            answer = "No";
        }        
        return answer;
    }
}