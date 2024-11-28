package TT;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class SellerTransactionTest extends BaseTest {

    private final String changePageButtonXpath;
    private final String transactionButtonXpath;
    private final String customerButtonXpath;
    private final String confirmButtonXpath;

    public SellerTransactionTest(String changePageButtonXpath, String transactionButtonXpath, String customerButtonXpath, String confirmButtonXpath) {
        this.changePageButtonXpath = changePageButtonXpath;
        this.transactionButtonXpath = transactionButtonXpath;
        this.customerButtonXpath = customerButtonXpath;
        this.confirmButtonXpath = confirmButtonXpath;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        String csvFilePath = "test99/TT/SellerTransactionTest.csv"; // CSV 文件路径
        List<Object[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            boolean isFirstLine = true; // 跳过标题行
            while ((line = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }
                String[] split = line.split(",");
                String changePageButtonXpath = split[0].trim();
                String transactionButtonXpath = split[1].trim();
                String customerButtonXpath = split[2].trim();
                String confirmButtonXpath = split[3].trim();
                data.add(new Object[]{changePageButtonXpath, transactionButtonXpath, customerButtonXpath, confirmButtonXpath});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Test
    public void testSellerInitiatesTransaction() {
        // 登录为卖家
        loginAsSeller();

        WebDriverWait wait = new WebDriverWait(driver, 10);

        // 等待商品管理页面加载并找到换页按钮
        WebElement changeButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(changePageButtonXpath)));
        changeButton.click();

        // 选择商品交易按钮
        WebElement selectButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(transactionButtonXpath)));
        selectButton.click();

        // 等待客户列表加载并选择客户交易按钮
        WebElement customerRow = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(customerButtonXpath)));
        customerRow.click();

        // 点击确认按钮
        WebElement confirmButton = driver.findElement(By.xpath(confirmButtonXpath));
        confirmButton.click();
    }

    // 辅助方法：登录为卖家
    private void loginAsSeller() {
        driver.get("http://localhost:8081/#/login"); // 打开登录页面

        // 输入用户名和密码
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("seller");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("seller");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证是否跳转到商品管理页面
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/#/dashboard/good/manager"));
    }
}
