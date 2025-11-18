class Solution {
    
    // 할인율 종류
    static int[] DISCOUNT_RATES = {10, 20, 30, 40};
    // 최대 가입자 수, 최대 매출액 저장
    int maxSubscribers = 0;
    int maxSales = 0;

    public int[] solution(int[][] users, int[] emoticons) {
        // 각 이모티콘에 적용될 할인율을 저장할 배열
        int[] discounts = new int[emoticons.length];
        
        // DFS 시작 (첫 번째 이모티콘부터)
        dfs(0, users, emoticons, discounts);
        
        return new int[]{maxSubscribers, maxSales};
    }

    /**
     * DFS로 모든 할인율 조합을 만드는 함수
     * @param depth 현재 할인율을 정할 이모티콘의 인덱스
     */
    private void dfs(int depth, int[][] users, int[] emoticons, int[] discounts) {
        // [종료 조건] 모든 이모티콘의 할인율이 결정되었을 때
        if (depth == emoticons.length) {
            calculateResult(users, emoticons, discounts);
            return;
        }

        // [탐색] 4가지 할인율을 각각 적용해보고 다음 이모티콘으로 넘어감
        for (int rate : DISCOUNT_RATES) {
            discounts[depth] = rate;
            dfs(depth + 1, users, emoticons, discounts);
        }
    }

    /**
     * 결정된 할인율 조합으로 가입자 수와 매출액을 계산하고 최댓값을 갱신하는 함수
     */
    private void calculateResult(int[][] users, int[] emoticons, int[] discounts) {
        int subscribers = 0;
        int sales = 0;

        for (int[] user : users) {
            int userRate = user[0];   // 유저가 원하는 최소 할인율
            int userLimit = user[1];  // 유저의 가격 상한선
            int userTotalCost = 0;    // 이 유저의 총 구매액

            // 각 이모티콘에 대해 구매 여부 확인
            for (int i = 0; i < emoticons.length; i++) {
                // 적용된 할인율이 유저 기준 이상이면 구매
                if (discounts[i] >= userRate) {
                    userTotalCost += emoticons[i] * (100 - discounts[i]) / 100;
                }
            }

            // 구매액이 한도를 넘으면 -> 플러스 가입
            if (userTotalCost >= userLimit) {
                subscribers++;
            } 
            // 아니면 -> 매출액에 추가
            else {
                sales += userTotalCost;
            }
        }

        // [최댓값 갱신 로직]
        // 1. 가입자 수가 더 많으면 무조건 교체
        if (subscribers > maxSubscribers) {
            maxSubscribers = subscribers;
            maxSales = sales;
        }
        // 2. 가입자 수가 같으면, 매출액이 더 클 때 교체
        else if (subscribers == maxSubscribers) {
            if (sales > maxSales) {
                maxSales = sales;
            }
        }
    }
}