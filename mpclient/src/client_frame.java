package chat_client;

import java.net.*;
import java.io.*;
import java.util.*;
import javax.swing.*;

/*
  _____.__              .__   
_/ ____\__| ____ _____  |  |  
\   __\|  |/    \\__  \ |  |  
 |  |  |  |   |  \/ __ \|  |__
 |__|  |__|___|  (____  /____/
               \/     \/      
*/



public class client_frame extends javax.swing.JFrame 
{
    String username, address = "localhost";
    ArrayList<String> users = new ArrayList();
    int port = 33333;
    Boolean isConnected = false;
    Socket sock;
    BufferedReader reader;
    PrintWriter writer;
    String[] tempList;
    
    public client_frame() 
    {
        initComponents();
    }
    
    
//------------------------------------------------------->> LAUNCH GUI 
    
    public static void main(String args[]) 
    {
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new client_frame().setVisible(true);
            }
        });
    }
    
    
    
    
    
//------------------------------------------------>> GUI AND ACTION_PERFORMED

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        b_disconnect = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta_chat = new javax.swing.JTextArea();
        tf_chat = new javax.swing.JTextField();
        b_send = new javax.swing.JButton();
        lb_username = new javax.swing.JLabel();
        tf_username = new javax.swing.JTextField();
        b_connect = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat - Client's frame");
        setBackground(new java.awt.Color(255, 102, 51));
        setName("client"); // NOI18N
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 102, 51));

        b_disconnect.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        b_disconnect.setText("LOGOUT");
        b_disconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_disconnectActionPerformed(evt);
            }
        });

        ta_chat.setColumns(20);
        ta_chat.setRows(5);
        ta_chat.setBorder(null);
        ta_chat.setFocusable(false);
        jScrollPane1.setViewportView(ta_chat);

        b_send.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        b_send.setText("SEND ALL");
        b_send.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_sendActionPerformed(evt);
            }
        });

        lb_username.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        lb_username.setText("Username :");

        tf_username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usernameActionPerformed(evt);
            }
        });

        b_connect.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        b_connect.setText("LOGIN");
        b_connect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b_connectActionPerformed(evt);
            }
        });

        jButton2.setText("SEND TO");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 3, 13)); // NOI18N
        jLabel1.setText("Write a message");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lb_username, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(b_connect)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(b_disconnect))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(14, 14, 14)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 549, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(tf_chat, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jButton2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(b_send, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(21, 21, 21)))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tf_username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(b_connect)
                    .addComponent(b_disconnect)
                    .addComponent(lb_username))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(2, 2, 2)
                        .addComponent(b_send, javax.swing.GroupLayout.PREFERRED_SIZE, 22, Short.MAX_VALUE))
                    .addComponent(tf_chat))
                .addContainerGap())
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

    private void b_connectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_connectActionPerformed
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        if(tf_username.getText().trim().length()!=0){
                if (isConnected == false)
                {
                    username = tf_username.getText();
                    tf_username.setEditable(false);

                    try
                    {
                        sock = new Socket(address, port);
                        InputStreamReader streamreader = new InputStreamReader(sock.getInputStream());
                        reader = new BufferedReader(streamreader);
                        writer = new PrintWriter(sock.getOutputStream());
                        writer.println(username + ":-:Connect:Broadcast");
                        writer.flush();
                        isConnected = true;
                    }
                    catch (Exception ex)
                    {
                        JOptionPane.showMessageDialog(client_frame.this,"CANNOT CONNECT! TRY AGAIN","Warning",JOptionPane.WARNING_MESSAGE);
                        tf_username.setEditable(true);
                    }

                    Thread IncomingReader = new Thread(new IncomingReader());
                    IncomingReader.start();

                } else if (isConnected == true)
                {
                    ta_chat.append("ALREADY CONNECTED! \n");
                }
            }
        else
            JOptionPane.showMessageDialog(client_frame.this,"INSERT A VALID USERNAME","Warning",JOptionPane.WARNING_MESSAGE);
    }//GEN-LAST:event_b_connectActionPerformed

    private void tf_usernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usernameActionPerformed

    }//GEN-LAST:event_tf_usernameActionPerformed

    private void b_disconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_disconnectActionPerformed
        sendDisconnect();
        Disconnect();
        int ret = JOptionPane.showConfirmDialog (client_frame.this, "Close the window?",null, JOptionPane.YES_NO_OPTION);
        if (ret == JOptionPane.YES_OPTION)
            this.dispose();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    }//GEN-LAST:event_b_disconnectActionPerformed

    private void b_sendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b_sendActionPerformed
        String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
            try {
                writer.println(username + ":" + tf_chat.getText() + ":" + "Chat" + ":" + "Broadcast");
                writer.flush(); 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(client_frame.this,"Message was not sent","Warning",JOptionPane.WARNING_MESSAGE);
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
    }//GEN-LAST:event_b_sendActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        String nothing = "";
        if ((tf_chat.getText()).equals(nothing)) {
            tf_chat.setText("");
            tf_chat.requestFocus();
        } else {
            try {
                ta_chat.append("\nPrivate message to " +jComboBox2.getSelectedItem() + ": " +tf_chat.getText()+ "\n" );
                writer.println(username + ":" + tf_chat.getText() + ":" + "Chat" + ":" + jComboBox2.getSelectedItem());
                writer.flush(); 
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(client_frame.this,"Message was not sent","Warning",JOptionPane.WARNING_MESSAGE);
            }
            tf_chat.setText("");
            tf_chat.requestFocus();
        }

        tf_chat.setText("");
        tf_chat.requestFocus();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        
    }//GEN-LAST:event_jComboBox2ActionPerformed
    
     
//--------------------------------------------------->> CLIENT ACTIONS
    
    public void userAdd(String data) 
    {
        if(!users.contains(data)){
            ta_chat.append(data + " IS NOW ONLINE.\n");
            users.add(data);
            jComboBox2.removeAllItems();
            for (int i=0; i<users.size(); i++) {  
                jComboBox2.addItem(users.get(i));
            }
        }
        

    }
    
    public void userRemove(String data) 
    {
        
        users.remove(data);
        
        jComboBox2.removeAllItems();
        for (int i=0; i<users.size(); i++) {  
            jComboBox2.addItem(users.get(i));
        }
        ta_chat.append(data + " IS NOW OFFLINE.\n");
    }
    
    public void sendDisconnect() 
    {
        String bye = (username + "::Disconnect:Broadcast");
        try
        {
            writer.println(bye); 
            writer.flush(); 
        } catch (Exception e) 
        {
            JOptionPane.showMessageDialog(client_frame.this,"Could not send Disconnect message","Warning",JOptionPane.WARNING_MESSAGE);
        }
        users.clear();
        jComboBox2.removeAllItems();
    }

    public void Disconnect() 
    {
        try 
        {
            ta_chat.append("DISCONNECTED!\n");
            sock.close();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(client_frame.this,"FAILED TO DISCONNECT","Warning",JOptionPane.WARNING_MESSAGE);

        }
        isConnected = false;
        tf_username.setEditable(true);

    }    
    
    

    
    
    
//---------------------------------------------->> CLIENT INPUT MANAGER     
    
    public class IncomingReader implements Runnable
    {
        @Override
        public void run() 
        {
            String[] data;
            String stream, usr, connect = "Connect", disconnect = "Disconnect", chat = "Chat", broadcast = "Broadcast",update = "Update";

            try 
            {
                while ((stream = reader.readLine()) != null) 
                {
                    data = stream.split(":");
                    
                    if (data[2].equals(connect))
                    {
                       userAdd(data[0]);
                    } 
                    else if (data[2].equals(update)) 
                    {
                       userAdd(data[0]);
                    } 
                    else if (data[2].equals(disconnect)) 
                    {
                       userRemove(data[0]);
                    } 
                    else if (data[2].equals(chat)) 
                    {
                       ta_chat.append("\nMessage from "+data[0] + ": " + data[1] + "\n");
                       ta_chat.setCaretPosition(ta_chat.getDocument().getLength());
                    } 
                }
           }catch(Exception ex) { }
            
            if(!sock.isClosed()){
                sendDisconnect();
                Disconnect();
            }
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b_connect;
    private javax.swing.JButton b_disconnect;
    private javax.swing.JButton b_send;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lb_username;
    private javax.swing.JTextArea ta_chat;
    private javax.swing.JTextField tf_chat;
    private javax.swing.JTextField tf_username;
    // End of variables declaration//GEN-END:variables
}
