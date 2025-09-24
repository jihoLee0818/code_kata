class Solution {

    public int solution(int n, int a, int b) {
        int round = 1;
        a--;
        b--;

        // a와 b가 같은 조에 속하게 될 때까지 반복
        while ((a /= 2) != (b /= 2)) round++;

        return round;
    }
}