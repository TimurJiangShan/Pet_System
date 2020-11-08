package com.example.config;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;


@Component
public class ConvertDateConfig implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// trans data to y m d
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// CustomDateEditor for custom date editor
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

}
