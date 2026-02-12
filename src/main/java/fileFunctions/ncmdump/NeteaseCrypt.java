package fileFunctions.ncmdump;

import com.sun.jna.Memory;
import com.sun.jna.Pointer;

import java.nio.charset.StandardCharsets;

public class NeteaseCrypt implements AutoCloseable {
    private Pointer handle;

    private static Pointer toUTF8(String str) {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        Memory mem = new Memory(bytes.length + 1);
        mem.write(0, bytes, 0, bytes.length);
        mem.setByte(bytes.length, (byte) 0);
        return mem;
    }

    public NeteaseCrypt(String filePath) {
        Pointer path = toUTF8(filePath);
        this.handle = NCMDumpLibrary.INSTANCE.CreateNeteaseCrypt(path);
        if (this.handle == null) {
            throw new RuntimeException("创建NeteaseCrypt失败");
        }
    }

    public void dump(String outputPath) {
        Pointer path = toUTF8(outputPath);
        NCMDumpLibrary.INSTANCE.Dump(this.handle, path);
    }

    public void fixMetadata() {
        NCMDumpLibrary.INSTANCE.FixMetadata(this.handle);
    }

    @Override
    public void close() {
        if (this.handle != null) {
            NCMDumpLibrary.INSTANCE.DestroyNeteaseCrypt(this.handle);
            this.handle = null;
        }
    }
}
