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
public class Game extends Thread{
    Gamer X; // игрок за крестик
    Gamer O; // Игрок за нолик
    ArrayList<Gamer> watchers=new ArrayList(); // зрители
    GameProcess process;
    
    public Game(){
        process=new GameProcess();
        X=O=null;
        watchers.clear();
        
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }
    public boolean isFull(){
        if(O!=null)
            return true;
        return false;
    }
    public void addGamer(Socket s){
        if(X==null)
            X=new Gamer(this,s,0);
        else if(O==null)
            O=new Gamer(this,s,1);
    }
    public void addWatcher(Socket s){
        watchers.add(new Gamer(this,s,2));
    }
    public void tellAll(String str) throws IOException{
        X.tell(str);
        O.tell(str);
        for(Gamer t: watchers){
            t.tell(str);
        }
    }
    @Override
    public void run(){
        try{
            
        }
        catch(Exception ex){
            
        }
    }
}
