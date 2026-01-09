class Solution {
    public int solution(int n, int w, int num) {
        // num 상자의 위치
        int numFloor = (num - 1) / w;
        int numPos  = (numFloor % 2 == 0)
                ? (num - 1) % w
                : w - 1 - (num - 1) % w;

        // n 상자의 위치
        int nFloor = (n - 1) / w;
        int nPos  = (nFloor % 2 == 0)
                ? (n - 1) % w
                : w - 1 - (n - 1) % w;

        // 위에 쌓인 층 수 차이
        int count = nFloor - numFloor;

        // 마지막 상자 층 방향에 따라 비교
        if (nFloor % 2 == 0) {
            // 마지막 상자가 왼→오 층에 있으면
            if (numPos <= nPos) count++;
        } else {
            // 오른→왼 층에 있으면
            if (numPos >= nPos) count++;
        }

        return count;
    }
}
