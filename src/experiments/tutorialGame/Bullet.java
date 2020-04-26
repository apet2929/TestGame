package experiments.tutorialGame;

import java.awt.*;

public class Bullet extends GameObject{

    public Bullet(int x, int y, ID id, int vx, int vy) {
        super(x, y, id);
        velX = vx;
        velY = vy;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;
    }
    @Override
    public void render(Graphics g) {
        g.setColor(Color.darkGray);
        g.fillRect(x,y,2,2);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,2,2);
    }
}
