package TT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(Parameterized.class)
public class ProductSearchTest extends BaseTest {

    private final String searchType;
    private final String query;
    private final boolean expectedResult;

    public ProductSearchTest(String searchType, String query, boolean expectedResult) {
        this.searchType = searchType;
        this.query = query;
        this.expectedResult = expectedResult;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        String csvFilePath = "test99/TT/ProductSearchTest.csv"; // CSV 文件路径
        List<Object[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(csvFilePath), "UTF-8"))) {
            String line;
            boolean isFirstLine = true; // 跳过标题行
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] split = line.split(",");
                String searchType = split[0].trim();
                String query = split[1].trim();
                boolean expectedResult = Boolean.parseBoolean(split[2].trim());
                data.add(new Object[]{searchType, query, expectedResult});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }


    @Test
    public void testProductSearch() {
        driver.get("http://localhost:8081/#/");

        if ("name".equals(searchType)) {
            // 按商品名称搜索
            driver.findElement(By.xpath("//*[@id=\"searchBox\"]/div/input")).sendKeys(query);
            driver.findElement(By.xpath("//*[@id=\"searchBox\"]/div/div[2]/button")).click();
        } else if ("category".equals(searchType)) {
            // 按商品分类搜索
            driver.findElement(By.xpath("//*[@id=\"searchBox\"]/div/div[1]/div/div[1]/input")).sendKeys(query);
        }

        if (expectedResult) {
            // 验证搜索结果是否显示
            By elementLocator = By.xpath("//*[@id=\"layoutview\"]/section/main/div[4]/div/div[1]");
            WebDriverWait wait = new WebDriverWait(driver, 10);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
            assertTrue("搜索结果未显示", element.isDisplayed());
        } else {
            // 验证无结果时
            boolean isElementPresent = !driver.findElements(By.xpath("//*[@id=\"layoutview\"]/section/main/div[4]/div/div[1]")).isEmpty();
            assertFalse("应无搜索结果，但找到元素", isElementPresent);
        }
    }
}
