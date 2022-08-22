package programmers;

public class 스킬트리 {
    public static void main(String[] args) {
        String skill = "CBD";
        String[] skill_trees = new String[]{"BACDE", "CBADF", "AECB", "BDA"};

        Solution2 solution = new Solution2();

//        System.out.println(skill.substring(0,));

        System.out.println(solution.solution(skill, skill_trees));
    }

}


class Solution2{
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String str : skill_trees) {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < str.length(); i++) {
                String compared = String.valueOf(str.charAt(i));
                if (skill.contains(compared)) {// skill에 해당 문자가 포함이 되는가 ?
                    // 된다면 Stringbuilder에 추가
                    sb.append(compared);
                } else {
                    continue;
                }
            }

            // sb 검사 -> skill의 순서를 고려하였는가를 검사
            // B 를 배우기 전 C를 무조건 배워야 하므로 C , CB , CBD 순으로 검사 해야함.
            for (int i = skill.length(); i > -1; i--) {
                String partSkill = skill.substring(0 , i); // C , CB , CBD 잘라낸 것 + // 공집합도 걸러내야함.

                if (partSkill.equals(sb.toString())) { // 잘라낸 문자열과 skill_trees를 돌면서 만들어 낸 StringBuilder와 비교연산산
                    answer++;
                    break;
                }
            }
        }

        return answer;
    }
}
