package Application.Model.World;

import Application.Geometry.Point;
import Application.Geometry.Size;
import Application.View.ImagesRepository;
import Application.View.Renderer;

public abstract class Character {

    private final Point position;
    private final Size size;
    private int healthPoints;

    public Character(Point position, Size size) {
        this.position = position;
        this.size = size;
        this.healthPoints = getMaxHealthPoints();
    }

    public DamageResult dealDamage(Damage damage)
    {
        int damageToDeal = damage.getChemicalDamage() + damage.getMechanicalDamage();
        if (damageToDeal > healthPoints) {
            int pointsTaken = healthPoints;
            healthPoints = 0;
            return new DamageResult(pointsTaken, 0);
        }
        healthPoints -= damageToDeal;
        return new DamageResult(damageToDeal, healthPoints);
    }

    public Point getPosition() {
        return position;
    }

    public abstract int getMaxHealthPoints();

    public abstract Control getControl();

    public abstract Renderer getRenderer(ImagesRepository images);
}
