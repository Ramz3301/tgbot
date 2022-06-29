import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
//            botsApi.registerBot(new MyAmazingBot());
//            botsApi.registerBot(new PhotoBot());
            botsApi.registerBot(new DocumentBot());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
