import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Comparator;
import java.util.List;

public class MyAmazingBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendMessage message = new SendMessage(); // Create a SendMessage object with mandatory fields
//            message.setChatId(update.getMessage().getChatId().toString());
//            message.setText(update.getMessage().getText());
            message.setChatId(-1001579511149L);
            message.setText("Hello!");
//            SendDocument document = new SendDocument();
//            document.setChatId(-1001579511149L);
//            InputFile file = new InputFile("text.txt");
//            document.setDocument(file);


            try {
                execute(message); // Call method to send the message
//                execute(document);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public PhotoSize getPhoto(Update update) {
        if (update.hasMessage() && update.getMessage().hasPhoto()) {
            List<PhotoSize> photos = update.getMessage().getPhoto();

            return photos.stream()
                    .max(Comparator.comparing(PhotoSize::getFileSize)).orElse(null);
        }
        return null;
    }

    @Override
    public String getBotUsername() {
        return "time_aston_bot";
    }

    @Override
    public String getBotToken() {
        return "5267404223:AAHsDpkVo5_USHywawH8NPMQsHqwxntDAmk";
    }
}