package fileFunctions.ncmdump;

import com.sun.jna.Library;
import com.sun.jna.Pointer;
import utils.LibLoader;

public interface NCMDumpLibrary extends Library {
    String url = "https://link.jiyiho.cn/orfile/down.php/0dd2e2e37bf3e8fb5e18868c035d1b5f.dll";
    // 动态库名称（无扩展名）
    NCMDumpLibrary INSTANCE = LibLoader.load("libncmdump", url);

    Pointer CreateNeteaseCrypt(String path);
    int Dump(Pointer neteaseCrypt, String outputPath);
    void FixMetadata(Pointer neteaseCrypt);
    void DestroyNeteaseCrypt(Pointer neteaseCrypt);
}