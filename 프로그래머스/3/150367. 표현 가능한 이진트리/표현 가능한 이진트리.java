class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            // 1. 이진수 변환
            String binary = Long.toBinaryString(numbers[i]);
            
            // 2. 포화 이진트리 길이로 맞추기 (Padding)
            String fullBinary = getFullBinaryString(binary);
            
            // 3. 트리 구조 유효성 검사
            if (isValidTree(fullBinary, 0, fullBinary.length() - 1, false)) {
                answer[i] = 1;
            } else {
                answer[i] = 0;
            }
        }
        
        return answer;
    }

    // 이진수 문자열을 포화 이진트리 길이(2^n - 1)에 맞춰 앞에 0을 채우는 함수
    private String getFullBinaryString(String binary) {
        int len = binary.length();
        int nodeCount = 1; // 포화 이진트리의 노드 개수 (1, 3, 7, 15...)
        int level = 1;     // 트리 높이

        // 현재 길이보다 크거나 같은 포화 이진트리 노드 개수 찾기
        while (len > nodeCount) {
            level++;
            nodeCount = (int) Math.pow(2, level) - 1;
        }

        // 부족한 만큼 앞에 '0' 추가
        StringBuilder sb = new StringBuilder();
        int diff = nodeCount - len;
        for (int i = 0; i < diff; i++) {
            sb.append("0");
        }
        sb.append(binary);
        
        return sb.toString();
    }

    /**
     * 재귀적으로 트리의 유효성을 검사하는 함수
     * @param s : 전체 이진 문자열
     * @param start : 현재 서브트리의 시작 인덱스
     * @param end : 현재 서브트리의 끝 인덱스
     * @param isParentDummy : 부모 노드가 더미('0')인지 여부
     */
    private boolean isValidTree(String s, int start, int end, boolean isParentDummy) {
        int mid = (start + end) / 2; // 현재 트리의 루트 인덱스
        char root = s.charAt(mid);

        // [핵심 로직] 부모가 더미('0')인데 현재 노드가 실제 노드('1')라면 불가능한 구조
        if (isParentDummy && root == '1') {
            return false;
        }

        // 리프 노드에 도달하면 검사 종료 (성공)
        if (start == end) {
            return true;
        }

        // 현재 노드가 '0'이면 자식들도 '0'이어야 함 -> isParentDummy = true 전달
        // 현재 노드가 '1'이면 자식들은 상관없음 -> isParentDummy = false 전달
        // 단, 이미 부모가 더미였다면(isParentDummy=true), 현재 노드는 무조건 '0'일 것이므로 true가 유지됨.
        boolean nextDummyCheck = (root == '0');

        // 왼쪽 서브트리와 오른쪽 서브트리 모두 유효해야 함
        return isValidTree(s, start, mid - 1, nextDummyCheck) && 
               isValidTree(s, mid + 1, end, nextDummyCheck);
    }
}