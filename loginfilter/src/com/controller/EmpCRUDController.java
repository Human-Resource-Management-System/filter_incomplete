package com.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class EmpCRUDController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String employeeLogin() {
		System.out.println("empLogin JSP Requested");
		return "login";
	}

	@RequestMapping(value = "/protected", method = RequestMethod.GET)
	public String getPage2Content(HttpServletRequest Request) {

		System.out.println("coming....");
		System.out.println(Request.getParameter("employee"));

		// Employee yourObject = empserv.getObject();
		// model.addAttribute("yourObject", yourObject);

		// Employee empdetails = empserv.getByEmail(email);

		return "profile";

	}

}
