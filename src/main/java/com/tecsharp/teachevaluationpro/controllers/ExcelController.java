package com.tecsharp.teachevaluationpro.controllers;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tecsharp.teachevaluationpro.models.User;
import com.tecsharp.teachevaluationpro.services.excel.ExcelService;
import com.tecsharp.teachevaluationpro.services.user.UserService;

@Controller
public class ExcelController {

	@Autowired
	UserService userService;

	@Autowired
	ExcelService excelService;

	@PostMapping({ "/app/crud/excel/upload" })
	public String englishLesson(HttpServletRequest req, @RequestParam("file") MultipartFile file, Model model, Long fid,
			Long classId, Long lvl) {

		String userLogged = (String) req.getSession().getAttribute("USERNAME");
		User user = userService.getUserByUsername(userLogged);
		if (user != null && user.getUsername().equals(userLogged)) {
			try {
			
			List<User> userList = excelService.readUsersFromExcel(file);

			userService.registerStudentsByExcel(userList, fid, classId, lvl);
			return "redirect:/app/admin/school/filial/level/classroom?clssid=" + classId + "&fid=" + fid;
			} catch (Exception e) {
				model.addAttribute("error", e + "... Puede que algunos usuarios ya existan.");
				return "404";
			}
		} else {
			return "redirect:/login";
		}
		

	}

}
