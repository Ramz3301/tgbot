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
        return "TrackingReportsBot";
    }

    @Override
    public String getBotToken() {
        return "5459953599:AAGzJmz5IZEaNcTAADcSxY3fhDy0PQwk74c";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendDocument document = new SendDocument();
            document.setChatId("-1001579511149L");

            MyHTMLFile fileHtml = new MyHTMLFile();
            File file = fileHtml.createFile();

            document.setDocument(new InputFile(file));
            try {
                execute(document);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

}