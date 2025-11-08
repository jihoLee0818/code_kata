import java.util.*;

class Solution {
    
    // 5칸짜리 '청크' 정보를 저장할 헬퍼 클래스
    class Chunk implements Comparable<Chunk> {
        int diamonds, irons, stones;
        int steelCost; // 이 청크를 강철 곡괭이로 캤을 때의 피로도 (정렬 기준)

        public Chunk(int diamonds, int irons, int stones) {
            this.diamonds = diamonds;
            this.irons = irons;
            this.stones = stones;
            // "가치" 계산: 강철 곡괭이 기준
            this.steelCost = (diamonds * 25) + (irons * 5) + (stones * 1);
        }

        // "가치"가 높은 순 (내림차순)으로 정렬
        @Override
        public int compareTo(Chunk other) {
            return other.steelCost - this.steelCost;
        }
    }

    public int solution(int[] picks, String[] minerals) {
        int totalPicks = picks[0] + picks[1] + picks[2];
        List<Chunk> chunks = new ArrayList<>();

        // 1. 5칸씩 묶기 (단, 곡괭이 수만큼만)
        for (int i = 0; i < minerals.length; i += 5) {
            if (totalPicks == 0) break; // 더 이상 사용할 곡괭이가 없으면 종료

            int diamonds = 0, irons = 0, stones = 0;
            // 5칸 또는 남은 미네랄 끝까지
            int end = Math.min(i + 5, minerals.length);
            
            for (int j = i; j < end; j++) {
                if (minerals[j].equals("diamond")) diamonds++;
                else if (minerals[j].equals("iron")) irons++;
                else stones++;
            }
            
            chunks.add(new Chunk(diamonds, irons, stones));
            totalPicks--; // 이 청크에 곡괭이 하나를 배정할 것임
        }

        // 3. 청크를 "가치" 순으로 내림차순 정렬
        Collections.sort(chunks);

        int totalFatigue = 0;
        
        // 4. 곡괭이 배정 (가장 좋은 곡괭이 -> 가장 가치 있는 청크)
        for (Chunk chunk : chunks) {
            if (picks[0] > 0) { // 다이아 곡괭이 사용
                totalFatigue += chunk.diamonds + chunk.irons + chunk.stones;
                picks[0]--;
            } else if (picks[1] > 0) { // 철 곡괭이 사용
                totalFatigue += (chunk.diamonds * 5) + chunk.irons + chunk.stones;
                picks[1]--;
            } else if (picks[2] > 0) { // 강철 곡괭이 사용
                totalFatigue += (chunk.diamonds * 25) + (chunk.irons * 5) + chunk.stones;
                picks[2]--;
            }
        }
        
        return totalFatigue;
    }
}