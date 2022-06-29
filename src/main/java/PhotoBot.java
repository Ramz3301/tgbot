import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class PhotoBot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "time_aston_bot";
    }

    @Override
    public String getBotToken() {
        return "5267404223:AAHsDpkVo5_USHywawH8NPMQsHqwxntDAmk";
    }

//    @Override
//    public void onUpdateReceived(Update update) {
//
//        // We check if the update has a message and the message has text
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            // Set variables
//            String messageText = update.getMessage().getText();
//            long chatId = update.getMessage().getChatId();
//            SendMessage message = new SendMessage();
////            message.setChatId(-1001579511149L);
//            message.setChatId(chatId);
//            message.setText(messageText);
////            message.setText("Hello tg");
//            try {
//                execute(message); // Sending our message object to user
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
//            // Message contains photo
//            // Set variables
//            long chat_id = update.getMessage().getChatId();
//
//            // Array with photo objects with different sizes
//            // We will get the biggest photo from that array
//            List<PhotoSize> photos = update.getMessage().getPhoto();
//            // Know file_id
//            String f_id = photos.stream()
//                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
//                    .findFirst()
//                    .orElse(null).getFileId();
//            // Know photo width
//            int f_width = photos.stream()
//                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
//                    .findFirst()
//                    .orElse(null).getWidth();
//            // Know photo height
//            int f_height = photos.stream()
//                    .sorted(Comparator.comparing(PhotoSize::getFileSize).reversed())
//                    .findFirst()
//                    .orElse(null).getHeight();
//            // Set photo caption
//            String caption = "file_id: " + f_id + "\nwidth: " + Integer.toString(f_width) + "\nheight: " + Integer.toString(f_height);
//            SendPhoto msg = new SendPhoto();
//            msg.setChatId(chat_id);
////                    .setChatId(chat_id)
//                    msg.setPhoto(f_id);
////                    msg.setPhoto();
//                    msg.setCaption(caption);
//            try {
////                sendPhoto(msg); // Call method to send the photo with caption
//                execute(msg);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//    }


    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            SendPhoto photo = new SendPhoto(); // Create a SendMessage object with mandatory fields
//            message.setChatId(update.getMessage().getChatId().toString());
//            message.setText(update.getMessage().getText());
            photo.setChatId("-1001579511149L");
            photo.setPhoto(new InputFile(new File("/home/ramz/Downloads/taco_cloud.jpeg")));
//            SendDocument document = new SendDocument();
//            document.setChatId(-1001579511149L);
//            InputFile file = new InputFile("text.txt");
//            document.setDocument(file);


            try {
                execute(photo); // Call method to send the message
//                execute(document);

            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendImageUploadingAFile(String filePath, String chatId) {
        // Create send method
        SendPhoto sendPhotoRequest = new SendPhoto();
        // Set destination chat id
        sendPhotoRequest.setChatId(chatId);
        // Set the photo file as a new photo (You can also use InputStream with a constructor overload)
        sendPhotoRequest.setPhoto(new InputFile(new File(filePath)));
        try {
            // Execute the method
            execute(sendPhotoRequest);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
