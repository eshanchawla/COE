import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.Statement;

public class Selector {

	// remove main, only for testing


	public static void main(String args[]) {
		@SuppressWarnings("unused")
		ArrayList<ArrayList<String>> s = dataFetcher("09ITMG1517AU",
				"summer2010cse");

	}

	// Function returns course code, course name, course credits and
	// corresponding grade according to roll number and table name provided
	public static ArrayList<ArrayList<String>> dataFetcher(String rollNumber,
			 String tableName)  {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/coe", "root", "");
		} catch (ClassNotFoundException e1 ) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		ArrayList<ArrayList<String>> completeData = null;
		
		try {
			Statement stmt = (Statement) con.createStatement();
			String query = "Select * from " + tableName
					+ " where enrolmentNo='" + rollNumber + "'";
			ResultSet rs = stmt.executeQuery(query);
			ResultSetMetaData metaData = (ResultSetMetaData) rs.getMetaData(); // fetching
																				// table
																				// metadata
																				// (column
																				// names)
			ArrayList<String> courseCode = new ArrayList<String>();
			ArrayList<String> grades = new ArrayList<String>();
			ArrayList<String> OtherInfo = new ArrayList<String>();
			int counter;
			if (rs.first()) { // Selecting first (and the only) row
				String resultdate = rs.getString("resultdate");
				String examdate = rs.getString("examdate");
				for (counter = 6; counter <= metaData //Starting directly from course code first column
						//Can make less error prone by making counter 0 but probability is very low
						.getColumnCount(); counter++) { 
					if (metaData.getColumnName(counter).matches(
							"^[a-zA-Z]{3}[0-9]{3}$")) { // RegExp match for
														// course code pattern
						String grade = rs.getString(counter);
						if (grade.length() != 0) { // Trimming all blank entries
													// in
													// grades
							grades.add(grade);
							courseCode.add(metaData
									.getColumnName(counter));
						}
					}
				}
				int columnCount = metaData.getColumnCount();
				//Selecting non summer, with sgpa
				if (tableName.substring(0,6).compareTo("summer") != 0) {
					String totalCredits = rs.getString(columnCount - 2);
					String sgpa = rs.getString(columnCount - 1);
					String cgpa = rs.getString(columnCount);

					OtherInfo.add(resultdate);
					OtherInfo.add(examdate);
					OtherInfo.add(totalCredits);
					OtherInfo.add(sgpa);
					OtherInfo.add(cgpa);
				}
				//selecting summer, without sgpa
				else {
					String totalCredits = rs.getString(columnCount - 1);
					String cgpa = rs.getString(columnCount);

					OtherInfo.add(resultdate);
					OtherInfo.add(examdate);
					OtherInfo.add(totalCredits);
					OtherInfo.add(cgpa);
				}

			}
			else //if resultset is empty
			{
				return null;
			}
			ArrayList<String> courseName = new ArrayList<String>();
			ArrayList<String> courseCredits = new ArrayList<String>();

			query = "Select coursecode, coursename, coursecredits FROM courseinformation where ";

			for (int i = 0; i < courseCode.size(); i++) {
				query = query + " coursecode = '" + courseCode.get(i) + "' OR";
			}
			// System.out.println(query);
			query = query.substring(0, query.length() - 3); // removing trailing
															// OR

			rs = stmt.executeQuery(query); // reusing rs object for other query

			while (rs.next()) {
				courseName.add(rs.getString("coursename"));
				courseCredits
						.add(String.valueOf(rs.getDouble("coursecredits")));
			}

			completeData = new ArrayList<ArrayList<String>>();
			completeData.add(courseCode);
			completeData.add(courseName);
			completeData.add(courseCredits);
			completeData.add(grades);
			completeData.add(OtherInfo); // has resultdate, examdate, total
											// creds, sgpa and cgpa in
											// respective order
			/* for testing purpose
			for (int i = 0; i < courseCode.size(); i++)
				System.out.println(courseCode.get(i) + courseName.get(i)
						+ courseCredits.get(i) + grades.get(i));
			for (int i = 0; i < OtherInfo.size(); i++)
				System.out.println(OtherInfo.get(i));
				*/
			con.close();
		} catch (SQLException e) {
			System.out.print("Error!! Table does not exist!!");
			e.printStackTrace();
			return null;
		}
		return completeData;
	}

	public static ArrayList<String> studentInfoFetcher(String rollno) {
		// Start: establishing connection
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost/coe", "root", "");
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		// end: done

		String sql = "Select studentname, fathername, mothername from tmkc where enrolmentnumber = '"
				+ rollno + "'";
		ArrayList<String> neededData = new ArrayList<String>();
		try {
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.first()) {
				String studentName = rs.getString("studentname");
				String fatherName = rs.getString("fathername");
				String motherName = rs.getString("mothername");
				neededData.add(studentName);
				neededData.add(fatherName);
				neededData.add(motherName);
			} else {
				// ToDo: proper error handling
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return neededData;
	}
}