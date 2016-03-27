/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oxClient;

import java.awt.GridLayout;
import java.io.IOException;
import java.net.Socket;
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
    private Socket s;
    private OXGameFrame gameFrame;
    public OXGame(){
        setDaemon(true);
        setPriority(NORM_PRIORITY);
    }
    public void addFrame(OXGameFrame gameFrame){
        this.gameFrame=gameFrame;
    }
    public void startGame(int needGame) throws IOException{
        s=new Socket("localhost", 56003);
        if(needGame==-1){
            System.out.println("Отправили Игрок");
            s.getOutputStream().write("Игрок".getBytes());
        }
        else{
            System.out.println("Отправили Зритель"+needGame);
            s.getOutputStream().write(("Зритель "+needGame).getBytes());
        }
        start();
    }
    public void sendChat(String text){
        try{
            System.out.println("Отправили Chat "+text);
            s.getOutputStream().write(("Chat "+text).getBytes());
        }
        catch(Exception ex){
            
        }
    }
    public void click(Integer X, Integer Y){
        try{
            System.out.println("Отправили "+"Game "+X.toString()+" "+Y.toString());
            s.getOutputStream().write(("Game "+X.toString()+" "+Y.toString()).getBytes());
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }
        
    }
    private void Analize(String str){
        String[] parts=str.split(" ");
        switch(parts[0]){
            case "Игрок":
                if(parts[1].compareTo("X")==0){
                    gameFrame.gameStart('X');
                }
                else {
                    gameFrame.gameStart('O');
                }
                break;
            case "X:":
            case "O:":
                gameFrame.updateChat(str);
                break;
            case "Ожидание игроков":
                break;
            case "Победил":
            case "Победила":
                gameFrame.endGame(str);
            case "Game":
                gameFrame.updateField(str.replaceFirst("Game ", ""));
        }
    }
    @Override
    public void run(){
        try{
            byte buf[] = new byte[64*1024];
            while(true){
                int r = s.getInputStream().read(buf);
                String data = new String(buf, 0, r);
                System.out.println("Приняли "+data);
                // выводим ответ в консоль
                this.Analize(data);
            }
        }
        catch(Exception ex){
            System.out.println(ex.toString());
        }
    }
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        OXGame game=new OXGame();
        OXGameFrame gameFrame=new OXGameFrame(game);
        gameFrame.setVisible(true);
    }
    
}
