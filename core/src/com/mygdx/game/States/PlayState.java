package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.CharacterMainHeroM;
import com.mygdx.game.sprites.Obstacle;
import com.mygdx.game.sprites.Zombie;

/**
 * Created by Kamil on 2018-03-05.
 */

public class PlayState extends State{
    private CharacterMainHeroM cosiek;
    private Texture bg;

    private Obstacle[] obstacles;
    private Zombie[] zombies;
    private Obstacle temp;
    private Zombie tempz;
    private float q;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        cosiek = new CharacterMainHeroM(50,100);
        cam.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        bg = new Texture("bg.png");

        obstacles = new Obstacle[4];
        obstacles[0] = (new Obstacle(1));
        obstacles[1] = (new Obstacle(2));
        obstacles[2] = (new Obstacle(3));
        obstacles[3] = (new Obstacle(4));

        zombies = new Zombie[3];
        zombies[0] = (new Zombie(1));
        zombies[1] = (new Zombie(2));
        zombies[2] = (new Zombie(3));
    }

    @Override
    protected void handleInput() {
        if(Gdx.input.justTouched()){
            cosiek.jump();
        }
    }

    @Override
    public void update(float dt) {
        handleInput();
        cosiek.update(dt);
        for(int i=0;i<3;i++) {
            zombies[i].update(dt);
            if( zombies[i].collision(cosiek.getPosition())){
                gsm.set(new PlayState(gsm));
            }
            if(cam.position.x - 200 > zombies[i].getPosZombie().x + zombies[i].Zombie_WIDTH){
                tempz = zombies[2];
                q = zombies[2].getPosZombie().x;
                zombies[i].reposition(q);
                zombies[2] = zombies[i];
                zombies[i] = tempz;
            }
        }

        cam.position.x = cosiek.getPosition().x + 80;

        for(int i=0;i<4;i++){
            if(cam.position.x - 200 > obstacles[i].getPosObstacle().x + obstacles[i].getObstacle().getWidth()){
                temp = obstacles[3];
                q = obstacles[3].getPosObstacle().x;
                obstacles[i].reposition(q);
                obstacles[3] = obstacles[i];
                obstacles[i] = temp;
            }

            if( obstacles[i].collision(cosiek.getPosition())){
                gsm.set(new PlayState(gsm));
            }
        }

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - ( cam.viewportWidth / 2), 0);
        sb.draw(cosiek.getTexture(), cosiek.getPosition().x, cosiek.getPosition().y);
        for (int i=0;i<4;i++) {
            sb.draw(obstacles[i].getObstacle(), obstacles[i].getPosObstacle().x, obstacles[i].getPosObstacle().y);
        }
        for (int i=0;i<3;i++) {
            sb.draw(zombies[i].getZombie(), zombies[i].getPosZombie().x, zombies[i].getPosZombie().y);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
