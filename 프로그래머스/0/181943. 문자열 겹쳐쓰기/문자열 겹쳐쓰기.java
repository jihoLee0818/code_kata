class Solution {
    public String solution(String my_string, String overwrite_string, int s) {
        // 1. 앞부분: 0부터 s 직전까지 자름
        String before = my_string.substring(0, s);
        
        // 2. 뒷부분: (s + 덮어쓸 길이)부터 끝까지 자름
        int afterIndex = s + overwrite_string.length();
        String after = my_string.substring(afterIndex);
        
        // 3. 이어 붙여서 반환
        return before + overwrite_string + after;
    }
}