import java.util.*;

class Solution {
    
    // 과제 정보를 담을 클래스
    static class Assignment implements Comparable<Assignment> {
        String name;
        int start;
        int playTime;

        public Assignment(String name, int start, int playTime) {
            this.name = name;
            this.start = start;
            this.playTime = playTime;
        }

        @Override
        public int compareTo(Assignment o) {
            return this.start - o.start; // 시작 시간 오름차순 정렬
        }
    }

    public String[] solution(String[][] plans) {
        List<String> answerList = new ArrayList<>();
        
        // 1. 데이터를 다루기 편하게 변환 및 정렬
        Assignment[] assignments = new Assignment[plans.length];
        for (int i = 0; i < plans.length; i++) {
            assignments[i] = new Assignment(
                plans[i][0],
                timeToMinutes(plans[i][1]),
                Integer.parseInt(plans[i][2])
            );
        }
        Arrays.sort(assignments); // 시작 시간 순 정렬

        // 멈춘 과제를 저장할 스택
        Stack<Assignment> pausedTasks = new Stack<>();

        // 2. 과제 순회 (마지막 과제 전까지)
        for (int i = 0; i < assignments.length - 1; i++) {
            Assignment cur = assignments[i];
            Assignment next = assignments[i + 1];

            int timeDiff = next.start - cur.start; // 다음 과제까지 남은 시간

            if (cur.playTime > timeDiff) {
                // [Case 1] 시간이 부족해서 다 못 끝냄
                cur.playTime -= timeDiff; // 할 수 있는 만큼만 하고
                pausedTasks.push(cur);    // 스택에 저장
            } else {
                // [Case 2] 시간 안에 끝낼 수 있음
                answerList.add(cur.name); // 현재 과제 완료
                int remainTime = timeDiff - cur.playTime; // 남는 시간

                // 남는 시간 동안 멈춰둔 과제 처리
                while (remainTime > 0 && !pausedTasks.isEmpty()) {
                    Assignment paused = pausedTasks.peek();
                    
                    if (paused.playTime <= remainTime) {
                        // 멈춘 과제도 완료 가능
                        pausedTasks.pop();
                        answerList.add(paused.name);
                        remainTime -= paused.playTime;
                    } else {
                        // 멈춘 과제를 조금 더 진행만 가능
                        paused.playTime -= remainTime;
                        remainTime = 0; // 남는 시간 다 씀
                    }
                }
            }
        }

        // 3. 마지막 과제 처리
        answerList.add(assignments[assignments.length - 1].name);

        // 4. 스택에 남은 과제들 순서대로 처리
        while (!pausedTasks.isEmpty()) {
            answerList.add(pausedTasks.pop().name);
        }

        return answerList.toArray(new String[0]);
    }

    // "HH:MM" -> 분(int) 변환 함수
    private int timeToMinutes(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }
}