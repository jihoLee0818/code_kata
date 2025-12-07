class Solution {
    public int solution(int[] a) {
        // 풍선이 1개면 무조건 살아남음
        if (a.length == 1) return 1;
        
        int n = a.length;
        
        // 왼쪽 방향 최솟값 배열
        int[] leftMin = new int[n];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (a[i] < min) min = a[i];
            leftMin[i] = min;
        }
        
        // 오른쪽 방향 최솟값 배열
        int[] rightMin = new int[n];
        min = Integer.MAX_VALUE;
        for (int i = n - 1; i >= 0; i--) {
            if (a[i] < min) min = a[i];
            rightMin[i] = min;
        }
        
        // 양 끝 값은 무조건 살아남을 수 있으므로 기본값 2
        // (단, 배열 길이가 1인 경우는 위에서 처리함)
        int answer = 2; 
        
        // 1번부터 n-2번까지만 검사 (양 끝 제외)
        for (int i = 1; i < n - 1; i++) {
            // "내 왼쪽 그룹의 최솟값"과 "내 오른쪽 그룹의 최솟값" 확인
            int l = leftMin[i - 1];
            int r = rightMin[i + 1];
            
            // 내가 왼쪽 최솟값보다 작거나 OR 오른쪽 최솟값보다 작으면 생존 가능
            // (둘 다보다 크면 생존 불가)
            if (a[i] < l || a[i] < r) {
                answer++;
            }
        }
        
        return answer;
    }
}