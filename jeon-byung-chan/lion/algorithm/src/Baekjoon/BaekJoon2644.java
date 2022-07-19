package Baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


// 인접리스트 설명 url : https://freestrokes.tistory.com/87
// 인접 행렬을 이용하여서도 풀이가 가능하다.
// 인접 행렬 : 그래프에 간선이 많이 존재하는 밀집 그래프
// 인접 리스트 : 그래프에 간선이 적게 존재하는 희소 그래프

public class BaekJoon2644 {

    static int answer = -1; // 정답이 나오지 않는다면 -1 을 출력해야하기 때문에 1부터 시작

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 정점의 개수

        StringTokenizer st = new StringTokenizer(br.readLine());

        int p1 = Integer.parseInt(st.nextToken()); // 촌수를 구해야하는 서로 다른 두사람의 번호 , endPoint
        int p2 = Integer.parseInt(st.nextToken()); // startPoint

        // 인접리스트 구현
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        int m = Integer.parseInt(br.readLine()); // 부모 자식들간의 관계의 개수 , 간선의 개수
        int count = 0; // 촌수 계산
        boolean[] checked = new boolean[n + 1];

        // 양방향 인접리스트
        for (int i = 0; i < m; i++) {
            // 앞에 나오는 번호 x는 뒤에 나오는 정수 y의 부모 번호
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            // 양방향 인접리스트
            graph.get(parent).add(child);
            graph.get(child).add(parent);

        }
        dfs(graph, p2 , p1 , count , checked);

        System.out.println(answer);
    }

    private static void dfs(ArrayList<ArrayList<Integer>> graph , int startPoint , int endPoint , int count , boolean[] checked) {
        checked[startPoint] = true; // 방문 한 노드는 true 로
        for (int i : graph.get(startPoint)) {
            if (!checked[i]) { // 방문하지 않았고,
                if (i == endPoint){ // 마지막 종료 지점과 같은 값이라면
                    answer = count + 1; // 해당 노드로 이동해야하기 때문에 +1 해준뒤 결과를 출력
                    return; // dfs 종료
                }
                // 방문을 하지 않았는데 정답이 아니라면 다음 노드로 dfs를 진행한다.
                dfs(graph, i, endPoint, count + 1, checked); //
            }
        }
    }
}
