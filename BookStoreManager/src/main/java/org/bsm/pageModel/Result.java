package org.bsm.pageModel;
import java.util.List;
public class Result
{
    private String face_token;

    private List<User_list> user_list;

    public void setFace_token(String face_token){
        this.face_token = face_token;
    }
    public String getFace_token(){
        return this.face_token;
    }
    public void setUser_list(List<User_list> user_list){
        this.user_list = user_list;
    }
    public List<User_list> getUser_list(){
        return this.user_list;
    }
}