//package bot;
//
//import com.itextpdf.layout.Document;
//import file.MyHTMLFile;
//import file.MyPdfFile;
//import org.telegram.telegrambots.bots.TelegramLongPollingBot;
//import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
//import org.telegram.telegrambots.meta.api.objects.InputFile;
//import org.telegram.telegrambots.meta.api.objects.Update;
//import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
//
//import java.io.File;
//
//public class DocumentBot extends TelegramLongPollingBot {
//
//    @Override
//    public String getBotUsername() {
////        return "TrackingBot"; // our bot
//        return "amazingAstonBot"; // my tg bot
//    }
//
//    @Override
//    public String getBotToken() {
////        return "5459953599:AAGzJmz5IZEaNcTAADcSxY3fhDy0PQwk74c"; // our tg bot
//        return "5267404223:AAHsDpkVo5_USHywawH8NPMQsHqwxntDAmk"; // my bot
//    }
//
//    @Override
//    public void onUpdateReceived(Update update) {
//        if (update.hasMessage() && update.getMessage().hasText()) {
//            MyPdfFile pdfFile = new MyPdfFile();
//            pdfFile.createPdf();
//
//            SendDocument document = new SendDocument();
//            document.setChatId(String.valueOf(update.getMessage().getChatId()));
//
//            File file = new File("/home/ramz/Documents/report.pdf");
//
//            document.setDocument(new InputFile(file));
//
//            try {
//                execute(document);
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
//
//    }
//
//}