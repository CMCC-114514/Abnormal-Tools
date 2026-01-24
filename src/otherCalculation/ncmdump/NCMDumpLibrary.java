package otherCalculation.ncmdump;

import com.sun.jna.Library;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public interface NCMDumpLibrary extends Library {
    // 动态库名称（无扩展名）
    NCMDumpLibrary INSTANCE = Native.load("ncmdump", NCMDumpLibrary.class);

    Pointer CreateNeteaseCrypt(String path);
    int Dump(Pointer neteaseCrypt, String outputPath);
    void FixMetadata(Pointer neteaseCrypt);
    void DestroyNeteaseCrypt(Pointer neteaseCrypt);
}