package com.mygdx.game.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import java.util.Random;

/**
 * Created by Kamil on 2018-05-03.
 */

public class Obstacle {
    private Texture obstacle;
    private Vector2 posObstacle;
    private Random rand;
    private Polygon boundsOfObstacle;
    private static final int OBSTACLE_GAP = 300;
    public static final int OBSTACLE_WIDTH = 65;
    private boolean jump=true;

    public Obstacle(int x){
        obstacle = new Texture("spike.png");
        int randSpace = x * 150 + (int)(Math.random() * ((300) + 1));

        posObstacle = new Vector2(((x * OBSTACLE_GAP) + randSpace), 0);
        float[] triangleVertices = {posObstacle.x,0,posObstacle.x+OBSTACLE_WIDTH,0,posObstacle.x+(OBSTACLE_WIDTH/2),OBSTACLE_WIDTH};
        boundsOfObstacle = new Polygon(triangleVertices);
    }

    public Texture getObstacle() {
        return obstacle;
    }

    public Vector2 getPosObstacle() {
        return posObstacle;
    }

    public boolean getStatusObstacle() {
        return jump;
    }

    public void reposition(float x){
        int randSpace = 0 + (int)(Math.random() * ((100) + 1));
        posObstacle.set((x+OBSTACLE_GAP + randSpace), 0);
        float[] triangleVertices = {posObstacle.x,0,posObstacle.x+OBSTACLE_WIDTH,0,posObstacle.x+(OBSTACLE_WIDTH/2),OBSTACLE_WIDTH};
        boundsOfObstacle = new Polygon(triangleVertices);
        jump=true;
    }

    public void changeStatus(){
        jump=false;
    }

    public boolean collision(Vector3 position){
        return boundsOfObstacle.contains(position.x, position.y);
    }

}
