class Solution {
    public double solution(int[] numbers) {
        double sum = 0;
        for (int number : numbers) {
            sum += number;
        }
        double results = sum / numbers.length;
        return results;
    }
}