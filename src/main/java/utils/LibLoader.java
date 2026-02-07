package utils;

import com.sun.jna.Native;
import fileFunctions.ncmdump.NCMDumpLibrary;

public final class LibLoader {

    private LibLoader() {}

    private static volatile NCMDumpLibrary instance;

    public static NCMDumpLibrary load(String name, String url) {
        if (instance == null) {
            synchronized (LibLoader.class) {
                if (instance == null) {
                    try {
                        // 1. 确保 DLL 已经下载
                        DependencyManager.ensureDllPresent(name, url);

                        // 2. 设置 JNA 搜索路径
                        System.setProperty("jna.library.path",
                                AppPaths.libDir().toString());

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
