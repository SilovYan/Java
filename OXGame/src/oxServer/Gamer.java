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
    private boolean notEnd=true;
    private int codeGamer; // 0 - X, 1 - O, 2 - Watch
   
    public Gamer(Game game, Socket s, int codeGamer){
        super();
        System.out.println("Создан игрок");
        this.game=game;
        this.s=s;
        this.codeGamer=codeGamer;
        if(codeGamer==2)
            tell("Зритель");
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }
    public void tell(String str){
        try{
            System.out.println("Отправили "+str);
            s.getOutputStream().write(str.getBytes());
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    @Override
    public void run(){
        try{
            byte buf[] = new byte[64*1024];
            while(notEnd){
                int r = s.getInputStream().read(buf);
                String data = new String(buf, 0, r);
                System.out.println("Приняли "+data);
                StringBuilder sb=new StringBuilder(data);
                if(sb.indexOf("Chat")==0){ //Определяем это чат или игра
                    if(codeGamer==0)
                        sb=sb.replace(0, 5, "X: ");
                    if(codeGamer==1)
                        sb=sb.replace(0, 5, "O: ");
                    if(codeGamer==2)
                        sb=sb.replace(0,5,"W: ");
                    System.out.println("Сообщение в чат: "+sb.toString());
                    game.tellAllChat(sb.toString());
                }
                else if(sb.indexOf("Game")==0){
                    sb=sb.replace(0, 5, "");
                    System.out.println("Ход в виде "+sb.toString());
                    game.gameMoment(sb.toString(),codeGamer);
                }
                else
                    System.out.println("Не прошло парсинг");
            }
        }
        catch(Exception ex){
            System.out.println("init error2: "+ex);
        }
    }

    void close() {
        notEnd=false;
        try {
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(Gamer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
