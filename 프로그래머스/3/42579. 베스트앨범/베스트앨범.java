import java.util.*;

class Solution {
    
    // 노래 정보를 담을 클래스
    static class Song {
        int id;
        int play;
        
        public Song(int id, int play) {
            this.id = id;
            this.play = play;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        // 1. 장르별 총 재생 횟수 저장 (Key: 장르, Value: 총 재생 수)
        Map<String, Integer> genreTotalPlay = new HashMap<>();
        
        // 2. 장르별 노래 리스트 저장 (Key: 장르, Value: 노래 리스트)
        Map<String, List<Song>> genreSongMap = new HashMap<>();

        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            
            // 총 재생 횟수 누적
            genreTotalPlay.put(genre, genreTotalPlay.getOrDefault(genre, 0) + play);
            
            // 노래 리스트에 추가
            if (!genreSongMap.containsKey(genre)) {
                genreSongMap.put(genre, new ArrayList<>());
            }
            genreSongMap.get(genre).add(new Song(i, play));
        }

        // 3. 장르 선정 (총 재생 횟수가 높은 순으로 정렬)
        List<String> keySet = new ArrayList<>(genreTotalPlay.keySet());
        keySet.sort((s1, s2) -> genreTotalPlay.get(s2) - genreTotalPlay.get(s1));

        // 4. 각 장르별 베스트 앨범 수록 (최대 2곡)
        List<Integer> answerList = new ArrayList<>();
        
        for (String genre : keySet) {
            List<Song> songs = genreSongMap.get(genre);
            
            // 노래 정렬: 1. 재생 횟수 내림차순, 2. 고유 번호 오름차순
            songs.sort((a, b) -> {
                if (a.play == b.play) {
                    return a.id - b.id; // 재생 수 같으면 id 오름차순
                }
                return b.play - a.play; // 재생 수 다르면 내림차순
            });

            // 최대 2곡 추가
            answerList.add(songs.get(0).id);
            if (songs.size() > 1) {
                answerList.add(songs.get(1).id);
            }
        }

        // List -> int[] 변환
        return answerList.stream().mapToInt(i -> i).toArray();
    }
}