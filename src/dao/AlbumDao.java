package dao;

import java.sql.Connection;
import java.util.List;

import entity.Album;

public interface AlbumDao {
  public void addAlbum(Connection conn,Album album);
  public void updateAlbum(Connection conn,Album queAlbum,Album conAlbum);
  public void deleteAlbum(Connection conn,Album album);
  public Album queryForAlbum(Connection conn,Album queAlbum,Album conAlbum);
  public List<Album> queryForAlbumList(Connection conn,Album queAlbum,Album conAlbum);
  public int getMaxId(Connection conn);
}
