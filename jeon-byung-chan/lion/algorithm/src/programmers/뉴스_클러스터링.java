package programmers;

import java.util.ArrayList;

public class 뉴스_클러스터링 {
    public static void main(String[] args) {
        Solution_news solution2 = new Solution_news();
        System.out.println(solution2.solution("handshake" , "shake hands"));
    }
}

class Solution_news {
    public int solution(String str1, String str2) {

        ArrayList<String> listA = new ArrayList<>();
        ArrayList<String> listB = new ArrayList<>();

        double intersectionCount = 0;
        double answer;

        // str1 , str2 를 두 글자씩 끊어서 다중집합 만들기
        for (int i = 0; i < str1.length() - 1; i++) {
            String str = str1.substring(i, i + 2); // 두글자씩 끊기
            // 두글자가 모두 영어인지 검사후 다중 집합에 추가
            if (checkStr(str.charAt(0)) && checkStr(str.charAt(1)) ) {
                str = str.toUpperCase();
                listA.add(str);
            }
        }
        int sizeListA = listA.size();

        for (int i = 0; i < str2.length() - 1; i++) {
            String str = str2.substring(i, i + 2); // 두글자씩 끊기
            // 두글자가 모두 영어인지 검사후 다중 집합에 추가
            if (checkStr(str.charAt(0)) && checkStr(str.charAt(1)) ) {
                str = str.toUpperCase();
                listB.add(str);
            }
        }
        int sizeListB = listB.size();

        if (sizeListA == 0 && sizeListB == 0) { // 모두 공집합이라면
            answer = 1;
        } else {
            // 다중 집합의 교집합의 개수 구하기
            for (String str : listA) {
                if (listB.contains(str)) { // 만약 포함이 되는 친구인가 ?
                    // 중복이 있더라도 제일 앞에 나오는 첫 요소만 삭제한다.
                    listB.remove(str);
                    intersectionCount++;
                }
            }
            // 합집합 개수
            double unionCount = sizeListA + sizeListB - intersectionCount;

            // J(A,B)
            answer = intersectionCount / unionCount;
        }

        int result = (int)Math.floor(answer * 65536); // 버림

        return result;
    }

    public static boolean checkStr(char c) { // 두 글자가 모두 영어 소문자, 대문자 인지 검사
        if (c >= 65 && c <= 90) {
            return true;
        } else if (c >= 97 && c <= 122) {
            return true;
        }
        return false;
    }
}
