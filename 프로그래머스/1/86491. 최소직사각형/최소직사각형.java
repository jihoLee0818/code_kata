import java.util.*;


class Solution {
    public int solution(int[][] sizes) {

        int garo = 0;
        int sero = 0;

        for(int i = 0; i < sizes.length; i++) {
            int max_now = Math.max(sizes[i][0], sizes[i][1]);
            int min_now = Math.min(sizes[i][0], sizes[i][1]);

            garo = Math.max(garo, max_now);
            sero = Math.max(sero, min_now);
        }



        return garo*sero;
    }
}