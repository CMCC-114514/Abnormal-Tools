package utils.dependencyManager;

import java.io.*;
import java.net.*;
import java.nio.file.*;

public class DependencyManager {

    public static final String libUrl = "https://github.com/CMCC-114514/Abnormal_Dependencies/releases/download/lib/lib.zip";

    public static void ensurePresent(String name) throws Exception {
        Path resource = AppPath.resourcePath(name);

        if (Files.exists(resource)) {
            return;
        }

        // 这里写你的下载逻辑
        downloadFromServer(resource, libUrl);
        // verifySha256(resource);
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
                System.out.print("\rDownloading: " + percent + "%");
            }
        }
    }
}

