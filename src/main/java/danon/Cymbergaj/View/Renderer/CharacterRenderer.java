package danon.Cymbergaj.View.Renderer;

import danon.Cymbergaj.Character;
import danon.Cymbergaj.Geometry.Angle;
import danon.Cymbergaj.Geometry.Point;
import danon.Cymbergaj.Geometry.Rotation;
import danon.Cymbergaj.Model.Updatable;
import danon.Cymbergaj.View.Renderer.Character.CharacterWeaponSheet;
import danon.Cymbergaj.View.SpriteSheet;

import java.awt.*;

public class CharacterRenderer extends BodyRenderer implements Updatable {
    private Character character;
    private final ImagesRepository images;

    private double totalElapsedTime = 0;

    public CharacterRenderer(Character character, ImagesRepository imagesRepository) {
        super(character);
        this.character = character;
        this.images = imagesRepository;
    }

    private SpriteSheet getCurrentSheet() {
        CharacterWeaponSheet weaponSheet = images.weaponSheets.get(character.getWeaponType());
        return character.getPosture().getSheetFor(weaponSheet);
    }

    @Override
    protected void renderBody(Graphics2D canvas) {
        SpriteSheet sheet = getCurrentSheet();
        sheet.drawOn(canvas,
                new Point(0, 0),
                new Rotation(new Angle(character.getOrientation()), sheet.getAnchorPoint())
        );
    }

    @Override
    public void update(double elapsedTime) {
        totalElapsedTime += elapsedTime;
        int frame = (int) Math.round(totalElapsedTime * 20.0);
        getCurrentSheet().setFrame(frame);
    }
}
