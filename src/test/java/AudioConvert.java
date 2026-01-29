import com.github.kokorin.jaffree.ffmpeg.FFmpeg;
import com.github.kokorin.jaffree.ffmpeg.UrlInput;
import com.github.kokorin.jaffree.ffmpeg.UrlOutput;

import java.nio.file.Paths;

public class AudioConvert {

    public static void main(String[] args) {

        FFmpeg.atPath(Paths.get("ffmpeg")) // 使用系统 PATH 中的 ffmpeg
                .addInput(
                        UrlInput.fromPath(Paths.get("C:\\Users\\29395\\Desktop\\test.mp3"))
                )
                .addOutput(
                        UrlOutput.toPath(Paths.get("C:\\Users\\29395\\Desktop\\test.wav"))
                )
                .execute();
    }
}
