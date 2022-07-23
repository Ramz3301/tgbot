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
public class MyPdfFile implements MyFile {

    private ReportDTO reportDTO;
    private PdfWriter pdfWriter;

    /**
     * Create pdf file for report
     */
    public void createFile() {
        log.info("createFile()");
        ConnectDemo connect = new ConnectDemo();
        reportDTO = connect.getReportDTO();

        String path = "C:\\Users\\ramzk\\Documents\\report.pdf";
//        PdfWriter pdfWriter;
        try {
            pdfWriter = new PdfWriter(path);
        } catch (FileNotFoundException e) {
//            throw new RuntimeException(e);
            e.printStackTrace();
            log.error("File not found");
        }

        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        Document document = new Document(pdfDocument);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        //280px for column in file
        Table table = addTableWidth();

        // add header
        createTableHeader(table);

        // add second table
        Table itemInfoTable = addTableColumns();

        // get report
        List<TeamReportDTO> teams = reportDTO.getTeamReports();

        //get all information from report about team and users
        getReportsFromJson(itemInfoTable, teams);

        document.add(table);
        // add gap between tables
        document.add(new Paragraph("\n\n"));

        String teamName = reportDTO.getTeamReports().get(0).getTeamName();
        Paragraph paragraph = getParagraph(teamName);

        document.add(paragraph);
        document.add(itemInfoTable);
        document.close();
        log.info("pdf file created");
    }

    private Table addTableWidth() {
        float col = 280f;
        float[] columnWidth = {col, col};
        Table table = new Table(columnWidth);
        table.setBackgroundColor(new DeviceRgb(63, 169, 219)).setFontColor(Color.WHITE);
        return table;
    }

    private void getReportsFromJson(Table itemInfoTable, List<TeamReportDTO> teams) {
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
    }

    private Paragraph getParagraph(String teamName) {
        Paragraph paragraph = new Paragraph("TEAM " + teamName)
                .setFontColor(Color.BLACK)
                .setMargins(0, 0, 0 , 230);
        return paragraph;
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
//                        .format(DateTimeFormatter.ofPattern("MMMM d")))
//                        .format(DateTimeFormatter.ofPattern("yyyy MMM dd")))
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .setTextAlignment(TextAlignment.CENTER)
                .setVerticalAlignment(VerticalAlignment.MIDDLE)
                .setMarginTop(30f)
                .setMarginBottom(30f)
                .setFontSize(30f)
                .setBorder(Border.NO_BORDER)
        );
    }

}
