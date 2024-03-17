package org.freepixels.handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.freepixels.model.LocationDataModel;

public class LocationDataModelHandler {

    private static final Path DbJson = Paths.get("config", "freepixels_site.json");
    private List<LocationDataModel> locations = new ArrayList<>();
    private static final Gson gson = new Gson();

    // =============================================================

    // 获取列表
    public ArrayList<LocationDataModel> getLocationList(){
        loadDataFromFile();
        // 注意,这里返回的是副本!
        return new ArrayList<>(locations);
    }

    // 通过名称查询特定的位置
    public LocationDataModel getLocationByName(String SearchName) {
        loadDataFromFile();
        Optional<LocationDataModel> location = locations.stream()
                .filter(l -> l.getName().equals(SearchName))
                .findFirst();
        return location.orElse(null);
    }

    // 添加位置
    public boolean addLocation(String name, String dimension, String holder, String holderuuid, String time, int posx, int posy, int posz) {
        LocationDataModel newLocation = new LocationDataModel(name, dimension, holder, holderuuid, time, posx, posy, posz);
        locations.add(newLocation);
        return saveDataToFile();
    }

    // 删除位置
    public boolean deleteLocation(String DeleteName){
        // 重载数据库
        loadDataFromFile();
        // 移除记录
        boolean isRemoved = locations.removeIf(location -> location.getName().equals(DeleteName));
        if (isRemoved) {
            if(!saveDataToFile()){
                return false;
            };
        }
        return true;
    }


    // ==== 写入库 =========================================================

    public boolean saveDataToFile() {
        String jsonData = getJsonData();
        try {
            Files.write(DbJson, jsonData.getBytes());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getJsonData() {
        return gson.toJson(locations);
    }

    // ==== 读取库 =========================================================

    public void loadDataFromFile() {
        try {
            String jsonData = new String(Files.readAllBytes(DbJson));
            Type listType = new TypeToken<ArrayList<LocationDataModel>>(){}.getType();
            locations = gson.fromJson(jsonData, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
