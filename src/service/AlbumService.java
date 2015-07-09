package service;

import java.util.List;

import entity.Album;

public interface AlbumService {
  public void addAlbum(Album album);
  public void updateAlbum(Album queAlbum,Album conAlbum);
  public void deleteAlbum(Album album);
  public Album queryOneAlbum(Album queAlbum,Album conAlbum);
  public List<Album> queryAlbumList(Album queAlbum,Album conAlbum);
}
