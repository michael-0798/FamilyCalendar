package FamilyCalendar;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

/**
 * use Map and LocalDate, DateTimeFormatter to create a calendar, which records
 * all important events of the user. I contains the following functions:
 * functions:
 *  1, check if specific day is a working day
 *  (Presumption: assuming that the user starts working on 2021.1.1. According to the working
 *  rules, he/she works continuously for 3 days and then have 2 days vacations in
 *  straight, regardless holidays or weekends.)
 * 	2, add new events
 *  3, delete events
 *  4, update events
 *  5, event reminding
 * 
 * @author  mikew
 * @version 1.0
 * @since	Feb 26,2021
 */

/*
 * 
 * something needs to be improved:
 * 1, create a Menu class, to interact with the user (user just needs to input
 *  String instead of creating the objects of LocalDate)
 * 2, improve the show() method, list the events order by date
 * 3, to make the show() method more efficiently
 */
public class MyCalendar {
	
	/**
	 * set the variable as today;
	 * set it as "static", so every time MyCalendar was initiated, the attribute
	 * will be set the the current day;
	 */
	private static LocalDate today = LocalDate.now();

	/**
	 * create a Map to contain the event (dates and title);
	 * set LocateDate(date to be added into the calendar) as Value, and String (title
	 * for the specific date) as Key, because different events may occur in the 
	 * same day;
	 * set the attribute as the "static", which means only 1 map(calendar) for each
	 * class object;
	 */
	private static HashMap<String, LocalDate> events = new HashMap<String, LocalDate>();
	
	/**
	 * create the attribute of the date which is the first day of work
	 * set it as private, so as not to be changed outside the class
	 */
	private LocalDate firstDayOfWork = LocalDate.of(2021, 1, 1);
	
	/**
	 * create a non-args constructor
	 */
	public MyCalendar() {
		super();
	}
	

	/**
	 * create a set method to set the value of the first working date
	 * @param firstDayOfWork: the new date value for the first working day
	 */
	public void setFirstDayOfWork(LocalDate firstDayOfWork) {
		this.firstDayOfWork = firstDayOfWork;
	}

	/**
	 * get the first working date's value
	 * @return: the date for the first working day
	 */
	public LocalDate getFirstDayOfWork() {
		return firstDayOfWork;
	}


	/**
	 * check if the specific date is a working day
	 * output "working day" or "vacation day"
	 * @param date: the specific date needs to be checked
	 */
	public void checkWorkingDay(LocalDate date) {
		Long days = date.toEpochDay() - firstDayOfWork.toEpochDay();
		int dayCheck = (int)(days%5);
		if(dayCheck==0 || dayCheck==1 || dayCheck==2) {
			System.out.println(date + " is a working day");
		}else {
			System.out.println(date + " is a vacation");
		}
		
	}
	
	/**
	 * add important event to calendar
	 * 1, check if the same title and date has been added before
	 * 2, if the title name exists, ask user to change a new title name to avoid confusion
	 * @param date:  	the date to be input
	 * @param title:	the tile for the date (suck as: wedding memory)
	 */
	public void addDate(LocalDate date, String title) {
		if(events.containsKey(title)&& events.containsValue(date)) {
			System.out.println("the date ("+date+") and title (" +title+
					") you want to add already exists, please check again");
		}else if(events.containsKey(title) && !events.containsValue(date)){
			System.out.println("this title is duplicated with the existing one"
					+ "please use a new title to avoid confusion");
		}else {
			events.put(title, date);
		}
	}
	
	/**
	 * delete the specific record of the event in the calendar
	 * @param date: the date need to be deleted
	 * @param title: the title for the specific date which needs to be deleted
	 */
	public void deleteDate(LocalDate date, String title) {
		if(events.containsKey(title) && events.containsValue(date)) {
			events.remove(title);
		}else {
			System.out.println("the date ("+date+") and title (" +title+
					") you want to delete does not match"
					+ " the records in calendar, please check again");
		}
	}
	
	/**
	 * update the current event record, change it to the new date or title
	 * @param oldtitle	the old title want to be changed
	 * @param oldDate	the old date want to be changed
	 * @param newTitle   the new title of the date
	 * @param newDate 	the new date want to be updated
	 */
	public void updateDate(String oldtitle, LocalDate oldDate, String newTitle, LocalDate newDate) {
		if(events.containsKey(oldtitle)&&events.containsValue(oldDate)) {
			deleteDate(oldDate, oldtitle);
			events.put(newTitle, newDate);
		}else {
			System.out.println("the date ("+oldDate+") and title (" +oldtitle+
					") you want to update does not match"
					+ " the records in calendar, please check again");
		}
	}

	/**
	 * remind the important events in the following 30 days 
	 * output the dates and titles
	 */
	public void remind() {
		Set<Entry<String,LocalDate>> set = events.entrySet();
		Iterator<Entry<String, LocalDate>> it = set.iterator();
		System.out.println("NOTE: reminding date:");
		while(it.hasNext()) {
			Entry<String, LocalDate> event = it.next();
			int days = (int)(event.getValue().toEpochDay() - today.toEpochDay());
			if(days>=0 && days<=30) {
				System.out.println(event);
			}
		}
		
	}
	
	
	/**
	 * list all the events recorded in the calendar
	 */
	public void show() {
		Set<Entry<String,LocalDate>> set = events.entrySet();
		Iterator<Entry<String, LocalDate>> it = set.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
		}
	}
}
