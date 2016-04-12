/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oxServer;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Силов
 */
public class Server extends Thread{
    private Socket s;
    private int num; // количество игр
    private ArrayList<Game> games=new ArrayList(); // список игр
    
    public Server(){
        super();
        games.clear();
        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }
    @Override
    public void run(){
         try
        {
            byte buf[] = new byte[64*1024];
            int n;
            // привинтить сокет на локалхост, порт 3128
            ServerSocket server = new ServerSocket(56003, 0,InetAddress.getByName("localhost"));
            System.out.println("server is started");
            // слушаем порт
            while(true)
            {
                // ждём нового подключения, после чего запускаем обработку клиента
                // в новый вычислительный поток и увеличиваем счётчик на единичку
                int countGames=games.size();
                if(countGames==0){
                    games.add(new Game());
                    countGames++;
                }
                else if(games.get(countGames-1).isFull()==true){
                    //games.get(countGames-1).start();
                    games.add(new Game());
                    countGames++;
                }
                System.out.println("Ждем подключения ");
                Socket client=server.accept();
                System.out.println("Кто-то подключился ");
                InputStream is=client.getInputStream();
                n=is.read(buf);
                String str=new String(buf, 0, n);
                System.out.println("Приняли "+str);
                //is.close(); // надо ли тут закрывать поток?
                if(str.compareTo("Игрок")==0)
                    games.get(countGames-1).addGamer(client);
                else{
                    String[] parts=str.split(" ");
                    Integer k=Integer.parseInt(parts[1]);
                    if(k<games.size() && k>-1)
                        games.get(k).addWatcher(client);
                }
                System.out.println("one connect");
            }
        }
        catch(Exception ex) // вывод исключений
        {
            System.out.println("init error1: "+ex);
        } 
    }
    
    public static void main(String[] args){
        Server server=new Server();
        try {
            server.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
