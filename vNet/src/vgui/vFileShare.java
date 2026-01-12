/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vgui;

import dao.SMBBucket;
import dao.smbdao;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Files;
import java.nio.file.Path;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import model.TreeModel;

/**
 *
 * @author nurali
 */
public class vFileShare extends javax.swing.JInternalFrame {
    // -- DATA Members
    private SMBBucket bucket;
    
    // handle to our TreeModel object
    private TreeModel tm;

    // ----------------------------------------------------
    
    
    // ----------------------------------------------------
    // --- Inner Thread
    private class SMB extends Thread{
        private String smbpath;
        private String ip;
        private smbdao sdao;
        private boolean ok;
        
        public SMB(){
            
        }
        
        public SMB(String path){
            this.smbpath = path;
            ip = ipTF.getText();
            ok = false;
            start();
        }
        
        public SMB(String ip, String path){
            this.ip = ip;
            this.smbpath = path;
            ok = false;     
        }
        
        public SMB(smbdao sd){
            this.sdao = sd;
            ip = sdao.getIp();
            smbpath = sdao.getFullpath();
            ok = false;
            start();
        }
        
        // thread run
        public void run(){
            
             
            
            try {
                
                InetAddress addr = null;
                try {
                    addr = InetAddress.getByName(ip);
                    InetAddress[] ads = InetAddress.getAllByName(ip);
                    for(int i =0; i < ads.length; i++){
                        jTextArea1.append("address " + ads[i] + " :: ");
                    }
                    jTextArea1.append("\n");
                } catch (UnknownHostException ex) {
                    System.getLogger(vFileShare.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                    jProgressBar1.setIndeterminate(false);
                jProgressBar1.setString("Error");
                jTextArea1.setText(ex.getMessage());
                }
                
                if(addr.isReachable(100)){
                    jTextArea1.append("\nReachable " + addr.toString() + "\n");
                    
                }
                else
                    jTextArea1.append("\nCan't connect to server\n");
                
                
                pause();
                
                try{
                    Path p = Path.of(smbpath);
                    if(Files.exists(p)){
                        System.out.println("Exists " );
                        System.out.println("is directory " +  Files.isDirectory(p));
                        jTextArea1.append("\nExists");
                        jTextArea1.append("\nis directory " + Files.isDirectory(p));
                        ok = true;
                    }
                    else{
                        System.out.println("doesn't exist");
                        jTextArea1.append("\nFile path Doesn't exist, can't connect");
                    }
                }catch(Exception ex){
                    System.err.println(ex);
                    jProgressBar1.setIndeterminate(false);
                    jProgressBar1.setString("Error");
                    jTextArea1.setText(ex.getMessage());
                    ok = false;
                }
                
                File f = new File(smbpath);
                if(f.exists()){
                    ok = true;
                    System.out.println("File exists " + f.getAbsolutePath());
                    jTextArea1.append("\nFile path exists " + f.getAbsolutePath() + "\n");
                    String[] listing =  f.list();
                    if(listing != null){
                        tm.setupTree(f);
                        
                        for(int i =0; i < listing.length; i++){
                            System.out.println(listing[i]);
                            jTextArea1.append(listing[i]);
                            jTextArea1.append("\n");
                        }
                    }else
                        jTextArea1.append("\nListing is null, can't get listing.\n");
                }else{
                    System.out.println("Doesn't exist");
                    jTextArea1.append("\nFile path Doesn't Exist, can't connect");
                }
                
            } catch (IOException ex) {
                System.getLogger(vFileShare.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                jProgressBar1.setIndeterminate(false);
                jProgressBar1.setString("Error");
                jTextArea1.setText(ex.getMessage());
                ok = false;
            }
            
            
            
            jTextArea1.append("\n\n\nThread Done");
           
            if(ok){
            //jProgressBar1.setVisible(false);
                jProgressBar1.setIndeterminate(false);
                jProgressBar1.setString("Done");
            }else{
                jProgressBar1.setIndeterminate(false);
                jProgressBar1.setString("Error");
            }
        }// end run
        
        // sleep ourselves
        private void pause(){
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                System.getLogger(vFileShare.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
        
    }
    // --------------------------------------------------
    
    
    /**
     * Creates new form vFileShare
     */
    public vFileShare() {
        // init the List
        bucket = new SMBBucket();
        // init the TreeModel
        tm = new TreeModel();
        
        UIManager.put("ProgressBar.background", Color.BLACK);
        UIManager.put("ProgressBar.foreground", Color.BLUE);
        UIManager.put("ProgressBar.selectionBackground", Color.RED);
        UIManager.put("ProgressBar.selectionForeground", Color.GREEN);
        UIManager.put( "nimbusBlue", Color.BLUE );
        initComponents();
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        ipTF = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        filepathTF = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        progressPanel = new javax.swing.JPanel();
        jProgressBar1 = new javax.swing.JProgressBar();
        jProgressBar1.setVisible(false);
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = new javax.swing.JTree(tm.getTreeModel());
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("File Share SMB");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Server Details"));

        jLabel1.setText("Server DNS / Ip address");

        ipTF.setText("194.168.2.4");

        jLabel2.setText("file path");

        filepathTF.setText("nurali");
        filepathTF.setToolTipText("folder path like docs or files");

        jButton1.setText("Connect");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connect(evt);
            }
        });

        jButton2.setText("Go");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gosmb(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(ipTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, 0, 666, Short.MAX_VALUE))
                            .addComponent(filepathTF))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(ipTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(filepathTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jButton1))
        );

        progressPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        progressPanel.setLayout(new java.awt.BorderLayout());

        jProgressBar1.setEnabled(false);
        jProgressBar1.setIndeterminate(true);
        jProgressBar1.setString("connecting...");
        jProgressBar1.setStringPainted(true);
        progressPanel.add(jProgressBar1, java.awt.BorderLayout.CENTER);

        jSplitPane1.setOneTouchExpandable(true);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jTextArea1.setBackground(new java.awt.Color(0, 51, 153));
        jTextArea1.setColumns(20);
        jTextArea1.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea1.setRows(5);
        jTextArea1.setText("test ip");
        jTextArea1.setMargin(new java.awt.Insets(2, 12, 12, 6));
        jScrollPane1.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jSplitPane1.setRightComponent(jPanel3);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.BorderLayout());

        jTree1.addTreeSelectionListener(new javax.swing.event.TreeSelectionListener() {
            public void valueChanged(javax.swing.event.TreeSelectionEvent evt) {
                listenTree(evt);
            }
        });
        jScrollPane2.setViewportView(jTree1);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jSplitPane1.setLeftComponent(jPanel2);

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSplitPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(progressPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connect(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connect
        try {  
            jProgressBar1.setEnabled(true);
            jProgressBar1.setVisible(true);
            jProgressBar1.setIndeterminate(true);
            jProgressBar1.setString("Connecting");
            // TODO add your handling code here:
            // need to check for nulls
            String ip = ipTF.getText();
            String path = filepathTF.getText();
            smbdao smbd = new smbdao(ip, path);
            bucket.addSMB(smbd);
            jTextArea1.setText( ip + " " + path);
            tm.init(ip);
            
            // private String nas = "\\\\TRUENAS\\nas-data";
            String esc = "\\\\";
            String doub = "\\";
            String full = null;
            if(path != "" || !path.isEmpty())
                 full = esc+ip+doub+path;
            else
                full = ip;
            
            jTextArea1.append("\n Full path: " + full);
            jTextArea1.append("\n");
            smbd.setFullpath(full);
            // remove the item if it is in the combo box
            jComboBox1.removeItem(smbd.getFullpath());
            jComboBox1.addItem(smbd.getFullpath()); // now add it
            //jComboBox1.setSelectedItem(full);
            // offload to thread
            SMB smb = new SMB(full);
            
           
        } catch (Exception ex) {
            System.getLogger(vFileShare.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            jProgressBar1.setIndeterminate(false);
            jProgressBar1.setString("Error");
            jTextArea1.setText(ex.getMessage());
        }
    }//GEN-LAST:event_connect

    
    private void gosmb(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gosmb
        // TODO add your handling code here:
        jProgressBar1.setEnabled(true);
        jProgressBar1.setVisible(true);
        jProgressBar1.setIndeterminate(true);
        jProgressBar1.setString("Connecting");
            
       String item = (String) jComboBox1.getSelectedItem();
       jTextArea1.setText(item);
       
       //smbdao sm = bucket.getSMB(item);
       smbdao sm = bucket.findbyFullPath(item);
       ipTF.setText(sm.getIp());
       filepathTF.setText(sm.getFilepath());
       jTextArea1.append("\n" + sm.toString());
       jTextArea1.append("\nAttempting connection...\n");
       // offload to thread
       SMB s = new SMB(sm);
    }//GEN-LAST:event_gosmb

    // Event listener for the JTree
    private void listenTree(javax.swing.event.TreeSelectionEvent evt) {//GEN-FIRST:event_listenTree
        // TODO add your handling code here:
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           jTree1.getLastSelectedPathComponent();
        
        if(node == null) return;
        
        TreeNode atreenode = node.getParent();
        if(atreenode != null){
            jTextArea1.append("\nParent :: " + atreenode.getParent() + "\n\n");
            jTextArea1.append("listing nodes:\n");
            TreeNode[] nodes =  node.getPath();
            for(int i = 0; i<nodes.length; i++){
                jTextArea1.append( nodes[i].toString());
                 jTextArea1.append("\\");
                /* 
                 \\serverip\folder1\f2
                */
            }
        }
        Object nodeinfo = node.getUserObject();
        if(node.isLeaf()){
            String nodename = (String)nodeinfo;
            jTextArea1.append("\nNode info :: " + nodename);
        }else{
             String nodename = (String)nodeinfo;
            jTextArea1.append("\nNode info :: " + nodename);
        }
    }//GEN-LAST:event_listenTree


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField filepathTF;
    private javax.swing.JTextField ipTF;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTree jTree1;
    private javax.swing.JPanel progressPanel;
    // End of variables declaration//GEN-END:variables
}
