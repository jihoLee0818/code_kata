import java.util.HashSet;
import java.util.Set;

class Solution {
    // 2. 중복 제거를 위해 생성된 모든 숫자를 저장할 Set
    Set<Integer> numberSet = new HashSet<>();
    
    public int solution(String numbers) {
        // 1. 모든 숫자 조합 생성
        // "17" -> ["1", "7"]
        String[] digits = numbers.split("");
        boolean[] visited = new boolean[digits.length];
        
        // DFS 시작: 빈 문자열에서 시작하여 모든 길이의 순열을 생성
        dfs("", digits, visited);
        
        // 3. 소수 판별
        int primeCount = 0;
        for (int num : numberSet) {
            if (isPrime(num)) {
                primeCount++;
            }
        }
        
        // 4. 개수 반환
        return primeCount;
    }

    /**
     * @param currentStr 현재까지 조합된 숫자 문자열
     * @param digits     사용 가능한 숫자 조각 배열
     * @param visited    숫자 조각 사용 여부
     */
    private void dfs(String currentStr, String[] digits, boolean[] visited) {
        // 모든 숫자 조각을 순회하며 다음에 붙일 숫자를 찾음
        for (int i = 0; i < digits.length; i++) {
            if (!visited[i]) {
                // 방문 처리
                visited[i] = true;
                
                String newStr = currentStr + digits[i];
                // "011"과 같이 0으로 시작하는 경우도 숫자로 변환 (e.g., 11)
                numberSet.add(Integer.parseInt(newStr)); 
                
                // 다음 숫자 조합을 위해 재귀 호출
                dfs(newStr, digits, visited);
                
                // 백트래킹: 현재 경로 탐색이 끝났으므로 방문 표시 해제
                visited[i] = false; 
            }
        }
    }

    /**
     * 소수인지 판별하는 헬퍼 메소드
     */
    private boolean isPrime(int n) {
        // 0과 1은 소수가 아님
        if (n <= 1) {
            return false;
        }
        
        // 2부터 숫자의 제곱근까지 나누어 떨어지는 수가 있는지 확인
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false; // 나누어 떨어지면 소수가 아님
            }
        }
        
        return true; // 나누어 떨어지는 수가 없으면 소수임
    }
}