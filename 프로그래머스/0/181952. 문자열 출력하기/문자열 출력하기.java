import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        // 1. 입력을 받기 위한 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);
        
        // 2. 문자열 입력 받기
        String a = sc.next();
        
        // 3. 입력받은 문자열 그대로 출력하기
        System.out.print(a);
        
        // Scanner 닫기 (필수는 아니지만 좋은 습관)
        sc.close();
    }
}