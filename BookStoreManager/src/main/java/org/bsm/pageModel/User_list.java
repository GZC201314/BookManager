package org.bsm.pageModel;
public class User_list
{
    private double score;

    private String group_id;

    private String user_id;

    private String user_info;

    public void setScore(double score){
        this.score = score;
    }
    public double getScore(){
        return this.score;
    }
    public void setGroup_id(String group_id){
        this.group_id = group_id;
    }
    public String getGroup_id(){
        return this.group_id;
    }
    public void setUser_id(String user_id){
        this.user_id = user_id;
    }
    public String getUser_id(){
        return this.user_id;
    }
    public void setUser_info(String user_info){
        this.user_info = user_info;
    }
    public String getUser_info(){
        return this.user_info;
    }
	@Override
	public String toString() {
		return "User_list [score=" + score + ", group_id=" + group_id + ", user_id=" + user_id + ", user_info="
				+ user_info + "]";
	}
}