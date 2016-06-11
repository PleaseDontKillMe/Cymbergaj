package Application.Model.World;

class DamageResult {
    private final int pointsTaken;
    private final int pointsLeft;

    public DamageResult(int pointsTaken, int pointsLeft) {
        this.pointsTaken = pointsTaken;
        this.pointsLeft = pointsLeft;
    }

    public int getPointsTaken() {
        return pointsTaken;
    }

    public int getPointsLeft() {
        return pointsLeft;
    }

    public boolean resultedInDeath() {
        return pointsLeft == 0;
    }
}