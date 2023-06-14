package com.greedy.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class MainController {

	@RequestMapping(value= {"/", "main"}) // "/"또는 "main"이면 return으로 이동
	public String defaultLocation() {
		
		return"main";
	}
}
