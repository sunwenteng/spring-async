package com.bingo.async;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sync")
public class Sync {

	@RequestMapping("/response-body")
	public @ResponseBody String sync() throws Exception {
		Thread.sleep(2000);
		return "test";
	}

}