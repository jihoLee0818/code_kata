class Solution {
    public String solution(int num) {
        String result = "";
            if (num % 2 == 0 || num == 0){
                result = "Even";
            } else {
                result = "Odd";
            }
        return result;
    }
}