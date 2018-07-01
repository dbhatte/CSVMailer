package com.devendra.csvmailer;

import com.devendra.csvmailer.service.BulkMailer;

/**
 * Main class
 */
public class App {
    public static void main(String[] args) {
        String fileName = args[0];

        BulkMailer bulkMailer = new BulkMailer(fileName, ";");

        bulkMailer.process();
    }
}
