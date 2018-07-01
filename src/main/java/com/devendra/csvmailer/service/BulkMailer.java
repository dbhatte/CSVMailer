package com.devendra.csvmailer.service;

import com.devendra.csvmailer.model.Message;
import com.devendra.csvmailer.model.Recipient;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/*
 * This class takes a fileName
 * and posts mail to the mail service
 * */
public class BulkMailer {

    private static IMailService mailService = new MockMailService();

    //We use newWorkStealingPool as its an implementation of ForkJoinPool using all available processor core
    //This will firstly allow us to scale according to the number of available cores
    //Also, this allows the least amount of idling of threads as idle threads steal work from busy threads
    private static ExecutorService service = Executors.newWorkStealingPool();

    private String fileName;

    private String delimiter;

    public BulkMailer(String fileName, String delimiter) {
        this.fileName = fileName;
        this.delimiter = delimiter;
    }

    public void process() {
        // Encoding size is assumed as UTF-8 as described in the problem
        try (Stream<String> stream = Files.lines(Paths.get(fileName), StandardCharsets.UTF_8)) {
            stream.map(s -> Recipient.valueOf(s, delimiter)).forEach(BulkMailer::sendMessage);
        } catch (IOException e) {
            e.printStackTrace();
        }

        awaitTerminationAfterShutdown(service);
    }


    private static void awaitTerminationAfterShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            // The timeout needs to be this large as the mailing process is quite slow
            // This can be externalized to a property file
            if (!threadPool.awaitTermination(60, TimeUnit.DAYS)) {
                threadPool.shutdownNow();
            }
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    private static void sendMessage(Recipient recipient) {
        Runnable runnable = () -> mailService.sendMail(new Message(recipient, "", ""));

        service.execute(runnable);
    }
}
