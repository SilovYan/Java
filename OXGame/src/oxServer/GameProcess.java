/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oxServer;

/**
 *
 * @author Силов
 */
public class GameProcess {
    boolean gameStart=false;
    int[][] field=new int[3][3];
    public GameProcess(){
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++)
                field[i][j]=-1;
        }
    }
}
