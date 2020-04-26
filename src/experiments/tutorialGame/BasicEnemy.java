package experiments.tutorialGame;

import java.awt.*;

public class BasicEnemy extends GameObject {
    private Handler handler;
    private Rectangle lastBounds;
    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        velY = 5;
        velX = 5;
        this.handler = handler;
        lastBounds = getBounds();
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;

        onBlockCollision(lastBounds);
        lastBounds = getBounds();
        handler.addObject(new Trail(x, y, ID.Trail, Color.pink, 15, 15, 0.04f, this.handler));
    }

    private void onBlockCollision(Rectangle lastBounds) {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject block = handler.object.get(i);
            if (block.getId() == ID.Block && getBounds().intersects(block.getBounds())) { //If its a block and colliding
                if(Game.isBetweenExclusive(x, block.getX(), block.getX() + (int) block.getBounds().getWidth())){ //If x is between left and right corners
                    setVelY(getVelY() * -1);
                }
                if(Game.isBetweenExclusive(y, block.getY(), block.getY() + (int) block.getBounds().getHeight())){ //If y is between top and bottom corners
                    setVelX(getVelX() * -1);
                }

            }
        }
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x,y,16,16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,16,16);
    }
}
