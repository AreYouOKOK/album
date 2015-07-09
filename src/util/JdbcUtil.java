package util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * 创建数据库连接，释放数据库资源
 * @author liuxiaojun
 *
 */
public class JdbcUtil {
  
  public static Connection getConnection(){
	  Context ctx = null;
	  DataSource ds = null;
	  Connection conn = null;
	try {
		ctx = new InitialContext();
	    ds = (DataSource)ctx.lookup("java:comp/env/jdbc/mysql");
	    conn = ds.getConnection();
	    conn.setAutoCommit(false);
	} catch (NamingException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	}
	 
	  return conn;
  }
  public static void release(Connection conn){
	  if(conn!=null){
		  try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  }
  }
  public static void release(PreparedStatement ps){
	  if(ps!=null){
		  try {
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	  }
  }
  public static void release(ResultSet rs){
	  if(rs!=null){
		  try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  rs = null;
	  }
  }
}
