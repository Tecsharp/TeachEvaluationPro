package com.tecsharp.teachevaluationpro.services.excel;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tecsharp.teachevaluationpro.models.User;

public interface ExcelService {
	
	 List<User> readUsersFromExcel(MultipartFile file) throws IOException;
	 

}
