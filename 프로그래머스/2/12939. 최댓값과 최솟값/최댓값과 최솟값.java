import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(String s) {
        String answer = "";

        int max = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).max().getAsInt();
        int min = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).min().getAsInt();   

        return String.valueOf(min) +" "+String.valueOf(max);
    }
}