package TT;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public class BaseTest {
    protected WebDriver driver;

    @Before
    public void setUp() {
        // 设置 Edge WebDriver 驱动路径
        System.setProperty("webdriver.edge.driver", "C:\\Users\\DELL\\Desktop\\22\\软件质量测试\\edgedriver_win64\\edgedriver_win64\\msedgedriver.exe");
        driver = new EdgeDriver(); // 使用 Edge 浏览器
        driver.manage().window().maximize();
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // 关闭浏览器
        }
    }
}
