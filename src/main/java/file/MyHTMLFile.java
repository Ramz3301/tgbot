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
            List<UserReportDTO> usersOnTeam = teams.get(i).getUserReports();// usersOnTeam for 1 team

            for (int j = 0; j < usersOnTeam.size(); j++) {
                usersName.add(usersOnTeam.get(j).getFullName()); // add usersOnTeam name

                List<TaskDTO> userTasks = usersOnTeam.get(j).getTasks(); // tasks for 1 person

                for (int k = 0; k < userTasks.size(); k++) {
                    String description = userTasks.get(k).getDescription();
                    descriptions.add(description);
                    int timeInMinutes = userTasks.get(k).getTimeInMinutes();
                    minutes.add(timeInMinutes);
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


            reportDTO.getTeamReports().forEach(tr -> {
                try {
                    writer.write("<b>" + tr.getTeamName() + "</b>" + "<br>");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                tr.getUserReports().forEach(ur -> {
                    try {
                        writer.write(ur.getFullName() + " ");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        writer.write("<br>");
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    ur.getTasks().forEach(t -> {
                        try {
                            writer.write(t.getDescription() + " ");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                        try {
                            writer.write(t.getTimeInMinutes() + "<br>");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                });
            });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
