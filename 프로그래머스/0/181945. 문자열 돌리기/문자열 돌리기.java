import java.util.Scanner;

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String a = sc.next();
        
        // 문자열의 길이만큼 반복하면서 한 글자씩 출력
        for (int i = 0; i < a.length(); i++) {
            // charAt(i)를 사용하여 i번째 문자를 가져오고, 
            // println을 사용하여 줄바꿈과 함께 출력합니다.
            System.out.println(a.charAt(i));
        }
    }
}