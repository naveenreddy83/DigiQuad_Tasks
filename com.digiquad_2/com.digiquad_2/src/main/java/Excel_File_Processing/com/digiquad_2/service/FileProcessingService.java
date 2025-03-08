package Excel_File_Processing.com.digiquad_2.service;



import org.apache.commons.csv.*;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import Excel_File_Processing.com.digiquad_2.dto.RowData;

import java.io.*;
import java.util.*;

@Service
public class FileProcessingService {

    public List<Excel_File_Processing.com.digiquad_2.dto.RowData> processFile(MultipartFile file, int startRow) throws IOException {
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            throw new IOException("Invalid file name");
        }

        if (fileName.endsWith(".csv")) {
            return processCSV(file, startRow);
        } else if (fileName.endsWith(".xlsx") || fileName.endsWith(".xls")) {
            return processExcel(file, startRow);
        } else {
            throw new IOException("Unsupported file type. Please upload Excel or CSV.");
        }
    }

    // Process CSV File
    private List<Excel_File_Processing.com.digiquad_2.dto.RowData> processCSV(MultipartFile file, int startRow) throws IOException {
        List<Excel_File_Processing.com.digiquad_2.dto.RowData> dataList = new ArrayList<>();
        try (Reader reader = new InputStreamReader(file.getInputStream());
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT)) {

            int rowNum = 0;
            for (CSVRecord record : csvParser) {
                if (rowNum >= startRow) {
                    dataList.add(new RowData(record.get(0), record.get(1), record.get(2)));
                }
                rowNum++;
            }
        }
        return dataList;
    }

    // Process Excel File
    private List<Excel_File_Processing.com.digiquad_2.dto.RowData> processExcel(MultipartFile file, int startRow) throws IOException {
        List<Excel_File_Processing.com.digiquad_2.dto.RowData> dataList = new ArrayList<>();
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);
            int rowNum = 0;
            for (Row row : sheet) {
                if (rowNum >= startRow) {
                    dataList.add(new Excel_File_Processing.com.digiquad_2.dto.RowData(
                            getCellValue(row, 0),
                            getCellValue(row, 1),
                            getCellValue(row, 2)
                    ));
                }
                rowNum++;
            }
        }
        return dataList;
    }

    private String getCellValue(Row row, int cellIndex) {
        Cell cell = row.getCell(cellIndex, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
        return cell.toString().trim();
    }
}

