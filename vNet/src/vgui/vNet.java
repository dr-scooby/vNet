/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package vgui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import static java.lang.Thread.sleep;
import java.net.*;
import java.util.Properties;
import javax.swing.ComboBoxModel;
import javax.swing.JScrollBar;

/**
 * JInternalFrame - a network tool, ping, dns lookup.
 * @author nurali
 */
public class vNet extends javax.swing.JInternalFrame {
    // used for the Serializable
    private static final long serialVersionUID = 144L; // Explicitly declared
    private int spincount; // grab it from the jSpinner1
    private String spins = "5"; // init to 5
    //private String osName;
    
    //private DNSList dnslisting;
    
    // WebServer
    //private WebServer webserver;
    
    private String myhostname;
    private String myip;
    
    private String osName = System.getProperty("os.name");
    
    // End Data Members
    // -------------------------------------------------------------------------
    
    // --- Inner Threads
    // Inner threads have to class variables
    
    
    private class GoTask extends Thread{
        private String inet;
        
        public GoTask(){
            inet = (String) jComboBox1.getSelectedItem();
            start(); // start ourselves
        }
        
        // run thread
        public void run(){
            try{
                InetAddress[] a = InetAddress.getAllByName(inet.strip());
                display.append("\n---------\nchecking ...\n");
                int count = 1;
                for(int i=0; i < a.length; i++){
                
                display.append(count + ". " +  a[i].toString());
                if(a[i].isReachable(200)){
                display.append("   ---> is reachable");
                //timetogo = false;
                //break;
                }else{
                    display.append("   ---> NOT reachable");
                    //timetogo = false;
                
                }
                display.append("\n");
                display.append("\nAttempting Socket connection...\n");
                try{
                    // connect to the server on port 80, check if web server is 
                    // running server on port 80. No transfer of data
                    // this just proves the web server is running
                    Socket sock = new Socket(a[i], 80);
                    
                    InetAddress addr = sock.getInetAddress();
                    display.append("Connected to server " + addr);
                    display.append("\n\n");
                }catch(IOException io){
                    display.append("Can't connect to server\n");
                    
                }
                
                count++;
            }// end for loop
            }catch(Exception e){
                display.append("error " + e);
            }
            
            display.append("\n\n...Done...");
        }
        
        
    }
    
     // --- PingMe Thread
    private class PingMe extends Thread{
        //private String osName = System.getProperty("os.name");
        private int spincount;
        private String ip;
        
        public PingMe(){
            ip = combo().strip();
            // need to convert the Object from getValue, but check if it's instance of Integer
            Object obval = jSpinner1.getValue();
            if(obval instanceof Integer){
                 Integer ival =  (Integer)obval ;
                 spincount = ival.intValue();
                 spins = ival.toString();
            } 
            start();
        }
        
        
        
        public void run(){
            // the ping count:
            // in Windows: ping -n 5 google.com
            // in linux : ping -c 5 google.com
            
            try{
                if(isWindows()){
                     //String[] cmd = {"cmd.exe", "/c", "ping -n "+spins +" " + jTextField1.getText().strip()};
                     String[] cmd = {"cmd.exe", "/c", "ping -n "+ spincount +" " + ip.strip()};
                     Process p = Runtime.getRuntime().exec(cmd);
                    // this will open the command prompt 
                    //Runtime.getRuntime().exec(new String[]{"cmd", "/K", "Start"});
                    // this will open the file explorer !!!!
                    //Runtime.getRuntime().exec(new String[]{"explorer", "/K", "Start"});
                    BufferedReader read = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String tmp = "";
                    // do something
                    while( (tmp = read.readLine()) != null){
                        System.out.println(tmp);
                          display.append(tmp);
                          display.append("\n");
                          autoScroll();
                          sleep(200); // pause
                    }
                }else if(isLinux()){
                    System.out.println("isLinux OS...");
                    display.append("\nusing Linux OS...\n");
                    ProcessBuilder proc;
                    if(spincount > 0){
                        
                        proc = new ProcessBuilder("ping", "-c", spins, ip.strip());
                    }else
                        proc = new ProcessBuilder("ping", "-c", "5", ip.strip());
                    
                    Process p = proc.start();
                    BufferedReader read = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String tmp = "";
                    // do something
                    while( (tmp = read.readLine()) != null){
                        System.out.println(tmp);
                          display.append(tmp);
                          display.append("\n");
                          autoScroll();
                          sleep(200); // pause
                    }
                }
                
                
            }catch(Exception e){
                System.err.println(e);
            }
        }
    }
    
    // nslookup
    private class NSLookup extends Thread{
        private String dnsname;
        
        
        public NSLookup(){
            //start();
        }
        
        public NSLookup(String dns){
            this.dnsname = dns;
            start();
        }
        
        public void run(){
            if(isWindows()){
                try{
                    String[] cmd = {"cmd.exe", "/c", "nslookup -type=any " + dnsname.strip()};
                    Process proc = Runtime.getRuntime().exec(cmd);
                    BufferedReader read = new BufferedReader(new InputStreamReader(proc.getInputStream()));
                    String tmp = "";
                    // do something
                    while( (tmp = read.readLine()) != null){
                        System.out.println(tmp);
                          display.append(tmp);
                          display.append("\n");
                           autoScroll();
                          sleep(100); // pause
                    }
                }catch(Exception ex){
                    System.err.println(ex);
                    display.setText(ex.getMessage());
                }
            }
        }
    }
    
    // -------------------------------------------------------------------------
    
    /**
     * Creates new form vNet
     */
    public vNet() {
        initComponents();
        
        SysProps();
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
        jPanel2 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        goButton = new javax.swing.JButton();
        pingButton = new javax.swing.JButton();
        nslookupButton = new javax.swing.JButton();
        clearButton = new javax.swing.JButton();
        javax.swing.SpinnerModel model = 
        new javax.swing.SpinnerNumberModel(5, 5, 100, 1); // initial, min, max, step
        jSpinner1 =  new javax.swing.JSpinner(model);
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        display = new javax.swing.JTextArea();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("JNet");

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jComboBox1.setEditable(true);
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "google.com" }));

        goButton.setText("Go");
        goButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                go(evt);
            }
        });

        pingButton.setText("Ping");
        pingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ping(evt);
            }
        });

        nslookupButton.setText("nslookup");
        nslookupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nslook(evt);
            }
        });

        clearButton.setText("Clear");
        clearButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clear(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(goButton)
                .addGap(18, 18, 18)
                .addComponent(pingButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(nslookupButton)
                .addGap(18, 18, 18)
                .addComponent(clearButton)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(goButton)
                    .addComponent(pingButton)
                    .addComponent(nslookupButton)
                    .addComponent(clearButton)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel3.setLayout(new java.awt.BorderLayout());

        display.setBackground(new java.awt.Color(0, 0, 0));
        display.setColumns(20);
        display.setForeground(new java.awt.Color(0, 255, 0));
        display.setRows(5);
        display.setText("ping google.com");
        jScrollPane1.setViewportView(display);

        jPanel3.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE)
                .addContainerGap())
        );

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("Run");

        jMenuItem1.setText("cmd");
        jMenu3.add(jMenuItem1);

        jMenuItem2.setText("File Explorer");
        jMenu3.add(jMenuItem2);

        jMenuItem3.setText("RDP");
        jMenu3.add(jMenuItem3);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void go(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_go
        // TODO add your handling code here:
        clearDis();
        GoTask gt = new GoTask();
        
    }//GEN-LAST:event_go

    private void ping(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ping
        // TODO add your handling code here:
        clearDis();
        PingMe pm = new PingMe();
    }//GEN-LAST:event_ping

    private void clear(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clear
        // TODO add your handling code here:
        clearDis();
        
    }//GEN-LAST:event_clear

    private void nslook(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nslook
        // TODO add your handling code here:
        String dns = combo();
        display.setText("DNS lookup for : " + dns + "\n");
        NSLookup ns = new NSLookup(dns.strip());
    }//GEN-LAST:event_nslook

    private void clearDis(){
        display.setText("");
    }
    
    // update the scroll bar
    private void autoScroll(){
        JScrollBar verticalbar = jScrollPane1.getVerticalScrollBar();
        verticalbar.setValue(verticalbar.getMaximum()); // this is how to autoscroll !!!!!!!!!
    }
    
    
    // check OS type
    private boolean isWindows(){
        return osName.toLowerCase().contains("windows");
    }
        
    private boolean isLinux(){
        return osName.toLowerCase().contains("linux") || osName.toLowerCase().contains("unix");
    }
    
    // system properties
    private void SysProps(){
        clearDis();
        Properties p = System.getProperties();
        p.list(System.out);
        display.setText("OS: " + p.getProperty("os.name"));
        display.append("\nOS Version: " + p.getProperty("os.version"));
        display.append("\nUser home: " + p.getProperty("user.home"));
        display.append("\nJava version: " + p.getProperty("java.version"));
    }
    
    private String combo(){
        String item = (String) jComboBox1.getSelectedItem();
        jComboBox1.removeItem(item); // remove if in list
        jComboBox1.insertItemAt(item, 0); // insert at beginning
        jComboBox1.setSelectedItem(item);
        
        //ComboBoxModel<String> cb = jComboBox1.getModel();
        
        return item;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton clearButton;
    private javax.swing.JTextArea display;
    private javax.swing.JButton goButton;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JButton nslookupButton;
    private javax.swing.JButton pingButton;
    // End of variables declaration//GEN-END:variables
}
