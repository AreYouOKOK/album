package serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.Date;

import service.UserService;
import util.DateUtil;
import util.JdbcUtil;
import dao.UserDao;
import daoImpl.UserDaoImpl;
import entity.User;

public class UserServiceImpl implements UserService {
    private UserDao dao = new UserDaoImpl();
	@Override
	public void addUser(User user) {
		Connection conn = JdbcUtil.getConnection();
		Savepoint savepoint = null;
		user.setId(dao.getMaxId(conn));
		user.setCreateTime(DateUtil.formatDate(new Date()));
		
		try {
			savepoint = conn.setSavepoint("berefore add");
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
        try{
        	dao.addUser(conn, user);
        	conn.commit();
        }catch(Exception e){
        	try {
				conn.rollback(savepoint);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }finally{
        	JdbcUtil.release(conn);
        }
        
	}
	@Override
	public User queryOneUser(User user) {
		Connection conn = JdbcUtil.getConnection();
		User queryUser  = dao.queryForUser(conn, user);
		JdbcUtil.release(conn);
		return  queryUser;
		
	}
	@Override
	public int countUsername(String username) {
		Connection conn = JdbcUtil.getConnection();
		int count = dao.countUsername(conn, username);
		JdbcUtil.release(conn);
		return count;
	}
	@Override
	public int countEmail(String email) {
		Connection conn = JdbcUtil.getConnection();
		int count = dao.countEmail(conn, email);
		JdbcUtil.release(conn);
		return count;
	}
	@Override
	public void updateUser(User newUser,User oldUser) {
		Connection conn = JdbcUtil.getConnection();
		Savepoint savepoint = null;
		newUser.setUpdateTime((DateUtil.formatDate(new Date())));;
		
        try{
        	savepoint = conn.setSavepoint("berefore add");
        	dao.updateUser(conn, newUser,oldUser);
        	conn.commit();
		} catch(Exception e){
        	try {
				conn.rollback(savepoint);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
        }finally{
        	JdbcUtil.release(conn);
        }
		
	}

}
