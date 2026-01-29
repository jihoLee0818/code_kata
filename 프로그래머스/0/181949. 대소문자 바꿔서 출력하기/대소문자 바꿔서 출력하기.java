import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        
        // 문자열의 각 문자를 순회
        for (int i = 0; i < a.length(); i++) {
            char c = a.charAt(i);
            
            // 대문자인지 확인
            if (Character.isUpperCase(c)) {
                // 대문자라면 소문자로 변환하여 출력
                System.out.print(Character.toLowerCase(c));
            } else {
                // 소문자라면 대문자로 변환하여 출력
                System.out.print(Character.toUpperCase(c));
            }
        }
    }
}