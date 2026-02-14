package utils;

import javax.swing.*;
import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;

public class Downloader extends SwingWorker<Void, Integer> {

    private final Path target;
    private final String url;

    public Downloader(Path target, String url) {
        this.target = target;
        this.url = url;
    }

    @Override
    protected Void doInBackground() throws Exception {
        HttpClient client = HttpClient.newBuilder()
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create(url))
                // .timeout(Duration.ofSeconds(30)) // 设置读取超时
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36")
                .header("Accept", "*/*")
                .header("Accept-Encoding", "identity");
        HttpRequest request = requestBuilder.build();

        // 获取InputStream
        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        int statusCode = response.statusCode();
        if (statusCode != 200) {
            throw new Exception("下载失败，ERROR = " + statusCode);
        }

        // 获取文件大小
        long contentLength = response.headers().firstValueAsLong("Content-Length").orElse(-1);

        // 创建父目录
        // Files.createDirectories(target.getParent());

        try (InputStream in = response.body();
             OutputStream out = Files.newOutputStream(target)) {

            byte[] buffer = new byte[8192];
            long totalBytesRead = 0;
            int bytesRead;

            while ((bytesRead = in.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
                totalBytesRead += bytesRead;

                int percent = (int) (totalBytesRead * 100 / contentLength);
                setProgress(percent); // 关键：通知 Swing
            }
        }
        return null;
    }

    @Override
    protected void done() {
        try {
            get(); // 触发异常检查
            JOptionPane.showMessageDialog(null, "下载完成！");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "下载失败：" + e.getMessage(),
                    "错误",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

