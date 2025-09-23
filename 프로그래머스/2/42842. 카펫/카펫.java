class Solution {

    public int[] solution(int brown, int yellow) {

        int width = brown / 2 - 2;
        int height = 0;

        while (--width * ++height != yellow);

        return new int[] {width + 2, height + 2};
    }

}