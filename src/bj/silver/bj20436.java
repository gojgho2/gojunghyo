package bj.silver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class bj20436 {

    //키보드 배열 정의
    static char[][] keyboard = {
            {'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p'},
            {'a', 's' ,'d','f', 'g', 'h', 'j', 'k', 'l' , ';'},
            {'z', 'x', 'c', 'v', 'b', 'n', 'm', ',', '.', '/'}
    };

    // 키 위치 저장하는 맵
    static Map<Character, int[]> keyPosition = new HashMap<>();

    // 모음 구분
    static Set<Character> vowels = new HashSet<>(Arrays.asList('y', 'u', 'i', 'o', 'p', 'h', 'j', 'k', 'l', 'b', 'n', 'm'));

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        char leftStart = st.nextToken().charAt(0);
        char rightStart = st.nextToken().charAt(0);

        //입력할 문자열
        String input = br.readLine();

        //키보드 맵 초기화
        initializeKeyPosition();

        // 초기 위치 설정
        int[] leftPos = keyPosition.get(leftStart);
        int[] rightPos = keyPosition.get(rightStart);

        int totalTime = 0;
        // 입력 문자열 처리
        for (char ch : input.toCharArray()) {
            int[] targetPos = keyPosition.get(ch);

            if(vowels.contains(ch)) {
                //모음일떄 오른손 입력
                totalTime += calculateTime(rightPos, targetPos);
                rightPos = targetPos;
            } else {
                //오른손 입력
                totalTime += calculateTime(leftPos, targetPos);
                leftPos = targetPos;
            }
            // 입력 시간 1ㄹ초 추가
             totalTime += 1;
        }
        System.out.println(totalTime);
    }

    private static void initializeKeyPosition() {
        for(int i=0; i<keyboard.length; i++) {
            for(int j= 0; j<keyboard[i].length; j++) {
                keyPosition.put(keyboard[i][j], new int[]{i, j});
            }
        }
    }

    static int calculateTime(int[] startPos, int[] endPos) {
        return Math.abs(startPos[0] - endPos[0]) + Math.abs(startPos[1] - endPos[1]);
    }
}
