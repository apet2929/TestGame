package experiments.tutorialGame;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
    private Handler handler;

    public KeyInput(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_ESCAPE) System.exit(1);
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                //All key events for player
                if(key == KeyEvent.VK_W) tempObject.setVelY(-5);   //Moves up on key W pressed
                if(key == KeyEvent.VK_S) tempObject.setVelY(+5);
                if(key == KeyEvent.VK_A) tempObject.setVelX(-5);
                if(key == KeyEvent.VK_D) tempObject.setVelX(+5);

            }

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++){
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Player){
                //All key events for player
                if(key == KeyEvent.VK_W) tempObject.setVelY(0);
                if(key == KeyEvent.VK_S) tempObject.setVelY(0);
                if(key == KeyEvent.VK_A) tempObject.setVelX(0);
                if(key == KeyEvent.VK_D) tempObject.setVelX(0);
//                if(key == KeyEvent.VK_SHIFT) handler.addObject(new Bullet(tempObject.getX()+16,tempObject.getY()+16, ID.Bullet, tempObject.getVelX(), tempObject.getVelY()));
            }

        }
    }
}
