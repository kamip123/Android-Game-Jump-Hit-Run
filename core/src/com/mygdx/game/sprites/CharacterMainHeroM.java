package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Kamil on 2018-03-05.
 */

public class CharacterMainHeroM {
    private static final int GRAVITY = -20;
    private static final int MOVEMENT = 365;
    private Vector3 position;
    private Vector3 velocity;

    private Texture cosiek;

    public CharacterMainHeroM(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        cosiek = new Texture("cosiekanim.png");
    }

    public void update(float dt){
        velocity.add(0, GRAVITY, 0);
        velocity.scl(dt);
        position.add(MOVEMENT * dt, velocity.y, 0);
        velocity.scl(1/dt);
        if(position.y<0) {
            position.y=0;
        }
    }

    public Vector3 getPosition() {
        return position;
    }

    public Texture getTexture() {
        return cosiek;
    }

    public void jump(){
        if (position.y==0) {
            velocity.y=480;
        }
    }
}
