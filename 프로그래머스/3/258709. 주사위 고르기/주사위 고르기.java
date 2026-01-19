import java.util.*;

class Solution {
    int N;
    List<Integer> answerList;
    int maxWins = -1;
    int[][] diceInfo;

    public int[] solution(int[][] dice) {
        this.N = dice.length;
        this.diceInfo = dice;
        
        // 1. 주사위 조합 생성 (0 ~ N-1 중 N/2개 선택)
        selectDice(0, 0, new ArrayList<>());
        
        // 결과 반환 (List -> int[])
        int[] answer = new int[N / 2];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i) + 1; // 1-based index로 변환
        }
        return answer;
    }

    // 백트래킹으로 A팀 주사위 선택
    private void selectDice(int depth, int start, List<Integer> selectedIndices) {
        if (depth == N / 2) {
            calculateWinRate(selectedIndices);
            return;
        }

        for (int i = start; i < N; i++) {
            selectedIndices.add(i);
            selectDice(depth + 1, i + 1, selectedIndices);
            selectedIndices.remove(selectedIndices.size() - 1);
        }
    }

    // 선택된 조합에 대해 승리 횟수 계산
    private void calculateWinRate(List<Integer> teamAIndices) {
        // B팀 인덱스 구하기
        List<Integer> teamBIndices = new ArrayList<>();
        boolean[] isSelected = new boolean[N];
        for (int idx : teamAIndices) isSelected[idx] = true;
        for (int i = 0; i < N; i++) {
            if (!isSelected[i]) teamBIndices.add(i);
        }

        // 2. 각 팀의 주사위 눈금 합의 모든 경우의 수 생성
        List<Integer> sumsA = new ArrayList<>();
        List<Integer> sumsB = new ArrayList<>();
        
        generateSums(0, 0, teamAIndices, sumsA);
        generateSums(0, 0, teamBIndices, sumsB);

        // 3. 이분 탐색을 위해 B팀의 합 정렬
        Collections.sort(sumsB);

        // A가 이기는 횟수 카운트
        int wins = 0;
        for (int scoreA : sumsA) {
            // sumsB에서 scoreA보다 작은 값의 개수 찾기 (Lower Bound)
            int count = lowerBound(sumsB, scoreA);
            wins += count;
        }

        // 최대 승수 갱신
        if (wins > maxWins) {
            maxWins = wins;
            answerList = new ArrayList<>(teamAIndices);
        }
    }

    // 주사위 묶음의 모든 합 경우의 수 생성 (DFS)
    private void generateSums(int depth, int currentSum, List<Integer> diceIndices, List<Integer> resultSums) {
        if (depth == diceIndices.size()) {
            resultSums.add(currentSum);
            return;
        }

        int diceIndex = diceIndices.get(depth);
        for (int i = 0; i < 6; i++) {
            generateSums(depth + 1, currentSum + diceInfo[diceIndex][i], diceIndices, resultSums);
        }
    }

    // 이분 탐색 (target보다 작은 원소의 개수 반환)
    private int lowerBound(List<Integer> list, int target) {
        int left = 0;
        int right = list.size();
        
        while (left < right) {
            int mid = (left + right) / 2;
            if (list.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left; // target보다 작은 값들의 개수이자, target이 삽입될 위치
    }
}