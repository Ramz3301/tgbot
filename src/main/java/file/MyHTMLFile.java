package file;

import connect.ConnectDemo;
import dto.ReportDTO;
import dto.TaskDTO;
import dto.TeamReportDTO;
import dto.UserReportDTO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class MyHTMLFile implements CreateFile {

    private List<UserReportDTO> user;
    private List<TaskDTO> taskDTO;
    private ReportDTO reportDTO;
    private List<TeamReportDTO> reports;
    private ArrayList<String> teamNames;

    @Override
    public File createFile() {
        teamNames = new ArrayList<>();
        ConnectDemo connectDemo = new ConnectDemo();
        reportDTO = connectDemo.getReport();
        reports = reportDTO.getTeamReports();


        for (int i = 0; i < reports.size(); i++) {
            teamNames.add(reportDTO.getTeamReports().get(i).getTeamName());
//            teamName.add(reports.get(i));

        }


        File file = new File("/home/ramz/Documents/demo.html");
        writeIntoFile(file);
        return file;
    }

    private void writeIntoFile(File file) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("<img style=\"width:250px;height:100px; display:block; margin:0 auto;" +
                    "\" src=\"/home/ramz/Documents/team-blue.jpg\"/>");
            writer.write("<h1 style=\"text-align:center;te\">Report on " + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "</h1>");



//            writer.write("<table style=\"text-align:center; margin:0 auto; \">\n" +
//                    "  <tr>\n" +
//                    "  \t<th>Name</th>\n" +
//                    "  \t<th>Activity</th>\n" +
//                    "  \t<th>Time</th>\n" +
//                    "  \t<th>Team Name</th>\n" +
//                    "\t</tr>\n" +
//                    "    <tr>\n" +
//                    "\t<td>" + teamName + "</td>\n" +
//                    "    </tr>\n" +
//                    "</table>");

            for (int i = 0; i < teamNames.size(); i++) {
                writer.write(teamNames.get(i) + " ");
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
