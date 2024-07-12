import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    static final int[] map = new int[101];

    static final Map<Integer, Integer> ladderAndSnake = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int[] input1 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input1[0];
        int M = input1[1];

        for (int i = 0; i < N + M; i++) {
            int[] input2 = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            ladderAndSnake.put(input2[0], input2[1]);
        }

        Stack<Counter> stack = new Stack<>();
        stack.push(new Counter(0, 1));
        while (!stack.isEmpty()) {
            Counter counter = stack.pop();
            int turn = counter.turn + 1;
            int currentPosition = counter.position;
            for (int i = currentPosition + 1; i <= currentPosition + 6; i++) {
                if (i > 100) break;  // 100을 초과하는 부분에 대해서는 갱신 필요 없음

                if (ladderAndSnake.get(i) != null) {
                    int j = ladderAndSnake.get(i);
                    if (map[j] == 0 || map[j] > turn) {
                        map[j] = turn;
                        stack.push(new Counter(turn, j));
                    }
                    continue;
                }
                if(map[i] == 0 || map[i] > turn) {
                    map[i] = turn;
                    stack.push(new Counter(turn, i));   // 조건을 만족하면 stack에 추가
                }
            }
        }
        System.out.println(map[100]);
    }

    static class Counter {
        int turn;   // 몇번째 턴인가
        int position;   // 현재 위치가 어디인가

        Counter(int turn, int position) {
            this.turn = turn;
            this.position = position;
        }
    }
}