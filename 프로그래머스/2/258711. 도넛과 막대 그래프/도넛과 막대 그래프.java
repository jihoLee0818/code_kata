import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        // 노드 번호가 최대 1,000,000이므로 넉넉하게 배열 선언
        int[] inDegree = new int[1000001];
        int[] outDegree = new int[1000001];
        int maxNode = 0;

        // 1. 간선 정보 저장 및 차수 계산
        for (int[] edge : edges) {
            int start = edge[0];
            int end = edge[1];
            
            outDegree[start]++;
            inDegree[end]++;
            
            // 루프 범위를 줄이기 위해 등장하는 최대 노드 번호 기록
            maxNode = Math.max(maxNode, Math.max(start, end));
        }

        int createdNode = 0;
        int barGraph = 0;
        int eightGraph = 0;

        // 2. 각 노드를 순회하며 특징 파악
        for (int i = 1; i <= maxNode; i++) {
            // 정점이 존재하지 않는 번호(간선이 없는 경우)는 스킵
            if (inDegree[i] == 0 && outDegree[i] == 0) continue;

            // [생성된 정점 찾기]
            // 들어오는 간선이 없고(0), 나가는 간선이 2개 이상인 점
            if (inDegree[i] == 0 && outDegree[i] >= 2) {
                createdNode = i;
            }
            // [막대 그래프 찾기]
            // 나가는 간선이 없는(0) 점 (막대 그래프의 끝점)
            // (생성된 정점이 연결되어 있으므로 inDegree는 반드시 1 이상임)
            else if (outDegree[i] == 0) {
                barGraph++;
            }
            // [8자 그래프 찾기]
            // 나가는 간선이 2개이고, 들어오는 간선이 2개 이상인 점 (8자 그래프의 중심점)
            else if (outDegree[i] == 2 && inDegree[i] >= 2) {
                eightGraph++;
            }
        }

        // 3. 도넛 그래프 개수 계산
        // 생성된 정점에서 나가는 간선의 수(총 그래프 수)에서 나머지 그래프 수를 뺌
        int totalGraphs = outDegree[createdNode];
        int donutGraph = totalGraphs - barGraph - eightGraph;

        return new int[]{createdNode, donutGraph, barGraph, eightGraph};
    }
}