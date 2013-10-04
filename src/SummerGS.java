import java.awt.List;
import java.io.FileOutputStream;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SummerGS {

	public static void main(String args[]){
		String batchString = "2010";
		Integer batchInt = Integer.valueOf(batchString);
		String stream = "cse";
		String rno = "09ITMG1518AU";		
		//summerCreator(batchInt, rno, stream);
	}
	
	private static PdfPTable tableCreator(int year,
			ArrayList<ArrayList<String>> data) {

		ArrayList<String> courseCode = new ArrayList<String>();
		ArrayList<String> courseName = new ArrayList<String>();
		ArrayList<String> courseCredits = new ArrayList<String>();
		ArrayList<String> grade = new ArrayList<String>();
		int length = 0;
		try {

			courseCode = data.get(0);
			courseName = data.get(1);
			courseCredits = data.get(2);
			grade = data.get(3);
			length = courseCode.size();
		} catch (NullPointerException e) { // if data=null, fill table with
											// empty values
			length = 1;
		}

		while (length <= 15) {
			courseCode.add(" ");
			courseName.add(" ");
			courseCredits.add(" ");
			grade.add(" ");
			length++;
		}
		Font f2 = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
		PdfPCell summer = new PdfPCell(new Phrase("Summer "
				+ String.valueOf(year++), f2));
		Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 6.5f);
		PdfPCell blanks = new PdfPCell(new Phrase(" ", f1));
		PdfPTable table = new PdfPTable(5);
		try {
			table.setWidths(new float[] { .3f, 2f, .4f, .5f, .5f });
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PdfPCell sno = new PdfPCell(new Phrase("S.No.", f1));
		PdfPCell course = new PdfPCell(new Phrase("COURSE", f1));
		PdfPCell code = new PdfPCell(new Phrase("Code", f1));
		PdfPCell credits = new PdfPCell(new Phrase("Credits", f1));
		PdfPCell grade1 = new PdfPCell(new Phrase("Grade", f1));
		sno.setHorizontalAlignment(Element.ALIGN_CENTER);
		course.setHorizontalAlignment(Element.ALIGN_CENTER);
		code.setHorizontalAlignment(Element.ALIGN_CENTER);
		credits.setHorizontalAlignment(Element.ALIGN_CENTER);
		grade1.setHorizontalAlignment(Element.ALIGN_CENTER);
		summer.setHorizontalAlignment(Element.ALIGN_CENTER);

		table.addCell(blanks);
		table.addCell(summer);
		table.addCell(blanks);
		table.addCell(blanks);
		table.addCell(blanks);
		table.addCell(sno);
		table.addCell(course);
		table.addCell(code);
		table.addCell(credits);
		table.addCell(grade1);
		for (int i = 1; i <= 15; i++) {
			table.addCell(new PdfPCell(new Phrase(String.valueOf(i), f1)));
			table.addCell(new PdfPCell(new Phrase(courseName.get(i - 1), f1)));
			table.addCell(new PdfPCell(new Phrase(courseCode.get(i - 1), f1)));
			table.addCell(new PdfPCell(new Phrase(courseCredits.get(i - 1), f1)));
			table.addCell(new PdfPCell(new Phrase(grade.get(i - 1), f1)));
		}
		return table;
	}

	public static void summerCreator(Document document, Integer batchInt, String rno, String stream) {
		//Document document = new Document(PageSize.LEGAL, 10, 10, 15, 10);
		document.setMargins(10, 10, 15, 10);
		try {
			document.open();
			// data from UI

			String tableName = "summer" + batchInt.toString() + stream;
			// end
			ArrayList<ArrayList<String>> summer[] = (ArrayList<ArrayList<String>>[])new ArrayList[4];
			// data for all 4 summer tables
			summer[0] = Selector.dataFetcher(rno,
					tableName);

			batchInt++;
			tableName = "summer" + batchInt.toString() + stream;

			summer[1] = Selector.dataFetcher(rno,
					tableName);
			batchInt++;
			tableName = "summer" + batchInt.toString() + stream;

			summer[2] = Selector.dataFetcher(rno,
					tableName);
			batchInt++;
			tableName = "summer" + batchInt.toString() + stream;

			summer[3] = Selector.dataFetcher(rno,
					tableName);
			batchInt++;
			tableName = "summer" + batchInt.toString() + stream;
			// end

			// fonts
			Font f1 = new Font(Font.FontFamily.TIMES_ROMAN, 8);
			Font f2 = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.BOLD);
			Font f3 = new Font(Font.FontFamily.TIMES_ROMAN, 10);

			// header
			Paragraph header = new Paragraph("", f3);
			header.add("Summer / Re Major Test is appicable fo all reappear students in that Academic year.");
			header.setAlignment(Element.ALIGN_CENTER);
			document.add(header);

			// Cells
			int yr = 2010; // year ki value
			PdfPCell blank = new PdfPCell(new Phrase(" ", f1));
			PdfPCell blanks = new PdfPCell(new Phrase(" ", f1));

			PdfPCell remajor_yr = new PdfPCell(new Phrase("Re Major "
					+ String.valueOf(yr++), f2));

			remajor_yr.setHorizontalAlignment(Element.ALIGN_CENTER);

			String cgpa = new String("CGPA : ");
			String total_creds = new String("Total Credits Earned : ");

			// OtherInfo contains resultDate, examDate, total credits, cgpa for all semesters
			ArrayList<String> OtherInfo[] =  (ArrayList<String>[])new ArrayList[4];
			//The loop below is used to fetch other info for all 4 semesters. In case data is null, simply
			//add two null values into the arraylist to denote that CGPA and total Credits is empty
			for (int i = 0; i < 4; i++) {
				try {
					OtherInfo[i] = summer[i].get(4);
				} catch (NullPointerException e) {
					OtherInfo[i] = new ArrayList<String>();
					OtherInfo[i].add(""); //examdate (worthless here)
					OtherInfo[i].add(""); //resultdate (worthless here)
					OtherInfo[i].add(""); //total credits
					OtherInfo[i].add(""); //cgpa
				}
			}

			PdfPCell cgpa_cell1 = new PdfPCell(new Phrase(cgpa + OtherInfo[0].get(3), f1));
			PdfPCell cgpa_cell2 = new PdfPCell(new Phrase(cgpa + OtherInfo[1].get(3), f1));
			PdfPCell cgpa_cell3 = new PdfPCell(new Phrase(cgpa + OtherInfo[2].get(3), f1));
			PdfPCell cgpa_cell4 = new PdfPCell(new Phrase(cgpa + OtherInfo[3].get(3), f1));

			PdfPCell tcredit_cell1 = new PdfPCell(new Phrase(
					total_creds + OtherInfo[0].get(2), f1)); // 40 ki jagah asli value aayegi
												// ... similarly neeche bhi
			PdfPCell tcredit_cell2 = new PdfPCell(new Phrase(total_creds + OtherInfo[1].get(2),
					f1));
			PdfPCell tcredit_cell3 = new PdfPCell(new Phrase(total_creds + OtherInfo[2].get(2),
					f1));
			PdfPCell tcredit_cell4 = new PdfPCell(new Phrase(total_creds + OtherInfo[3].get(2),
					f1));

			cgpa_cell1.setBorder(0); // border
			cgpa_cell2.setBorder(0);
			cgpa_cell3.setBorder(0);
			cgpa_cell4.setBorder(0);
			tcredit_cell1.setBorder(0);
			tcredit_cell2.setBorder(0);
			tcredit_cell3.setBorder(0);
			tcredit_cell4.setBorder(0);

			cgpa_cell1.setPaddingLeft(20f); // left padding
			cgpa_cell2.setPaddingLeft(25f);
			cgpa_cell3.setPaddingLeft(20f);
			cgpa_cell4.setPaddingLeft(25f);
			tcredit_cell1.setPaddingLeft(20f);
			tcredit_cell2.setPaddingLeft(25f);
			tcredit_cell3.setPaddingLeft(20f);
			tcredit_cell4.setPaddingLeft(25f);

			batchInt -= 4;
			// tables 1 & 2
			PdfPTable table1 = tableCreator(batchInt, summer[0]);
			PdfPTable table2 = tableCreator(batchInt + 1, summer[1]);

			PdfPCell t1 = new PdfPCell(table1);
			t1.setBorder(0);
			PdfPCell t2 = new PdfPCell(table2);
			t2.setBorder(0);
			PdfPTable mixtable1 = new PdfPTable(3);
			mixtable1.setWidthPercentage(100);
			mixtable1.setWidths(new float[] { 25f, 1f, 25f });
			mixtable1.addCell(t1);
			blank.setBorder(0);
			mixtable1.addCell(blank);
			mixtable1.addCell(t2);
			mixtable1.setSpacingBefore(8f);
			document.add(mixtable1);

			PdfPTable cgpa_credit1 = new PdfPTable(2);
			cgpa_credit1.addCell(cgpa_cell1);
			cgpa_credit1.addCell(cgpa_cell2);
			cgpa_credit1.addCell(tcredit_cell1);
			cgpa_credit1.addCell(tcredit_cell2);
			cgpa_credit1.setWidthPercentage(100);
			document.add(cgpa_credit1);

			// tables 3 & 4
			PdfPTable table3 = tableCreator(batchInt + 2, summer[2]);

			PdfPTable table4 = tableCreator(batchInt + 3, summer[3]);

			PdfPCell t3 = new PdfPCell(table3);
			t3.setBorder(0);
			PdfPCell t4 = new PdfPCell(table4);
			t4.setBorder(0);
			PdfPTable mixtable2 = new PdfPTable(3);
			mixtable2.setWidthPercentage(100);
			mixtable2.setWidths(new float[] { 25f, 1f, 25f });
			mixtable2.addCell(t3);
			blanks.setBorder(0);
			mixtable2.addCell(blanks);
			mixtable2.addCell(t4);
			mixtable2.setSpacingBefore(8f);
			document.add(mixtable2);

			PdfPTable cgpa_credit2 = new PdfPTable(2);
			cgpa_credit2.addCell(cgpa_cell3);
			cgpa_credit2.addCell(cgpa_cell4);
			cgpa_credit2.addCell(tcredit_cell3);
			cgpa_credit2.addCell(tcredit_cell4);
			cgpa_credit2.setWidthPercentage(100);
			document.add(cgpa_credit2);

			// grading system
			Paragraph heading = new Paragraph("GRADING SYSTEM", new Font(
					Font.FontFamily.TIMES_ROMAN, 10, Font.BOLD));
			heading.setAlignment(Element.ALIGN_CENTER);
			document.add(heading);

			// para1
			Paragraph detail = new Paragraph("", f3);
			detail.add("Each letter grade awarded to the student indicates the level of performance in a course and has a grade point for the"
					+ " purpose of computing semester grade point average (SGPA) and cumulative grade point average (CGPA) as given below:");
			detail.setIndentationLeft(20f);
			detail.setIndentationRight(20f);
			document.add(detail);

			// grade table
			PdfPTable grade_point = new PdfPTable(3);
			grade_point.setWidths(new float[] { 3f, 1f, 1f });
			grade_point.setWidthPercentage(90);
			grade_point.setSpacingBefore(8f);

			grade_point.addCell(new PdfPCell(new Paragraph(
					"Academic Performance", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("Grade", f3)));
			grade_point
					.addCell(new PdfPCell(new Paragraph("Grade Points", f3)));

			grade_point.addCell(new PdfPCell(new Paragraph("Outstanding", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("A+", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("10", f3)));

			grade_point.addCell(new PdfPCell(new Paragraph("Excellent", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("A", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("9", f3)));

			grade_point.addCell(new PdfPCell(new Paragraph("Very good", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("B+", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("8", f3)));

			grade_point.addCell(new PdfPCell(new Paragraph("Good", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("B", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("7", f3)));

			grade_point.addCell(new PdfPCell(new Paragraph("Average", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("C+", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("6", f3)));

			grade_point
					.addCell(new PdfPCell(new Paragraph("Below Average", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("C", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("5", f3)));

			grade_point.addCell(new PdfPCell(new Paragraph("Marginal", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("D", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("4", f3)));

			grade_point.addCell(new PdfPCell(new Paragraph("Fail", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("F", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("0", f3)));

			grade_point.addCell(new PdfPCell(new Paragraph("Audit Pass", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("AP", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("-", f3)));

			grade_point.addCell(new PdfPCell(new Paragraph("Audit Fail", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("AF", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("-", f3)));

			grade_point
					.addCell(new PdfPCell(new Paragraph("Satisfactory", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("S", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("-", f3)));

			grade_point.addCell(new PdfPCell(new Paragraph("Not Satisfactory",
					f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("NS", f3)));
			grade_point.addCell(new PdfPCell(new Paragraph("-", f3)));

			document.add(grade_point);

			// para2
			Paragraph para1 = new Paragraph("", f3);
			para1.setIndentationLeft(20f);
			para1.setIndentationRight(20f);
			para1.add("Semester grade point average (SGPA) is calculated as below for all courses (viz. m) in which the student has"
					+ " passed in the semester:");
			document.add(para1);

			Paragraph para2 = new Paragraph("", f3);
			para2.setAlignment(Element.ALIGN_CENTER);
			para2.add("SGPA = ");
			document.add(para2);

			Paragraph para3 = new Paragraph("", f3);
			para3.setIndentationLeft(20f);
			para3.setIndentationRight(20f);
			para3.add("Cumulative grade point average (CGPA) is calculated as below for all courses (viz. n) in which the student has"
					+ " passed till that semester:");
			document.add(para3);

			Paragraph para4 = new Paragraph("", f3);
			para4.setAlignment(Element.ALIGN_CENTER);
			para4.add("CGPA = ");
			document.add(para4);

			Paragraph para5 = new Paragraph("", f3);
			para5.setIndentationLeft(20f);
			para5.setIndentationRight(20f);
			para5.add("Ci denotes the credits assigned to ith course and Gi indicates the Grade Point Equivalent to the Letter Grade obtained"
					+ " by the student to the ith course.");
			document.add(para5);

			document.close();
		}

		catch (DocumentException ex) {
			Logger.getLogger(SummerGS.class.getName()).log(Level.SEVERE, null,
					ex);
		}

	}
}
