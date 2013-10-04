
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class importfile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int numberofcolum = 0;
		//String nameoffiletobeimported = args[0];
		String nameoftableasselectedbyuser = "summer2010cse";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/coe", "root", "");
			con.setAutoCommit(false);
			PreparedStatement pstm = null;
			FileInputStream input = new FileInputStream("C:\\Users\\admin\\Downloads\\student project\\student project\\RESULT FORMAT - 2009 BATCH Summer 2010 (AUTO).xls");
			POIFSFileSystem fs = new POIFSFileSystem(input);
			HSSFWorkbook wb = new HSSFWorkbook(fs);
			HSSFSheet sheet = wb.getSheetAt(1);
			writeCourseList(con, wb.getSheetAt(0));
			Row row;
			/*----check if the table exists or not-------*/
			Statement st = (Statement) con.createStatement();
			st.close();
			/* getting the names for the columns */
			ArrayList<String> columns = new ArrayList<String>();
			row = sheet.getRow(0);
			Iterator<Cell> itr = row.cellIterator();
			while (itr.hasNext()) {
				columns.add(itr.next().toString());
				numberofcolum++;
			}
			/* ends here */

			/* creating the schema for the excel file to be imported */
			System.out.println("Success import excel to mysql table"
					+ numberofcolum);
			Iterator arr = columns.iterator();
			String query = "CREATE TABLE IF NOT EXISTS " + nameoftableasselectedbyuser + "(";
			while (arr.hasNext()) {
				String col = (String) arr.next();
				col = col.replaceAll(" ", "");
				query = query + col + " varchar(50)" + ",";
			}

			System.out.println(query.charAt(query.length() - 1));
			String exec = query.substring(0, query.length() - 1);
			exec = exec + ",PRIMARY KEY(enrolmentno)" + ");";
			pstm = (PreparedStatement) con.prepareStatement(exec);
			pstm.execute();
			con.commit();
			pstm.close();
			System.out.println("table did not exists so created");

			System.out.println("last row=" + sheet.getLastRowNum());
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);
				Cell c = row.getCell(0);
				if (c.getStringCellValue().isEmpty())
					continue;
				String sql = "REPLACE INTO " + nameoftableasselectedbyuser
						+ " VALUES (";
				String quote = "'";
				for (int j = 0; j < numberofcolum; j++) {
					Cell curr;
					if (row.getCell(j).getCellType() == 0) {
						curr = row.getCell(j);
						if (HSSFDateUtil.isCellDateFormatted(curr)) {
							Date date = curr.getDateCellValue();
							String datedata;
							datedata = date.getMonth() +1 + "/"
									+ (date.getDate() ) + "/"
									+ (date.getYear() + 1900);
							sql = sql + quote + datedata + quote + ",";
						}

						else {
							String doubleValue = (Double.toString(row.getCell(j)
									.getNumericCellValue()));
							
							doubleValue = doubleValue.length()>=4?doubleValue.substring(0,4):doubleValue;
							
							sql = sql
									+ quote
									+ doubleValue + quote //substring to take only 4 characters eg. 7.57 and ignore rest float values
									+ ",";
						}
					} else{
						if(j!=0)
						sql = sql + quote + row.getCell(j).getStringCellValue()
								+ quote + ",";
				else
					sql = sql + quote + row.getCell(j).getStringCellValue().replaceAll(" ","")
					+ quote + ",";
					}

				}
				sql = sql.substring(0, sql.length() - 1);
				sql = sql + ");";
				System.out.println(sql);
				pstm = (PreparedStatement) con.prepareStatement(sql);
				pstm.executeUpdate();
				con.commit();
				pstm.close();
			}

			con.close();
			input.close();

		} catch (ClassNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println(ex);
		} catch (IOException ioe) {
			ioe.printStackTrace();
			System.out.println(ioe);
		}

	}

	public static void writeCourseList(Connection con, HSSFSheet sheet) {
		Iterator<Row> rowIterator = sheet.iterator();

		rowIterator.next();// Ignore first row, has only definitions
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Cell courseCode = row.getCell(0);
			String courseCodeString = courseCode.getStringCellValue();
			courseCodeString = courseCodeString.replace(" ", "");
			Cell courseName = row.getCell(1);
			String courseNameString = courseName.getStringCellValue();
			Cell courseCreds = row.getCell(2);
			Double courseCredsDouble = courseCreds.getNumericCellValue();
			insertCourseInfo(con, courseCodeString, courseNameString,
					courseCredsDouble);
		}
	}

	public static void insertCourseInfo(Connection c, String courseCode,
			String courseName, Double courseCreds) {

		try {
			Statement stmt = (Statement) c.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS CourseInformation ("
					+ "coursecode varchar(100) primary key,"
					+ "coursename varchar(50)," + "coursecredits Double)";
			stmt.executeUpdate(sql);

			sql = "REPLACE INTO CourseInformation VALUES('" + courseCode
					+ "', '" + courseName + "', " + courseCreds + ")";
			stmt.executeUpdate(sql);
			c.commit();
			stmt.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}