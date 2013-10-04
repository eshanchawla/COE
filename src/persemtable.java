import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class persemtable {
	static String semester[] = { "O", "I", "II", "III", "IV", "V", "VI", "VII",
			"VIII" };

	public static void main(String... s) {
		Document doc = new Document(PageSize.LEGAL, -60, -60, 20, 20);
		try {
			PdfWriter pdf = PdfWriter.getInstance(doc, new FileOutputStream(
					"C:\\users\\admin\\desktop\\persem.pdf"));
			doc.open();

			// PdfPTable res = persemtable.getpersemtable();
			// doc.add(res);
			doc.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static PdfPTable getpersemtable(GenerateDGS obj[]) {
		PdfPTable persemtable = new PdfPTable(16);
		try {
			persemtable.setWidths(new int[] { 9, 6, 9, 6, 9, 6, 9, 6, 9, 6, 9,
					6, 9, 6, 9, 6 });
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		PdfPCell cell1[] = new PdfPCell[8];

		// (new Phrase("Semester "));
		PdfPCell cell2 = new PdfPCell(new Phrase("Credit Earned",
				FontFactory.getFont(FontFactory.TIMES_ROMAN, 6.5F)));
		cell2.setRowspan(2);
		cell2.setBorderWidthLeft(0.1F);
		cell2.setBorderWidthRight(0.1F);
		cell2.setBorderWidthBottom(0F);
		cell2.setHorizontalAlignment(Element.ALIGN_CENTER);

		for (int i = 0; i < 8; i++) {
			cell1[i] = new PdfPCell(new Phrase("Sem." + semester[i + 1],
					FontFactory.getFont(FontFactory.TIMES_ROMAN, 7F)));
			cell1[i].setHorizontalAlignment(Element.ALIGN_CENTER);
		}

		for (int i = 0; i < 8; i++) {
			persemtable.addCell(cell1[i]);
			persemtable.addCell(cell2);
		}
		PdfPTable sgpacgpaheading = new PdfPTable(2);
		PdfPCell sgpa = new PdfPCell(new Phrase("SGPA", FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 6F)));
		sgpa.setBorder(0);
		PdfPCell cgpa = new PdfPCell(new Phrase("CGPA", FontFactory.getFont(
				FontFactory.TIMES_ROMAN, 6F)));
		cgpa.setBorder(0);
		sgpacgpaheading.addCell(sgpa);
		sgpacgpaheading.addCell(cgpa);
		PdfPCell converttableintocell = new PdfPCell(sgpacgpaheading);
		converttableintocell.setBorderWidthBottom(0F);
		for (int i = 0; i < 8; i++) {
			persemtable.addCell(converttableintocell);
		}
		/* adding the sgpa and cgpa for each sem */
		for (int i = 0; i < 8; i++) {
			sgpa = new PdfPCell(new Phrase(obj[i].sgpa, FontFactory.getFont(
					FontFactory.TIMES_ROMAN, 6.5F)));
			sgpa.setBorder(0);
			sgpa.setHorizontalAlignment(Element.ALIGN_CENTER);
			cgpa = new PdfPCell(new Phrase(obj[i].cgpa, FontFactory.getFont(
					FontFactory.TIMES_ROMAN, 6.5F)));
			cgpa.setBorder(0);
			cgpa.setHorizontalAlignment(Element.ALIGN_CENTER);

			cell2 = new PdfPCell(new Phrase(obj[i].creditsearned,
					FontFactory.getFont(FontFactory.TIMES_ROMAN,7F)));
			cell2.setBorderWidthLeft(0.1F);
			cell2.setBorderWidthRight(0.1F);
			cell2.setBorderWidthTop(0.2F);
			cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			sgpacgpaheading = new PdfPTable(2);
			sgpacgpaheading.addCell(sgpa);
			sgpacgpaheading.addCell(cgpa);
			converttableintocell = new PdfPCell(sgpacgpaheading);
			converttableintocell.setBorderWidthBottom(0.2F);
			persemtable.addCell(converttableintocell);
			persemtable.addCell(cell2);
		}
		return persemtable;
	}

}
