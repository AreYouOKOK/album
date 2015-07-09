package dao;

import java.sql.Connection;
import java.util.List;

import entity.Photo;

public interface PhotoDao {
	 public void addPhoto(Connection conn,Photo photo);
	  public void updatePhoto(Connection conn,Photo quePhoto,Photo conPhoto);
	  public void deletePhoto(Connection conn,Photo photo);
	  public Photo queryForPhoto(Connection conn,Photo quePhoto,Photo conPhoto);
	  public List<Photo> queryForPhotoList(Connection conn,Photo quePhoto,Photo conPhoto);
}
