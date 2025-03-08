package XML_to_JSON_Conversion.com.digiQuad.dto;

public class JsonFile {

        private String fileName;
	    private String filePath;

	    public JsonFile(String fileName, String filePath) {
	        this.fileName = fileName;
	        this.filePath = filePath;
	    }

	    public String getFileName() { return fileName; }
	    public String getFilePath() { return filePath; }
	}

