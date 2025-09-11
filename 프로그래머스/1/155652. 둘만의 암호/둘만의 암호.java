class Solution {

    public String solution(String s, String skip, int index) {
        StringBuilder sb = new StringBuilder();

        int abcLen = 'z' - 'a' + 1;
        boolean[] isSkip = new boolean[abcLen];

        for (char c : skip.toCharArray()) {
            isSkip[c - 'a'] = true;
        }

        for (char c : s.toCharArray()) {
            int i = c - 'a', cnt = 0;
            while (cnt != index) {
                i = (i + 1) % abcLen;
                if (!isSkip[i]) cnt++;
            }
            sb.append((char) ('a' + i));
        }

        return sb.toString();
    }

}