import java.util.Arrays;

class Solution {
    public int solution(int a, int b, int c, int d) {
        // 1. 주사위 숫자를 배열에 담고 오름차순 정렬합니다.
        int[] dice = {a, b, c, d};
        Arrays.sort(dice);
        
        // 2. 조건에 따라 점수를 계산합니다.
        // 네 숫자가 모두 같은 경우
        if (dice[0] == dice[3]) {
            return 1111 * dice[0];
        } 
        // 세 숫자가 같은 경우 (앞의 3개가 같거나, 뒤의 3개가 같거나)
        else if (dice[0] == dice[2]) {
            int p = dice[0];
            int q = dice[3];
            return (10 * p + q) * (10 * p + q);
        } else if (dice[1] == dice[3]) {
            int p = dice[1];
            int q = dice[0];
            return (10 * p + q) * (10 * p + q);
        } 
        // 두 개씩 같은 숫자가 나온 경우
        else if (dice[0] == dice[1] && dice[2] == dice[3]) {
            int p = dice[0];
            int q = dice[2];
            // p - q가 음수가 될 수 있으므로 Math.abs()로 절댓값을 구합니다.
            return (p + q) * Math.abs(p - q);
        } 
        // 두 숫자만 같고 나머지 둘은 다른 경우
        else if (dice[0] == dice[1]) {
            return dice[2] * dice[3];
        } else if (dice[1] == dice[2]) {
            return dice[0] * dice[3];
        } else if (dice[2] == dice[3]) {
            return dice[0] * dice[1];
        } 
        // 네 숫자가 모두 다른 경우
        else {
            // 배열이 오름차순 정렬되어 있으므로 인덱스 0이 최솟값입니다.
            return dice[0];
        }
    }
}