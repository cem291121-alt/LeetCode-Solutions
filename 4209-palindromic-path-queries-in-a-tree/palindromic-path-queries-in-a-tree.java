import java.util.*;

class Solution {
    public List<Boolean> palindromePath(
            int n, int[][] edges, String s, String[] queries) {

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }

        int log = 1;
        while ((1 << log) <= n) {
            log++;
        }

        int[] parent = new int[n];
        int[] depth = new int[n];
        int[] tin = new int[n];
        int[] tout = new int[n];
        int[] order = new int[n];

        // Iterative DFS to avoid recursion stack overflow on a long tree.
        Arrays.fill(parent, -1);
        int timer = 0;
        int orderSize = 0;

        int[] stackNode = new int[n];
        int[] stackNext = new int[n];
        int top = 0;

        stackNode[top] = 0;
        stackNext[top] = 0;
        parent[0] = 0;
        tin[0] = timer++;
        order[orderSize++] = 0;

        while (top >= 0) {
            int u = stackNode[top];

            if (stackNext[top] == graph[u].size()) {
                tout[u] = timer - 1;
                top--;
                continue;
            }

            int v = graph[u].get(stackNext[top]++);
            if (v == parent[u]) {
                continue;
            }

            parent[v] = u;
            depth[v] = depth[u] + 1;
            tin[v] = timer++;
            order[orderSize++] = v;

            top++;
            stackNode[top] = v;
            stackNext[top] = 0;
        }

        int[][] up = new int[log][n];
        for (int node = 0; node < n; node++) {
            up[0][node] = parent[node];
        }
        for (int k = 1; k < log; k++) {
            for (int node = 0; node < n; node++) {
                up[k][node] = up[k - 1][up[k - 1][node]];
            }
        }

        FenwickXor fenwick = new FenwickXor(n + 1);
        char[] chars = s.toCharArray();

        // Add each node's character mask to its subtree's Euler interval.
        for (int node = 0; node < n; node++) {
            int mask = 1 << (chars[node] - 'a');
            fenwick.toggle(tin[node], mask);
            fenwick.toggle(tout[node] + 1, mask);
        }

        List<Boolean> answer = new ArrayList<>();

        for (String query : queries) {
            String[] parts = query.split(" ");
            int u = Integer.parseInt(parts[1]);

            if (parts[0].equals("update")) {
                char newChar = parts[2].charAt(0);
                int delta = (1 << (chars[u] - 'a')) ^ (1 << (newChar - 'a'));

                fenwick.toggle(tin[u], delta);
                fenwick.toggle(tout[u] + 1, delta);
                chars[u] = newChar;
            } else {
                int v = Integer.parseInt(parts[2]);
                int ancestor = lca(u, v, depth, up);

                int pathMask = fenwick.prefix(tin[u])
                             ^ fenwick.prefix(tin[v])
                             ^ (1 << (chars[ancestor] - 'a'));

                // A rearrangement can form a palindrome if at most one
                // character has an odd frequency.
                answer.add((pathMask & (pathMask - 1)) == 0);
            }
        }

        return answer;
    }

    private int lca(int a, int b, int[] depth, int[][] up) {
        if (depth[a] < depth[b]) {
            int temp = a;
            a = b;
            b = temp;
        }

        int difference = depth[a] - depth[b];
        for (int k = 0; k < up.length; k++) {
            if (((difference >> k) & 1) != 0) {
                a = up[k][a];
            }
        }

        if (a == b) {
            return a;
        }

        for (int k = up.length - 1; k >= 0; k--) {
            if (up[k][a] != up[k][b]) {
                a = up[k][a];
                b = up[k][b];
            }
        }

        return up[0][a];
    }

    private static class FenwickXor {
        private final int[] tree;

        FenwickXor(int size) {
            tree = new int[size + 1];
        }

        void toggle(int index, int value) {
            for (int i = index + 1; i < tree.length; i += i & -i) {
                tree[i] ^= value;
            }
        }

        int prefix(int index) {
            int result = 0;
            for (int i = index + 1; i > 0; i -= i & -i) {
                result ^= tree[i];
            }
            return result;
        }
    }
}