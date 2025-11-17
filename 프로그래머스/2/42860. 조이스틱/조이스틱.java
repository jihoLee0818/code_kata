class Solution {
    public int solution(String name) {
        int n = name.length();
        int verticalCost = 0; // 1. 상하 조작 비용
        
        // 2. 좌우 조작 비용
        // 2-1. [기본] 오른쪽으로만 가는 비용 (예: "JEROEN")
        int horizontalCost = n - 1; 

        for (int i = 0; i < n; i++) {
            // 1. 상하 비용 계산
            char c = name.charAt(i);
            verticalCost += Math.min(c - 'A', 'Z' - c + 1);

            // 2-2. [A 덩어리 스킵] 계산
            // i번 인덱스 다음부터 'A'가 아닌 글자가 언제 나오는지 찾음
            int nextIdx = i + 1;
            while (nextIdx < n && name.charAt(nextIdx) == 'A') {
                nextIdx++;
            }
            
            // nextIdx는 i 다음에 나오는 'A'가 아닌 첫 글자의 인덱스 (없으면 n)

            // [A 덩어리 스킵 1] (예: "BBAAAB")
            // (0 -> i) 오른쪽으로 이동 + (i -> 0) 되돌아감 + (0 -> nextIdx) 왼쪽으로 이동
            int costRightThenLeft = (i * 2) + (n - nextIdx);
            
            // [A 덩어리 스킵 2] (예: "JAAAAZ")
            // (0 -> nextIdx) 왼쪽으로 이동 + (nextIdx -> 0) 되돌아감 + (0 -> i) 오른쪽으로 이동
            int costLeftThenRight = i + ((n - nextIdx) * 2);

            // 2-3. 세 가지 방법 중 최솟값 갱신
            horizontalCost = Math.min(horizontalCost, costRightThenLeft);
            horizontalCost = Math.min(horizontalCost, costLeftThenRight);
        }

        return verticalCost + horizontalCost;
    }
}