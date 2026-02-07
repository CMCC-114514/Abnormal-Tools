package utils;

import javax.swing.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;

public class DependencyManager {

    public static void ensureDllPresent(String name, String url) throws Exception {
        Path dll = AppPaths.dllPath(name + ".dll");

        if (Files.exists(dll)) {
            return;
        }

        // 这里写你的下载逻辑
        downloadFromServer(dll, url);
        // verifySha256(dll);
    }

    // 下载内容
    public static void downloadFromServer(Path target, String url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);

        int contentLength = conn.getContentLength();

        try (InputStream in = conn.getInputStream();
             OutputStream out = Files.newOutputStream(target)) {

            byte[] buffer = new byte[8192];
            int len;
            long total = 0;

            while ((len = in.read(buffer)) > 0) {
                out.write(buffer, 0, len);
                total += len;

                int percent = (int) (total * 100 / contentLength);
                System.out.print("\rDownloading DLL: " + percent + "%");
            }
        }
    }
}

