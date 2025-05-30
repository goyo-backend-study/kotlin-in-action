package Chapter09;

// 제네릭 클래스 정의
class BoxJava<T> {
    private T value;

    public BoxJava(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}

public class Chapter_9_1 {
    public static void main(String[] args) {
        // Raw 타입 사용 (컴파일 경고는 발생하지만 오류는 아님)
        BoxJava rawBoxJava = new BoxJava("Hello");  // Raw 타입 사용

        // 타입 안전성이 없음 - 어떤 타입이든 설정 가능
        rawBoxJava.setValue(42);  // String 타입이었지만 Integer로 변경 가능

        // ClassCastException 발생 위험
        try {
            String value = (String) rawBoxJava.getValue();  // 런타임 오류 발생!
            System.out.println(value);
        } catch (ClassCastException e) {
            System.out.println("예상된 오류 발생: " + e.getMessage());
        }

        // 올바른 제네릭 사용법
        BoxJava<String> stringBoxJava = new BoxJava<>("Hello");
        // stringBox.setValue(42);  // 컴파일 오류 - 타입 안전성 보장

        // 다이아몬드 연산자(Java 7+)
        BoxJava<Integer> intBoxJava = new BoxJava<>(42);

        // 제네릭 메소드 호출 시에도 raw 타입 사용 가능하지만 권장되지 않음
        printBox(rawBoxJava);  // 경고 발생
        printBox(stringBoxJava);  // 정상 동작
    }

    // 제네릭 메소드
    public static <T> void printBox(BoxJava<T> boxJava) {
        System.out.println("Box contains: " + boxJava.getValue());
    }
}