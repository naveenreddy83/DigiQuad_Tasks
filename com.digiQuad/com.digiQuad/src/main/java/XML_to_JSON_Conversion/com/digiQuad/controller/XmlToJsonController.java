package XML_to_JSON_Conversion.com.digiQuad.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class XmlToJsonController {

    @Autowired
    private XML_to_JSON_Conversion.com.digiQuad.service.XmlToJsonService xmlToJsonService;

    @PostMapping("/convert")
    public String uploadXmlFile(@RequestParam("file") MultipartFile file) {
        try {
            String jsonFilePath = xmlToJsonService.convertAndSaveXmlToJson(file);
            return "JSON file saved successfully at: " + jsonFilePath;
        } catch (IOException e) {
            return "Error processing file: " + e.getMessage();
        }
    }
}
