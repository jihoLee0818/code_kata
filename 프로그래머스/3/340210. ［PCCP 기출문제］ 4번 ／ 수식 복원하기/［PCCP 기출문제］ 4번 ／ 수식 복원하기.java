import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        // 1. 수식에 등장하는 숫자 중 최댓값 찾기 (최소 진법 결정을 위해)
        int maxDigit = 0;
        for (String expr : expressions) {
            for (char c : expr.toCharArray()) {
                if (Character.isDigit(c)) {
                    maxDigit = Math.max(maxDigit, c - '0');
                }
            }
        }
        
        // 2. 가능한 진법 후보 찾기 (maxDigit + 1 ~ 9)
        // 완성된 수식(X가 없는 식)을 모두 만족하는 진법만 리스트에 담음
        List<Integer> validBases = new ArrayList<>();
        
        for (int base = maxDigit + 1; base <= 9; base++) {
            boolean isPossible = true;
            
            for (String expr : expressions) {
                String[] parts = expr.split(" ");
                String A = parts[0];
                String op = parts[1];
                String B = parts[2];
                String C = parts[4];
                
                // 결과가 X인 식은 검증에 사용할 수 없음
                if (C.equals("X")) continue;
                
                // 해당 진법으로 변환하여 수식이 성립하는지 확인
                try {
                    int numA = Integer.parseInt(A, base);
                    int numB = Integer.parseInt(B, base);
                    int numC = Integer.parseInt(C, base);
                    
                    if (op.equals("+")) {
                        if (numA + numB != numC) isPossible = false;
                    } else { // "-"
                        if (numA - numB != numC) isPossible = false;
                    }
                } catch (NumberFormatException e) {
                    // 해당 진법으로 변환 불가능한 숫자가 포함된 경우 (사실 maxDigit 로직으로 걸러지긴 함)
                    isPossible = false;
                }
                
                if (!isPossible) break;
            }
            
            if (isPossible) {
                validBases.add(base);
            }
        }
        
        // 3. X가 포함된 수식의 결과 도출
        List<String> answerList = new ArrayList<>();
        
        for (String expr : expressions) {
            String[] parts = expr.split(" ");
            String C = parts[4];
            
            // X가 포함된 수식만 처리
            if (C.equals("X")) {
                String A = parts[0];
                String op = parts[1];
                String B = parts[2];
                
                String expectedResult = null;
                boolean isAmbiguous = false;
                
                // 가능한 모든 진법에 대해 계산 수행
                for (int base : validBases) {
                    int numA = Integer.parseInt(A, base);
                    int numB = Integer.parseInt(B, base);
                    int resInt = 0;
                    
                    if (op.equals("+")) resInt = numA + numB;
                    else resInt = numA - numB;
                    
                    // 결과를 해당 진법의 문자열로 변환
                    String resStr = Integer.toString(resInt, base);
                    
                    if (expectedResult == null) {
                        expectedResult = resStr;
                    } else {
                        // 이전 진법에서의 결과와 다르다면 확정 불가
                        if (!expectedResult.equals(resStr)) {
                            isAmbiguous = true;
                            break;
                        }
                    }
                }
                
                // 결과 포맷팅
                String resultVal = isAmbiguous ? "?" : expectedResult;
                answerList.add(A + " " + op + " " + B + " = " + resultVal);
            }
        }
        
        return answerList.toArray(new String[0]);
    }
}