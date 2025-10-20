import java.util.Stack;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        
        // 1. 보조 벨트(스택) 준비
        Stack<Integer> subBelt = new Stack<>();
        
        // 2. 기사님이 원하는 순서
        int orderIndex = 0;

        // 3. 메인 벨트는 1번 상자부터 n번 상자까지 순서대로 진행
        for (int mainBeltBox = 1; mainBeltBox <= order.length; mainBeltBox++) {
            
            // 현재 메인 벨트의 상자가 원하는 상자인 경우
            if (mainBeltBox == order[orderIndex]) {
                answer++;       // 트럭에 싣기
                orderIndex++;   // 다음 순서로
            
            } else {
                // 당장 필요한 상자가 아니면 보조 벨트에 넣기
                subBelt.push(mainBeltBox);
            }

            // [핵심] 
            // 메인 벨트에서 상자를 실었든, 보조 벨트에 넣었든
            // 현재 원하는 상자가 보조 벨트 맨 위에 있는지 "계속" 확인해야 함
            while (!subBelt.isEmpty() && subBelt.peek() == order[orderIndex]) {
                subBelt.pop();    // 보조 벨트에서 꺼내서
                answer++;       // 트럭에 싣기
                orderIndex++;   // 다음 순서로
            }
        }
        
        return answer;
    }
}