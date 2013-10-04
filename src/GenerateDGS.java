import java.io.FileOutputStream;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.Connection;

public class GenerateDGS {
	static String semester[] = { "O", "I", "II", "III", "IV", "V", "VI", "VII",
			"VIII" };
	static String month[] = { "", "January", "February", "March", "April",
			"May", "June", "July", "August", "September", "October",
			"November", "December" };

	/* object for returning the table and cgpa credits sgpa */
	String cgpa, creditsearned, sgpa;
	String resultdate;
	PdfPTable table;
	String examdate;
	static int variableusedforleavingappropriatespace = 0;// will be set by
															// number of rows in
															// table;

	public static void main(String... s) {
		String rollno = "09ITMG15s17AU"; // yaha roll number aur table name
										// aayenge UI se
		String tableName = "mains2010cse5th";
		generatelayoutfordgs(tableName, rollno);
	}

	public static void generatelayoutfordgs(String tablekafullname,
			String rollno) {
		Document doc = new Document(PageSize.A4, -20, 20, 100, 40);
		try {
			int arrayindex = Integer
					.parseInt(Character.toString(tablekafullname
							.charAt(tablekafullname.length() - 3)));

			GenerateDGS obj = tablegenerator("mains2010cse3rd", rollno,
					new int[] { 10, 75, 18, 15, 15 }, 11F, 2F, false);
			PdfPTable recordstable = obj.table;

			Paragraph blank = new Paragraph(" ");// empty phrase for adding
													// blank spaces

			PdfPTable header = new PdfPTable(3);
			header.setWidths(new int[] { 30, 100, 30 });

			PdfPTable details = new PdfPTable(1);
			PdfPCell detailsdatacell = new PdfPCell(new Phrase(
					"UNDER CREDIT BASED SYSTEM"));
			detailsdatacell.setHorizontalAlignment(Element.ALIGN_CENTER);
			detailsdatacell.setBorder(0);
			details.addCell(detailsdatacell);

			detailsdatacell = new PdfPCell(new Phrase("FOR"));
			detailsdatacell.setHorizontalAlignment(Element.ALIGN_CENTER);
			detailsdatacell.setBorder(0);
			details.addCell(detailsdatacell);

			detailsdatacell = new PdfPCell(new Phrase("BACHELOR OF TECHNOLOGY"));
			detailsdatacell.setHorizontalAlignment(Element.ALIGN_CENTER);
			detailsdatacell.setBorder(0);
			details.addCell(detailsdatacell);

			detailsdatacell = new PdfPCell(new Phrase("Automobile Engineering"));
			detailsdatacell.setHorizontalAlignment(Element.ALIGN_CENTER);
			detailsdatacell.setBorder(0);
			details.addCell(detailsdatacell);

			/* getting the month for exam date */
			int index = Integer.parseInt(obj.examdate.substring(3, 4));

			detailsdatacell = new PdfPCell(new Phrase(semester[arrayindex]
					+ " Semester Examination "
					+ utilitymethods.month(obj.examdate)));
			// + month[index]
			// + ","
			// + obj.examdate.substring(obj.examdate.length()-4,
			// obj.examdate.length())));

			detailsdatacell.setHorizontalAlignment(Element.ALIGN_CENTER);
			detailsdatacell.setBorder(0);
			details.addCell(detailsdatacell);

			PdfPCell generalheader = new PdfPCell(details);
			generalheader.setBorder(0);
			Image image2 = Image
					.getInstance("c:\\users\\admin\\desktop\\logo.jpg");
			// image2.setIndentationLeft(100f);
			PdfPCell studentphoto = new PdfPCell(image2);
			studentphoto.setBorder(0);

			header.addCell(studentphoto);
			header.addCell(generalheader);

			image2 = Image
					.getInstance("c:\\users\\admin\\desktop\\11MBA001.jpg");
			studentphoto = new PdfPCell(image2);
			studentphoto.setFixedHeight(100F);
			studentphoto.setPaddingLeft(50F);
			studentphoto.setBorder(0);
			header.addCell(studentphoto);

			// Start: getting student info
			String fatherName, motherName, studentName;

			ArrayList<String> studentInfo = Selector.studentInfoFetcher(rollno);

			studentName = studentInfo.get(0);
			motherName = studentInfo.get(1);
			fatherName = studentInfo.get(2);
			// end: fetched all info in respective strings

			/* Table for Name of student and baap ka naam and ma ka naam */
			PdfPCell blankcell = new PdfPCell(new Phrase(" "));
			blankcell.setBorder(0);
			PdfPTable name = new PdfPTable(2);
			name.setWidths(new int[] { 45, 55 });
			name.addCell(blankcell);
			name.addCell(blankcell);

			PdfPCell col = new PdfPCell(new Phrase("	Name: " + studentName,
					FontFactory.getFont(FontFactory.TIMES_BOLD, 11F)));
			col.setBorder(0);
			name.addCell(col);

			col = new PdfPCell(new Phrase("Enrollment no: " + rollno,
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 11F)));
			col.setPaddingLeft(60F);
			col.setBorder(0);
			name.addCell(col);

			col = new PdfPCell(new Phrase("	Fathers Name: " + fatherName,
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 11F)));
			col.setBorder(0);
			name.addCell(col);

			col = new PdfPCell(new Phrase("Mother Name: " + motherName,
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 11F)));
			col.setPaddingLeft(60F);
			col.setBorder(0);
			name.addCell(col);
			name.addCell(blankcell);
			name.addCell(blankcell);

			@SuppressWarnings("unused")
			PdfWriter wr = PdfWriter.getInstance(doc, new FileOutputStream(
					"c:\\users\\admin\\desktop\\functiontest.pdf"));
			doc.open();

			doc.add(header);
			doc.add(blank);
			doc.add(blank);
			doc.add(name);
			doc.add(blank);
			doc.add(blank);

			doc.add(recordstable);

			PdfPTable sgpacgpasemtable = new PdfPTable(1);
			sgpacgpasemtable.setWidths(new int[] { 40 });

			PdfPCell sem = new PdfPCell(new Phrase("SEMESTER "
					+ semester[arrayindex], FontFactory.getFont(
					FontFactory.TIMES_BOLD, 11F)));
			sem.setBorder(0);
			PdfPCell totalcredits = new PdfPCell(new Phrase(
					"TOTAL CREDITS EARNED UPTO " + semester[arrayindex]
							+ " Semester: " + obj.creditsearned,
					FontFactory.getFont(FontFactory.TIMES_BOLD, 11F)));
			totalcredits.setBorder(0);
			PdfPCell semestergradepoint = new PdfPCell(new Phrase(
					"SEMESTER GRADE POINT AVERAGE (SGPA) : " + obj.sgpa));
			semestergradepoint.setBorder(0);
			PdfPCell cumulativegradepoint = new PdfPCell(new Phrase(
					"CUMULATIVE GRADE POINT AVERAGE (CGPA) : " + obj.cgpa));
			cumulativegradepoint.setBorder(0);

			/* getting month of result date */

			DateFormat df = new SimpleDateFormat("mm/dd/yyyy");
			// index = Integer.parseInt(obj.resultdate.substring(3,4));
			Date result = df.parse(obj.resultdate);
			System.out.println("date=" + result.toString());
			index = result.getMonth() + 1;
			// System.out.print("index = "+index);
			PdfPCell resultdate = new PdfPCell(new Phrase("Dated : "
					+ utilitymethods.month(obj.resultdate)));

			// + month[index]
			// + ","
			// + obj.resultdate.substring(obj.resultdate.length()-4,
			// obj.resultdate.length()))); //last 4 characters are year
			resultdate.setBorder(0);
			sgpacgpasemtable.addCell(sem);
			sgpacgpasemtable.addCell(totalcredits);
			sgpacgpasemtable.addCell(semestergradepoint);
			sgpacgpasemtable.addCell(cumulativegradepoint);
			sgpacgpasemtable.addCell(resultdate);
			while (variableusedforleavingappropriatespace < 15) {
				doc.add(blank);
				variableusedforleavingappropriatespace++;
				System.out.println(variableusedforleavingappropriatespace);
			}
			/* to be changed heree */

			doc.add(sgpacgpasemtable);
			doc.close();
		} 
		catch(NullPointerException e){
			System.out.print("Error!! Roll number does not exist in given table");
		}
		catch(IndexOutOfBoundsException e){
			System.out.print("Error!! Insufficient information to generate DGS.");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static GenerateDGS tablegenerator(String tablename, String rollno,
			int a[], float fontsize, float subtractby, boolean consolidatedornot) throws NullPointerException, IndexOutOfBoundsException {
		PdfPTable recordstable = new PdfPTable(5);
		GenerateDGS obj = new GenerateDGS();
		try {
			recordstable.setWidths(a);
			/* header for the table */
			if (consolidatedornot == true) {
				PdfPCell blankcellone = new PdfPCell(new Phrase(" ",
						FontFactory.getFont(FontFactory.HELVETICA, fontsize
								- subtractby)));
				blankcellone.setBorderWidthBottom(0);
			//	blankcellone.setBorderWidthTop(0);
				blankcellone.setBorderWidthRight(0.5F);

				PdfPCell blankcelltwo = new PdfPCell(new Phrase(
						"Semester : "
								+ semester[Integer.parseInt(Character
										.toString(tablename.charAt(tablename
												.length() - 3)))],
						FontFactory.getFont(FontFactory.HELVETICA, fontsize
								- subtractby)));
				blankcelltwo.setHorizontalAlignment(Element.ALIGN_CENTER);
				blankcelltwo.setBorderWidthBottom(0);
			//	blankcelltwo.setBorderWidthTop(0);
				blankcelltwo.setBorderWidthLeft(0);
				blankcelltwo.setBorderWidthRight(0.5F);

				PdfPCell blankcellthree = new PdfPCell(new Phrase(" ",
						FontFactory.getFont(FontFactory.HELVETICA, fontsize
								- subtractby)));
				blankcellthree.setBorderWidthBottom(0);
			//	blankcellthree.setBorderWidthTop(0);
				blankcellthree.setBorderWidthLeft(0F);
				blankcellthree.setBorderWidthRight(0);

				PdfPCell blankcellfour = new PdfPCell(new Phrase(" ",
						FontFactory.getFont(FontFactory.HELVETICA, fontsize
								- subtractby)));
				blankcellfour.setBorderWidthBottom(0);
				blankcellfour.setBorderWidthTop(0.5F);

				PdfPCell blankcellfive = new PdfPCell(new Phrase(" ",
						FontFactory.getFont(FontFactory.HELVETICA, fontsize
								- subtractby)));
				blankcellfive.setBorderWidthBottom(0);
	//			blankcellfive.setBorderWidthTop(0);
				blankcellfive.setBorderWidthLeft(0);

				recordstable.addCell(blankcellone);
				recordstable.addCell(blankcelltwo);
				recordstable.addCell(blankcellthree);
				recordstable.addCell(blankcellfour);
				recordstable.addCell(blankcellfive);
			}
			PdfPCell sno = new PdfPCell(new Phrase("S.no.",
					FontFactory.getFont(FontFactory.TIMES_ROMAN, fontsize)));
			sno.setPaddingTop(5F);
			sno.setPaddingBottom(5F);
			sno.setHorizontalAlignment(Element.ALIGN_CENTER);
			sno.setBorderWidthBottom(0.5F);

			PdfPCell course = new PdfPCell(new Phrase("Course Name",
					FontFactory.getFont(FontFactory.TIMES_ROMAN, fontsize)));
			course.setPaddingTop(5F);
			course.setPaddingBottom(5F);
			course.setBorderWidthLeft(0F);

			PdfPCell code = new PdfPCell(new Phrase("Code",
					FontFactory.getFont(FontFactory.TIMES_ROMAN, fontsize)));
			code.setHorizontalAlignment(Element.ALIGN_CENTER);
			code.setPaddingTop(5F);
			code.setPaddingBottom(5F);
			code.setBorderWidthBottom(0.5F);
			code.setBorderWidthRight(0);
			code.setBorderWidthLeft(0);

			PdfPCell credits = new PdfPCell(new Phrase("Credits",
					FontFactory.getFont(FontFactory.TIMES_ROMAN, fontsize)));
			credits.setHorizontalAlignment(Element.ALIGN_CENTER);
			credits.setPaddingTop(5F);
			credits.setPaddingBottom(5F);
			credits.setBorderWidthTop(0.5F);
			credits.setBorderWidthBottom(0.1F);

			PdfPCell grade = new PdfPCell(new Phrase("Grade",
					FontFactory.getFont(FontFactory.TIMES_ROMAN, fontsize)));
			grade.setHorizontalAlignment(Element.ALIGN_CENTER);
			grade.setPaddingTop(5F);
			grade.setPaddingBottom(5F);
			grade.setBorderWidthLeft(0);

			recordstable.addCell(sno);
			recordstable.addCell(course);
			recordstable.addCell(code);
			recordstable.addCell(credits);
			recordstable.addCell(grade);

			ArrayList<ArrayList<String>> s = Selector.dataFetcher(rollno,
					tablename);

			ArrayList<String> courseCode = s.get(0);
			ArrayList<String> courseName = s.get(1);
			ArrayList<String> courseCredits = s.get(2);
			ArrayList<String> courseGrades = s.get(3);

			ArrayList<String> OtherInfo = s.get(4);

			obj.resultdate = OtherInfo.get(0);
			obj.examdate = OtherInfo.get(1);
			obj.creditsearned = OtherInfo.get(2);
			obj.sgpa = OtherInfo.get(3);
			obj.cgpa = OtherInfo.get(4);

			for (int i = 0; i < courseCode.size(); i++) {

				variableusedforleavingappropriatespace++;// simply counts number
															// of rows in table;
				sno = new PdfPCell(new Phrase(Integer.toString(i + 1),
						FontFactory.getFont(FontFactory.TIMES_ROMAN, fontsize
								- subtractby)));
				sno.setBorderWidthTop(0);
				sno.setBorderWidthBottom(0);
				sno.setBorderWidthRight(0.5F);

				sno.setHorizontalAlignment(Element.ALIGN_CENTER);

				course = new PdfPCell(new Phrase(courseName.get(i),
						FontFactory.getFont(FontFactory.HELVETICA, fontsize
								- subtractby)));
				course.setBorderWidthTop(0);
				course.setBorderWidthBottom(0);
				course.setBorderWidthLeft(0);
				course.setBorderWidthRight(0.5F);

				code = new PdfPCell(new Phrase(courseCode.get(i)
						.substring(0, 3)
						+ " "
						+ courseCode.get(i).substring(3, 6),
						FontFactory.getFont(FontFactory.HELVETICA, fontsize
								- subtractby)));
				code.setHorizontalAlignment(Element.ALIGN_CENTER);
				code.setBorderWidthTop(0);
				code.setBorderWidthBottom(0);
				code.setBorderWidthLeft(0F);
				code.setBorderWidthRight(0);

				credits = new PdfPCell(new Phrase(courseCredits.get(i),
						FontFactory.getFont(FontFactory.TIMES_BOLD, fontsize
								- subtractby)));
				credits.setHorizontalAlignment(Element.ALIGN_CENTER);
				credits.setBorderWidthTop(0);
				credits.setBorderWidthBottom(0);

				String gradestring = courseGrades.get(i);
				gradestring = "     " + gradestring;
				grade = new PdfPCell(new Phrase(gradestring,
						FontFactory.getFont(FontFactory.TIMES_BOLD, fontsize
								- subtractby)));
				grade.setBorderWidthTop(0);
				grade.setBorderWidthBottom(0);
				grade.setHorizontalAlignment(Element.ALIGN_LEFT);
				grade.setBorderWidthLeft(0);

				recordstable.addCell(sno);
				recordstable.addCell(course);
				recordstable.addCell(code);
				recordstable.addCell(credits);
				recordstable.addCell(grade);

			}
			/* for adding empty space at the end */
			if (consolidatedornot == true) {
				PdfPCell blankcellone = new PdfPCell(new Phrase(" ",
						FontFactory.getFont(FontFactory.HELVETICA, fontsize
								- subtractby)));
				blankcellone.setBorderWidthBottom(0);
				blankcellone.setBorderWidthTop(0);
				blankcellone.setBorderWidthRight(0.5F);

				PdfPCell blankcelltwo = new PdfPCell(new Phrase("",
						FontFactory.getFont(FontFactory.HELVETICA, fontsize
								- subtractby)));
				blankcelltwo.setBorderWidthBottom(0);
				blankcelltwo.setBorderWidthTop(0);
				blankcelltwo.setBorderWidthLeft(0);
				blankcelltwo.setBorderWidthRight(0.5F);

				PdfPCell blankcellthree = new PdfPCell(new Phrase(" ",
						FontFactory.getFont(FontFactory.HELVETICA, fontsize
								- subtractby)));
				blankcellthree.setBorderWidthBottom(0);
				blankcellthree.setBorderWidthTop(0);
				blankcellthree.setBorderWidthLeft(0F);
				blankcellthree.setBorderWidthRight(0);

				PdfPCell blankcellfour = new PdfPCell(new Phrase(" ",
						FontFactory.getFont(FontFactory.HELVETICA, fontsize
								- subtractby)));
				blankcellfour.setBorderWidthBottom(0);
				blankcellfour.setBorderWidthTop(0);

				PdfPCell blankcellfive = new PdfPCell(new Phrase(" ",
						FontFactory.getFont(FontFactory.HELVETICA, fontsize
								- subtractby)));
				blankcellfive.setBorderWidthBottom(0);
				blankcellfive.setBorderWidthTop(0);
				blankcellfive.setBorderWidthLeft(0);

				int h = courseCode.size();
				while (h < 13) {
					recordstable.addCell(blankcellone);
					recordstable.addCell(blankcelltwo);
					recordstable.addCell(blankcellthree);
					recordstable.addCell(blankcellfour);
					recordstable.addCell(blankcellfive);
					System.out.println(h);
					h++;
				}
			}

			PdfPCell blankcell = new PdfPCell(new Phrase(""));
			blankcell.setBorderWidthTop(0);
			blankcell.setBorderWidthLeft(0);
			blankcell.setBorderWidthRight(0);

			recordstable.addCell(blankcell);
			recordstable.addCell(blankcell);
			recordstable.addCell(blankcell);
			recordstable.addCell(blankcell);
			recordstable.addCell(blankcell);

		} catch (DocumentException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		obj.table = recordstable;
		return obj;

	}
}
