import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.poi.hssf.record.HorizontalPageBreakRecord;

import com.itextpdf.awt.geom.Line2D;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.Barcode128;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfIndirectReference;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfStream;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64.InputStream;
import com.itextpdf.text.pdf.codec.Base64.OutputStream;
import com.itextpdf.text.pdf.draw.LineSeparator;

public class GenerateCL {
	static String semestername[] = { "1st", "2nd", "3rd", "4th", "5th", "6th",
			"7th", "8th" };
	static String month[] = { "", "January", "February", "March", "April",
			"May", "June", "July", "August", "September", "October",
			"November", "December" };

	static GenerateDGS obj[] = new GenerateDGS[8];
	static PdfPTable alltables[] = new PdfPTable[8];
	static PdfContentByte cb;

	public static void generate(String tablename, String rollno) {
		for (int i = 0; i < semestername.length; i++) {
			String tablenametouse = tablename;
			tablenametouse = tablenametouse + semestername[i];
			obj[i] = GenerateDGS.tablegenerator(tablenametouse, rollno,
					new int[] { 6, 29, 7, 6, 6 }, 8F, 1.5F, true);
		}
		Document my = new Document(PageSize.LEGAL, -60, -60, 10F, 20);
		try {
			PdfWriter writer = PdfWriter.getInstance(my, new FileOutputStream(
					"C:\\Users\\admin\\Desktop\\conTable.pdf"));

			my.open();
			cb = writer.getDirectContent();
			PdfPTable maintable = new PdfPTable(2);

			/* Creating the header for the Consolidated layout */

			PdfPTable header = new PdfPTable(3);
			header.setWidths(new int[] { 25, 80, 30 });

			PdfPTable details = new PdfPTable(1);
			PdfPCell blankcell=new PdfPCell(new Phrase(" "));
			blankcell.setBorder(0);
			details.addCell(blankcell);
			//details.addCell(blankcell);
			PdfPCell detailsdatacell = new PdfPCell(new Phrase(
					"ITM UNIVERSITY,GURGAON", FontFactory.getFont(
							FontFactory.TIMES_BOLD, 17F)));
			detailsdatacell.setHorizontalAlignment(Element.ALIGN_CENTER);
			detailsdatacell.setBorder(0);
			details.addCell(detailsdatacell);
			/*
			 * detailsdatacell = new PdfPCell(new Phrase("FOR"));
			 * detailsdatacell.setHorizontalAlignment(Element.ALIGN_CENTER);
			 * detailsdatacell.setBorder(0); details.addCell(detailsdatacell);
			 */
			detailsdatacell = new PdfPCell(new Phrase(
					"BACHELOR OF TECHNOLOGY,COMPUTER SCIENCE",
					FontFactory.getFont(FontFactory.TIMES_BOLD, 11F)));
			detailsdatacell.setHorizontalAlignment(Element.ALIGN_CENTER);
			detailsdatacell.setBorder(0);
			details.addCell(detailsdatacell);

			detailsdatacell = new PdfPCell(new Phrase(
					"CONSOLIDATED STATEMENT OF GRADES", FontFactory.getFont(
							FontFactory.TIMES_BOLD, 12F, BaseColor.RED)));
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
			image2.scalePercent(80, 80);
			studentphoto = new PdfPCell(image2);
			studentphoto.setFixedHeight(100F);
			studentphoto.setPaddingLeft(50F);
			studentphoto.setBorder(0);
			header.addCell(studentphoto);

			my.add(header);
			/* ends */

			Paragraph blank = new Paragraph(" ", FontFactory.getFont(
					FontFactory.TIMES_BOLD, 4F));
			my.add(blank);
			my.add(blank);my.add(blank);
			LineSeparator ls = new LineSeparator();
			ls.setLineWidth(10F);
			ls.setPercentage(80);
			ls.setLineColor(BaseColor.BLACK);
			my.add(ls);

			String fatherName, motherName, studentName;

			ArrayList<String> studentInfo = Selector.studentInfoFetcher(rollno);

			PdfPTable detailstable = GenerateCL.returndetailstable(rollno,
					studentInfo);
			my.add(blank);
			my.add(detailstable);

			/*
			 * studentName = studentInfo.get(0); motherName =
			 * studentInfo.get(1); fatherName = studentInfo.get(2);
			 * 
			 * /*Adding Mother Father name PdfPTable parentsdetail=new
			 * PdfPTable(2);
			 */

			/* getting the values of pdfptable from 8 obj objects */
			PdfPTable tables[] = new PdfPTable[8];
			PdfPCell tablecell;
			for (int j = 0; j < 8; j++) {
				tables[j] = obj[j].table;
				tablecell = new PdfPCell(tables[j]);
				tablecell.setBorder(0);
				tablecell.setPaddingLeft(5F);
				tablecell.setPaddingRight(5F);
				tablecell.setPaddingBottom(1F);
				tablecell.setPaddingTop(1F);
				maintable.addCell(tablecell);
			}

			my.add(maintable);
			my.add(blank);
			my.add(blank);
			PdfPTable p = persemtable.getpersemtable(obj);
			my.add(p);
			/*Generating division table--*/
			
			PdfPTable divisontable=new PdfPTable(4);
			
			PdfPCell division=new PdfPCell(new Phrase("Division",FontFactory.getFont(FontFactory.TIMES_ROMAN, 9F)));
			blankcell=new PdfPCell(new Phrase(""));
			blankcell.setBorderWidth(0F);
			divisontable.addCell(blankcell);divisontable.addCell(blankcell);divisontable.addCell(division);
			float lastsemcgpa=Float.parseFloat(obj[7].cgpa);
					
			if(lastsemcgpa>8.00F)
			division=new PdfPCell(new Phrase(" First Division",FontFactory.getFont(FontFactory.TIMES_ROMAN, 9F)));

			else
				division=new PdfPCell(new Phrase(" Second Division",FontFactory.getFont(FontFactory.TIMES_ROMAN, 9F)));
		divisontable.addCell(division);
		my.add(divisontable);
			
			
			PdfPTable bottompart = GenerateCL.bottompart(studentInfo.get(0),
					rollno, writer);
			my.add(blank);
			my.add(blank);
			my.add(blank);
			my.add(blank);
			my.add(blank);
			my.add(blank);
			my.add(blank);
			my.add(bottompart);
			my.add(blank);
			my.add(blank);
			my.add(blank);
			
			ls.setLineWidth(15F);
			my.add(ls);
			SummerGS.summerCreator(my, 2010, rollno, "cse");
			my.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static PdfPTable returndetailstable(String rollno,
			ArrayList<String> a) {
		PdfPTable data = new PdfPTable(2);
		PdfPCell roll = new PdfPCell(new Phrase("Enrolment No.   :  " + rollno,
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 9F)));
		roll.setHorizontalAlignment(Element.ALIGN_RIGHT);
		roll.setBorder(0);
		PdfPCell father = new PdfPCell(new Phrase("Fathers Name   : "
				+ a.get(2), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9F)));
		father.setBorder(0);
		PdfPCell mother = new PdfPCell(new Phrase("Mothers Name   :  "
				+ a.get(1), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9F)));
		mother.setHorizontalAlignment(Element.ALIGN_RIGHT);
		mother.setBorder(0);
		PdfPCell student = new PdfPCell(new Phrase("Name                :  "
				+ a.get(0), FontFactory.getFont(FontFactory.TIMES_ROMAN, 9F)));
		student.setBorder(0);
		data.addCell(student);
		data.addCell(roll);
		data.addCell(father);
		data.addCell(mother);
		return data;
	}

	public static PdfPTable bottompart(String name, String roll, PdfWriter pdf) {
		PdfPTable table = new PdfPTable(3);
		try {
			table.setWidths(new int[] { 30, 50, 30 });
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PdfPCell blank = new PdfPCell(new Phrase(" ", FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 7F)));
		PdfPCell checkedby = new PdfPCell(new Phrase("Checked by:",
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 7F)));
		checkedby.setHorizontalAlignment(Element.ALIGN_LEFT);
		checkedby.setBorder(0);
		table.addCell(checkedby);
		Barcode128 code = new Barcode128();
		code.setCode(roll);
		cb = pdf.getDirectContent();
		code.setGenerateChecksum(false);
		code.setFont(null);
		Image im = code.createImageWithBarcode(cb, null, null);
		PdfPCell barcode = new PdfPCell(im);
		barcode.setBorder(0);
		barcode.setHorizontalAlignment(Element.ALIGN_CENTER);

		checkedby = new PdfPCell(new Phrase("Controller of Examination",
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 8F)));
		checkedby.setHorizontalAlignment(Element.ALIGN_RIGHT);
		checkedby.setPaddingRight(20F);
		checkedby.setBorder(0);
		table.addCell(barcode);
		table.addCell(checkedby);
		blank.setBorder(0);
		table.addCell(blank);
	
		Date date = new Date();
		PdfPCell datecell = new PdfPCell(new Phrase("Dated :" + date.getDate()
				+ " " + month[date.getMonth() + 1] + " "
				+ (date.getYear() + 1900), FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 7F)));
		datecell.setBorder(0);
		datecell.setHorizontalAlignment(Element.ALIGN_CENTER);
		table.addCell(datecell);
		table.addCell(blank);
		return table;
	}

	public static void main(String... s) {
		GenerateCL.generate("mains2010cse", "09ITMG1518AU");
	}
}
