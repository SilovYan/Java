/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oxServer;

import java.awt.List;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Силов
 */
public class Game{
    Gamer X; // игрок за крестик
    Gamer O; // Игрок за нолик
    ArrayList<Gamer> watchers=new ArrayList(); // зрители
    GameProcess process;
    
    public Game(){
        X=O=null;
        watchers.clear();
        process=new GameProcess(this);

    }
    public boolean isFull(){
        if(O!=null){
            System.out.println("Игра полная");
            return true;
        }
        return false;
    }
    public void addGamer(Socket s){
        if(X==null)
            X=new Gamer(this,s,0);
        else if(O==null)
        {
            O=new Gamer(this,s,1);
            tellAllGameStart();
            process.start();
        }
    }
    public void addWatcher(Socket s){
        watchers.add(new Gamer(this,s,2));
    }
    public void tellAllGame(String str){
        X.tell(str);
        O.tell(str);
        for(Gamer t: watchers){
            t.tell(str);
        }
    }
    public void tellAllGameStart(){
        X.tell("Игрок X");
        O.tell("Игрок O");
    }
    public void tellAllChat(String str) throws IOException{
        X.tell(str);
        O.tell(str);
        for(Gamer t: watchers){
            t.tell(str);
        }
    }
    public void gameMoment(String str, int codeGamer) throws IOException{
        String[] parts=str.split(" ");
        process.checkHop((int)Integer.parseInt(parts[0]), (int)Integer.parseInt(parts[1]), codeGamer);
        tellAllGame(process.getCureStatField());
        if(process.isStart()==false){
            tellAllGame(process.getCureStats());
        }
        
    }
}
