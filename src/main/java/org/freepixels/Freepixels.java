package org.freepixels;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.fabricmc.api.ModInitializer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

// 最先运行

public class Freepixels implements ModInitializer{

    private static final Path SiteListJson = Paths.get("config", "freepixels_site.json");

    @Override
    public void onInitialize(){
        System.out.println("Freepixels plugin - build: 2024-3-13");
        System.out.println("by: Tomoko Aoyama (QQ: 1635582152) && YuanYuJunChangAn(QQ: 906506482)");
        System.out.println("[Freepixels][SiteModule] Self-testing in progress...");

        if(!initializeSiteListModule()){
            // 自检失败,数据Json有问题,抛出一个未捕获的异常,让游戏退出.
            throw new RuntimeException("[Freepixels][SiteModule] Json data parsing failed!");
        }

    }

    private static boolean initializeSiteListModule() {
        try {
            // 检查文件是否存在
            if (!Files.exists(SiteListJson)) {
                // 如果文件不存在，则创建文件并初始化为一个空的数组
                ArrayList<Object> emptyList = new ArrayList<>();
                Gson gson = new Gson();
                String json = gson.toJson(emptyList);
                Files.createDirectories(SiteListJson.getParent()); // 确保父目录存在
                Files.write(SiteListJson, json.getBytes());
                System.out.println("[Freepixels][SiteModule] Plugin initialized successfully!");
            } else {
                // 文件存在，尝试读取并解析
                String json = Files.readString(SiteListJson);
                Gson gson = new Gson();
                ArrayList<Object> list = gson.fromJson(json, new TypeToken<ArrayList<Object>>(){}.getType());
                System.out.println("[Freepixels][SiteModule] Self-test was successful!");
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("[Freepixels][SiteModule] Failed to initialize data!");
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("[Freepixels][SiteModule] Failed to parse the site list JSON!");
            return false;
        }
    }


}
