import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // 1. 문자열과 반복 횟수 입력 받기
        String str = sc.next();
        int n = sc.nextInt();
        
        // 2. n번 만큼 반복하며 출력하기
        for (int i = 0; i < n; i++) {
            System.out.print(str);
        }
        
        // Scanner 리소스 닫기 (선택)
        sc.close();
    }
}