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
    private Game game;
    private boolean gameStart=false;
    private int codeWin=-1;
    private boolean cureX=true;
    private int[][] field=new int[3][3];
    public GameProcess(Game game){
        this.game=game;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++)
                field[i][j]=-1;
        }
    }
    public void start(){
        if(gameStart==false){
            gameStart=true;
            game.tellAllGame(getCureStats());
            // Запустить игру надо как-то
        }
    }
    public String getCureStatField(){
        StringBuilder res=new StringBuilder("");
        res.append("Game ");
        for(int line[]:field){
            for(int t:line){
                res.append(t+" ");
            }
        }
        if(cureX){
            res.append("X");
        }
        else{
            res.append("O");
        }
        System.out.println("cure stats: "+res.toString());
        return res.toString();
    }
    public String getCureStats(){
        StringBuilder res=new StringBuilder("");
        if(gameStart==false)
        {
            if(codeWin==-1){
                res.append("Ожидание игроков");
            }
            else{
                res.append("Победил");
                if(codeWin==0)
                    res.append(" игрок X");
                else if(codeWin==1)
                    res.append(" игрок O");
                else
                    res.append("а дружба");
                System.out.println("cure stats: "+res.toString());
            }
        }
        return res.toString();
    }
    public boolean isStart(){
        return gameStart;
    }
    public boolean isCureX(){
        return cureX;
    }
    public boolean checkHop(int x, int y, int codeSender){
        if(field[x][y]==-1 && (codeSender==0)==cureX){
            field[x][y]=codeSender;
            cureX=!cureX;
            checkEndGame();
            return true;
        }
        return false;
    }
    
    private int checkRowWin(int i){
        if(field[i][0]!=-1 && field[i][0]==field[i][1] && field[i][1]==field[i][2]){
            return field[i][0];
        }
        return -1;
    }
    private int checkColumWin(int i){
        if(field[0][i]!=-1 && field[0][i]==field[1][i] && field[1][i]==field[2][i]){
            return field[0][i];
        }
        return -1;
    }
    private int checkX(){
        if(field[1][1]!=-1){
            boolean hunter, altern; // основная и побочная диагонали
            hunter=(field[0][0]==field[1][1] && field[1][1]==field[2][2]);
            altern=(field[0][2]==field[1][1] && field[1][1]==field[2][0]);
            if(hunter || altern){
                return field[1][1];
            }
        }
        return -1;
    }
    private boolean checkFull(){
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                if(field[i][j]==-1)
                    return false;
        return true;
    }
    private boolean endGame(int gamer){
        if(gamer!=-1){
            gameStart=false;
            codeWin=gamer;
        }
        return false;
    }
    private void checkEndGame(){
        if(checkX()!=-1){
            endGame(field[1][1]);
            return;
        }
        for(int i=0; i<3; i++){
            if(checkRowWin(i)!=-1 || checkColumWin(i)!=-1){
                endGame(field[i][i]);
                return;
            }
        }
        if(checkFull()==true){
            endGame(-1);
        }
    }
}
