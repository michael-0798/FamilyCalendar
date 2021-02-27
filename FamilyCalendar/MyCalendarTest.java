package FamilyCalendar;

import java.time.LocalDate;import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoPeriod;
import java.time.chrono.Chronology;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalField;
import java.time.temporal.TemporalUnit;
/**
 * testing class for MyCalendar
 * add some new events (date & title) into the calendar
 * delete some of them
 * update some of them
 * check the working day
 * list all the events in calendar
 * list all the important events in the following 30 days
 * @author mikew
 * @version 1.0
 * @since Feb 26, 2021
 */
public class MyCalendarTest {
	public static void main(String[] args) {
		MyCalendar cc = new MyCalendar();
		// create some new dates
		LocalDate date = LocalDate.parse("2021-03-09");
		LocalDate date2 = LocalDate.parse("2021-03-11");
		LocalDate date3 = LocalDate.parse("2021-03-11");
		LocalDate date4 = LocalDate.parse("2021-04-22");

		// add events
		cc.addDate(date, "kid's school day");
		cc.addDate(date2, "wife's birthday");
		cc.addDate(date3, "PD day");
		cc.addDate(date4, "wedding ceremony");
		cc.addDate(date, "work3");
		
		// add duplicated events 
		cc.addDate(date, "work3");
		cc.deleteDate(date, "work3");
		
		// update event in calendar
		cc.updateDate("work3", date, "hiking day",date2);
		
		// check if the specific day is a working day
		cc.checkWorkingDay(date);
		
		// list all the events in calendar
		cc.show();
		
		// list all the important events in the following 30 days
		cc.remind();
	}

}
