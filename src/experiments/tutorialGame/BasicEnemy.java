package experiments.tutorialGame;

import java.awt.*;

public class BasicEnemy extends GameObject {
    private final Handler handler;


    public BasicEnemy(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        velY = 5;
        velX = 5;
        this.handler = handler;


    }

    @Override
    public void tick() {
        for(int i = 0; i < Math.abs(velX); i++){
            x += velX > 0 ? 1 : -1;
            onBlockCollision();

        }
        for(int i = 0; i < Math.abs(velY); i++){
            y += velY > 0 ? 1 : -1;
            onBlockCollision();

        }

        if(y <= 0 || y >= Game.HEIGHT - 32) velY *= -1;
        if(x <= 0 || x >= Game.WIDTH - 16) velX *= -1;


        handler.addObject(new Trail(x, y, ID.Trail, Color.pink, 15, 15, 0.04f, this.handler));
    }

    private void onBlockCollision() {
        for (int i = 0; i < handler.object.size(); i++) {
            GameObject block = handler.object.get(i);
            if (block.getId() == ID.Block) {
                if (getBounds().intersects(block.getBounds())) {
                    System.out.println("yes");
                    if (!new Rectangle(x, y-velY,16,16).intersects(block.getBounds())) {
                        setVelY(getVelY()*-1);
                    }
                    if(!new Rectangle(x - velX, y, 16, 16).intersects(block.getBounds())){
                        setVelX(getVelX()*-1);
                    }
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
