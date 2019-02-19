package DataDrivenPackage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class DataDrivenTest {
	 public WebDriver driver;
	    
	    @BeforeTest
	    public void openapplication() throws InterruptedException {
	        System.setProperty("webdriver.chrome.driver","c:\\chromedriver\\chromedriver.exe");
	           driver=new ChromeDriver();
	        String baseUrl="http://newtours.demoaut.com";
	        driver.get(baseUrl);
	        Thread.sleep(5000);
	    }
	   
	    @Test(priority=0)
	    public void VerifyRegiterLinkTest() {
	        try
	        {
	          
	                  driver.findElement(By.linkText("REGISTER")).click();
	                  Thread.sleep(3000);
	                  String expectedresult="Register: Mercury Tours";
	                  String actualresult = driver.getTitle();
	                  Assert.assertEquals(actualresult, expectedresult);
	        }
	        catch( Exception e)
	        {
	        	System.out.println("exception name:"+e.toString());
	        //	System.out.println("exception messgae:"+e.getMessage());
	        driver.quit();
	        
	        }
	    }
	    @Test(priority=1)
	  public void VerifyUserRegistrationTest() throws InterruptedException, IOException {
	      String excelpath="D:\\Selenium - Java\\Scripts\\SeleniumDataDriven\\Registration.xlsx";
	       
	      File fi=new File(excelpath);
	      FileInputStream fis=new FileInputStream(fi);
	      Workbook  wb = new XSSFWorkbook(fis);
	      Sheet sh=wb.getSheet("Sheet1");
	    	int rowcount=sh.getLastRowNum()-sh.getFirstRowNum();
	    	
	    	System.out.println("No of rows:"+ rowcount);
	    	
	    	for (int row=1;row<=rowcount; row++)
	    	{
	    		
	    	Row rw = sh.getRow(row);
	    	
	                driver.findElement(By.linkText("REGISTER")).click();
	               Thread.sleep(3000);
	                driver.findElement(By.name("firstName")).sendKeys(rw.getCell(0).getStringCellValue());
	                   driver.findElement(By.name("lastName")).sendKeys(rw.getCell(1).getStringCellValue());
	                   driver.findElement(By.name("phone")).sendKeys(rw.getCell(2).getStringCellValue());
	                   driver.findElement(By.name("userName")).sendKeys(rw.getCell(3).getStringCellValue());
	                   driver.findElement(By.name("address1")).sendKeys(rw.getCell(4).getStringCellValue());
	                   driver.findElement(By.name("address2")).sendKeys(rw.getCell(5).getStringCellValue());
	                   driver.findElement(By.name("city")).sendKeys(rw.getCell(6).getStringCellValue());
	                   driver.findElement(By.name("state")).sendKeys(rw.getCell(7).getStringCellValue());
	                   driver.findElement(By.name("postalCode")).sendKeys(rw.getCell(8).toString());
	                   Select drpcountry = new Select(driver.findElement(By.name("country")));
	                 drpcountry.selectByVisibleText(rw.getCell(9).getStringCellValue());
	                 driver.findElement(By.name("email")).sendKeys(rw.getCell(10).getStringCellValue());
	                 driver.findElement(By.name("password")).sendKeys(rw.getCell(11).getStringCellValue());
	                 driver.findElement(By.name("confirmPassword")).sendKeys(rw.getCell(12).getStringCellValue());
	                 driver.findElement(By.name("register")).click();
	                 Thread.sleep(2000);
	                 String expectedresult = "Your user name is "+rw.getCell(10).getStringCellValue();
	                 String actualresult = driver.findElement(By.tagName("body")).getText();
	                 
	                 Assert.assertTrue(actualresult.contains(expectedresult));
	                 
	                 
	              //   driver.findElement(By.linkText("SIGN-OFF")).click();
	             //    Thread.sleep(2000);
	             //   driver.close();
	             //   driver.quit();
	    	}
	  }
	  @AfterTest
	  
	  public void closeapplication()
	  {
	  driver.close();
	 driver.quit();
	  //driver.findElement(By.linkText("SIGN-OFF")).click();
	}
	}