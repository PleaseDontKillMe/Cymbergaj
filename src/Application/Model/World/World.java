package Application.Model.World;


import Application.Geometry.Size;
import Application.Model.GameEventListener;
import Application.Model.World.Character.Character;
import Application.View.ImagesRepository;
import Application.View.Renderer;
import Application.View.WorldRenderer;

import java.util.ArrayList;
import java.util.List;

public class World implements GameEventListener {

    private final List<Character> characters = new ArrayList<>();
    private final Size size;

    private double backgroundSlide = 0.0;
    private final static double backgroundSlideSpeed = 0.8;

    public World(Size size) {
        this.size = size;
    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public int backgroundX() {
        return (int) backgroundSlide;
    }

    public Renderer getRenderer(ImagesRepository images) {
        return new WorldRenderer(this, images);
    }

    @Override
    public void update() {
        backgroundSlide += backgroundSlideSpeed;
    }
}