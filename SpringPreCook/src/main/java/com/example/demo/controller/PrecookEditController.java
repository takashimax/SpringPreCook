package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.constant.UrlConst;
import com.example.demo.constant.ViewNameConst;

@Controller
public class PrecookEditController {
	@GetMapping(UrlConst.PECOOKEDIT)
	public String view() {
		return ViewNameConst.PRECOOKEDIT;
	}
}
