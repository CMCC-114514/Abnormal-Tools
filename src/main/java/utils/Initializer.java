package utils;

import utils.dependencyManager.AppPath;
import utils.fileDownloader.Download;

import java.nio.file.*;

public class Initializer {
    public static void main(String[] args) {

        Path initFile = AppPath.resourcePath("init");

        if (Files.exists(initFile)) {
            return;
        } else {
            try {
                Download.main(args);
                Files.write(initFile, "1".getBytes(), StandardOpenOption.CREATE_NEW);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
