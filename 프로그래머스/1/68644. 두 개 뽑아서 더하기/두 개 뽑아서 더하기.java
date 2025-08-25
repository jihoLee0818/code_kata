import java.util.*;
 
class Solution {
    public int[] solution(int[] numbers) {
        int[] temp = new int[numbers.length * (numbers.length - 1) / 2];
        int idx = 0;
 
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                temp[idx++] = numbers[i] + numbers[j];
            }
        }
 
        int[] result = Arrays.stream(temp).distinct().sorted().toArray();
 
        return result;
    }
}