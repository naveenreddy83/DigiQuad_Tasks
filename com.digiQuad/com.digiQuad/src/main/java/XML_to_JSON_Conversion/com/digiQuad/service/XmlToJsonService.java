package XML_to_JSON_Conversion.com.digiQuad.service;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class XmlToJsonService {

    @Autowired
    private XML_to_JSON_Conversion.com.digiQuad.repo.FileStorageRepository fileStorageRepository;

    public String convertAndSaveXmlToJson(MultipartFile file) throws IOException {
        // Convert XML to JSON
        XmlMapper xmlMapper = new XmlMapper();
        JsonNode jsonNode = xmlMapper.readTree(file.getInputStream());

        ObjectMapper jsonMapper = new ObjectMapper();
        String jsonString = jsonMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);

        // Save JSON file
        String fileName = file.getOriginalFilename().replace(".xml", ".json");
        return fileStorageRepository.saveJsonToFile(jsonString, fileName);
    }
}
