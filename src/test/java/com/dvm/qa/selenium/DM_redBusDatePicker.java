package com.dvm.qa.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DM_redBusDatePicker {
	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		driver = new ChromeDriver(ops);
		selectDate("Oct 2024", "25");
	}




	public static void selectDate(String travelMonthy, String traveldate) throws InterruptedException{


		String travelMonthyear = travelMonthy;
		String travelDate = traveldate;

		driver.get("https://www.redbus.in/");

		WebElement datePick = driver.findElement(By.cssSelector(".dateText"));
		datePick.click();

		WebElement monthyear = driver.findElement(By.cssSelector("div[class*='DayNavigator__CalendarHeader-qj8jdz-1 fxvMrr']>div[style*='2']"));

		WebElement nextMonth = driver.findElement(By.cssSelector("div[class*='DayNavigator__CalendarHeader-qj8jdz-1 fxvMrr']>div~div~div"));


		while(!travelMonthyear.equals(monthyear.getText().substring(0, 8))) {

			/*************************/

			System.out.println(monthyear.getText().substring(0, 8));

			boolean holidyCntIsPresent = driver.findElements(By.cssSelector(".holiday_count")).size() > 0;
			// this will be false when there are no elements present on the web page

			if(holidyCntIsPresent==false) {
				System.out.println("No Holidays");
			}else {
				System.out.println(driver.findElement(By.cssSelector(".holiday_count")).getText());
			}

			/*****************************/

			nextMonth.click();

			Thread.sleep(500);

			if(travelMonthyear.equals(monthyear.getText().substring(0, 8))) {
				System.out.println(monthyear.getText().substring(0, 8));
				System.out.println(driver.findElement(By.cssSelector(".holiday_count")).getText());
				break;
			}
		}

		List<WebElement> SelectDates = driver.findElements(By.cssSelector("div[class*='DatePicker__MainBlock-sc-1kf43k8-1 hHKFiR']>div~div~div>div>span>div>span"));

		for (WebElement date : SelectDates) {

			if(date.getText().equals(travelDate)) {

				date.click();
				break;
			}

		}


		driver.close();
	}



}
