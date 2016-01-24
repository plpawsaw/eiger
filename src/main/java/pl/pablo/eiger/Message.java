package pl.pablo.eiger;

import java.io.Serializable;

class Message implements Serializable {
    
    private String str_Name;
    
    Message(String str_Name){
        this.str_Name = str_Name;
    }
    
    void setName(String str_Name){
        this.str_Name = str_Name;
    }
    
    String getName(){
        return this.str_Name;
    }
    
}
