import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        // 1. 초기화
        int[] answer = new int[id_list.length];
        
        // 각 유저별로 신고한 사람들의 목록을 저장 (중복 신고 방지를 위해 Set 사용)
        Map<String, Set<String>> reportedInfoMap = new HashMap<>();
        
        // 각 유저가 받을 메일 수를 저장
        Map<String, Integer> mailCountMap = new HashMap<>();

        for (String id : id_list) {
            reportedInfoMap.put(id, new HashSet<>());
            mailCountMap.put(id, 0);
        }

        // 2. 신고 기록 집계
        for (String r : report) {
            String[] parts = r.split(" ");
            String reporter = parts[0];
            String reported = parts[1];
            
            // reported(신고당한 사람)를 key로, reporter(신고한 사람)를 value Set에 추가
            reportedInfoMap.get(reported).add(reporter);
        }

        // 3. 정지 유저 판별 및 메일 수 계산
        for (String reportedUser : reportedInfoMap.keySet()) {
            Set<String> reporters = reportedInfoMap.get(reportedUser);
            
            // 신고한 사람의 수가 k명 이상이면
            if (reporters.size() >= k) {
                // 이 유저를 신고한 모든 사람(reporters)에게 메일 발송 (+1)
                for (String reporter : reporters) {
                    mailCountMap.put(reporter, mailCountMap.get(reporter) + 1);
                }
            }
        }

        // 4. id_list 순서에 맞게 결과 배열에 담기
        for (int i = 0; i < id_list.length; i++) {
            answer[i] = mailCountMap.get(id_list[i]);
        }
        
        return answer;
    }
}