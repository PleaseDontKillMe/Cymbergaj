package Application.Model.World;

import Application.Geometry.Angle;
import Application.Geometry.Point;
import Application.Model.Delta.QuadraticSolution;
import Application.Model.Delta.QuadraticSolver;
import Application.Model.World.Character.Body;
import Application.Model.World.Character.Fireball;

import java.util.List;

public class FireballBounce {
    private Fireball fireball;
    private final List<Body> allBodies;
    private final DebugExportInterface export;

    public FireballBounce(List<Body> bodies, DebugExportInterface export) {
        this.allBodies = bodies;
        this.export = export;
    }

    public FireballBounce(List<Body> bodies) {
        this.allBodies = bodies;
        this.export = new IgnoreDebugExport();
    }

    public void bounce(Fireball bodyToBounce) {
        export.clear();
        this.fireball = bodyToBounce;
        export.setFireball(fireball);
        allBodies.stream()
                .filter(body -> body != bodyToBounce)
                .filter(this::bodiesHeadTowards)
                .forEach(this::bounceWithBody);
    }

    private boolean bodiesHeadTowards(Body second) {
        return true;
    }

    /**
     * Za tą metodą stoi silna matematyka. Wchodzisz na własną odpowiedzialność.
     * Zobacz demo, uruchamiając metodę main w Application.Debug.DebugRunner
     * <p>
     * Ta metoda rozumuje tak: mają się odbić dwa okrągłe ciała. Znajdujemy na okręgu piłki taki punkt (a),
     * który, jeżeli poprowadzić od niego linię równoległą do kierunku piłki, utworzy najbliższy dystans do
     * okręgu drugiej piłki. Czyli po prostu obliczamy punkt w którym dwie piłki się odbiją (c). Obliczamy teraz
     * tylko odległość (dystans) od tych dwóch punktów, i mamy dystans który ma przebyć piłka. Następnie zmieniamy jej
     * kierunek (odbijamy ją). Piłka nie wchodzi w gracza bo ma już zmieniony kierunek.
     * <p>
     * <p>
     * Korzystamy z: <p>
     * 1) faktu że pierwszy punkt (a) musi być na okręgu koła (odległość od środka ma być równa promieniowi) <p>
     * 2) wyznaczenia punktu pod pewnym kątem i odległością od innego punkty <p>
     * (c.x - a.x)^2 + (c.y - a.y)^2 = r^2 <p>
     * c.x = a.x + dystans * sin(angle) <p>
     * c.y = a.y + dystans * sin(kąt) <p>
     * <p>
     * Po uproszczeniu wychodzi nam: <p>
     * r^2 = dystans^2 * sin(kąt)^2 + 2*dystans*sin(kąt) *(a.y–c.y) + (a.y – c.y)^2 <p>
     * + dystans^2 * cos(kąt)^2 + 2*dystans*cos(kąt)*(a.x–c.x)) + (a.x – c.x))^2 <p>
     * <p>
     * A po jeszcze większym, równanie kwadratowe (dystans to nasz x do znalezienia) <p>
     * 0 = dystans^2 * (sin(kąt)^2 cos(kąt)^2) + 2*dystans*sin(kąt) *(cos(kąt)*(a.x–c.x) + (a.y–c.y)) + (a.y – c.y)^2 (a.x – c.x))^2 - r^2
     * <p>
     * <p>
     * PS: Ta metoda jest tak zajebista, że działa nawet kiedy te koła się przecinają ale są w sobie.
     * <p>
     * 1) Oba x1 oraz x2 są dodatnie:                       koła się nie stykają (tutaj odbijamy)<p>
     * 2) Jedno x1 albo x2 jest dodatnie, a drugie ujemne:  koła się przecinają<p>
     * 3) Oba są ujemne:                                    jedno koło zjada całę inne koło, albo drugie koło jest za pierwszym i i tak się nigdy nie zderzą<p>
     * 4) Nie ma miejsc zerowych:                           Jedno koło, zachowując swoją prędkość nigdy nie spotka<p>
     * drugiego (nie zderzą się nigdy)
     *
     * @param body Jedno ciało od którego ma odbić się piłka.
     */
    private void bounceWithBody(Body body) {
        Point ship = body.getPosition();
        Point a = fireball.getPosition().find(fireball.getRadius(), fireball.getPosition().angleTo(ship));
        Angle direction = fireball.getDirection();

        double a1 = 1;
        double b1 = 2 * direction.sin() * (a.y - ship.y) + 2 * direction.cos() * (a.x - ship.x);
        double c1 = a.distancePowTo(ship) - Math.pow(body.getRadius(), 2);

        QuadraticSolution solution = new QuadraticSolver(a1, b1, c1).solve();

        if (solution.hasNone()) {
            export.hasNoDistance();
        }

        if (solution.hasOne()) {
            double distance = solution.getFirst();
            export.singleDistance(distance);
            if (0 < distance && distance <= fireball.getVelocity()) {
                doStuffWithDistance(a, ship, direction, distance);
            }
        }

        if (solution.hasTwo()) {
            double distance1 = solution.getFirst();
            double distance2 = solution.getSecond();
            export.doubleDistance(distance1, distance2);
            if (distance1 >= 0 && distance2 >= 0) {
                double distance = Math.min(distance1, distance2);
                if (distance <= fireball.getVelocity()) {
                    doStuffWithDistance(a, ship, direction, distance);
                }
            }
        }
    }

    private void doStuffWithDistance(Point a, Point ship, Angle direction, double distance) {
        export.doStuffWith(a, ship, direction, distance);
        double remain = fireball.getVelocity() - distance;
        Point b = a.find(distance, direction);
        fireball.getControl().bounceAngle(b.angleTo(ship).plus(Math.PI / 2));
        fireball.getPosition().setSize(b);
        fireball.getControl().moveAlong(remain);
    }
}