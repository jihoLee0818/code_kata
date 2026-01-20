import java.util.*;

class Solution {
    // 집합에서 합이 target이 되는 쌍을 찾아 제거하는 헬퍼 함수
    // 성공 시 true 반환
    private boolean makePair(Set<Integer> set1, Set<Integer> set2, int target) {
        for (int num : set1) {
            int pair = target - num;
            if (set2.contains(pair)) {
                // set1과 set2가 같은 객체일 때(비용 0, 비용 2 케이스) 중복 사용 방지
                // 예: target이 10인데 5가 있는 경우, 5+5는 불가능하므로 제외
                if (set1 == set2 && num == pair) continue;
                
                // 카드 사용 (제거)
                set1.remove(num);
                set2.remove(pair);
                return true;
            }
        }
        return false;
    }

    public int solution(int coin, int[] cards) {
        int n = cards.length;
        int target = n + 1;
        
        // 내 손에 있는 카드 (비용 0 후보)
        Set<Integer> hand = new HashSet<>();
        // 뽑았지만 아직 동전을 안 쓴 카드 (비용 1, 2 후보)
        Set<Integer> drawn = new HashSet<>();
        
        // 1. 초기 카드 분배 (n/3장)
        int idx = 0;
        for (; idx < n / 3; idx++) {
            hand.add(cards[idx]);
        }
        
        int round = 1;
        
        // 2. 게임 진행 (카드 뭉치가 빌 때까지)
        while (idx < n) {
            // 카드 2장 뽑기 (일단 drawn에 저장)
            drawn.add(cards[idx++]);
            drawn.add(cards[idx++]);
            
            // 라운드 통과 시도
            boolean pass = false;
            
            // [우선순위 1] 내 손에 있는 것끼리 해결 (동전 0개)
            if (makePair(hand, hand, target)) {
                pass = true;
            } 
            // [우선순위 2] 내 손 1장 + 뽑은 카드 1장 (동전 1개)
            else if (coin >= 1 && makePair(hand, drawn, target)) {
                coin -= 1;
                pass = true;
            } 
            // [우선순위 3] 뽑은 카드 2장 (동전 2개)
            else if (coin >= 2 && makePair(drawn, drawn, target)) {
                coin -= 2;
                pass = true;
            }
            
            // 이번 라운드를 통과하지 못했으면 게임 종료
            if (!pass) {
                break;
            }
            
            round++;
        }
        
        return round;
    }
}