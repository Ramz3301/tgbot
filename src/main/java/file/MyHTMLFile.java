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
                    "\t<td>Валерия</td>\n" +
                    "    <td>UI TG</td>\n" +
                    "    <td>3 часа</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "\t<td>Илья</td>\n" +
                    "    <td>MS Router</td>\n" +
                    "    <td>3 часа</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "\t<td>Маргарита</td>\n" +
                    "    <td>MS </td>\n" +
                    "    <td>3 часа</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "\t<td>Мария</td>\n" +
                    "    <td>UI TG</td>\n" +
                    "    <td>3 часа</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "\t<td>Глеб</td>\n" +
                    "    <td>UI TG</td>\n" +
                    "    <td>3 часа</td>\n" +
                    "    </tr><tr>\n" +
                    "\t<td>Ярослав</td>\n" +
                    "    <td>UI TG</td>\n" +
                    "    <td>3 часа</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "\t<td>Рамзан</td>\n" +
                    "    <td>UI TG</td>\n" +
                    "    <td>3 часа</td>\n" +
                    "    </tr>\n" +
                    "</table>");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }
}
