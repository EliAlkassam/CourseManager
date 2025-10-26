package gui;

public enum Credits {

    SEVEN_POINT_FIVE(7.5),
    FIFTEEN(15.0),
    THIRTY(30.0);

    private final double points;

    private Credits(double points) {
        this.points = points;
    }

    public double getCredits() {
        return this.points;
    }

    @Override
    public String toString() {
        return String.valueOf(points);
    }

}
