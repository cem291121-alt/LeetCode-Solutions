import java.util.*;

class Solution {
    private static class State {
        int node;
        int run;
        long cost;

        State(int node, int run, long cost) {
            this.node = node;
            this.run = run;
            this.cost = cost;
        }
    }

    public int shortestPath(int n, int[][] edges, String labels, int k) {
        List<int[]>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            graph[edge[0]].add(new int[]{edge[1], edge[2]});
        }

        // State: current node and consecutive count of its label.
        long[][] dist = new long[n][k + 1];
        for (long[] row : dist) {
            Arrays.fill(row, Long.MAX_VALUE);
        }

        PriorityQueue<State> pq =
            new PriorityQueue<>(Comparator.comparingLong(state -> state.cost));

        dist[0][1] = 0;
        pq.offer(new State(0, 1, 0));

        while (!pq.isEmpty()) {
            State cur = pq.poll();

            if (cur.cost != dist[cur.node][cur.run]) {
                continue;
            }

            if (cur.node == n - 1) {
                return (int) cur.cost;
            }

            for (int[] edge : graph[cur.node]) {
                int next = edge[0];
                int nextRun = labels.charAt(next) == labels.charAt(cur.node)
                    ? cur.run + 1
                    : 1;

                if (nextRun <= k) {
                    long nextCost = cur.cost + edge[1];
                    if (nextCost < dist[next][nextRun]) {
                        dist[next][nextRun] = nextCost;
                        pq.offer(new State(next, nextRun, nextCost));
                    }
                }
            }
        }

        return -1;
    }
}