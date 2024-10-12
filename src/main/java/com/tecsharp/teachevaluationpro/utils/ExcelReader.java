package com.tecsharp.teachevaluationpro.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.tecsharp.teachevaluationpro.models.User;

public class ExcelReader {

    public static void main(String[] args) {
        String excelFilePath = "ruta/del/archivo.xlsx"; // Reemplaza con la ruta real de tu archivo Excel

        try (FileInputStream inputStream = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            List<User> userList = readUsersFromExcel(workbook);

            // Imprime los usuarios leídos
            for (User user : userList) {
                System.out.println(user);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<User> readUsersFromExcel(Workbook workbook) {
        List<User> userList = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0); // Obtén la primera hoja del libro (índice 0)

        Iterator<Row> rowIterator = sheet.iterator();
        // Omitir la primera fila si contiene encabezados
        if (rowIterator.hasNext()) {
            rowIterator.next();
        }

        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            // Lee los datos de las celdas y crea un objeto User
            User user = new User();
            user.setUsername(row.getCell(0, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            user.setPassword(row.getCell(1, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            user.setName(row.getCell(2, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            user.setLastnameone(row.getCell(3, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            user.setLastnametwo(row.getCell(4, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());
            user.setEmail(row.getCell(5, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK).getStringCellValue());

            userList.add(user);
        }

        return userList;
    }
}
