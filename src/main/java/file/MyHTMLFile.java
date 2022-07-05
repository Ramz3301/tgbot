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

    //    private List<UserReportDTO> users;
    private List<String> usersName;
    //    private List<TaskDTO> taskDTO;
    private List<String> tasks;
    private List<Integer> minutes;
    private ReportDTO reportDTO;
    private List<TeamReportDTO> teams;
    private ArrayList<String> teamNames;
    private ArrayList<String> descriptions;
    private ArrayList<String> greenTeam;
    private ArrayList<String> blueTeam;

    @Override
    public File createFile() {
        descriptions = new ArrayList<>();
        minutes = new ArrayList<>();
        usersName = new ArrayList<>();
        teamNames = new ArrayList<>();
        tasks = new ArrayList<>();
        greenTeam = new ArrayList<>();
        blueTeam = new ArrayList<>();

        ConnectDemo connectDemo = new ConnectDemo();
        reportDTO = connectDemo.getReportDTO();

        teams = reportDTO.getTeamReports();

        for (int i = 0; i < teams.size(); i++) {
            teamNames.add(reportDTO.getTeamReports().get(i).getTeamName()); // team names
            List<UserReportDTO> users = teams.get(i).getUserReports();// users for 1 team

            for (int j = 0; j < users.size(); j++) {
                usersName.add(users.get(j).getFullName()); // add users name

                List<TaskDTO> taskDTOList = users.get(j).getTasks(); // tasks for 1 person

                for (int k = 0; k < taskDTOList.size(); k++) {
                    descriptions.add(taskDTOList.get(k).getDescription());
                }

            }

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


            writer.write("Teams: " );
            for (int i = 0; i < teamNames.size(); i++) {
                writer.write(teamNames.get(i) + " ");
            }
            writer.write("<br>");

            writer.write("Names: ");
            for (int i = 0; i < usersName.size(); i++) {
                writer.write(usersName.get(i) + " ");
            }
            writer.write("<br>");

            writer.write("Tasks: ");
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i) + " ");
            }
            writer.write("<br>");

//            writer.write("Minutes: ");
//            for (int i = 0; i < minutes.size(); i++) {
//                writer.write(minutes.get(i) + " ");
//            }
//            writer.write("<br>");

//            for (int i = 0; i < reports.size(); i++) {
//                writer.write(teamNames.get(i) + " ");
//            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
