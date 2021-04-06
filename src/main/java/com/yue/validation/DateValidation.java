package com.yue.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateValidation {
	public boolean isBefore(String startDate, String endDate) {
		LocalDate startDateTime = LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		LocalDate endDateTime = LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return startDateTime.isBefore(endDateTime);
	}
}
