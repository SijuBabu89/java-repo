package com.buddywindow.auth.util;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    static {
    	mapper.setSerializationInclusion(Include.NON_NULL);
    	mapper.registerModule(new JavaTimeModule());
    }
    
    public static <T> Map<String, Object> toMap(T ob) {
        try {
            return mapper.convertValue(ob, Map.class);
        } catch (Throwable e) {
            return Collections.emptyMap();
        }
    }

    public static String serialize(Object ob) {
        try {
            return mapper.writeValueAsString(ob);
        } catch (Throwable e) {
            return "";
        }
    }
    
    public static Map<String, Object> toFilterMap(final String json) {
      try {
        if (json == null) {
          return new HashMap<>();
        }
        return mapper.readValue(json, Map.class);
      } catch (Throwable e) {
        e.printStackTrace();
        throw new RuntimeException();
        //throw new PlatformRuntimeException("Invalid filter input", e);
      }
    }
    
    public static Map<String, Object> emptyFilterMap() {
          return new HashMap<>();
    }

    public static <T> T deserialize(final String json, Class<T> cls) {
        try {
            return mapper.readValue(json, cls);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> T fromMap(final Map<?, ?> map, Class<T> cls) {
        try {
            return mapper.convertValue(map, cls);
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T> List<T> asList(String jsonArray, TypeReference<List<T>> typeReference) {
        try {
            return mapper.readValue(
                    jsonArray, typeReference);
        } catch (Throwable e) {
            return null;
        }
    }
}
