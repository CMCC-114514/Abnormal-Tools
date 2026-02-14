package utils.dependencyManager;

import com.sun.jna.Native;
import fileFunctions.ncmdump.NCMDumpLibrary;

public final class LibLoader {

    private LibLoader() {}

    private static volatile NCMDumpLibrary instance;

    public static NCMDumpLibrary load(String name) {
        if (instance == null) {
            synchronized (LibLoader.class) {
                if (instance == null) {
                    try {
                        // 1. 确保 资源文件 已经下载
                        AppPath.resourceCheck(name);

                        // 2. 设置 JNA 搜索路径
                        System.setProperty("jna.library.path",
                                AppPath.libDir().toString());

                        // 3. 再加载
                        instance = Native.load(name, NCMDumpLibrary.class);

                    } catch (Exception e) {
                        throw new RuntimeException("JNA 加载失败", e);
                    }
                }
            }
        }
        return instance;
    }
}
