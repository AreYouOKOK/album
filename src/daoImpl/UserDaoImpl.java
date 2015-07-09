package daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import util.JdbcUtil;
import util.SQLBaseImpl;
import dao.UserDao;
import entity.User;

public class UserDaoImpl extends SQLBaseImpl implements UserDao {
    private static Log log =  
            LogFactory.getLog(UserDaoImpl.class.getName());  
	@Override
	public void addUser(Connection conn, User user) {
		add(conn, user);

	}

	

	@Override
	public void updateUser(Connection conn, User newUser,User oldUser) {
		update(conn,newUser,oldUser);

	}

	@Override
	public User queryForUser(Connection conn, User user) {
		return (User) queryForObject(conn, user);
	}

	@Override
	public int getMaxId(Connection conn) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int maxId = 0;
		String sql = "select max(id) from user";
		try {
			 ps = conn.prepareStatement(sql);
			 rs = ps.executeQuery();
			 log.info(Thread.currentThread().getStackTrace()[1].getClassName()+
					 Thread.currentThread().getStackTrace()[1].getMethodName()+"Ö´ÐÐSQL£º"+sql);
			 while(rs.next()){
				 maxId = rs.getInt(1);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(rs);
			JdbcUtil.release(ps);
		}
		
		return maxId+1;
	}

	@Override
	public int countUsername(Connection conn, String username) {
		String sql = "select count(username) from user where username = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			 ps = conn.prepareStatement(sql);
			 ps.setString(1, username);
			 rs = ps.executeQuery();
			 log.info(Thread.currentThread().getStackTrace()[1].getClassName()+
					 Thread.currentThread().getStackTrace()[1].getMethodName()+"Ö´ÐÐSQL£º"+sql);
			 while(rs.next()){
				count = rs.getInt(1); 
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

	@Override
	public int countEmail(Connection conn, String email) {
		String sql = "select count(email) from user where email = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			 ps = conn.prepareStatement(sql);
			 ps.setString(1, email);
			 rs = ps.executeQuery();
			 log.info(Thread.currentThread().getStackTrace()[1].getClassName()+"Ö´ÐÐSQL£º"+sql);
			 while(rs.next()){
				count = rs.getInt(1); 
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}

}
