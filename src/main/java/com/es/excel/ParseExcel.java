package com.es.excel;

import com.es.pojo.BlankCodeExcel;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParseExcel {

    public static List<BlankCodeExcel> parseExcel(InputStream inputStream, String suffix, int startRow) throws IOException {
        Workbook workbook;
        if ("xls".equals(suffix)) {
            workbook = new HSSFWorkbook(inputStream);
        } else if ("xlsx".equals(suffix)) {
            workbook = new XSSFWorkbook(inputStream);
        } else {
            return null;
        }
        Sheet sheet = workbook.getSheetAt(0);
        if (sheet == null) {
            return null;
        }
        int lastRowNum = sheet.getLastRowNum();
        if (lastRowNum <= startRow) {
            return null;
        }
        List<BlankCodeExcel> result = new ArrayList<>();
        Row row = null;
        Cell cell = null;
        for (int rowNum = startRow; rowNum <= lastRowNum; rowNum++) {
            row = sheet.getRow(rowNum);
            short firstCellNum = row.getFirstCellNum();
            short lastCellNum = row.getLastCellNum();
            BlankCodeExcel pojo = new BlankCodeExcel();
            if (lastCellNum != 0) {
                for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                    cell = row.getCell(cellNum);
                    if (cell != null) {
                        if (cellNum == 0){
                            String url = cell.getStringCellValue().trim();
                            Long qrcode_id = Long.parseLong(url.substring(url.indexOf("id=")+3));
                            pojo.setQrcode_id(qrcode_id);
                        } else if (cellNum == 1) {
                            pojo.setDev_code(cell.getStringCellValue().trim());
                        } else {
                            break;
                        }
                    }
                }
            }
            result.add(pojo);
        }
        return result;
    }
}

