package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by Kamil on 2018-05-03.
 */

public class Zombie {
    private Vector2 posZombie;
    private Random rand;
    private Polygon boundsOfZombie;
    private Animation zombieAnimation;
    private Vector3 position;
    private static final int Zombie_GAP = 300;
    public static final int Zombie_WIDTH = 35;
    private static final int MOVEMENT = -50;

    public Zombie(int x){
        Texture textureAnimation = new Texture("zombieanim.png");
        zombieAnimation = new Animation(new TextureRegion(textureAnimation), 4, 0.8f);

        int randSpace = x * 250 + (int)(Math.random() * ((300) + 1));

        posZombie = new Vector2(((x * Zombie_GAP) + randSpace), 0);
        float[] zombieVertices = {posZombie.x,0,posZombie.x+Zombie_WIDTH,0,posZombie.x,55,posZombie.x+Zombie_WIDTH,55};
        boundsOfZombie = new Polygon(zombieVertices);
    }

    public void update(float dt){
        zombieAnimation.update(dt);
        posZombie.add(MOVEMENT * dt, 0);
        float[] triangleVertices = {posZombie.x,0,posZombie.x+Zombie_WIDTH,0,posZombie.x+(Zombie_WIDTH/2),Zombie_WIDTH};
        boundsOfZombie = new Polygon(triangleVertices);
        //if(position.x<0) {
        //    position.x=0;
        //}
    }

    public TextureRegion getZombie() {
        return zombieAnimation.getFrame();
    }

    public Vector2 getPosZombie() {
        return posZombie;
    }

    public void reposition(float x){
        int randSpace = 0 + (int)(Math.random() * ((100) + 1));
        posZombie.set((x+Zombie_GAP + randSpace), 0);
        float[] triangleVertices = {posZombie.x,0,posZombie.x+Zombie_WIDTH,0,posZombie.x+(Zombie_WIDTH/2),Zombie_WIDTH};
        boundsOfZombie = new Polygon(triangleVertices);
    }

    public void killed(float x){
        int randSpace = 0 + (int)(Math.random() * ((100) + 1));
        posZombie.set((x+Zombie_GAP + randSpace), 0);
        float[] triangleVertices = {posZombie.x,0,posZombie.x+Zombie_WIDTH,0,posZombie.x+(Zombie_WIDTH/2),Zombie_WIDTH};
        boundsOfZombie = new Polygon(triangleVertices);
    }

    public boolean collision(Vector3 position){
        return boundsOfZombie.contains(position.x, position.y);
    }
}
