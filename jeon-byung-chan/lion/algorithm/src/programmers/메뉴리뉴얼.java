package programmers;

import java.util.*;
import java.util.Map.Entry;

public class 메뉴리뉴얼 {
    public static void main(String[] args) {
        Solution1 solution = new Solution1();

        String[] orders = new String[]{"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
//        String[] orders = new String[]{"ABCDE", "AB", "CD", "ADE", "XYZ", "XYZ", "ACD"};
//        String[] orders = new String[]{"XYZ", "XWY", "WXA"};

        int[] course = new int[]{2, 3, 4};
//        int[] course = new int[]{2, 3, 5};
//        int[] course = new int[]{2, 3, 4};
        System.out.println(solution.solution(orders,course));
    }
}

class Solution1 {
    static HashMap<String, Integer> countMap;
            // 조합 메서드
        public static void combineMenu(String str , StringBuilder sb , int idx , int cnt , int n){
            if (cnt == n) {
                countMap.put(sb.toString(), countMap.getOrDefault(sb.toString(), 0) + 1);
                return;
            }

        for (int i = idx; i < str.length(); i++) {
            sb.append(str.charAt(i));
            // 재귀 호출
            combineMenu(str , sb , i+1 , cnt+1 , n);
            // 붙였던 것 떼기
            sb.delete(cnt, cnt + 1);
        }
    }

    public ArrayList<String> solution(String[] orders, int[] course) {

        ArrayList<String> answer = new ArrayList<>();

        for (int i = 0; i < orders.length; i++) { // WXA 와 같은 조합들을 오름차순으로 정렬.
            char[] charArray = orders[i].toCharArray();
            Arrays.sort(charArray);
            orders[i] = String.valueOf(charArray);
        }

        // course의 요소들이 조합의 길이를 나타냄.
        for (int i = 0; i < course.length; i++) {

            countMap = new HashMap<>(); // 만들어진 조합이 총 몇번 나왔는지 ?

            int max = Integer.MIN_VALUE;

            for (int j = 0; j < orders.length; j++) {
                StringBuilder sb = new StringBuilder();

                if (course[i] <= orders[j].length()) {
                    // 해당 course 의 길이보다 길다면 조합을 시작한다.
                    combineMenu(orders[j] , sb , 0 , 0 , course[i]);
                }
            }

            for (Entry<String, Integer> entry : countMap.entrySet()) {
                max = Math.max(max , entry.getValue());
            }

            for (Entry<String, Integer> entry : countMap.entrySet()) {
                if ( max >= 2 && entry.getValue() == max){
                    answer.add(entry.getKey());
                }
            }
        }

        Collections.sort(answer);

        return answer;
    }

}
