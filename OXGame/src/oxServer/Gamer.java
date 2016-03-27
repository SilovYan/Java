/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oxServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Силов
 */
public class Gamer extends Thread{
    private Game game;
    private Socket s;
    private int codeGamer; // 0 - X, 1 - O, 2 - Watch
   
    public Gamer(Game game, Socket s, int codeGamer){
        super();
        this.game=game;
        this.s=s;
        this.codeGamer=codeGamer;
        
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }
    public void tell(String str) throws IOException{
        s.getOutputStream().write(str.getBytes());
    }
    @Override
    public void run(){
        try{
            byte buf[] = new byte[64*1024];
            int r = s.getInputStream().read(buf);
            String data = new String(buf, 0, r);
            StringBuilder sb=new StringBuilder(data);
            if(sb.indexOf("Chat")==0){ //Определяем это чат или игра
                if(codeGamer==0)
                    sb=sb.replace(0, 5, "X");
                if(codeGamer==1)
                    sb=sb.replace(0, 5, "O");
                game.tellAllChat(sb.toString());
            }
            else if(sb.indexOf("Game")==0){
                sb=sb.replace(0, 5, "");
                game.gameMoment(sb.toString(),codeGamer);
            }
        }
        catch(Exception ex){
            System.out.println("init error2: "+ex);
        }
    }
}
