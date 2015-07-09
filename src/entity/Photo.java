package entity;

   /**
    * photo  µÃÂ¿‡
    * Tue Jun 30 18:46:54 CST 2015 liuxiaojun
    */ 


public class Photo{
	private int id;
	private int albumId;
	private String name;
	private String path;
	private String description;
	private String comment;
	private String createTime;
	private String updateTime;
	private String vistor;
	public void setId(int id){
	this.id=id;
	}
	public int getId(){
		return id;
	}
	public void setAlbumId(int albumId){
	this.albumId=albumId;
	}
	public int getAlbumId(){
		return albumId;
	}
	public void setName(String name){
	this.name=name;
	}
	public String getName(){
		return name;
	}
	public void setPath(String path){
	this.path=path;
	}
	public String getPath(){
		return path;
	}
	public void setDescription(String description){
	this.description=description;
	}
	public String getDescription(){
		return description;
	}
	public void setComment(String comment){
	this.comment=comment;
	}
	public String getComment(){
		return comment;
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

