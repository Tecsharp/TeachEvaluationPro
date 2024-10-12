package com.tecsharp.teachevaluationpro.services.excel.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.services.excel.ExcelService;

@Service
public class ExcelServiceImpl implements ExcelService {

	@Override
	public List<User> readUsersFromExcel(MultipartFile file) throws IOException {

		List<User> userList = new ArrayList<>();
		Workbook workbook = new XSSFWorkbook(file.getInputStream());
		Sheet sheet = workbook.getSheetAt(0); // Obtén la primera hoja del libro (índice 0)

		Iterator<Row> rowIterator = sheet.iterator();
		// Omitir la primera fila si contiene encabezados
		if (rowIterator.hasNext()) {
			rowIterator.next();
		}

		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();

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
