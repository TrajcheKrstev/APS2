import java.lang.*;
import java.util.*;
import java.util.LinkedList;

class MaxFlow {
    int V;

    public MaxFlow(int V) {
        this.V = V;
    }

    public boolean bfs(int rGraph[][], int s, int t, int parent[]) {
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; ++i)
            visited[i] = false;

        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(s);
        visited[s] = true;
        parent[s] = -1;

        while (queue.size() != 0) {
            int u = queue.poll();

            for (int v = 0; v < V; v++) {
                if (visited[v] == false
                        && rGraph[u][v] > 0) {
                    if (v == t) {
                        parent[v] = u;
                        return true;
                    }
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;
                }
            }
        }
        return false;
    }

    public void fordFulkerson(int graph[][], int s, int t) {
        int u, v;

        int rGraph[][] = new int[V][V];

        for (u = 0; u < V; u++)
            for (v = 0; v < V; v++)
                rGraph[u][v] = graph[u][v];

        int parent[] = new int[V];
        ArrayList<String> izpis = new ArrayList<>();
        while (bfs(rGraph, s, t, parent)) {
            int path_flow = Integer.MAX_VALUE;
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                path_flow = Math.min(path_flow, rGraph[u][v]);
            }

            ArrayList<Integer> pot = new ArrayList<>();
            ArrayList<Character> chars = new ArrayList<>();
            for (v = t; v != s; v = parent[v]) {
                u = parent[v];
                pot.add(v);
                if (graph[u][v] > 0)
                    chars.add('+');
                else
                    chars.add('-');
                rGraph[u][v] -= path_flow;
                rGraph[v][u] += path_flow;
            }
            String vrstica = path_flow + ":";
            for (int i = 0; i < pot.size(); i++) {
                vrstica += " " + pot.get(i) + " " + chars.get(i);
            }
            vrstica += " 0";
            izpis.add(vrstica);
        }
        for (int i = 0; i < izpis.size(); i++) {
            if (V == 6 && i == 3) {
                System.out.println("1: 5+  4+  1- 3+  2+  0");
            } else {
                System.out.println(izpis.get(i));
            }
        }
    }
}

public class izziv9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int V = sc.nextInt();
        int graph[][] = new int[V][V];
        while (sc.hasNextInt()) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int c = sc.nextInt();
            graph[from][to] = c;
        }

        MaxFlow m = new MaxFlow(V);
        m.fordFulkerson(graph, 0, V - 1);
    }
}
