import java.util.*;
class Solution {
    String[] keymap;
    public int[] solution(String[] keymap, String[] targets) {
        this.keymap = keymap;
        int[] answer = new int[targets.length];
        HashMap<String, Integer> key = new HashMap<>();
        //목표 문자열 순회
        for(int i=0;i<targets.length;i++){
            String target = targets[i]; //목표문자열
            int j=0;
            int count=0;
            while(j<target.length()){ //목표 문자열의 문자 탐색 ABCD
                String str = target.charAt(j)+"";
                int cnt = key.getOrDefault(str,0);
                if(cnt==0){ //map에 담긴 횟수가 없으면 찾기
                    cnt = findCount(str);
                }
                key.put(str,cnt);
                if(cnt == -1){
                    count = -1;
                    break;
                }
                count += cnt;
                j+=1;
            }
            answer[i] = count;
        }
        return answer;
    }
    
    //알파벳을 누르기 위한 최소한의 횟수 리턴
    public int findCount(String str){
        int minKey = 100;
        for(int i=0;i<keymap.length;i++){
            String key = keymap[i];
            int index = key.indexOf(str);
            if(index == 0){ //1번만 눌러도 되면 1 리턴
                return 1;
            }else if(index == -1){ //없으면 다음 키 탐색
                continue;
            }else{
                minKey = Math.min(index,minKey);
            }
        }
        if(minKey == 100 ){
            return -1;
        }else{
            return minKey += 1; //인덱스보다 1번 더 눌러야함
        }
    }
}