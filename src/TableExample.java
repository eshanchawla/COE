import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.TabStop.Alignment;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.net.URL;

import org.apache.poi.ss.usermodel.HorizontalAlignment;

public class TableExample {
    public static void main(String[] args) {
        Document document = new Document(PageSize.LEGAL,20,25,20,10);

        try {
            PdfWriter.getInstance(document,
                new FileOutputStream("C:\\Users\\Admin\\Desktop\\HelloWorld-Table.pdf"));
    
            document.open();
            
Image image1=Image.getInstance("c:\\users\\admin\\desktop\\title.jpg");
            Image image2 = Image.getInstance("c:\\users\\admin\\desktop\\logo.jpg");
image2.setIndentationLeft(100f);

PdfPTable headerpart=new PdfPTable(3);
headerpart.setWidths(new int[]{3,10,3});
PdfPTable main=new PdfPTable(2);
main.setWidths(new int[]{120,120});
Paragraph header=new Paragraph("                       ITM UNIVERSITY\n                                                  Gurgaon,Haryana",FontFactory.getFont(FontFactory.COURIER_BOLD,20f));
PdfPCell imagecell=new PdfPCell(image2);
imagecell.setBorder(0);
PdfPCell imagetitlecell=new PdfPCell(image1);
imagetitlecell.setBorder(0);
imagetitlecell.setBackgroundColor(BaseColor.WHITE);
headerpart.addCell(imagecell);
headerpart.addCell(imagetitlecell);
headerpart.addCell(imagecell);
document.add(headerpart);


PdfPCell blank=new PdfPCell(new Phrase(" "));
PdfPTable name=new PdfPTable(2);
blank.setBorder(0);
name.setWidths(new int[]{16,22});
name.addCell(blank);
name.addCell(blank);

PdfPCell col=new PdfPCell(new Phrase("	Name:abc studenthdhdhdhdhd",FontFactory.getFont(FontFactory.COURIER,11F)));
col.setBorder(0);
name.addCell(col);
col=new PdfPCell(new Phrase("                  Enrollment no:10csu057",FontFactory.getFont(FontFactory.COURIER,11F)));
col.setBorder(0);
name.addCell(col);
col=new PdfPCell(new Phrase("	Fathers Name:abcc Fatherdhdhdhdhdhd",FontFactory.getFont(FontFactory.COURIER,11F)));
col.setBorder(0);
name.addCell(col);
col=new PdfPCell(new Phrase("                  Mother Name:abcc Motherhdhdha",FontFactory.getFont(FontFactory.COURIER,11F)));
col.setBorder(0);
name.addCell(col);
name.addCell(blank);
name.addCell(blank);

document.add(name);
PdfPTable table = new PdfPTable(5); // 5 columns.
            table.setWidths(new int[]{2,8,2,2,2});
            PdfPCell empty = new PdfPCell(new Phrase(" ",FontFactory.getFont(FontFactory.defaultEncoding,10F)));
            PdfPCell semheader=new PdfPCell(new Paragraph("Semester 1",FontFactory.getFont(FontFactory.defaultEncoding, 10F)));
            semheader.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(empty);
            table.addCell(semheader);
            table.addCell(empty);
            table.addCell(empty);
            table.addCell(empty);
            
            PdfPCell sno=new PdfPCell(new Phrase("S.NO.",FontFactory.getFont(FontFactory.defaultEncoding,10F)));
            sno.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell course=new PdfPCell(new Phrase("Course",FontFactory.getFont(FontFactory.defaultEncoding,10F)));
            course.setHorizontalAlignment(Element.ALIGN_CENTER);
            PdfPCell code=new PdfPCell(new Phrase("Code",FontFactory.getFont(FontFactory.defaultEncoding,10F)));
            code.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell credits=new PdfPCell(new Phrase("Credits",FontFactory.getFont(FontFactory.defaultEncoding,10F)));
            credits.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            PdfPCell grade=new PdfPCell(new Phrase("Grade",FontFactory.getFont(FontFactory.defaultEncoding,10F)));
            grade.setHorizontalAlignment(Element.ALIGN_CENTER);
           
            table.addCell(sno);
            table.addCell(course);
            table.addCell(code);
            table.addCell(credits);
            table.addCell(grade);
            
            
            PdfPCell testsno=new PdfPCell(new Phrase("1",FontFactory.getFont(FontFactory.defaultEncoding,10F)));
           testsno.setHorizontalAlignment(Element.ALIGN_CENTER);
           testsno.setPadding(0F); 
           testsno.setFixedHeight(11F);
          testsno.setBorderWidthTop(0);
          testsno.setBorderWidthBottom(0);
          testsno.setPaddingBottom(0);
          testsno.setPaddingTop(0);  
         
          
          PdfPCell testcourse=new PdfPCell(new Phrase("Basics of Computer & C Programming",FontFactory.getFont(FontFactory.defaultEncoding,10F)));
          testcourse.setFixedHeight(11F);
          testcourse.setBorderWidthTop(0);
          testcourse.setBorderWidthBottom(0);
          testcourse.setPadding(0F); 
          testcourse.setPaddingBottom(0);
          testcourse.setPaddingTop(0);  
         
          
          
          PdfPCell testcode=new PdfPCell(new Phrase("HML 101",FontFactory.getFont(FontFactory.defaultEncoding,10F)));
            testcode.setHorizontalAlignment(Element.ALIGN_CENTER);
            testcode.setFixedHeight(11F);
            testcode.setBorderWidthTop(0);
            testcode.setBorderWidthBottom(0);
            testcode.setPaddingBottom(0);
            testcode.setPaddingTop(0);
            
            PdfPCell testcredits=new PdfPCell(new Phrase("5",FontFactory.getFont(FontFactory.defaultEncoding,10F)));
            testcredits.setHorizontalAlignment(Element.ALIGN_CENTER);
            testcredits.setFixedHeight(11F);
            testcredits.setBorderWidthTop(0);
            testcredits.setBorderWidthBottom(0);
            testcredits.setPadding(0F); 
            testcredits.setPaddingBottom(0);
            testcredits.setPaddingTop(0);  
           
            PdfPCell testgrade=new PdfPCell(new Phrase("B+",FontFactory.getFont(FontFactory.defaultEncoding,10F)));
            testgrade.setHorizontalAlignment(Element.ALIGN_CENTER);
            testgrade.setFixedHeight(11F);
            testgrade.setBorderWidthTop(0);
            testgrade.setBorderWidthBottom(0);
            testgrade.setPadding(0F); 
            testgrade.setPaddingBottom(0);
            testgrade.setPaddingTop(0);  
           
             table.addCell(testsno);
            table.addCell(testcourse);
            table.addCell(testcode);
            table.addCell(testcredits);
            table.addCell(testgrade);
            
            table.addCell(testsno);
            table.addCell(testcourse);
            table.addCell(testcode);
            table.addCell(testcredits);
            table.addCell(testgrade);
            
            table.addCell(testsno);
            table.addCell(testcourse);
            table.addCell(testcode);
            table.addCell(testcredits);
            table.addCell(testgrade);
            
            table.addCell(testsno);
            table.addCell(testcourse);
            table.addCell(testcode);
            table.addCell(testcredits);
            table.addCell(testgrade);
            
            table.addCell(testsno);
            table.addCell(testcourse);
            table.addCell(testcode);
            table.addCell(testcredits);
            table.addCell(testgrade);
            
            
            table.addCell(testsno);
            table.addCell(testcourse);
            table.addCell(testcode);
            table.addCell(testcredits);
            table.addCell(testgrade);
            
            
            table.addCell(testsno);
            table.addCell(testcourse);
            table.addCell(testcode);
            table.addCell(testcredits);
            table.addCell(testgrade);
            
            table.addCell(testsno);
            table.addCell(testcourse);
            table.addCell(testcode);
            table.addCell(testcredits);
            table.addCell(testgrade);
            
            table.addCell(testsno);
            table.addCell(testcourse);
            table.addCell(testcode);
            table.addCell(testcredits);
            table.addCell(testgrade);
            
            
            
            testsno.setBorderWidthBottom(1);

            testcourse.setBorderWidthBottom(1);
            

            testcredits.setBorderWidthBottom(1);
            

            testgrade.setBorderWidthBottom(1);
            

            testcode.setBorderWidthBottom(1);
            
            table.addCell(testsno);
            table.addCell(testcourse);
            table.addCell(testcode);
            table.addCell(testcredits);
            table.addCell(testgrade);
            
            
            
            
            
            PdfPCell tb=new PdfPCell(table);
            tb.setPaddingLeft(25F);
            tb.setPaddingRight(25F);
            tb.setPaddingBottom(10F);
            tb.setPaddingTop(1F);
            tb.setBorder(0);
            
            main.addCell(tb);
            main.addCell(tb);
            
            main.addCell(tb);
            main.addCell(tb);
            
            main.addCell(tb);
            main.addCell(tb);
            
            main.addCell(tb);
            main.addCell(tb);
            
            document.add(main);
            
PdfPTable cgpa=new PdfPTable(2);
PdfPCell cgpacell=new PdfPCell(new Phrase("CGPA :9.17"));
PdfPCell division=new PdfPCell(new Phrase("Division :First Class"));

division.setHorizontalAlignment(Element.ALIGN_RIGHT);
cgpa.addCell(blank);
cgpa.addCell(blank);
cgpa.addCell(cgpacell);
cgpa.addCell(division);
document.add(cgpa);
            document.close();
        } catch(Exception e){

        }
    }
}