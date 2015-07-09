package entity;

   /**
    * album  µÃÂ¿‡
    * Tue Jun 30 18:45:36 CST 2015 liuxiaojun
    */ 


public class Album{
	private int id;
	private String name;
	private String description;
	private int userid;
	private int cover;
	private String createTime;
	private String updateTime;
	private String vistor;
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setDescription(String description){
	this.description=description;
	}
	public String getDescription(){
		return description;
	}
	public void setUserid(int userid){
	this.userid=userid;
	}
	public int getUserid(){
		return userid;
	}
	public void setCover(int cover){
	this.cover=cover;
	}
	public int getCover(){
		return cover;
	}
	public void setCreateTime(String createTime){
	this.createTime=createTime;
	}
	public String getCreateTime(){
		return createTime;
	}
	public void setUpdateTime(String updateTime){
	this.updateTime=updateTime;
	}
	public String getUpdateTime(){
		return updateTime;
	}
	public void setVistor(String vistor){
	this.vistor=vistor;
	}
	public String getVistor(){
		return vistor;
	}
}

