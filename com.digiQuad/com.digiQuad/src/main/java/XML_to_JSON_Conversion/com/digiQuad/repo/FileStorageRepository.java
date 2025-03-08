package XML_to_JSON_Conversion.com.digiQuad.repo;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.stereotype.Repository;

@Repository
public class FileStorageRepository {

    private static final String OUTPUT_DIR = "./output/";

    public String saveJsonToFile(String jsonContent, String fileName) throws IOException {
        // Create directory if not exists
        File directory = new File(OUTPUT_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Save JSON file
        File file = new File(OUTPUT_DIR + fileName);
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(jsonContent);
        }

        return file.getAbsolutePath();
    }
}

