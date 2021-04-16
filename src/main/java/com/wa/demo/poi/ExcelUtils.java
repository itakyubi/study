package com.wa.demo.poi;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * ExcelUtils
 * 2021-04-16 16:57
 *
 * @author wuao
 */
public class ExcelUtils {

    public static void main(String[] args) throws IOException {
        parseExcel("/Users/wuao/Downloads/test.xlsx", "test.xlsx");
    }

    public static void parseExcel(String path, String fileName) throws IOException {
        parseExcel(new FileInputStream(new File(path)), fileName);
    }

    public static void parseExcel(MultipartFile file) throws IOException {
        parseExcel(file.getInputStream(), file.getOriginalFilename());
    }

    private static void parseExcel(InputStream inputStream, String fileName) throws IOException {
        Workbook workbook;
        if (fileName.endsWith(".xls")) {
            workbook = new HSSFWorkbook(inputStream);
        } else if (fileName.endsWith(".xlsx")) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            return;
        }

        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);
            if (row == null)
                continue;

            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < row.getLastCellNum(); j++) {
                Cell cell = row.getCell(j);
                if (cell != null) {
                    cell.setCellType(CellType.STRING);
                    sb.append(cell.getStringCellValue());
                }
                sb.append(",");
            }
            System.out.println(sb.toString());
        }

        workbook.close();
    }


}
