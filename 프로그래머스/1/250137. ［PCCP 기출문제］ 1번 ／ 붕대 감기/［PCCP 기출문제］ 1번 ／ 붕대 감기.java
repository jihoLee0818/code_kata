class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health; // 최대 체력 저장
        int curHealth = health; // 현재 체력
        
        int t = bandage[0];     // 시전 시간 (연속 성공 필요 시간)
        int x = bandage[1];     // 초당 회복량
        int y = bandage[2];     // 추가 회복량
        
        int lastAttackTime = 0; // 마지막으로 공격받은 시간 (초기값 0)
        
        for (int[] attack : attacks) {
            int attackTime = attack[0];
            int damage = attack[1];
            
            // 1. 공격과 공격 사이의 회복 텀(Gap) 계산
            // 예: 1초에 공격, 5초에 공격 -> 2,3,4초 (3초간 회복)
            int timeGap = attackTime - lastAttackTime - 1;
            
            // 2. 회복 진행 (회복할 시간이 있을 때만)
            if (timeGap > 0) {
                // 기본 회복량
                int healAmount = timeGap * x;
                
                // 추가 회복량 (연속 성공 횟수만큼)
                // 공격을 받으면 연속이 끊기므로, 이 구간 안에서의 성공 횟수만 계산하면 됨
                int bonusCount = timeGap / t;
                healAmount += bonusCount * y;
                
                // 체력 적용 (최대 체력 초과 불가)
                curHealth += healAmount;
                if (curHealth > maxHealth) {
                    curHealth = maxHealth;
                }
            }
            
            // 3. 몬스터 공격 적용
            curHealth -= damage;
            
            // 4. 사망 확인
            if (curHealth <= 0) {
                return -1;
            }
            
            // 5. 마지막 공격 시간 갱신
            lastAttackTime = attackTime;
        }
        
        return curHealth;
    }
}