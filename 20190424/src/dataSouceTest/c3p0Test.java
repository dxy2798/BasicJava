package dataSouceTest;

import java.sql.Connection;
import java.sql.SQLException;
import org.junit.Test;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class c3p0Test {

	@Test
	public void testJdbcTools() throws Exception{
		Connection connection = JDBCTools.getConnection();
		System.out.println(connection);
	}
	
	/**
	 * 1. 创建 c3p0-config.xml 文件，参考帮助文档 Appendix B: Configuation Files 的内容
	 * 2. 创建 ComboPooledDataSource 实例:
	 * ComboPooledDataSource dataSource = 
	 *			new ComboPooledDataSource("helloc3p0");
	 * 3. 从 dataSource 实例中获取数据库连接			
	 * @throws SQLException
	 */
	@Test
	public void testC3p0WithConfigFile() throws SQLException{
		
		ComboPooledDataSource dataSource = 
				new ComboPooledDataSource("helloc3p0");
				
		System.out.println(dataSource.getConnection());
		ComboPooledDataSource comboPooledDataSource = 
				dataSource;
		System.out.println(comboPooledDataSource.getMaxStatements());
	}
	
	
	
	@Test
	public void testC3P0() throws Exception {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		cpds.setDriverClass( "com.mysql.jdbc.Driver" ); //loads the jdbc driver            
		cpds.setJdbcUrl( "jdbc:mysql://192.168.3.26:3306/ajax" );
		cpds.setUser("root");                                  
		cpds.setPassword("32147");
		
		System.out.println(cpds.getConnection());
	}

}
