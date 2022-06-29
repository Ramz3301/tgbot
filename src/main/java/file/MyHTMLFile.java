package file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MyHTMLFile {
    /**
     * Create file and write into this something
     */
    public File createHTMLFile() {
        File file = new File("/home/ramz/Documents/demo.html");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            file.createNewFile();
            writer.write("<img style=\"width:250px;height:100px; display:block; margin:0 auto;" +
                    "\" src=\"/home/ramz/Documents/team-blue.jpg\"/>");
            writer.write("<h1 style=\"text-align:center;te\">Report on " + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "</h1>");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
