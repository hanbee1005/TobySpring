package domain;

public enum Level {
    // 3개의 enum object 정의
    GOLD(3, null), SILVER(2, GOLD), BASIC(1, SILVER);

    private final int value;
    private final Level next;

    // DB에 저장할 값을 넣어줄 생성자를 만들어준다.
    Level(int value, Level next) {
        this.value = value;
        this.next = next;
    }

    // 값을 가져오는 메소드
    public int intValue() {
        return value;
    }

    public Level nextLevel() { return next; }

    // 값으로부터 Level 타입 오브젝트를 가져오도록 만든 static method
    public static Level valueOf(int value) {
        switch (value) {
            case 1: return BASIC;
            case 2: return SILVER;
            case 3: return GOLD;
            default: throw new AssertionError("Unknown value: " + value);
        }
    }
}
