import java.util.*;

class Solution {
    // 50x50 크기의 표를 1차원 배열로 관리 (0 ~ 2499)
    int[] parent = new int[2500];
    String[] values = new String[2500];

    public String[] solution(String[] commands) {
        // 1. 초기화: 각 셀의 부모는 자기 자신
        for (int i = 0; i < 2500; i++) {
            parent[i] = i;
            values[i] = null; // 초기 값은 null (EMPTY)
        }

        List<String> result = new ArrayList<>();

        for (String command : commands) {
            StringTokenizer st = new StringTokenizer(command);
            String type = st.nextToken();

            if (type.equals("UPDATE")) {
                // UPDATE r c value
                if (st.countTokens() == 3) {
                    int r = Integer.parseInt(st.nextToken());
                    int c = Integer.parseInt(st.nextToken());
                    String value = st.nextToken();
                    
                    int root = find(convertIdx(r, c));
                    values[root] = value;
                } 
                // UPDATE value1 value2
                else {
                    String value1 = st.nextToken();
                    String value2 = st.nextToken();
                    
                    for (int i = 0; i < 2500; i++) {
                        // 루트 노드만 검사하여 값 변경 (자식 노드는 루트를 바라보므로 자동 적용됨)
                        // 주의: find(i)를 호출하지 않고 i가 루트인지만 확인해도 됨 (find 최적화)
                        if (i == parent[i] && value1.equals(values[i])) {
                            values[i] = value2;
                        }
                    }
                }
            } else if (type.equals("MERGE")) {
                int r1 = Integer.parseInt(st.nextToken());
                int c1 = Integer.parseInt(st.nextToken());
                int r2 = Integer.parseInt(st.nextToken());
                int c2 = Integer.parseInt(st.nextToken());

                int root1 = find(convertIdx(r1, c1));
                int root2 = find(convertIdx(r2, c2));

                if (root1 == root2) continue;

                // 병합 시 값 유지 정책: root1 값 우선, 없으면 root2 값 사용
                String mergeValue = values[root1];
                if (mergeValue == null) {
                    mergeValue = values[root2];
                }

                // root2를 root1 밑으로 병합
                values[root2] = null; // 자식 노드가 될 root2의 값은 삭제
                parent[root2] = root1;
                values[root1] = mergeValue;

            } else if (type.equals("UNMERGE")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                
                int targetIdx = convertIdx(r, c);
                int root = find(targetIdx);
                String rootValue = values[root];

                // 모든 셀을 돌면서 현재 그룹(root)에 속한 셀들을 분리
                // parent 배열을 루프 중에 변경하면 find 결과가 꼬일 수 있으므로
                // 대상을 먼저 리스트에 담고 일괄 처리
                List<Integer> deleteList = new ArrayList<>();
                for (int i = 0; i < 2500; i++) {
                    if (find(i) == root) {
                        deleteList.add(i);
                    }
                }

                for (int idx : deleteList) {
                    parent[idx] = idx; // 그룹 해제 (나홀로 그룹)
                    values[idx] = null; // 값 초기화
                }

                // 병합 해제 요청이 들어온 그 셀(targetIdx)만 값을 복구
                values[targetIdx] = rootValue;

            } else if (type.equals("PRINT")) {
                int r = Integer.parseInt(st.nextToken());
                int c = Integer.parseInt(st.nextToken());
                
                int root = find(convertIdx(r, c));
                
                if (values[root] == null) {
                    result.add("EMPTY");
                } else {
                    result.add(values[root]);
                }
            }
        }

        return result.toArray(new String[0]);
    }

    // Union-Find의 Find 연산 (경로 압축 적용)
    private int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    // (r, c) 좌표를 0~2499 인덱스로 변환
    private int convertIdx(int r, int c) {
        return (r - 1) * 50 + (c - 1);
    }
}