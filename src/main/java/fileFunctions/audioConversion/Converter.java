package fileFunctions.audioConversion;

import utils.AppPath;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class Converter {

    private static final String FFMPEG_PATH_SYSTEM = System.getenv("ffmpeg"); // 或 "ffmpeg"
    private static final String FFMPEG_PATH = AppPath.resourcePath("ffmpeg\\ffmpeg.exe").toString();

    // 假设已经设置了系统变量PATH
    public static final String[] AUDIO_FORMATS = {
            "MP3", "WAV", "FLAC", "OGG", "AAC", "M4A", "AMR", "OPUS", "WMA"
    };

    public static void convert(String input, String output) throws Exception {
        String path = Files.exists(Path.of(FFMPEG_PATH_SYSTEM)) ? "ffmpeg" : FFMPEG_PATH;

        if (!Files.exists(Path.of(FFMPEG_PATH))) {
            throw new Exception("没有在计算机上寻找到ffmpeg，请确保其已被安装并设置了系统PATH");
        }

        ProcessBuilder pb = new ProcessBuilder(
                path,
                "-y",                 // 覆盖输出
                "-i", input,          // 输入文件
                "-vn",                // 不处理视频
                output
        );

        pb.redirectErrorStream(true);
        Process process = pb.start();

        // 打印 ffmpeg 日志（非常重要）
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(process.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }

        int exitCode = process.waitFor();
        if (exitCode != 0) {
            throw new RuntimeException("FFmpeg failed with code " + exitCode);
        }
    }

    public static String getSupportedFormat() {
        StringBuilder info = new StringBuilder();
        for (String audioFormat : AUDIO_FORMATS) {
            info.append("  *").append(".").append(audioFormat);
        }
        return info.toString();
    }
}

