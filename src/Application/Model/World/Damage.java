package Application.Model.World;


public class Damage {
    private final int mechanicalDamage;
    private final int chemicalDamage;

    public Damage(int mechanicalDamage, int chemicalDamage) {
        this.mechanicalDamage = mechanicalDamage;
        this.chemicalDamage = chemicalDamage;
    }

    public int getMechanicalDamage() {
        return mechanicalDamage;
    }

    public int getChemicalDamage() {
        return chemicalDamage;
    }
}
