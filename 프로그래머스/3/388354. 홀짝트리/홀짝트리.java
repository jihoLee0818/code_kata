import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        int n = nodes.length;

        // ⭐ value → index 매핑 (핵심)
        Map<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(nodes[i], i);
        }

        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        // O(E)
        for (int[] e : edges) {
            int u = indexMap.get(e[0]);
            int v = indexMap.get(e[1]);
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        int hol = 0, rev = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                List<Integer> comp = new ArrayList<>();
                Queue<Integer> q = new LinkedList<>();
                visited[i] = true;
                q.offer(i);

                while (!q.isEmpty()) {
                    int cur = q.poll();
                    comp.add(cur);
                    for (int nx : adj.get(cur)) {
                        if (!visited[nx]) {
                            visited[nx] = true;
                            q.offer(nx);
                        }
                    }
                }

                int badHol = 0, badRev = 0;
                for (int u : comp) {
                    int deg = adj.get(u).size();
                    boolean holOk = (nodes[u] % 2) == ((deg - 1) % 2);
                    boolean revOk = (nodes[u] % 2) != ((deg - 1) % 2);
                    if (!holOk) badHol++;
                    if (!revOk) badRev++;
                }

                for (int u : comp) {
                    int deg = adj.get(u).size();
                    if (badHol <= 1 && (nodes[u] % 2) == (deg % 2)) hol++;
                    if (badRev <= 1 && (nodes[u] % 2) != (deg % 2)) rev++;
                }
            }
        }

        return new int[]{hol, rev};
    }
}
