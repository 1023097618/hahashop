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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class EditProductTest extends BaseTest {

    private final String productName;
    private final String productPrice;
    private final String productStock;

    public EditProductTest(String productName, String productPrice, String productStock) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productStock = productStock;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        String csvFilePath = "test99/TT/EditProductTest.csv"; // CSV 文件路径
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
                String productName = split[0].trim();
                String productPrice = split[1].trim();
                String productStock = split[2].trim();
                data.add(new Object[]{productName, productPrice, productStock});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    @Test
    public void testEditProductSuccess() {
        // 登录为卖家
        loginAsSeller();

        // 等待商品管理页面加载并找到商品
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[3]/div[5]/div[2]/table/tbody/tr[1]/td[8]/div/div/button[1]"))); // 第一行商品的编辑按钮
        editButton.click();

        // 等待修改弹窗出现
        WebElement editDialog = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"goods\"]/div[5]/div/div[2]/div/form/div[1]/div/div/input")));

        // 修改商品名称
        WebElement productNameInput = driver.findElement(By.xpath("//*[@id=\"goods\"]/div[5]/div/div[2]/div/form/div[1]/div/div/input"));
        productNameInput.clear();
        productNameInput.sendKeys(productName);

        // 修改商品价格
        WebElement productPriceInput = driver.findElement(By.xpath("//*[@id=\"goods\"]/div[5]/div/div[2]/div/form/div[2]/div/div/input"));
        productPriceInput.clear();
        productPriceInput.sendKeys(productPrice);

        // 修改库存
        WebElement productStockInput = driver.findElement(By.xpath("//*[@id=\"goods\"]/div[5]/div/div[2]/div/form/div[4]/div/div/input"));
        productStockInput.clear();
        productStockInput.sendKeys(productStock);

        // 提交修改
        WebElement confirmButton = driver.findElement(By.xpath("//*[@id=\"goods\"]/div[5]/div/div[3]/span/button[2]"));
        confirmButton.click();

        // 等待元素消失
        WebDriverWait waitDisappear = new WebDriverWait(driver, 10);
        boolean isGone = waitDisappear.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"goods\"]/div[5]/div")));

        // 验证是否消失
        assertTrue("商品修改后，修改界面未消失", isGone);

        // 验证修改后的商品信息
        WebElement updatedProductName = driver.findElement(By.xpath("//*[@id=\"goods\"]/div[3]/div[3]/table/tbody/tr[1]/td[2]/div"));
        WebElement updatedProductPrice = driver.findElement(By.xpath("//*[@id=\"goods\"]/div[3]/div[3]/table/tbody/tr[1]/td[3]/div"));
        WebElement updatedProductStock = driver.findElement(By.xpath("//*[@id=\"goods\"]/div[3]/div[3]/table/tbody/tr[1]/td[4]/div"));

        assertEquals("商品名称未正确更新", productName, updatedProductName.getText());
        assertEquals("商品价格未正确更新", productPrice, updatedProductPrice.getText());
        assertEquals("商品库存未正确更新", productStock, updatedProductStock.getText());
    }

    // 辅助方法：登录为卖家
    private void loginAsSeller() {
        driver.get("http://localhost:8081/#/login"); // 打开登录页面

        // 输入用户名和密码
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[1]/div/div/input")).sendKeys("seller");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[1]/form/div[2]/div/div/input")).sendKeys("seller");
        driver.findElement(By.xpath("//*[@id=\"app\"]/section/main/div/div[2]/button")).click();

        // 验证是否跳转到订单页面
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("http://localhost:8081/#/dashboard/good/manager"));
    }
}
