import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        Queue<Integer> daysToComplete = new LinkedList<>();
        for (int i = 0; i < progresses.length; i++) {
            int remainingProgress = 100 - progresses[i];
            int days = (int) Math.ceil((double) remainingProgress / speeds[i]);
            daysToComplete.offer(days);
        }
        List<Integer> answerList = new ArrayList<>();

        while (!daysToComplete.isEmpty()) {
            int deployCount = 0;
            int standardDay = daysToComplete.poll();
            deployCount++;

            while (!daysToComplete.isEmpty() && daysToComplete.peek() <= standardDay) {
                daysToComplete.poll();
                deployCount++;
            }
            
            answerList.add(deployCount);
        }
        
        return answerList.stream().mapToInt(Integer::intValue).toArray();
    }
}