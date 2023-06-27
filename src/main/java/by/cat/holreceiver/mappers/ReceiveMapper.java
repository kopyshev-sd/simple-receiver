package by.cat.holreceiver.mappers;

import by.cat.holreceiver.exceptions.ReceiveStorageException;
import by.cat.holreceiver.model.Receive;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;

public class ReceiveMapper {
    private static final ObjectMapper mapper = new ObjectMapper();
    static {
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    public static String toJSON(Receive receive) {
        return writeObject(receive);
    }

    public static String toJSON(List<Receive> receives) {
        return writeObject(receives);
    }

    public static Receive fromRequestJSON(String json) {
        JSONObject object = new JSONObject(json);
        return new Receive(object.getString("name"), object.getString("payload"));
    }

    private static String writeObject(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new ReceiveStorageException(e);
        }
    }
}
