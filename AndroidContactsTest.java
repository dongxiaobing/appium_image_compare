import static org.junit.Assert.assertTrue;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import Utils.Util;

public class AndroidContactsTest {
	private AppiumDriver<AndroidElement> driver;

	@Before
	public void setUp() throws Exception {
		// set up appium
		// File classpathRoot = new File(System.getProperty("user.dir"));
		// File appDir = new File(classpathRoot,
		// "../../../apps/ContactManager");
		// File app = new File(appDir, "ContactManager.apk");
		// System.out.println(app.getAbsolutePath());

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities = DesiredCapabilities.android();
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
				"Android Emulator");
		// capabilities.setCapability("platformName", "Android");
		// capabilities.setCapability("platformVersion", "4.4");
		// capabilities.setCapability("app", app.getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,
				"com.example.android.notepad");
		capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,
				".NotesList");
		driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),
				capabilities);

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void addContact() throws Exception {
		Thread.sleep(2000);
		WebElement el=driver.findElementById("android:id/text1");	
		el.click();
		Thread.sleep(1000);
		String p1 = "C:/1";
		String p2 = "C:/2";

		File f2 = new File(p2);

		File f1 = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f1, new File(p1));

		BufferedImage img1 = Util.getImageFromFile(f1);

		f2 = driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(f2, new File(p2));
		BufferedImage img2 = Util.getImageFromFile(f2);

		Boolean same = Util.sameAs(img1, img2, 0.9);
		assertTrue(same);

		BufferedImage subImg1 = Util.getSubImage(img1, 6, 39, 47, 38);
		BufferedImage subImg2 = Util.getSubImage(img1, 6, 39, 47, 38);
		same = Util.sameAs(subImg1, subImg2, 1);

		File f3 = new File("c:/sub-1.png");
		ImageIO.write(subImg1, "PNG", f3);

		File f4 = new File("c:/sub-2.png");
		ImageIO.write(subImg1, "PNG", f4);
	}

}