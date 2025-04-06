package ch.sobrado.radar;

public enum Position {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4);

    private final int order;

    Position(int order) {
        this.order = order;
    }

    public int getOrder() {
        return order;
    }
}
