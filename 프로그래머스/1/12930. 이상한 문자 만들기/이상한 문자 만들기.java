class Solution {

    public String solution(String s) {
        StringBuilder sb = new StringBuilder();
        int index = 0;

        for (char c : s.toCharArray()) {
            if (c == ' ') {
                sb.append(c);
                index = 0;
            } else {
                sb.append((index++ % 2 == 0) ? Character.toUpperCase(c) : Character.toLowerCase(c));
            }
        }

        return sb.toString();
    }

}