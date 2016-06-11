package Application.Model.World;


import Application.Model.GameEventListener;

import java.util.ArrayList;
import java.util.List;

public class World implements GameEventListener {

    private final List<Character> characters = new ArrayList<>();

    private double backgroundSlide = 0.0;
    private final static double backgroundSlideSpeed = 1.3;

    public World() {

    }

    public void addCharacter(Character character) {
        characters.add(character);
    }

    public int backgroundX() {
        return (int) backgroundSlide;
    }

    @Override
    public void update() {
        backgroundSlide += backgroundSlideSpeed;
    }
}