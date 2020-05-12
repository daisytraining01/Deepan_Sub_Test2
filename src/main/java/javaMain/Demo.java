package javaMain;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

import com.mysql.cj.x.protobuf.MysqlxSession.Reset;

public class Demo {
	private static WebDriver driver = null;

	public static void main(String[] args) {

		Connection con = null;
		try {
			con = DriverManager.
			

					getConnection(
							"jdbc:mysql://localhost/sampledb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC\r\n",
							"root", "");
			System.out.println("Connection is successful !!!!!");
			String userid = "select * from users,TansferDetail ";
			System.out.println(userid);
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(userid);
			while (rs.next()) {
				String username = rs.getString("username");
				String Password = rs.getString("password");
				String Name = rs.getString("Name");
				String Email = rs.getString("Email");
				String Phone = rs.getString("Phone");
				String AccountNo = rs.getString("Account_No");
				CallD(username, Password, Name, Email, Phone, AccountNo);
				System.out.println(rs.getString("Name") + rs.getString("Email"));
				System.out.println("" + rs.getString(3));
				System.out.println(rs.getString("username") + rs.getString("password"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// TODO Auto-generated method stub

	}

	private static void CallD(String username, String password, String name, String email, String phone,
			String accountNo) {

		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		String homePage = "http://elastic.rapidtestpro.com:8086/index";

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.get(homePage);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id='username']")).sendKeys(username);
		driver.findElement(By.xpath("//*[@id='password']")).sendKeys(password);

		driver.findElement(By.xpath("//*[text()='Sign in']")).click();
		driver.findElement(By.xpath("//*[text()='Transfer ']")).click();
		WebElement link_Home = driver.findElement(By.xpath("//a[text()='Add/Edit Recipient']"));
		
		Actions builder = new Actions(driver);
		Action mouseOverHome = builder.moveToElement(link_Home).click().build();
		mouseOverHome.perform();

		driver.findElement(By.xpath("//*[@id='recipientName']")).sendKeys(name);
		driver.findElement(By.xpath("//*[@id='recipientEmail']")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id='recipientPhone']")).sendKeys(phone);
		driver.findElement(By.xpath("//*[@id='recipientAccountNumber']")).sendKeys(accountNo);
		// driver.findElement(By.xpath("//button[text()='Add/Edit
		// Recipient']")).click();
		driver.quit();

		// TODO Auto-generated method stub

	}

}
