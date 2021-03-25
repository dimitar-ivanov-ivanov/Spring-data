package productShop.shop.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

@Component
public class JsonParser {

    private Gson gson;

    public JsonParser() {
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public <T> T objectFromFile(Class<T> tClass, String path) {
        return this.gson.fromJson(TextFileUtil.read(path), tClass);
    }

    public <T> void objectToFile(T obj, String path) {
        TextFileUtil.write(objectToJson(obj), path);
    }

    public <T> T objectFromJson(Class<T> tClass, String json) {
        return this.gson.fromJson(json, tClass);
    }

    public <T> String objectToJson(T obj) {
        return this.gson.toJson(obj);
    }

}
