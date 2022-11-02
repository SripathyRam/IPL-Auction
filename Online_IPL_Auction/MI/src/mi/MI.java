package mi;
import java.net.*;
import java.io.*;
import java.util.Scanner;
public class MI {
    public static void main(String[] args)throws Exception {
         try{
                     Scanner sc = new Scanner(System.in);
    Socket socket=new Socket("127.0.0.1",4040);
    DataInputStream dis=new DataInputStream(socket.getInputStream());
    DataOutputStream dos=new DataOutputStream(socket.getOutputStream());
    BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    String clientMessage="",serverMessage="";
    while (true)
        {
            String t = "MI";
            System.out.println("Team Name: " +t);
            System.out.println("Who's the next Player to be bid : ");
            dos.writeUTF(t);
            String p = dis.readUTF();
            System.out.println("The next player is : "+p);
            int b = 1;
            System.out.println("Enter 1 for Yes or 0 for No for Bidding");           
            while( b != 0){
                int inp = sc.nextInt();
                dos.writeInt(inp);                          
                if(inp == 0){
                    System.out.println("Team "+t+" stopped bidding .........");
                     break;
                }                   
            }           
            String ans = dis.readUTF();
            int price = dis.readInt();
            if(price == 0)
            {
                System.out.println("Team "+ans+" Quitted bidding for "+p+" !!!!!!!");
                break;
            }
            else{
            System.out.println("Team "+ans+" Bought"+ p+"for price "+price+" Lakhs!!!!!!!");
            break;}
        
        }
    dos.close();
    dos.close();
    socket.close();
  }catch(Exception e){
    System.out.println(e);
  }
    }
    
}
