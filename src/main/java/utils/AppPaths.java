package utils;

import java.nio.file.*;

// 工具类：返回运行环境的路径
public final class AppPaths {

    private AppPaths() {}

    public static Path appHome() {
        try {
            Path location = Paths.get(
                    AppPaths.class.getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
            );

            String path = location.toString().replace("\\", "/");

            // ① 开发期：在 build/classes 下运行
            if (path.contains("/build/classes/")) {
                // 回到项目根目录
                return location
                        .getParent()   // main
                        .getParent()   // java
                        .getParent()   // classes
                        .getParent();  // build
            }

            // ② 发布期：jar 在 app 目录
            if (path.endsWith(".jar")) {
                return location.getParent().getParent();
            }

            throw new IllegalStateException("无法识别运行环境: " + path);

        } catch (Exception e) {
            throw new RuntimeException("定位 appHome 失败", e);
        }
    }


    public static Path libDir() {
        Path lib = appHome().resolve("lib");
        try {
            Files.createDirectories(lib);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lib;
    }

    public static Path dllPath(String name) {
        return libDir().resolve(name);
    }

    public static void main(String[] args) {
        System.out.println(appHome());
    }
}

