package bot;

import file.MyHTMLFile;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

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
//            document.setChatId("-1001579511149L");
            document.setChatId("880825037");
//            document.setChatId();

            /**
             * Create file and write into this something
             */
            MyHTMLFile fileHtml = new MyHTMLFile();
            File file = fileHtml.createHTMLFile();

            document.setDocument(new InputFile(file));
            try {
                execute(document); // Call method to send the message
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

}