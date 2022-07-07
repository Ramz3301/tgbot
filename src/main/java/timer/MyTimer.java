package timer;

import file.MyPdfFile;
import soap.RouterServiceImplService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class MyTimer {

    public void timeTracker() {
        LocalTime localTime = LocalTime.of(19, 40);
        LocalDateTime sendingTime = LocalDateTime.of(LocalDate.now(), localTime);
        Date date = Date.from(sendingTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.print(sendingTime);

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sendReportToRouter();
            }
        }, date, TimeUnit.SECONDS.toMillis(10));
    }

    public void sendReportToRouter() {
        MyPdfFile pdfFile = new MyPdfFile();
        pdfFile.createPdf();

        byte[] bytes = new byte[0];
        try {
            bytes = Files.readAllBytes(Paths.get("/home/ramz/Documents/report.pdf"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        // Java 8 - Base64 class, finally.

        // encode, convert byte[] to base64 encoded string
        String encodeString = Base64.getEncoder().encodeToString(bytes);

        RouterServiceImplService serviceImplService = new RouterServiceImplService();
        serviceImplService.getRouterServiceImplPort().sendReport(encodeString);
    }
}
