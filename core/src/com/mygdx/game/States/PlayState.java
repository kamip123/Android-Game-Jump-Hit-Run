package com.mygdx.game.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.sprites.CharacterMainHeroM;

/**
 * Created by Kamil on 2018-03-05.
 */

public class PlayState extends State{
    private CharacterMainHeroM cosiek;
    private Texture bg;
    public PlayState(GameStateManager gsm) {
        super(gsm);
        cosiek = new CharacterMainHeroM(50,100);
        cam.setToOrtho(false, MyGdxGame.WIDTH / 2, MyGdxGame.HEIGHT / 2);
        bg = new Texture("bg.png");
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

        cam.position.x = cosiek.getPosition().x + 80;

        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bg, cam.position.x - ( cam.viewportWidth / 2), 0);
        sb.draw(cosiek.getTexture(), cosiek.getPosition().x, cosiek.getPosition().y);
        sb.end();
    }

    @Override
    public void dispose() {

    }
}
