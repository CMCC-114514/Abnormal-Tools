package otherCalculation.ncmdump;

import com.sun.jna.Pointer;

public class NeteaseCrypt implements AutoCloseable {
    private Pointer handle;

    public NeteaseCrypt(String filePath) {
        this.handle = NCMDumpLibrary.INSTANCE.CreateNeteaseCrypt(filePath);
        if (this.handle == null) {
            throw new RuntimeException("创建NeteaseCrypt失败");
        }
    }

    public int dump(String outputPath) {
        return NCMDumpLibrary.INSTANCE.Dump(this.handle,
                outputPath != null ? outputPath : "");
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
