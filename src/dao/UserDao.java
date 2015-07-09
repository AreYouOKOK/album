package dao;

import java.sql.Connection;

import entity.User;

public interface UserDao {
  public void addUser(Connection conn,User user);
  public void updateUser(Connection conn,User newUser,User oldUser);
  public User queryForUser(Connection conn,User user);
  public int getMaxId(Connection conn);
  public int countUsername(Connection conn,String username);
  public int countEmail(Connection conn,String email);
}
