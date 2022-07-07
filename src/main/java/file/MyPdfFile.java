package file;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.color.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import connect.ConnectDemo;
import dto.ReportDTO;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MyPdfFile {

    private ReportDTO reportDTO;

    public static void main(String[] args) {
        MyPdfFile myPdfFile = new MyPdfFile();
        myPdfFile.createPdf();
    }

    public void createPdf() {

        ConnectDemo connect = new ConnectDemo();
        reportDTO = connect.getReportDTO();

        String path = "/home/ramz/Documents/demoTable.pdf";
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = new PdfWriter(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        float col = 280f;
        float[] columnWidth = {col, col};
        Table table = new Table(columnWidth);


        table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE);


        // add header
        table.addCell(new Cell().add("Report on " + LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("MM-dd")))
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setFontSize(30f)
                .setBorder(Border.NO_BORDER)
        );

        // add third table
        float[] itemInfoColWidth = {180, 180, 180};
        Table itemInfoTable = new Table(itemInfoColWidth);

        itemInfoTable.addCell(new Cell().add("Full name"));
        itemInfoTable.addCell(new Cell().add("Activity"));
        itemInfoTable.addCell(new Cell().add("Time (in min)"));

        reportDTO.getTeamReports().forEach(
                tr ->
                {
                    itemInfoTable.addCell(new Cell().add(tr.getTeamName()));
                    tr.getUserReports().forEach(ur -> {
                        itemInfoTable.addCell(new Cell().add(ur.getFullName()));
                        ur.getTasks().forEach(t -> {
                            t.getDescription();
                            t.getTimeInMinutes();
                        });
                    });

                }
        );

//        itemInfoTable.addCell(new Cell().add("Oleg"));
//        itemInfoTable.addCell(new Cell().add("Learn about Spring MVC"));
//        itemInfoTable.addCell(new Cell().add("200"));
//
//        itemInfoTable.addCell(new Cell().add("Anna"));
//        itemInfoTable.addCell(new Cell().add("Hibernate cache"));
//        itemInfoTable.addCell(new Cell().add("150"));


        document.add(table);

        // add gap between tables
        document.add(new Paragraph("\n\n"));

        document.add(itemInfoTable);

        document.close();
        System.out.println("pdf file created");
    }

//    private void addRows(PdfPTable table) {
//        table.addCell("Oleg");
//        table.addCell("Read about Spring-MVC");
//        table.addCell("90");
//    }

}
