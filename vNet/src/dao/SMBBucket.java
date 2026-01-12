/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.*;

/**
 *
 * @author nurali
 */
public class SMBBucket {
    
    private ArrayList bucket;
    
    
    public SMBBucket(){
        bucket = new ArrayList();
    }
    
    public void addSMB(smbdao dao){
        bucket.add(dao);
    }
    
    public Iterator getIt(){
        return bucket.iterator();
    }
    
    // find by IP
    public smbdao findbyIP(String key){
        smbdao sd = new smbdao();
        smbdao sad ;
        Iterator it = getIt();
        while(it.hasNext()){
            sad =  (smbdao) it.next();
            if(sad.getIp().equals(key)){
                sd.setIp(sad.getIp());
                sd.setFilepath(sad.getFilepath());
                sd.setFullpath(sad.getFullpath());
            }
                
       }
       
       return sd;
    }
    
    // find by full path, eg: 194.6.2.10\docs
    public smbdao findbyFullPath(String key){
        smbdao sd = new smbdao();
       smbdao sad ;
       Iterator it = getIt();
       while(it.hasNext()){
            sad =  (smbdao) it.next();
            if(sad.getFullpath().equals(key)){
                sd.setIp(sad.getIp());
                sd.setFilepath(sad.getFilepath());
                sd.setFullpath(sad.getFullpath());
            }
                
       }
       
       return sd;
    }
    
   public smbdao getSMB(String key){
       smbdao sd = new smbdao();
       smbdao sad ;
       Iterator it = getIt();
       while(it.hasNext()){
            sad =  (smbdao) it.next();
            if(sad.getFullpath().equals(key)){
                sd.setIp(sad.getIp());
                sd.setFilepath(sad.getFilepath());
                sd.setFullpath(sad.getFullpath());
            }
                
       }
       
       return sd;
   }
    
}
