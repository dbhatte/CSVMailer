package com.devendra.csvmailer;


import com.devendra.csvmailer.model.Recipient;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVWriterTest {


    @Test
    public void writeFile() throws IOException {

        Appendable appendable = new PrintWriter(new BufferedWriter(new FileWriter("foo.csv")));

        CSVPrinter csvPrinter = new CSVPrinter(appendable, CSVFormat.DEFAULT.withQuote('"').withQuoteMode(QuoteMode.ALL).withDelimiter(';'));

        for (int i = 0; i < 2700; i++) {
            Recipient recipient = new Recipient("test" + i + "@test.com", "first" + i, "last" + i);
            csvPrinter.printRecord(recipient.getEmail(), recipient.getFirstName(), recipient.getLastName());
        }

        csvPrinter.close();
    }

}