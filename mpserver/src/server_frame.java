package chat_server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
  _____.__              .__   
_/ ____\__| ____ _____  |  |  
\   __\|  |/    \\__  \ |  |  
 |  |  |  |   |  \/ __ \|  |__
 |__|  |__|___|  (____  /____/
               \/     \/      
*/


public class server_frame extends javax.swing.JFrame 
{
    ArrayList clientOutputStreams;
    ArrayList<String> users;
    boolean state = false;
    ServerSocket serverSock;
    
    public server_frame() 
    {
        initComponents();
        clientOutputStreams = new ArrayList();
        users = new ArrayList();
    }
  
    
//------------------------------------------------------->> LAUNCH GUI 
    
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() {
                new server_frame().setVisible(true);
            }
        });
    }
    
    
   
    
    
//------------------------------------------>> GUI AND ACTION_PERFORMED
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        b_start = new javax.swing.JButton();
        b_end = new javax.swing.JButton();
        b_users = new javax.swing.JButton();
        b_clear = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Server Monitor");
        setName("server"); // NOI18N
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        b_start.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        b_start.setText("START");
        b_start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_startActionPerformed(evt);
            }
        });

        b_end.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        b_end.setText("END");
        b_end.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_endActionPerformed(evt);
            }
        });

        b_users.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        b_users.setText("Online Users");
        b_users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_usersActionPerformed(evt);
            }
        });

        b_clear.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        b_clear.setText("Clear");
        b_clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_clearActionPerformed(evt);
            }
        });

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        jScrollPane1.setViewportView(ta_chat);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(b_users)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_clear, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(b_start, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(300, 300, 300)
                        .addComponent(b_end, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_start)
                    .addComponent(b_end))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(b_users)
                    .addComponent(b_clear))
                .addGap(16, 16, 16))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void b_endActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_endActionPerformed
        if(state==true){    
            int ret = JOptionPane.showConfirmDialog (server_frame.this, "Close the Server?",null, JOptionPane.YES_NO_OPTION);
                if (ret == JOptionPane.YES_OPTION){
                    state = false;
                    tellEveryone("Server:is stopping and all users will be disconnected.\n:Chat");
                    ta_chat.append("SERVER STOPPING... \n");
                    try {
                        serverSock.close();
                    } catch (IOException ex) {
                        Logger.getLogger(server_frame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    clientOutputStreams.clear();
                    users.clear();

                    ta_chat.setText("");
                    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                }
        }
        else
            JOptionPane.showMessageDialog(server_frame.this,"SERVER IS NOT ACTIVE","Warning",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_b_endActionPerformed

    private void b_startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_startActionPerformed
        if(state==false){    
            state = true;
            try {
                serverSock = new ServerSocket(33333);
                Thread starter;
                starter = new Thread(new ServerStart(serverSock));
                starter.start();
            } catch (IOException ex) {
                Logger.getLogger(server_frame.class.getName()).log(Level.SEVERE, null, ex);
            }
            setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
            ta_chat.append("SERVER STARTED...\n");
        }
        else
            JOptionPane.showMessageDialog(server_frame.this,"SERVER IS ALREADY ACTIVE","Warning",JOptionPane.WARNING_MESSAGE);
            
    }//GEN-LAST:event_b_startActionPerformed

    private void b_usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_usersActionPerformed
        ta_chat.append("\n ONLINE USERS :\n");
        for (String current_user : users)
        {
            ta_chat.append(current_user);
            ta_chat.append("\n");
        }    
        
    }//GEN-LAST:event_b_usersActionPerformed

    private void b_clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_clearActionPerformed
        ta_chat.setText("");
    }//GEN-LAST:event_b_clearActionPerformed

        
    
    
    
    
    
    
    
    
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
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
            } 
            catch (Exception ex) 
            {
		ta_chat.append("Error telling everyone. \n");
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
                ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
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
                ta_chat.append("Unexpected error... \n");
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
                        ta_chat.append("User: "+data[0]+" is connected.\n");
                        userAdd(data[0]);
                        updateUser(data[0]);
                        tellEveryone(data[0] + ":" + data[1] + ":" + connect);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                        ta_chat.append("User: "+data[0]+" is diconnected.\n");
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
                        ta_chat.append("No Conditions were met. \n");
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

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_clear;
    private javax.swing.JButton b_end;
    private javax.swing.JButton b_start;
    private javax.swing.JButton b_users;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea ta_chat;
    // End of variables declaration//GEN-END:variables
}
