import java.util.Arrays;
import java.util.Collection;

public class Lamda_Closure_Java {
    public static void main(String[] args) {
        Collection<String> strings = Arrays.asList("4", "5","4", "5","4", "5");

//        int count = 0;
        final int[] count = {0}; // 속임수1 : 배열을 사용하여 변경 가능

        strings.forEach(string -> {
            if(string.startsWith("4")) {
                //count++;  // final 변수만 변경 가능
                count[0]++;             }
        });
        System.out.println(count[0]);
    }
}

// 상태를 유지하는 클래스
class Counter {
    private int count = 0;

    public void increment() {
        count++; // 값을 증가시킬 수 있음
    }

    public int getValue() {
        return count;
    }
}