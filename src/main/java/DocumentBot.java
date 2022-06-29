import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DocumentBot extends TelegramLongPollingBot {


    @Override
    public String getBotUsername() {
        return "time_aston_bot";
    }

    @Override
    public String getBotToken() {
        return "5267404223:AAHsDpkVo5_USHywawH8NPMQsHqwxntDAmk";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendDocument document = new SendDocument();
            document.setChatId("-1001579511149L");

            /**
             * Create file and write into this something
             */
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

            document.setDocument(new InputFile(file));
            try {
                execute(document); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

}
