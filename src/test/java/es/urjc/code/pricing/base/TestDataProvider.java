package es.urjc.code.pricing.base;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UncheckedIOException;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.FileCopyUtils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class TestDataProvider {

	private static final String CALCULATE_PRICE_REQUEST_JSON_PATH = "data/calculatePriceRequest.json";
	
	
    public static JsonObject getCalculatePriceRequestJson(){
        String jsonString = getJsonString(CALCULATE_PRICE_REQUEST_JSON_PATH);
        return mapStringToJsonObject(jsonString);
    }

    
    private static String getJsonString(String path) {
        Resource resource = getResource(path);
        return mapResourceToString(resource);
    }

    private static Resource getResource(String path) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        return resourceLoader.getResource("classpath:" + path);
    }

    private static String mapResourceToString(Resource resource) {
        try (Reader reader = new InputStreamReader(resource.getInputStream(), UTF_8)) {
            return FileCopyUtils.copyToString(reader);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
    
    private static JsonObject mapStringToJsonObject(String jsonString){
        Gson gson = new Gson();
        return gson.fromJson(jsonString, JsonObject.class);
    }
}
