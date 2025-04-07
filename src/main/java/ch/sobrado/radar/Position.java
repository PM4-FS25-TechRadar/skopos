package ch.sobrado.radar;

public enum Position {
    ONE(0),
    TWO(1),
    THREE(2),
    FOUR(3);

    private final int order;

    Position(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
