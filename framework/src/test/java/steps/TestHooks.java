package steps;

import org.openqa.selenium.WebDriver;
import org.veeva.config.AppConstants;
import org.veeva.drivers.DriverManager;
//import org.veeva.utils.ReportUtils;
import javax.imageio.ImageIO;
import java.io.ByteArrayOutputStream;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;




public class TestHooks {
	private static final ThreadLocal<Boolean> driverInitialized = new ThreadLocal<Boolean>() {
		@Override
		protected Boolean initialValue() {
			return false;
		}
	};

	@Before
	public void setup(Scenario scenario) {
		if (!driverInitialized.get()) {
			//String browser = System.getProperty("browser", "chrome");
		//	System.out.println("Initializing driver for Thread: " + Thread.currentThread().getId());

			DriverManager.setDriver(org.veeva.drivers.DriverFactory.createInstance(AppConstants.PROVIDED_BROWSER));
			driverInitialized.set(true);

			//System.out.println("Driver initialized for Scenario: " + scenario.getName());
		}
	}

	@After
	public void teardown(Scenario scenario) {
		WebDriver driver = DriverManager.getDriver();
		if (scenario.isFailed() && driver != null) {
			attachFullPageScreenshotToReport(scenario, driver);
		}

		if (driver != null) {
			driver.quit();
			driverInitialized.remove();
		}
	}

	/**
	 * Attaches full page screenshot to Cucumber report
	 */
	public static void attachFullPageScreenshotToReport(Scenario scenario, WebDriver driver) {
		try {
			byte[] screenshot = takeFullPageScreenshotAsBytes(driver);
			scenario.attach(screenshot, "image/png", scenario.getName());
		} catch (Exception e) {
			System.err.println("Failed to attach screenshot to report: " + e.getMessage());
		}
	}

	/*
	 *//**
	 * Takes full page screenshot and returns as byte array
	 *//*
	 * public static byte[] takeFullPageScreenshotAsBytes(WebDriver driver) throws
	 * Exception { Screenshot screenshot = new AShot()
	 * .shootingStrategy(ShootingStrategies.viewportPasting(1000))
	 * .takeScreenshot(driver);
	 * 
	 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 * ImageIO.write(screenshot.getImage(), "png", baos); return baos.toByteArray();
	 * }
	 */

	public static byte[] takeFullPageScreenshotAsBytes(WebDriver driver) throws Exception {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(driver);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ImageIO.write(screenshot.getImage(), "png", baos);
		return baos.toByteArray();
	}
}
