package top.duyt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;

import static org.junit.Assert.*;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.xml.sax.InputSource;

/**
 * Dbunit公共测试类，
 * 
 * @author Alvin Du
 * 
 */
public class CommonDbunitTestCase {

	public static IDatabaseConnection connection;

	public File tempFile;

	/**
	 * 初始化dbunit数据库连接
	 * 
	 * @throws DatabaseUnitException
	 */
	@BeforeClass
	public static void init() throws DatabaseUnitException {
		connection = new DatabaseConnection(Dbutil.getConn());
	}

	/**
	 * 根据数据源文件创建FlatdataSet
	 * 
	 * @param dname
	 * @return
	 * @throws IOException
	 * @throws DataSetException
	 */
	protected IDataSet createDataSet(String dname) throws DataSetException,
			IOException {
		InputStream is = this.getClass().getClassLoader()
				.getResourceAsStream(dname);
		assertNotNull("dbunit数据源文件不存在", is);
		return new FlatXmlDataSet(new FlatXmlProducer(new InputSource(is)));
	}

	/**
	 * 将dataSet写入到文件中
	 * 
	 * @param ds
	 * @param file
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws DataSetException
	 */
	protected void writeDataSetToFile(IDataSet ds, File file)
			throws DataSetException, FileNotFoundException, IOException {
		FlatXmlDataSet.write(ds, new PrintWriter(file));
	}

	/**
	 * 备份所有的
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws DataSetException
	 */
	protected void backUpAllTable() throws SQLException, DataSetException,
			FileNotFoundException, IOException {
		IDataSet ds = connection.createDataSet();
		tempFile = File.createTempFile("allBackUp","xml");
		writeDataSetToFile(ds, tempFile);
	}

	/**
	 * 备份指定的表
	 * 
	 * @param tnames
	 * @throws IOException
	 * @throws DataSetException
	 */
	protected void backUpCustomTable(String[] tnames) throws IOException,
			DataSetException {
		QueryDataSet qs = new QueryDataSet(connection);
		for (String t : tnames) {
			qs.addTable(t);
		}
		tempFile = File.createTempFile("CustomBackUp", "xml");
		writeDataSetToFile(qs, tempFile);
	}

	/**
	 * 备份一张表
	 * 
	 * @param tname
	 * @throws IOException
	 * @throws DataSetException
	 */
	protected void backUpOneTable(String tname) throws DataSetException,
			IOException {
		backUpCustomTable(new String[] { tname });
	}

	/**
	 * 还原表数据
	 * 
	 * @throws FileNotFoundException
	 * @throws SQLException
	 * @throws DatabaseUnitException
	 */
	protected void resumeData() throws FileNotFoundException,
			DatabaseUnitException, SQLException {
		IDataSet ds = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(
				new FileInputStream(tempFile))));
		DatabaseOperation.CLEAN_INSERT.execute(connection, ds);
	}

	/**
	 * 测试用例结束之后关闭数据库连接
	 */
	@AfterClass
	public static void destory() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
