package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler {
    private final DictionaryManager dictionaryManager;

    public BookScrabbleHandler() {
        this.dictionaryManager = DictionaryManager.get();
    }

    @Override
    public void handleClient(InputStream inFromClient, OutputStream outToClient) {
        try (Scanner in = new Scanner(inFromClient);
             PrintWriter out = new PrintWriter(outToClient)) {
            String request = in.nextLine();
            String[] parts = request.split(",");
            String operation = parts[0];

            String[] args = new String[parts.length];
            args[0] = operation;
            for (int i = 1; i < parts.length; i++) {
                args[i] = parts[i];
            }

            boolean result;
            if (operation.equals("Q")) {
                result = dictionaryManager.query(args);
            } else {
                result = dictionaryManager.challenge(args);
            }

            out.println(result + "\n");
            out.flush();
        }
    }

    @Override
    public void close() {
        // No resources to close
    }
}