/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package co.edu.unicauca.infra.dto;

/**
 *
 * @author RoLoNeGaTiVo
 */
import com.google.gson.*;

public class JsonUtils {
    private static final Gson gson = new Gson();
    
    public static String toJson(Object object) {
        return gson.toJson(object);
    }
    
    public static JsonObject parseJsonObject(String json) {
        return JsonParser.parseString(json).getAsJsonObject();
    }
    
    public static JsonArray parseJsonArray(String json) {
        return JsonParser.parseString(json).getAsJsonArray();
    }
}
