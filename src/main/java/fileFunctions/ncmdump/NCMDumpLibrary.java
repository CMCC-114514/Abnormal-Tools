package fileFunctions.ncmdump;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import utils.dependencyManager.LibLoader;

public interface NCMDumpLibrary extends Library {
    // 动态库名称（无扩展名）
    NCMDumpLibrary INSTANCE = LibLoader.load("ncmdump/libncmdump.dll");

    Pointer CreateNeteaseCrypt(Pointer path);
    int Dump(Pointer neteaseCrypt, Pointer path);
    void FixMetadata(Pointer neteaseCrypt);
    void DestroyNeteaseCrypt(Pointer neteaseCrypt);
}