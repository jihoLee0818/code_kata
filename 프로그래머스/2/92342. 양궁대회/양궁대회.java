class Solution {
    // 정답을 저장할 배열 (초기값은 -1)
    static int[] bestRyan = {-1};
    static int maxDiff = 0;
    static int[] apeachInfo;
    static int N;

    public int[] solution(int n, int[] info) {
        // 전역 변수 초기화 (테스트 케이스가 여러 개일 경우를 대비)
        bestRyan = new int[]{-1};
        maxDiff = 0;
        
        apeachInfo = info;
        N = n;

        // DFS 탐색 시작 (현재 쏜 화살 배열, 사용한 화살 수, 현재 과녁 인덱스)
        dfs(new int[11], 0, 0);

        return bestRyan;
    }

    // dfs(라이언의 화살 현황, 쏜 화살 수, 현재 보고 있는 과녁 인덱스)
    public void dfs(int[] ryan, int count, int index) {
        // [종료 조건] 화살을 다 썼거나, 마지막 과녁(0점)까지 다 본 경우
        if (count == N || index == 11) {
            // 화살이 남았다면 0점 과녁(인덱스 10)에 다 털어넣음
            // (배열은 참조값이므로 복사해서 사용해야 원본에 영향 안 줌)
            int[] currentRyan = ryan.clone();
            currentRyan[10] += (N - count); 
            
            // 점수 차이 계산
            int diff = getScoreDiff(currentRyan);

            // 라이언이 이기고(차이가 0보다 크고), 기존 최대 차이보다 크거나 같으면 갱신 시도
            if (diff > 0 && diff >= maxDiff) {
                if (diff > maxDiff) {
                    // 1. 차이가 더 크면 무조건 갱신
                    maxDiff = diff;
                    bestRyan = currentRyan;
                } else {
                    // 2. 차이가 같으면 "낮은 점수를 더 많이 맞힌" 경우인지 확인
                    if (isBetter(currentRyan, bestRyan)) {
                        bestRyan = currentRyan;
                    }
                }
            }
            return;
        }

        // [탐색 1] 이 점수(index)를 라이언이 가져가는 경우 (어피치보다 1발 더 쏨)
        // 남은 화살이 충분해야 함
        int arrowsNeeded = apeachInfo[index] + 1;
        if (count + arrowsNeeded <= N) {
            ryan[index] = arrowsNeeded;
            dfs(ryan, count + arrowsNeeded, index + 1);
            ryan[index] = 0; // 백트래킹 (원상복구)
        }

        // [탐색 2] 이 점수를 포기하는 경우 (0발 쏨)
        // (화살을 아껴서 뒤쪽의 낮은 점수나 다른 고득점을 노림)
        dfs(ryan, count, index + 1);
    }

    // 점수 차이 계산 (라이언 - 어피치)
    public int getScoreDiff(int[] ryan) {
        int rScore = 0;
        int aScore = 0;

        for (int i = 0; i <= 10; i++) {
            if (ryan[i] == 0 && apeachInfo[i] == 0) continue; // 둘 다 0발이면 무시

            if (ryan[i] > apeachInfo[i]) {
                rScore += (10 - i); // 라이언 득점
            } else {
                aScore += (10 - i); // 어피치 득점
            }
        }
        return rScore - aScore;
    }

    // 점수 차이가 같을 때, 낮은 점수를 더 많이 맞혔는지 확인
    // return true: current가 더 좋음 (갱신 필요)
    public boolean isBetter(int[] current, int[] best) {
        // 낮은 점수(인덱스 10)부터 비교
        for (int i = 10; i >= 0; i--) {
            if (current[i] > best[i]) return true;
            if (current[i] < best[i]) return false;
        }
        return false;
    }
}