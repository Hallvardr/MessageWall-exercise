package demo.impl;

import demo.spec.Message;
import demo.spec.MessageWall;
import demo.spec.UserAccess;
import java.util.List;

public class UserAccess_Impl implements UserAccess {

    private String user;
    private MessageWall messageWall;

    public UserAccess_Impl(MessageWall mw, String usr) {
        messageWall = mw;
        user = usr;
    }

    @Override
    public String getUser() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return this.user;
    }

    @Override
    public Message getLast() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return messageWall.getLast();
    }

    @Override
    public int getNumber() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return messageWall.getNumber();
    }

    @Override
    public void put(String msg) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        messageWall.put(this.user, msg);
    }

    @Override
    public boolean delete(int index) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        messageWall.delete(this.user, index);
        return true;
    }

    @Override
    public List<Message> getAllMessages() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return messageWall.getAllMessages();
    }

    
}
