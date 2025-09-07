import java.util.*;

class Solution {
    public int[] solution(String[] name, int[] yearning, String[][] photo) {
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < name.length; i++)
            map.put(name[i], yearning[i]);

        return Arrays.stream(photo)
            .mapToInt(persons -> Arrays.stream(persons)
                  .mapToInt(person -> map.getOrDefault(person, 0))
                  .sum())
            .toArray();
    }
}