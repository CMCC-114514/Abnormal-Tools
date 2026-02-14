package utils;

import java.io.*;
import java.nio.file.*;
import java.util.zip.*;

public class ZipExtractor {

    public static void unzip(Path zipFilePath, Path destPath) throws IOException {

        // 创建目标目录
        if (!Files.exists(destPath)) {
            Files.createDirectories(destPath);
        }

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilePath.toString()))) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {

                Path newPath = resolveZipEntry(destPath, entry);

                if (entry.isDirectory()) {
                    Files.createDirectories(newPath);
                } else {
                    // 创建父目录
                    if (newPath.getParent() != null) {
                        Files.createDirectories(newPath.getParent());
                    }

                    // 写入文件
                    try (OutputStream os = Files.newOutputStream(newPath)) {
                        byte[] buffer = new byte[8192];
                        int len;
                        while ((len = zis.read(buffer)) > 0) {
                            os.write(buffer, 0, len);
                        }
                    }
                }

                zis.closeEntry();
            }
        }
    }

    /**
     * 防止 Zip Slip 攻击
     */
    private static Path resolveZipEntry(Path destDir, ZipEntry entry) throws IOException {
        Path resolvedPath = destDir.resolve(entry.getName()).normalize();

        if (!resolvedPath.startsWith(destDir)) {
            throw new IOException("非法压缩条目: " + entry.getName());
        }

        return resolvedPath;
    }
}

