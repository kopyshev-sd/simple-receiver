package by.cat.holreceiver.controller;

import by.cat.holreceiver.Constants;
import by.cat.holreceiver.mappers.ReceiveMapper;
import by.cat.holreceiver.model.Receive;
import by.cat.holreceiver.repository.ObjectStreamReceiveRepository;
import by.cat.holreceiver.repository.ReceiveRepository;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RestController
@RequestMapping(value = Constants.BASE_API + "/receives")
public class SimpleRestController {

    private final ReceiveRepository repository = new ObjectStreamReceiveRepository();

    @GetMapping("{uuid}")
    public String get(@PathVariable("uuid") String uuid) {
        return ReceiveMapper.toJSON(repository.get(uuid));
    }

    @GetMapping()
    public String getAll() {
        return ReceiveMapper.toJSON(repository.getAll());
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> receive(@RequestBody String body) {
        Receive receive = ReceiveMapper.fromRequestJSON(body);
        repository.save(receive);
        return ResponseEntity.ok("Successfully saved");
    }
}
