package util;


import java.sql.Connection;
import java.util.List;




/**
 *增删改查工具类
 * @author liuxiaojun
 *
 */
public interface SQLBase {
	
	public <T> void add(Connection conn,T entity);
	public <T> void delete(Connection conn,T entity);
	public <T> void update(Connection conn, T newEntity,T oldEntity);
	public <T> int count(Connection conn,T entity);
	public <T> List<T> queryForList(Connection conn,T queryEntity,T conEntity);
	public <T> Object queryForObject(Connection conn,T entity);
	public <T> List<Object> queryForList(Connection conn,String sql,Object...params);
	
}
