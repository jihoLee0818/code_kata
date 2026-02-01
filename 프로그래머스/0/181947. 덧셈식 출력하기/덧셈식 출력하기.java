import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. 두 정수 입력 받기
        int a = sc.nextInt();
        int b = sc.nextInt();
        
        // 2. 덧셈 결과 계산
        int c = a + b;

        // 3. "a + b = c" 형식으로 출력
        // 문자열 연결(+)을 사용하여 변수와 기호를 조합합니다.
        System.out.println(a + " + " + b + " = " + c);
    }
}