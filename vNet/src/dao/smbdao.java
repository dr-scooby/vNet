/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 * Data Access object for the SMB
 * @author nurali
 */
public class smbdao {
    
    private String ip;
    private String filepath;
    private String fullpath;
    
    public smbdao(){
        ip = "";
        filepath = "";
        fullpath = "";
    }

    public smbdao(String ip, String filepath) {
        this();
        this.ip = ip;
        this.filepath = filepath;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }

    
    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    @Override
    public String toString() {
        return "smbdao{" + "ip=" + ip + ", filepath=" + filepath + '}';
    }
    
    
    
}
