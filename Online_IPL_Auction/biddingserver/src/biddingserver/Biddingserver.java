package biddingserver;
import java.net.*;
import java.io.*;
import java.util.*;
class ServerClientThread extends Thread {
  Socket serverClient;
  int clientNo;
  int squre;
  ServerClientThread(Socket inSocket,int counter){
    serverClient = inSocket;
    clientNo=counter;
  }
  public void run(){
    try{
      DataInputStream dis = new DataInputStream(serverClient.getInputStream());
      DataOutputStream dos = new DataOutputStream(serverClient.getOutputStream()); 
      Scanner sc = new Scanner(System.in);
        while (true)
        {
            String input = dis.readUTF();
            System.out.println("Player Name : ");
            String player = sc.nextLine();
            dos.writeUTF(player);
            int b = 1;
            int baseprice = 200;
            System.out.println("The baseprice for any player is : "+baseprice);
            int price = baseprice;
            System.out.println("Team Name : " +input);
            while(b!=0){
            int bool = dis.readInt();
            if(bool == 2){
                price = 0;
                break;
            }
            else if(bool == 1){
                price = price+10;
            }
            else if(bool == 0)
            {
                b = 0;
            }
            }
            System.out.println("Sending the result...");
            dos.writeUTF(input);
            dos.writeInt(price);
       break; 
        }
  }
  catch(Exception e) {
  e.printStackTrace();
  }
  }
}
public class Biddingserver {
public static void main(String[] args)throws Exception {
         try{
      ServerSocket server=new ServerSocket(4040);
      int counter=0;
      System.out.println("Server Started ....");
      while(true){
        counter++;
        Socket serverClient=server.accept(); 
        System.out.println(" >> " + "Client No:" + counter + " started!");
        ServerClientThread sct = new ServerClientThread(serverClient,counter); 
        sct.start();
      }
    }catch(Exception e){
      System.out.println(e);
    }
    }
}
