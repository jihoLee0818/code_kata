import java.util.*;

class Solution {
    public String[] solution(String[] players, String[] callings) {
        
		Map<String, Integer> map = new HashMap<>();
		
		for(int i = 0; i < players.length; i++) {
			map.put(players[i], i);
		}
		
		for(String calling : callings) {
			int index = map.get(calling);
			
			if(index > 0) {
				String fP = players[index - 1];
				players[index - 1] = players[index];
				players[index] = fP;
				
				map.put(calling, index - 1);
				map.put(fP, index);
			}
		}
        
        return players;
    }
}