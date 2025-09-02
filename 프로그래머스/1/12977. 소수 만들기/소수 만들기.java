class Solution {

    public int solution(int[] nums) {
        return dfs(0, -1, 0, nums);
    }

    // nums 배열에서 3개의 수를 선택하여 그 합이 소수인지 판별
    public int dfs(int sum, int index, int depth, int[] nums) {
        if (depth == 3) return isPrime(sum) ? 1 : 0;

        int count = 0;
        for (int i = index + 1; i < nums.length; i++) {
            count += dfs(sum + nums[i], i, depth + 1, nums);
        }

        return count;
    }

    // 소수 판별 함수
    public boolean isPrime(int num) {
        if (num == 2) return true;
        if (num <= 1 || num % 2 == 0) return false;

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) return false;
        }

        return true;
    }

}