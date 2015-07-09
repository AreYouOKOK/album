package daoImpl;

import java.sql.Connection;
import java.util.List;

import util.SQLBaseImpl;
import dao.PhotoDao;
import entity.Photo;

public class PhotoDaoImpl extends SQLBaseImpl implements PhotoDao {

	@Override
	public void addPhoto(Connection conn, Photo photo) {
		add(conn, photo);

	}

	@Override
	public void updatePhoto(Connection conn, Photo quePhoto, Photo conPhoto) {
		update(conn, quePhoto, conPhoto);

	}

	@Override
	public void deletePhoto(Connection conn, Photo photo) {
		delete(conn, photo);

	}

	@Override
	public Photo queryForPhoto(Connection conn, Photo quePhoto, Photo conPhoto) {
		return (Photo) queryForObject(conn, conPhoto);

	}

	@Override
	public List<Photo> queryForPhotoList(Connection conn, Photo quePhoto,
			Photo conPhoto) {
		return queryForPhotoList(conn, quePhoto, conPhoto);

	}

}
