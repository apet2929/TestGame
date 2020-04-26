package experiments.tutorialGame;

import java.awt.*;

public class Block extends GameObject{

    public Block(int x, int y) {
        super(x, y, ID.Block);
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x,y,32,32);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y,32,32);
    }
}
