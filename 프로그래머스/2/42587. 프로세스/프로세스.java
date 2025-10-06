import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] priorities, int location) {
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new int[]{i, priorities[i]});
        }

        int printOrder = 0;

        while (!queue.isEmpty()) {
            int[] currentDoc = queue.poll();
            int currentLocation = currentDoc[0];
            int currentPriority = currentDoc[1];

            boolean hasHigherPriority = false;
            for (int[] docInQueue : queue) {
                if (docInQueue[1] > currentPriority) {
                    hasHigherPriority = true;
                    break;
                }
            }

            if (hasHigherPriority) {
                queue.offer(currentDoc);
            } else {
                printOrder++;
                if (currentLocation == location) {
                    return printOrder;
                }
            }
        }
        
        return printOrder;
    }
}