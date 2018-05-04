package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by Kamil on 2018-03-05.
 */

public class CharacterMainHeroM {
    private static final int GRAVITY = -20;
    private static final int MOVEMENT = 365;
    private Vector3 position;
    private Vector3 velocity;

    private Animation cheroAnimation;

    private Animation cheroAnimationBackup;
    private Animation cheroAnimationAttack;
    private long startTime;
    int score = 0;

    public CharacterMainHeroM(int x, int y){
        position = new Vector3(x, y, 0);
        velocity = new Vector3(0, 0, 0);
        Texture textureAnimation = new Texture("cosiekanim.png");
        cheroAnimation = new Animation(new TextureRegion(textureAnimation), 4, 0.8f);
        Texture textureAnimationAttack = new Texture("cosiekanimattack.png");
        cheroAnimationAttack = new Animation(new TextureRegion(textureAnimationAttack), 4, 0.4f);
        cheroAnimationBackup=cheroAnimation;
    }

    public void update(float dt){
        long elapsedTime = System.currentTimeMillis() - startTime;
        if(elapsedTime>500)
        {
            cheroAnimation=cheroAnimationBackup;
            elapsedTime=0;
        }
        cheroAnimation.update(dt);
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

    public TextureRegion getTexture() {
        return cheroAnimation.getFrame();
    }

    public void jump(){
        if (position.y==0) {
            velocity.y=480;
        }
    }

    public int attack(Zombie[] zombies){
        startTime = System.currentTimeMillis();

        for(int i=0;i<3;i++) {
            if (zombies[i].getPosZombie().x>position.x && zombies[i].getPosZombie().x<position.x+100 ) {
                float q = zombies[2].getPosZombie().x;
                zombies[i].killed(q);
                cheroAnimation=cheroAnimationAttack;
                score++;
            }
        }
        int punkty = score;
        score=0;
        return punkty;
    }
}
