package fileFunctions.audioConversion;

import javax.sound.sampled.*;
import java.io.File;

public class Mp3ToWav {

    public static void convert(String mp3Path, String wavPath) throws Exception {
        File mp3File = new File(mp3Path);

        AudioInputStream mp3Stream = AudioSystem.getAudioInputStream(mp3File);

        AudioFormat sourceFormat = mp3Stream.getFormat();
        AudioFormat pcmFormat = new AudioFormat(
                AudioFormat.Encoding.PCM_SIGNED,
                sourceFormat.getSampleRate(),
                16,
                sourceFormat.getChannels(),
                sourceFormat.getChannels() * 2,
                sourceFormat.getSampleRate(),
                false
        );

        AudioInputStream pcmStream =
                AudioSystem.getAudioInputStream(pcmFormat, mp3Stream);

        AudioSystem.write(pcmStream, AudioFileFormat.Type.WAVE, new File(wavPath));

        mp3Stream.close();
        pcmStream.close();
    }
}
