package dataSouceTest;

import static org.hamcrest.CoreMatchers.nullValue;

import java.io.InputStream;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.time.Year;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 操作 JDBC 的工具类。其中封装了一些工具方法.
 * Version 1.0
 *
*/
public class JDBCTools {

	/**
	 * 用 C3P0 重构
	 * 1. 获取连接的方法。
	 *    通过读取配置文件从数据库服务器获取一个连接。
	 * @throws Exception 
	 */
	
	private static DataSource dataSource = null;
	
	//数据库连接池应只被初始化一次.
	static{
		dataSource = new ComboPooledDataSource("helloc3p0");
	}
	
	public static Connection getConnection() throws Exception{
		return dataSource.getConnection();
	}
	
	/**
	 * 2. 关闭 Statement 和 Connection 的方法
	 */
	public static void releaseSource(Statement stmp,Connection conn){
		if(stmp != null){
			try{
				stmp.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(conn != null){
			try{
				//数据库连接池的 Connection 对象进行 close 时
				//并不是真的进行关闭, 而是把该数据库连接会归还到数据库连接池中. 
				conn.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	/**
	 * 3. 关闭 ResultSet、Statement 和 Connection 的方法
	 */
	public static void release(ResultSet rs,Statement stmp,Connection conn){
		if(rs != null){
			try{
				rs.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(stmp != null){
			try{
				stmp.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		if(conn != null){
			try{
				conn.close();
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 4. 通用的 Query 方法(Select)
	 */
	
	public static ResultSet query(String sql,Statement stmp,Connection conn){
		ResultSet rs = null;
		try{
			rs = stmp.executeQuery(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		return rs;

	}
	
	/**
	 * 5.通用的更新的方法:包括INSERT、UPDATE、DELETE
	 * 
	 */
	public static void update(String sql){
		Connection conn = null;
		Statement stmp = null;
		
		try {
			conn = getConnection();
			stmp = conn.createStatement();
			stmp.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(stmp != null){
				try{
					stmp.close();
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
			if(conn != null){
				try{
					stmp.close();
				}catch(Exception e2){
					e2.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 6.update 方法的重载，使用 PreparedStatement
	 * @param sql
	 * @param args 可变参数
	 */
	public static void update(String sql,Object ...args){
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try{
			connection = JDBCTools.getConnection();
			preparedStatement = connection.prepareStatement(sql);
			for(int i=0;i<args.length;i++){
				preparedStatement.setObject(i + 1, args[i]);
			}
			preparedStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			JDBCTools.releaseSource(preparedStatement, connection);
		}
	}
	
	/**
	 * 7. 处理数据库事务的
	 */
	public static void commit(Connection connection){
		if(connection != null){
			try {
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 8.回滚事务的
	 */
	public static void rollback(Connection connection){
		if(connection != null){
			try {
				connection.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 9.开始事务的
	 */
	public static void beginTx(Connection connection){
		if(connection != null){
			try {
				connection.setAutoCommit(false);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
