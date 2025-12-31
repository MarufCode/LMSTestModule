package org.example.utils;

import java.io.*;
import java.util.Properties;

public class AttemptStoreUtil {

    private static final String FILE_PATH = System.getProperty("user.dir") + "/attempt-store.properties";

    public static Integer getPreviousAttempt() {

        Properties props = new Properties();

        try (FileInputStream fis = new FileInputStream(FILE_PATH)) {
            props.load(fis);
            return Integer.parseInt(props.getProperty("remainingAttempts"));
        } catch (Exception e) {
            return null;
        }
    }

    public static void saveCurrentAttempt(int attempts) {

        Properties props = new Properties();
        props.setProperty("remainingAttempts", String.valueOf(attempts));

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            props.store(fos, "Attempt Count Store");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
