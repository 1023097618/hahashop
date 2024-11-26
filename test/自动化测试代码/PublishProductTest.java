package TT;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.JavascriptExecutor;

public class PublishProductTest extends BaseTest {
	// 测试用例 TC15-1: 发布成功
	@Test
	public void testPublishProductSuccess() {
	    loginAsSeller();

	    WebDriverWait wait1 = new WebDriverWait(driver, 10);
	    WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[3]/div[5]/div[1]/table/thead/tr/th[8]/div/div/button")));
	    element.click();

	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[1]/div/div/input")));

	    // 填写商品信息
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[1]/div/div/input")).sendKeys("MacBook Pro");
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[2]/div/div/input")).sendKeys("12000");

	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[4]/div/div/input")).sendKeys("10");
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[7]/div[1]")).sendKeys("苹果笔记本电脑");

	    // 上传图片
	    WebElement uploadElement = driver.findElement(By.xpath("//*[@id='goods']/div[6]/div/div[2]/div/form/div[3]/div/div/div[1]/div/input"));
	    uploadElement.sendKeys("C://Users//DELL//Desktop//测试//OIP-C.jpg"); // 替换为图片的绝对路径

	    // 点击 Cascader 输入框以展开菜单
	    WebElement cascaderTrigger = driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[5]/div/div/div/input"));
	    cascaderTrigger.click();

	    // 创建 Actions 对象
	    Actions actions = new Actions(driver);

	    // 点击发布按钮
	    WebDriverWait wait5 = new WebDriverWait(driver, 10);
	    WebElement publishButton = wait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[3]/span/button[2]")));
	    publishButton.click();

	    // 等待元素消失
	    WebDriverWait waitDisappear = new WebDriverWait(driver, 10);
	    boolean isGone = waitDisappear.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"goods\"]/div[6]/div")));

	    // 验证是否消失
	    assertTrue("商品发布后，发布界面未消失", isGone);
	}



    // 测试用例 TC15-2: 名称为空
    @Test
    public void testPublishProductNameEmpty() {
    	loginAsSeller();

	    WebDriverWait wait1 = new WebDriverWait(driver, 10);
	    WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[3]/div[5]/div[1]/table/thead/tr/th[8]/div/div/button")));
	    element.click();

	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[1]/div/div/input")));

	    // 填写商品信息
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[1]/div/div/input")).sendKeys("");
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[2]/div/div/input")).sendKeys("12000");

	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[4]/div/div/input")).sendKeys("10");
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[7]/div[1]")).sendKeys("苹果笔记本电脑");

	    // 上传图片
	    WebElement uploadElement = driver.findElement(By.xpath("//*[@id='goods']/div[6]/div/div[2]/div/form/div[3]/div/div/div[1]/div/input"));
	    uploadElement.sendKeys("C://Users//DELL//Desktop//测试//OIP-C.jpg");

	    // 点击 Cascader 输入框以展开菜单
	    WebElement cascaderTrigger = driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[5]/div/div/div/input"));
	    cascaderTrigger.click();

	    // 创建 Actions 对象
	    Actions actions = new Actions(driver);

	    // 点击发布按钮
	    WebDriverWait wait5 = new WebDriverWait(driver, 10);
	    WebElement publishButton = wait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[3]/span/button[2]")));
	    publishButton.click();

	    // 等待元素消失
	    WebDriverWait waitDisappear = new WebDriverWait(driver, 10);
	    boolean isGone = waitDisappear.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"goods\"]/div[6]/div")));

	    // 验证是否消失
	    assertTrue("商品发布后，发布界面未消失", isGone);
	    }

    // 测试用例 TC15-3: 价格为负
    @Test
    public void testPublishProductPriceNegative() {
    	loginAsSeller();

	    WebDriverWait wait1 = new WebDriverWait(driver, 10);
	    WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[3]/div[5]/div[1]/table/thead/tr/th[8]/div/div/button")));
	    element.click();

	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[1]/div/div/input")));

	    // 填写商品信息
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[1]/div/div/input")).sendKeys("MacBook Pro");
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[2]/div/div/input")).sendKeys("-12000");

	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[4]/div/div/input")).sendKeys("10");
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[7]/div[1]")).sendKeys("苹果笔记本电脑");

	    // 上传图片
	    WebElement uploadElement = driver.findElement(By.xpath("//*[@id='goods']/div[6]/div/div[2]/div/form/div[3]/div/div/div[1]/div/input"));
	    uploadElement.sendKeys("C://Users//DELL//Desktop//测试//OIP-C.jpg"); // 替换为图片的绝对路径

	    // 点击 Cascader 输入框以展开菜单
	    WebElement cascaderTrigger = driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[5]/div/div/div/input"));
	    cascaderTrigger.click();

	    // 创建 Actions 对象
	    Actions actions = new Actions(driver);

	    // 点击发布按钮
	    WebDriverWait wait5 = new WebDriverWait(driver, 10);
	    WebElement publishButton = wait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[3]/span/button[2]")));
	    publishButton.click();

	    // 等待元素消失
	    WebDriverWait waitDisappear = new WebDriverWait(driver, 10);
	    boolean isGone = waitDisappear.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"goods\"]/div[6]/div")));

	    // 验证是否消失
	    assertTrue("商品发布后，发布界面未消失", isGone);
    }

    // 测试用例 TC15-4: 库存为负
    @Test
    public void testPublishProductStockNegative() {
    	loginAsSeller();

	    WebDriverWait wait1 = new WebDriverWait(driver, 10);
	    WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[3]/div[5]/div[1]/table/thead/tr/th[8]/div/div/button")));
	    element.click();

	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[1]/div/div/input")));

	    // 填写商品信息
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[1]/div/div/input")).sendKeys("MacBook Pro");
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[2]/div/div/input")).sendKeys("12000");

	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[4]/div/div/input")).sendKeys("-1");
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[7]/div[1]")).sendKeys("苹果笔记本电脑");

	    // 上传图片
	    WebElement uploadElement = driver.findElement(By.xpath("//*[@id='goods']/div[6]/div/div[2]/div/form/div[3]/div/div/div[1]/div/input"));
	    uploadElement.sendKeys("C://Users//DELL//Desktop//测试//OIP-C.jpg"); // 替换为图片的绝对路径

	    // 点击 Cascader 输入框以展开菜单
	    WebElement cascaderTrigger = driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[5]/div/div/div/input"));
	    cascaderTrigger.click();

	    // 创建 Actions 对象
	    Actions actions = new Actions(driver);

	    // 点击发布按钮
	    WebDriverWait wait5 = new WebDriverWait(driver, 10);
	    WebElement publishButton = wait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[3]/span/button[2]")));
	    publishButton.click();

	    // 等待元素消失
	    WebDriverWait waitDisappear = new WebDriverWait(driver, 10);
	    boolean isGone = waitDisappear.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"goods\"]/div[6]/div")));

	    // 验证是否消失
	    assertTrue("商品发布后，发布界面未消失", isGone);
    }

    // 测试用例 TC15-5: 所有字段为空
    @Test
    public void testPublishProductAllFieldsEmpty() {
    	loginAsSeller();

	    WebDriverWait wait1 = new WebDriverWait(driver, 10);
	    WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[3]/div[5]/div[1]/table/thead/tr/th[8]/div/div/button")));
	    element.click();

	    WebDriverWait wait = new WebDriverWait(driver, 10);
	    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[1]/div/div/input")));

	    // 填写商品信息
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[1]/div/div/input")).sendKeys("");
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[2]/div/div/input")).sendKeys("");

	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[4]/div/div/input")).sendKeys("");
	    driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[7]/div[1]")).sendKeys("");

	    // 上传图片
	    WebElement uploadElement = driver.findElement(By.xpath("//*[@id='goods']/div[6]/div/div[2]/div/form/div[3]/div/div/div[1]/div/input"));
	    uploadElement.sendKeys("C://Users//DELL//Desktop//测试//OIP-C.jpg"); // 替换为图片的绝对路径

	    // 点击 Cascader 输入框以展开菜单
	    WebElement cascaderTrigger = driver.findElement(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[2]/div/form/div[5]/div/div/div/input"));
	    cascaderTrigger.click();

	    // 创建 Actions 对象
	    Actions actions = new Actions(driver);

	    // 点击发布按钮
	    WebDriverWait wait5 = new WebDriverWait(driver, 10);
	    WebElement publishButton = wait5.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"goods\"]/div[6]/div/div[3]/span/button[2]")));
	    publishButton.click();

	    // 等待元素消失
	    WebDriverWait waitDisappear = new WebDriverWait(driver, 10);
	    boolean isGone = waitDisappear.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@id=\"goods\"]/div[6]/div")));

	    // 验证是否消失
	    assertTrue("商品发布后，发布界面未消失", isGone);
    }
    
    // 辅助方法，用于登录卖家
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
