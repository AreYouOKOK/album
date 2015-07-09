package util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public  class SQLBaseImpl implements SQLBase{
	  private static Log log =  
	            LogFactory.getLog(SQLBaseImpl.class.getName()); 
    /**
     * 添加一条记录
     */
	@Override
	public <T> void add(Connection conn, T entity) {
		Class<?> entityClass = entity.getClass();
		String  tableName =entityClass.getSimpleName();
		Field[] fields = entityClass.getDeclaredFields();
		StringBuffer sql = new StringBuffer("insert into "+tableName+" (");
		PreparedStatement ps = null;
		for(int i= 0;i<fields.length;i++){
			Field f = fields[i];
			f.setAccessible(true);
			if(i==fields.length-1){
				sql.append(f.getName()+") values (");
			}else{
				sql.append(f.getName()+",");
			}
		}
		
		for(int i= 0;i<fields.length;i++){
			if(i==fields.length-1){
				sql.append("?)");
			}else{
				sql.append("?,");
			}
		}
		try {
		    ps = conn.prepareStatement(sql.toString());
		    setParameters(ps, entity);
			log.info("向数据库添加一条记录："+sql.toString());
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(ps);
		}
		
	}
    /**
     * TODO 未测试：删除一条数据
     */
	@Override
	public <T> void delete(Connection conn, T entity) {
		Class<?> newEntityClass = entity.getClass();
		StringBuffer sbSql = new StringBuffer("delete from ");
		String tableName = newEntityClass.getSimpleName().toLowerCase();
		sbSql.append(tableName+" where ");
		String sql = setCondition(entity, sbSql);
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(ps);
		}
	   
				
	}

	@Override
	public <T> void update(Connection conn, T newEntity,T oldEntity) {
		Class<?> newEntityClass = newEntity.getClass();
		Class<?> oldEntityClass = oldEntity.getClass();
		if(!newEntityClass.getName().equals(oldEntityClass.getName())){
			log.info(Thread.currentThread().getStackTrace()[1].getClassName()+
				 Thread.currentThread().getStackTrace()[1].getMethodName()+"系统内部错误，不能更新");
		}else{
			String  tableName =newEntityClass.getSimpleName();
			Field[] setFields = newEntityClass.getDeclaredFields();
			Field[] conFields = oldEntityClass.getDeclaredFields();
			StringBuffer sql = new StringBuffer("update "+tableName+" set  id= id");
			PreparedStatement ps = null;
			
			for(int i= 0;i<setFields.length;i++){
				Field f = setFields[i];
				f.setAccessible(true);
				try {
					if(f.get(newEntity)!=null&&!f.get(newEntity).equals(new Integer(0))){
						if(f.getType().getSimpleName().equals("String")){
							sql.append(","+f.getName()+"= '"+f.get(newEntity)+"'");
						}else{
							sql.append(","+f.getName()+"= "+f.get(newEntity));
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			sql.append(" where ");
			for(int i=0;i<conFields.length;i++){
				Field f = conFields[i];
				f.setAccessible(true);
				try {
					if(f.get(oldEntity)!=null&&!f.get(oldEntity).equals(new Integer(0))){
						if(i==conFields.length-1){
							if(f.getType().getSimpleName().equals("String")){
								sql.append(f.getName()+" = '"+f.get(oldEntity)+"'");
							}else{
								sql.append(f.getName()+" = "+f.get(oldEntity));
							}
						}else{
							if(f.getType().getSimpleName().equals("String")){
								sql.append(f.getName()+" = '"+f.get(oldEntity)+"' and ");
							}else{
								sql.append(f.getName()+" = "+f.get(oldEntity)+" and ");
							}
							
							
						}
						
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
			String finalSQL = sql.toString();
			if(finalSQL.endsWith("and ")){
				finalSQL = finalSQL.substring(0, finalSQL.length()-5);
			}
			 log.info(Thread.currentThread().getStackTrace()[1].getClassName()+
					 Thread.currentThread().getStackTrace()[1].getMethodName()+"执行SQL："+finalSQL);
			try {
				ps = conn.prepareStatement(finalSQL);
				ps.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				JdbcUtil.release(ps);
			}
		}
		
	}
	
    /**
     */
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> queryForList(Connection conn, T queryEntity,T conEntity) {
		StringBuffer sbSql = new StringBuffer("select ");
		Class<?> queryEntityClass = queryEntity.getClass();
		Class<?> conEntityClass = conEntity.getClass();
		if(!queryEntityClass.getName().equals(conEntityClass.getName())){
			log.info(Thread.currentThread().getStackTrace()[1].getClassName()+
					 Thread.currentThread().getStackTrace()[1].getMethodName()+"系统内部错误，不能查询");
		}else{
			String tableName = queryEntityClass.getSimpleName().toLowerCase();
			Field [] queryFields = queryEntityClass.getDeclaredFields();
			for(int i=0;i<queryFields.length;i++){
				Field f = queryFields[i];
				f.setAccessible(true);
				if(i==queryFields.length-1){
					sbSql.append(f.getName().toLowerCase());
				}else{
					sbSql.append(f.getName().toLowerCase()+",");
				}
			}
			sbSql.append(" from "+tableName+" where 1=1");
			
			String sql = setCondition(conEntity, sbSql);
			log.info(Thread.currentThread().getStackTrace()[1].getClassName()+
					 Thread.currentThread().getStackTrace()[1].getMethodName()+"执行sql:"+sql);
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<T> list = null;
			try {
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();
				list = (List<T>) ResultToObject(rs,queryEntityClass);
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				JdbcUtil.release(rs);
				JdbcUtil.release(ps);
			}
			return list;
		}
		
		return null;
		
	}
    /**
     * TODO 重构：可以查询指定的列
     */
	@Override
	public <T> Object queryForObject(Connection conn, T entity) {
		Class<?> entityClass = entity.getClass();
		String tableName = entityClass.getSimpleName().toLowerCase();
		StringBuffer sql = new StringBuffer("select * from "+tableName+" where 1=1  ");
		String finalSql = setCondition(entity,sql);
		PreparedStatement ps = null;
		ResultSet rs = null;
		Object o = null;
		try {
			 ps = conn.prepareStatement(finalSql);
			 log.info(sql);
			 rs = ps.executeQuery();
			 List<?> list = ResultToObject(rs,entityClass);
			 if(list!=null&&list.size()>0){
				o =  list.get(0);
			 }
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(rs);
			JdbcUtil.release(ps);
		}
		return o;
	}
	 /**
	    * 反射解析ResultSet
	    * @param rs
	    * @param entityClass
	    * @return
	    */
	   private  static <T> List<T> ResultToObject( ResultSet rs,Class<T> entityClass){
			List<T> list=new ArrayList<T>();
			try{
			Field[] field = entityClass.getDeclaredFields();
			while(rs.next()){
				T entity = (T) entityClass.newInstance();
				for(Field it:field){
					it.setAccessible(true);
					String name=it.getName();
					String parameterType=it.getType().getSimpleName();
					String methodName="set"+((name.charAt(0) + "").toUpperCase())+name.substring(1);
					Method method = entity.getClass().getDeclaredMethod(methodName,
							it.getType());
					if (parameterType.equals("String")) {
						method.invoke(entity, rs.getString(name));
					}else if (parameterType.equals("int")) {
						method.invoke(entity, rs.getInt(name));
					}
				}
				list.add(entity);
			}
			}catch(Exception e){
				e.printStackTrace();
			}
			return list;
			
		} 
	   /**
	    * 设置简单sql的where条件
	    * @param entity
	    * @param sql
	    * @return
	    */
	private static <T> String setCondition (T entity,StringBuffer sql){
		Class<?> entityClass = entity.getClass();
		Field[] fields = entityClass.getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			try {
				Field f = fields[i];
				f.setAccessible(true);
				String type = f.getType().getSimpleName();
				Object o = f.get(entity);
				if(o!=null&&!o.equals(new Integer(0))){
					if (type.equals("String")) {
						sql.append(" and "+f.getName() + "= '" + (String) o + "'");
					}
					if (type.equals("int")) {
						sql.append(" and "+f.getName() + "= " + (Integer) o);
					}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return sql.toString();
	}
	private static  <T> void setParameters(PreparedStatement ps,T entity) throws IllegalArgumentException, IllegalAccessException, SQLException{
		Field[] fields = entity.getClass().getDeclaredFields();
		for(int i=0;i<fields.length;i++){
			Field f = fields[i];
			f.setAccessible(true);
			String type = f.getType().getSimpleName();
			if(type.equals("String")&&f.get(entity)!=null){
				ps.setString(i+1, f.get(entity).toString());
			}else{
				ps.setObject(i+1, f.get(entity));
			}
		}
	}
	@SuppressWarnings("rawtypes")
	@Override
	public <T> int count(Connection conn, T entity) {
		Class entityClass = entity.getClass();
		String tableName = entityClass.getSimpleName();
		String sql = "select count(*) from "+tableName;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int count = 0;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.release(ps);
			JdbcUtil.release(rs);
		}
		return count;
	}
	@Override
	public  List<Object> queryForList(Connection conn, String sql,
			Object... params) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Object> list = null;
		try {
			ps = conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			rs = ps.executeQuery();
			list = (List<Object>) ResultToObject(rs, Object.class);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
