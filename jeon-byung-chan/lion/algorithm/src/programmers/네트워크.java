package programmers;

public class 네트워크 {
    public static void main(String[] args) {
        solution sol = new solution();
        int n = 3;
//        int[][] computers = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}; // 에제 1
        int[][] computers = new int[][]{{1, 1, 0}, {1, 1, 1}, {0, 1, 1}}; // 예제 2
        System.out.println(sol.solution(n , computers));
    }
}

class solution{
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (!visited[i]) { // 만약 방문하지 않았다면
                dfs(i, computers , visited);
                answer++; 
            }
        }
        return answer;
    }

    private boolean[] dfs(int i, int[][] computers, boolean[] visited) {
        visited[i] = true;

        for (int j = 0; j < computers.length; j++) {
            // 1. 대각선 ( i , j ) 고려
            // 2. computers[i][j] == 1 인것을 고려
            // 3. 방문하지 않은 곳인지 확인 => visited[i] == false
            if (i != j && computers[i][j] == 1 && !visited[j]) {
                visited = dfs(j, computers, visited);
            }
        }
        return visited;
    }
}
