class Solution {

    public String solution(String s, int n) {
        return s.chars()
                .map(c -> 
                    !Character.isAlphabetic(c) ? c :

                    c >= 'a' ? (c - 'a' + n) % ('z' - 'a' + 1) + 'a' :

                    (c - 'A' + n) % ('Z' - 'A' + 1) + 'A'
                )
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}