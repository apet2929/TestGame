package experiments.tutorialGame;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.Vector;

public class Player extends GameObject{

    Random r = new Random();
    Handler handler;
    private Rectangle lastBounds;
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    @Override
    public void tick() {
        for(int i = 0; i < velX; i+=2){
            x+=2;
            onCollision();
        }

        for(int i = 0; i < velY; i+=2){
            y++;
            onCollision();
        }

        x = Game.clamp(x, 0, Game.WIDTH-47);
        y = Game.clamp(y, 0, Game.HEIGHT-70);


        //if(velX != 0 || this.velY != 0) handler.addObject(new Trail(x, y, ID.Trail, Color.white, 32, 32, 0.04f, this.handler));
    }
    private void onCollision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);

                switch (tempObject.getId()) {
                    case BasicEnemy:
                        onEnemyCollision(tempObject);
                        break;
                    case Food:
                        onFoodCollision(tempObject);
                        break;
                    case Block:
                        onBlockCollision(tempObject, lastBounds);
                        break;
                    default:
                        break;

                }
            }
            lastBounds = getBounds();

    }
    private void onEnemyCollision(GameObject tempObject){
        if(getBounds().intersects(tempObject.getBounds())) {
            HUD.HEALTH -= 2;
        }
    }

    private void onFoodCollision(GameObject tempObject){
        if(getBounds().intersects(tempObject.getBounds())) {
            if (Food.r.nextInt(10) == 1) {
                HUD.HEALTH -= 8;
            } else HUD.HEALTH += 2;
            handler.removeObject(tempObject);
        }
    }
    private void onBlockCollision(GameObject block, Rectangle lastBounds){
        if(getBounds().intersects(block.getBounds())) {

            if (!lastBounds.intersects(block.getBounds())) {

//                x = (int) lastBounds.getX();
//                y = (int) lastBounds.getY();
                velX = 0;
                velY = 0;
            } else {
                System.out.println("yes");
                if (x < block.getX() + block.getBounds().getWidth() / 2) x = block.getX();
                else x = block.getX() + (int) block.getBounds().getWidth();
                if (y < block.getY() + block.getBounds().getHeight() / 2) y = block.getY();
                else y = block.getY() + (int) block.getBounds().getHeight();
            }
        }
    }

    private int[] getFacing(){ //Returns an int array of the facing direction, where 0 is the x value and 1 is the y value of the facing direction projected to the edges of the screen
        int vx = (velX > 0) ? Game.WIDTH : 0;
        vx =  (velX == 0) ? x+16 : vx;
        int vy = (velY > 0) ? Game.HEIGHT : 0;
        vy = (velY == 0) ? y+16 : vy;
        return new int[]{vx, vy};
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.CYAN);
        g.fillRect(x,y,32,32);
        g.setColor(Color.MAGENTA);
        g.drawLine(x+16, y+16, getFacing()[0], getFacing()[1]);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }


}
