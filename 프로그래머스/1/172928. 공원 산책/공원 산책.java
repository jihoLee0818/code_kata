import java.util.*;

class Solution {

    private int[] H_W = new int[2];
    private int with, hight;
    private String[] park;

    public int[] solution(String[] park, String[] routes) {

        this.park = park;
        hight = park.length;
        with = park[0].length();

        loop: // 시작 좌표 입력. 
        for (int h = 0; h < hight; h++)
            for (int w = 0; w < with; w++)
                if(getState(h, w).equals("S")) {
                    H_W[0] = h;
                    H_W[1] = w;
                    break loop;
                }

        for (String r : routes) {
            char direction = r.charAt(0);
            int repeatNum = Character.getNumericValue(r.charAt(2));
            int[] tempH_W = H_W.clone();
            for (int i = 0; i < repeatNum; i++) // 이동가능 여부 체크
                if (isCanMove(direction, tempH_W) && repeatNum -1 == i)
                    for (int j = 0; j < repeatNum; j++) 
                        isCanMove(direction, H_W); // 이동시킴
        }


        return H_W;
    }

    // 좌표의 속성 반환
    private String getState (int h , int w) {
        return Character.toString(park[h].charAt(w));
    }

    // 존재하는 좌표인지 체크 
    private boolean isExistAndMove(int h, int w) {
        boolean exist = h >= 0 && w >=0 && h < hight && w < with;
        boolean canMove = false;
        if (exist)
            canMove = !getState(h, w).equals("X");
        return canMove;
    }

    // E,S,W,N 입력받고 H-W 이동시킴
    private boolean isCanMove(char direction, int[] tempHW) {
        switch (direction) {
            case 'N' :
                if (isExistAndMove(tempHW[0] - 1, tempHW[1])) {                
                    tempHW[0]--;
                    break;
                } else return false;

            case 'S' :
                if (isExistAndMove(tempHW[0] + 1, tempHW[1])) {
                    tempHW[0]++;
                    break;         
                } else return false;
            case 'W' :
                if (isExistAndMove(tempHW[0], tempHW[1] - 1)) { 
                    tempHW[1]--;
                    break;
                } else return false;
            case 'E' :
                if (isExistAndMove(tempHW[0], tempHW[1] + 1)) { 
                    tempHW[1]++;
                    break;
                } else return false;
        }
        return true;
    }

}