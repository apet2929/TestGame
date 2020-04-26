package experiments.tutorialGame;

import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Food extends GameObject {

    Handler handler;
    Timer timer;
    public static Random r;
    public Food(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
        timer = new Timer();
        r = new Random();


        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if(countFood() < 7)

                spawnFood();

            }
        }, 2000);

    }

    @Override
    public void tick() {
        if(countFood() == 0) spawnFood();
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x,y,16,16);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x,y,32,32);
    }
    public int countFood() {
        int cnt = 0;
        for (int i = 0; i < handler.object.size(); i++) {
            if (handler.object.get(i).getId() == ID.Food) cnt++;
        }
        return cnt;
    }

    public void spawnFood(){
        handler.addObject(new Food(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.Food, handler));
    }

}
