package service;

import java.util.List;

import entity.Photo;

public interface PhotoService {
	  public void addPhoto(Photo photo);
	  public void updatePhoto(Photo quePhoto,Photo conPhoto);
	  public void deletePhoto(Photo photo);
	  public Photo queryOnePhoto(Photo quePhoto,Photo conPhoto);
	  public List<Photo> queryPhotoList(Photo quePhoto,Photo conPhoto);
}
