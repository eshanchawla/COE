import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class ImportStudentInformation {

	
	public static void main(String args[]){
		
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/coe", "root", "root");
			con.setAutoCommit(false);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		FileInputStream file = null;
		try {
			file = new FileInputStream(new File("C:\\Users\\chirag\\Desktop\\student project\\STUDENT DETAILS - AUTO - 2009 BATCH.xls"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HSSFSheet completeSheet = workbook.getSheetAt(0);
		
		Iterator<Row> rowIterator = completeSheet.iterator();
		
		rowIterator.next(); //ignoring first row, contains only metadata
		
		String tablename = "tmkc";
		
		String sql = "CREATE TABLE IF NOT EXISTS "+tablename+"(enrolmentnumber varchar(50) primary key, " +
				"studentname varchar(50), " +
				"fathername varchar(50), " +
				"mothername varchar(50), " +
				"joiningbatch varchar(50))";
		try {
			Statement stmt = (Statement) con.createStatement();
			stmt.executeUpdate(sql);
			con.commit();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(rowIterator.hasNext()){
			Row row = rowIterator.next();
			Cell cell = row.getCell(0);
			String enrolmentnumber = cell.getStringCellValue().replace(" ", "");
			cell = row.getCell(2);
			String studentname = cell.getStringCellValue();
			cell = row.getCell(5);
			String fatherName = cell.getStringCellValue();
			cell = row.getCell(6);
			String motherName = cell.getStringCellValue();
			cell = row.getCell(8);
			String joiningBatch = Double.toString(cell.getNumericCellValue());
			dbWrite(con,tablename, enrolmentnumber, studentname, fatherName, motherName, joiningBatch);
		}
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void dbWrite(Connection con, String tableName, String enrolmentnumber,
			String studentname, String fatherName, String motherName, String joiningBatch) {
		// TODO Auto-generated method stub
		try {
			Statement stmt = (Statement) con.createStatement();
			String sql = "REPLACE INTO "+tableName+" VALUES('"+enrolmentnumber+"', '"+
					studentname+"', '"+
					fatherName+"', '"+
					motherName+"', '"+
					joiningBatch+"')";
			stmt.executeUpdate(sql);
			System.out.println(sql);
			con.commit();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
