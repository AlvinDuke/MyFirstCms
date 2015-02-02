package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
import org.dbunit.dataset.xml.FlatDtdDataSet;
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

	private File tempFile;
	
	private File dtdFile;

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
		return new FlatXmlDataSet( new FlatXmlProducer(new InputSource(is),new FlatDtdDataSet(new FileReader(dtdFile))));
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
	 * 将dtd写入到文件中
	 * 
	 * @param ds
	 * @param file
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws DataSetException
	 */
	protected void writeDtdToFile(IDataSet ds, File file)
			throws DataSetException, FileNotFoundException, IOException {
		FlatDtdDataSet.write(ds, new PrintWriter(file));
	}

	/**
	 * 备份所有的表
	 * 
	 * @throws SQLException
	 * @throws IOException
	 * @throws FileNotFoundException
	 * @throws DataSetException
	 */
	protected void backUpAllTable() throws SQLException, DataSetException,
			FileNotFoundException, IOException {
		IDataSet ds = connection.createDataSet();
		tempFile = File.createTempFile("allBackUp", "xml");
		dtdFile = File.createTempFile("dtdBackUp", "dtd");
		writeDataSetToFile(ds, tempFile);
		writeDtdToFile(ds, dtdFile);
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
		dtdFile = File.createTempFile("CustomDtdBackUp", "dtd");
		writeDataSetToFile(qs, tempFile);
		writeDtdToFile(qs, dtdFile);
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
	 * @throws SQLException
	 * @throws DatabaseUnitException
	 * @throws IOException 
	 */
	protected void resumeData() throws DatabaseUnitException, SQLException, IOException {
		IDataSet ds = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(
				new FileInputStream(tempFile)),new FlatDtdDataSet(new FileReader(dtdFile))));
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
