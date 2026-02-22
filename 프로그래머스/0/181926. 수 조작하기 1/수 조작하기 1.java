class Solution {
    public int solution(int n, String control) {
        // 문자열을 문자 배열로 변환하여 하나씩 순회합니다.
        for (char ch : control.toCharArray()) {
            switch (ch) {
                case 'w':
                    n += 1;
                    break;
                case 's':
                    n -= 1;
                    break;
                case 'd':
                    n += 10;
                    break;
                case 'a':
                    n -= 10;
                    break;
            }
        }
        
        // 최종적으로 조작된 n의 값을 반환합니다.
        return n;
    }
}