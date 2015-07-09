package service;


import entity.User;

public interface UserService {
   public void addUser(User user);
   public void updateUser(User newUser,User oldUser);
   public User queryOneUser(User user);
   public int countUsername(String username);
   public int countEmail(String email);
}
