import java.util.ArrayList;

class Solution {
    public int[] solution(int[] arr) {
        // 스택 역할을 할 ArrayList 생성
        ArrayList<Integer> stk = new ArrayList<>();
        int i = 0; // 인덱스 변수
        
        // i가 arr의 길이보다 작을 동안 반복
        while (i < arr.length) {
            // 1. stk가 비어있는 경우
            if (stk.isEmpty()) {
                stk.add(arr[i]);
                i++;
            } 
            // 2. stk의 마지막 원소가 arr[i]보다 작은 경우
            else if (stk.get(stk.size() - 1) < arr[i]) {
                stk.add(arr[i]);
                i++;
            } 
            // 3. stk의 마지막 원소가 arr[i]보다 크거나 같은 경우
            else {
                // 맨 마지막 원소를 제거 (i는 증가시키지 않음)
                stk.remove(stk.size() - 1);
            }
        }
        
        // 완성된 ArrayList를 int[] 배열로 변환
        int[] answer = new int[stk.size()];
        for (int j = 0; j < stk.size(); j++) {
            answer[j] = stk.get(j);
        }
        
        return answer;
    }
}