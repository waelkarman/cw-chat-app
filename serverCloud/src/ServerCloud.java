package servercloud;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
  _____.__              .__   
_/ ____\__| ____ _____  |  |  
\   __\|  |/    \\__  \ |  |  
 |  |  |  |   |  \/ __ \|  |__
 |__|  |__|___|  (____  /____/
               \/     \/      
*/ 


public class ServerCloud {

    ArrayList clientOutputStreams;
    ArrayList<String> users;
    boolean state = false;
    ServerSocket serverSock;
    
    public ServerCloud() 
    {
        clientOutputStreams = new ArrayList();
        users = new ArrayList();
        state = true;
        try {
            serverSock = new ServerSocket(33333);
            Thread starter;
            starter = new Thread(new ServerStart(serverSock));
            starter.start();
        } catch (IOException ex) {
            Logger.getLogger(ServerCloud.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new ServerCloud();
            }
        });
    }
    
    
    
    
    
    
    
    
    
    
//---------------------------------------------------->> MESSAGE FORWARDING
    public void tellEveryone(String message) 
    {
	Iterator it = clientOutputStreams.iterator();

        while (it.hasNext()) 
        {
            try 
            {
                PrintWriter writer = (PrintWriter) it.next();
		writer.println(message);
                writer.flush();
            } 
            catch (Exception ex) 
            {
		
            }
        } 
    }
    
    
public void tellToUser(String message, String usr) 
    {
        for(int i=0; i<=users.size()-1; i++){
            
            if(users.get(i).equals(usr)){
                PrintWriter writer1 = (PrintWriter) clientOutputStreams.get(i);
                writer1.println(message);
                writer1.flush();
            }
 
        }
    }

    
    
    
//------------------------------------------------------->> USER MANAGER
    public void userAdd (String data) 
    {
        if(!users.contains(data)){
            users.add(data);
        }
    }
    
    public void userRemove (String data) 
    {
        int i = users.indexOf(data);
        users.remove(data);
        clientOutputStreams.remove(i);
    }
    public void updateUser(String usr){
        for (int i=0; i<users.size(); i++) {  
            tellToUser(users.get(i)+":"+"To Update"+":"+"Update",usr);
        }
    }



    
    
//---------------------------------------------->> SERVER INPUT_OUTPUT MANAGER 
    
    
    public class ServerStart implements Runnable 
    {
        
        ServerSocket serverSock;

        public ServerStart(ServerSocket serverSock) throws IOException {
            this.serverSock = serverSock;
        }
        
        @Override
        public void run() 
        {
            try 
            {
                while (state) 
                {
                    Socket clientSock = serverSock.accept();
                    PrintWriter writer = new PrintWriter(clientSock.getOutputStream());
                    clientOutputStreams.add(writer);
                    Thread listener = new Thread(new ClientHandler(clientSock, writer));
                    listener.start();
                }
            }
            catch (Exception ex)
            {
                
            }
            
            
        }
    }
    
    public class ClientHandler implements Runnable	
   {
       BufferedReader reader;
       Socket sock;
       PrintWriter client;
 
       public ClientHandler(Socket clientSocket, PrintWriter user) 
       {
            client = user; 
            try 
            {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);
            }
            catch (Exception ex) 
            {
            }
       }

       
       
       @Override
       public void run() 
       {
            String message, broadcast = "Broadcast", connect = "Connect", disconnect = "Disconnect", chat = "Chat", usr ;
            String[] data;

            try 
            {
                while ((message = reader.readLine()) != null && state) 
                {
                    data = message.split(":");

                    if (data[2].equals(connect)) 
                    {
                        userAdd(data[0]);
                        updateUser(data[0]);
                        tellEveryone(data[0] + ":" + data[1] + ":" + connect);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                        tellEveryone((data[0] + ":" + data[1] + ":" + disconnect));
                        userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat) && data[3].equals(broadcast)) 
                    {
                        tellEveryone(data[0] + ":" + data[1] + ":" + data[2]);
                    } 
                    else if(data[2].equals(chat) && !data[3].equals(broadcast))
                    {
                        tellToUser(message,data[3]);
                    }
                    else 
                    {
                    }
                } 
             } 
             catch (Exception ex) 
             {
                ex.printStackTrace();
                clientOutputStreams.remove(client);
             } 
	} 
    }

}
