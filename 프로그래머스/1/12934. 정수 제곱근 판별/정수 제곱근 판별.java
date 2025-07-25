class Solution {
    public long solution(long n) {
        long m = -1;
        long number = (long)Math.sqrt(n);

        if(Math.pow(number,2) == n ){
            m = (long)Math.pow(number +1,2);
        }


        return m;
    }
}