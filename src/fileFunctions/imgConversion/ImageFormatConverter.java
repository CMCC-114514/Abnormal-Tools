package fileFunctions.imgConversion;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class ImageFormatConverter {
    private ImageFormatConverter(){}

    public static final String[] IMAGE_FORMATS = {
            "JPG", "TIFF", "GIF", "PNG", "TIF", "JPEG"
    };

    public static void convert(String inputPath, String outputPath, String format) {
        try {
            BufferedImage image = ImageIO.read(new File(inputPath));
            if (image == null) {
                throw new RuntimeException("无法读取图片");
            }

            String fmt = format.toLowerCase();
            BufferedImage target = image;

            // JPG / JPEG 不支持 alpha，必须转 RGB
            if ((fmt.equals("jpg") || fmt.equals("jpeg"))
                    && image.getColorModel().hasAlpha()) {

                BufferedImage rgb = new BufferedImage(
                        image.getWidth(),
                        image.getHeight(),
                        BufferedImage.TYPE_INT_RGB
                );

                Graphics2D g = rgb.createGraphics();
                g.setColor(Color.WHITE); // 背景色可换
                g.fillRect(0, 0, image.getWidth(), image.getHeight());
                g.drawImage(image, 0, 0, null);
                g.dispose();

                target = rgb;
            }

            boolean ok = ImageIO.write(target, fmt, new File(outputPath));
            if (!ok) {
                throw new RuntimeException("ImageIO 不支持该格式: " + format);
            }

        } catch (IOException e) {
            throw new RuntimeException("IO 错误: " + e.getMessage(), e);
        }
    }

    public static String getSupportedFormat() {
        StringBuilder info = new StringBuilder();
        for (String format : IMAGE_FORMATS) {
            info.append("  *").append(".").append(format);
        }

        return info.toString();
    }
}

