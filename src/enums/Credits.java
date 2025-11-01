package enums;

public enum Credits {

    SEVEN_POINT_FIVE(7.5),
    FIFTEEN(15.0),
    THIRTY(30.0);

    private final double points;

    private Credits(double points) {
        this.points = points;
    }

    public static Credits getEnum(String s) {
        Credits credit = null;

        switch (s) {
            case "7.5":
                credit = Credits.SEVEN_POINT_FIVE;
                break;
            case "15.0":
                credit = Credits.FIFTEEN;
                break;
            case "30.0":
                credit = Credits.THIRTY;
                break;
            default:
                break;
        }
        return credit;
    }

    @Override
    public String toString() {
        return String.valueOf(points);
    }

}
