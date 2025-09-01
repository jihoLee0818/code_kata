import java.util.*;

class Solution {
    public int[] solution(int[] answers) {
        int [] A = new int [] {1,2,3,4,5};
        int [] B = new int [] {2,1,2,3,2,4,2,5};
        int [] C = new int [] {3,3,1,1,2,2,4,4,5,5};

        int a_cnt, b_cnt, c_cnt;
        a_cnt = b_cnt = c_cnt = 0;

        for(int i = 0; i < answers.length; i++){
            if(A[i%5] == answers[i]) a_cnt++;
            if(B[i%8] == answers[i]) b_cnt++;
            if(C[i%10] == answers[i]) c_cnt++;
        }

        int max = 0;
        max = Math.max(a_cnt, b_cnt);
        max = Math.max(max, c_cnt);

        ArrayList<Integer> list = new ArrayList<>();
        if(max == a_cnt) list.add(1);
        if(max == b_cnt) list.add(2);
        if(max == c_cnt) list.add(3);

        int [] answer = new int [list.size()];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
}