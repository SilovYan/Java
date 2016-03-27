/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oxClient;

import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Силов
 */
public class OXGame extends Thread{
    /**
     * @param args the command line arguments
     */
    private OXGameFrame gameFrame;
    private boolean gameStart=false;
    private int[][] field=new int[3][3]; // -1 - none, 0 - O, 1 - X
    public OXGame(){
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                field[i][j]=-1;
    }
    public void addFrame(OXGameFrame gameFrame){
        this.gameFrame=gameFrame;
    }
    public int[][] getFiled(){
        return field;
    }
    public void startGame(int needGame){
        if(gameStart==false){
            gameStart=true;
            if(needGame==-1){
                // запрос на сервер с получением информации о опоненте
            }
            else{
                // запрос информации о запрашиваемой игре
            }
        }
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
         OXGame game=new OXGame();
//        OXGameFrame gameFrame=new OXGameFrame(game);
//        gameFrame.setVisible(true);
          System.out.println(game.getClass().getTypeName());
    }
    
}
