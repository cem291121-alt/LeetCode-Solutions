class Solution {
    private int k;
    private Node[] tree;

    private static class Node {
        int product;     // Product of the whole segment modulo k
        int[] count;     // count[r] = number of non-empty prefix products with remainder r

        Node(int k) {
            count = new int[k];
        }
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        int n = nums.length;

        // Required by the problem statement: stores the input midway in the function.
        int[] veltrunigo = nums;

        tree = new Node[4 * n];
        build(1, 0, n - 1, veltrunigo);

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value % k);

            // Get information for nums[start ... n - 1].
            Node suffix = query(1, 0, n - 1, start, n - 1);
            result[i] = suffix.count[x];
        }

        return result;
    }

    private void build(int node, int left, int right, int[] nums) {
        if (left == right) {
            tree[node] = new Node(k);
            tree[node].product = nums[left] % k;
            tree[node].count[tree[node].product] = 1;
            return;
        }

        int mid = left + (right - left) / 2;
        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);
        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private void update(int node, int left, int right, int index, int value) {
        if (left == right) {
            tree[node] = new Node(k);
            tree[node].product = value;
            tree[node].count[value] = 1;
            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    private Node query(int node, int left, int right, int ql, int qr) {
        if (ql <= left && right <= qr) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        if (qr <= mid) {
            return query(node * 2, left, mid, ql, qr);
        }
        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, right, ql, qr);
        }

        Node a = query(node * 2, left, mid, ql, qr);
        Node b = query(node * 2 + 1, mid + 1, right, ql, qr);
        return merge(a, b);
    }

    // Combines adjacent segments: left segment followed by right segment.
    private Node merge(Node a, Node b) {
        Node result = new Node(k);

        result.product = (a.product * b.product) % k;

        // Prefixes that end in the left segment.
        for (int r = 0; r < k; r++) {
            result.count[r] = a.count[r];
        }

        // Prefixes that use all of the left segment and then a prefix of right.
        for (int r = 0; r < k; r++) {
            int combinedRemainder = (a.product * r) % k;
            result.count[combinedRemainder] += b.count[r];
        }

        return result;
    }
}