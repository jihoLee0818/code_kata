class Solution {
    public int solution(int n, long l, long r) {
        // r까지의 1의 개수 - (l-1)까지의 1의 개수
        return (int) (query(n, r) - query(n, l - 1));
    }

    // n단계 비트열에서 앞에서부터 limit개(인덱스 0 ~ limit-1) 안에 있는 1의 개수 반환
    private long query(int n, long limit) {
        // [기저 조건 1] 범위가 0이면 1의 개수도 0
        if (limit == 0) {
            return 0;
        }
        // [기저 조건 2] n=0이면 "1" 한 글자이므로, limit이 1 이상이면 1 반환
        if (n == 0) {
            return 1;
        }

        // 현재 단계의 5등분 된 한 블록의 길이 (5^(n-1))
        long partSize = (long) Math.pow(5, n - 1);
        // 현재 단계의 한 블록에 들어있는 1의 개수 (4^(n-1))
        long partOnes = (long) Math.pow(4, n - 1);

        // limit이 5개 블록 중 몇 번째 블록에 속하는지 (0 ~ 4)
        long idx = limit / partSize;
        // 해당 블록 내부에서의 상대 위치
        long rem = limit % partSize;

        if (idx < 2) {
            // 0, 1번 블록: 지나온 블록 수(idx)만큼 더하고 재귀 호출
            return idx * partOnes + query(n - 1, rem);
        } else if (idx == 2) {
            // 2번 블록(가운데 0): 앞선 0, 1번 블록은 꽉 찼음. 여기는 모두 0이므로 종료.
            return 2 * partOnes;
        } else {
            // 3, 4번 블록: 2번 블록(0)을 제외한 앞선 블록 수(idx-1)만큼 더하고 재귀 호출
            return (idx - 1) * partOnes + query(n - 1, rem);
        }
    }
}