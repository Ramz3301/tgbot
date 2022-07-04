package file;

import connect.ConnectDemo;
import dto.ReportDTO;
import dto.TaskDTO;
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

    private UserReportDTO user = new UserReportDTO();
    private TaskDTO taskDTO = new TaskDTO();
    private ConnectDemo connectDemo = new ConnectDemo();

//    ReportDTO reportDTO = new


    @Override
    public File createFile() {
        ReportDTO report = connectDemo.getReport();
        user.setFullName(report.getTeamReports().get(0).getUserReports().get(0).getFullName());

        taskDTO.setDescription(report.getTeamReports().get(0).getUserReports().get(0).getTasks().get(0).getDescription());
        taskDTO.setTimeInMinutes(report.getTeamReports().get(0).getUserReports().get(0).getTasks().get(0).getTimeInMinutes());
//        user.setLastName();
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(taskDTO);

        user.setTasks(tasks);

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

            writer.write("<table style=\"text-align:center; margin:0 auto; \">\n" +
                    "  <tr>\n" +
                    "  \t<th>Name</th>\n" +
                    "  \t<th>Activity</th>\n" +
                    "  \t<th>Time</th>\n" +
                    "\t</tr>\n" +
                    "    <tr>\n" +
                    "\t<td>" + user.getFullName() + "</td>\n" +
                    "    <td> " + user.getTasks().get(0).getDescription() + "</td>\n" +
                    "    <td> " + taskDTO.getTimeInMinutes() + "</td>\n" +
                    "    </tr>\n" +
                    "</table>");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
