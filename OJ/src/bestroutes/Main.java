package bestroutes;

import java.util.*;

public class Main {
    static class Edge {
        int to, time;
        Edge(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }

    public static int dijkstra(int n, int s, List<List<Edge>> graph, List<Integer> startStations) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        for (int start : startStations) {
            dist[start] = 0;
            pq.offer(new int[]{0, start});
        }

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int currTime = curr[0], currStation = curr[1];

            if (currStation == s) return currTime;

            for (Edge edge : graph.get(currStation)) {
                int nextStation = edge.to, nextTime = currTime + edge.time;

                if (nextTime < dist[nextStation]) {
                    dist[nextStation] = nextTime;
                    pq.offer(new int[]{nextTime, nextStation});
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNext()) {
            int n = scanner.nextInt(), m = scanner.nextInt(), s = scanner.nextInt();
            List<List<Edge>> graph = new ArrayList<>();

            for (int i = 0; i <= n; i++) graph.add(new ArrayList<>());

            for (int i = 0; i < m; i++) {
                int p = scanner.nextInt(), q = scanner.nextInt(), t = scanner.nextInt();
                graph.get(p).add(new Edge(q, t));
            }

            int w = scanner.nextInt();
            List<Integer> startStations = new ArrayList<>();
            for (int i = 0; i < w; i++) {
                startStations.add(scanner.nextInt());
            }

            System.out.println(dijkstra(n, s, graph, startStations));
        }
        scanner.close();
    }
}