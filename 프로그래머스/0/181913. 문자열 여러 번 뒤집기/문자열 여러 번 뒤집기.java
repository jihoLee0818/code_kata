class Solution {
    public String solution(String my_string, int[][] queries) {
        // 1. 문자열을 다루기 쉽도록 문자 배열로 변환합니다.
        char[] arr = my_string.toCharArray();
        
        // 2. 쿼리를 하나씩 확인하며 뒤집기 작업을 수행합니다.
        for (int[] query : queries) {
            int s = query[0];
            int e = query[1];
            
            // 3. 시작 인덱스(s)가 끝 인덱스(e)보다 작을 동안만 두 글자의 위치를 바꿉니다.
            while (s < e) {
                char temp = arr[s];
                arr[s] = arr[e];
                arr[e] = temp;
                
                s++; // 시작점은 오른쪽으로 한 칸 이동
                e--; // 끝점은 왼쪽으로 한 칸 이동
            }
        }
        
        // 4. 조작이 완료된 문자 배열을 다시 문자열로 만들어 반환합니다.
        return new String(arr);
    }
}