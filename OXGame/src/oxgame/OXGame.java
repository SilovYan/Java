/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oxgame;

import java.awt.GridLayout;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Силов
 */
public class OXGame {

    /**
     * @param args the command line arguments
     */
    private OXGameFrame gameFrame;
    private boolean gameStart=false;
    private int gamerCode=1; // -2 - notStarted, -1 - watcher, 0 - O, 1 - X
    private boolean your=false;
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
    public int getGamerCode(){
        return gamerCode;
    }
    public void click(int i, int j){
        if(gameStart==true && your==true && field[i][j]==-1)
        {
            if(gamerCode==0)
                field[i][j]=0;
            if(gamerCode==1)
                field[i][j]=1;
            gameFrame.updateField();
            checkWin();
            //gamerCode=Math.abs(gamerCode-1);
        }
    }
    private int checkRow(int i){
        if(field[i][0]!=-1 && field[i][0]==field[i][1] && field[i][1]==field[i][2]){
            return field[i][0];
        }
        return -1;
    }
    private int checkColum(int i){
        if(field[0][i]!=-1 && field[0][i]==field[1][i] && field[1][i]==field[2][i]){
            return field[0][i];
        }
        return -1;
    }
    private int checkX(){
        if(field[1][1]!=-1){
            boolean hunter, altern;
            hunter=(field[0][0]==field[1][1] && field[1][1]==field[2][2]);
            altern=(field[0][2]==field[1][1] && field[1][1]==field[2][0]);
            if(hunter || altern){
                return field[1][1];
            }
        }
        return -1;
    }
    private boolean win(int gamer){
        if(gamer!=-1){
            gameStart=false;
            your=false;
            gamerCode=-2;
            gameFrame.updateFrame();
            return true;
        }
        return false;
    }
    private void checkWin(){
        if(win(checkX())){
            return;
        }
        for(int i=0; i<3; i++){
            if(win(checkRow(i)) || win(checkColum(i)))
                return;
        }
    }
    
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        OXGame game=new OXGame();
        OXGameFrame gameFrame=new OXGameFrame(game);
        gameFrame.setVisible(true);
    }
    
}
