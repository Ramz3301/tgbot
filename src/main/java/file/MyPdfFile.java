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
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.VerticalAlignment;
import connect.ConnectDemo;
import dto.ReportDTO;
import dto.TeamReportDTO;
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Class for create PDF file
 */

@Slf4j
public class MyPdfFile implements CreateFile {

    private ReportDTO reportDTO;

    /**
     * Create pdf file for report
     */
    public void createFile() {
        ConnectDemo connect = new ConnectDemo();
        reportDTO = connect.getReportDTO();

        String path = "/home/ramz/Documents/report.pdf";
        PdfWriter pdfWriter = null;
        try {
            pdfWriter = new PdfWriter(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        //280px for column in file
        float col = 280f;
        float[] columnWidth = {col, col};
        Table table = new Table(columnWidth);
        table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE);

        // add header
        createTableHeader(table);

        // add second table
        Table itemInfoTable = addTableColumns();

        // get report
        List<TeamReportDTO> teams = reportDTO.getTeamReports();

        //get all information from report about team and users
        teams.forEach(
                tr -> {
                    tr.getUserReports().forEach(ur -> {
                        itemInfoTable.addCell(new Cell().add(ur.getFullName()).setFontSize(16));
                        for (int i = 0; i < ur.getTasks().size(); i++) {
                            itemInfoTable.addCell(new Cell().add(ur.getTasks().get(i).getDescription()));
                            itemInfoTable.addCell(new Cell().add(String.valueOf(ur.getTasks().get(i).getTimeInMinutes())));

                            if (i != ur.getTasks().size() - 1) {
                                itemInfoTable.addCell(new Cell().add(""));
                            }
                        }

                    });
                }
        );

        String teamName = reportDTO.getTeamReports().get(0).getTeamName();

        document.add(table);
        // add gap between tables

        document.add(new Paragraph("\n\n"));
        Paragraph paragraph = new Paragraph("TEAM " + teamName)
//                .setFontColor(new DeviceRgb(63, 169, 219))
                .setFontColor(Color.BLACK)
                .setMargins(0, 0, 0 , 230);
        document.add(paragraph);
//        document.add(new Paragraph("\n"));
        document.add(itemInfoTable);

        document.close();
        System.out.println("pdf file created");
    }

    private Table addTableColumns() {
        float[] itemInfoColWidth = {180f, 180f, 180f};
        Table itemInfoTable = new Table(itemInfoColWidth).setTextAlignment(TextAlignment.CENTER);

        itemInfoTable.addCell(new Cell().add("Name").setBold());
        itemInfoTable.addCell(new Cell().add("Activity").setBold());
        itemInfoTable.addCell(new Cell().add("Time (in min)").setBold());
        return itemInfoTable;
    }

    private void createTableHeader(Table table) {
        table.addCell(new Cell().add("Report on " + LocalDate.now()
                        .format(DateTimeFormatter.ofPattern("MMMM d")))
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setFontSize(30f)
                .setBorder(Border.NO_BORDER)
        );
    }

}
