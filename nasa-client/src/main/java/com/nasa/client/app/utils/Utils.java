package com.nasa.client.app.utils;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.DurationFieldType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import reactor.core.publisher.Flux;

public class Utils {

	/**
	 * It returns all days of that period in yyyy-MM-dd string format
	 * @param start the start date of the date's range
	 * @param end the end date of the date's range
	 * @return all days in that period of time
	 */
	public static Flux<String> allDaysOfDateRange(String start, String end){
		DateTimeFormatter datForm = DateTimeFormat.forPattern("yyyy-MM-dd"); 
		long millisStart = datForm.parseMillis(start);
		long millisEnd = datForm.parseMillis(end);
		DateTime startDay = new DateTime(millisStart);
		DateTime endDay = new DateTime(millisEnd);

		int days = Days.daysBetween(startDay, endDay).getDays()+1;
		List<Integer> dayIndexes = IntStream.rangeClosed(0, days).boxed().collect(Collectors.toList());
		return Flux.fromIterable(dayIndexes)
		.map( (Integer dayIndex) ->{
			DateTime d = startDay.withFieldAdded(DurationFieldType.days(), dayIndex);
		    return d.toString(datForm);
		});
	}
	
	/**
	 * It returns all days of that year in yyyy-MM-dd string format
	 * @param year in yyyy-MM-dd string format
	 * @return days of that year 
	 */
	public static Flux<String> allDaysOfYear(String year){
		return allDaysOfDateRange(year+"-01-01", year+"-12-31");
	}
	
}
