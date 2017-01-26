package danon.Cymbergaj;

public enum WeaponType {
    FlashLight, Knife, HandGun, Rifle, Shotgun;

    private static final WeaponType[] types = values();

    public WeaponType next() {
        return types[(this.ordinal() + 1) % types.length];
    }
}
