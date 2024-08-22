import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestTimKiem {
    ChromeDriver chromeDriver;

    @BeforeMethod
    public void Setup(){
        WebDriverManager.chromedriver().setup();
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
    }

    @AfterMethod
    public void CleanUp(){
        chromeDriver.quit();
    }

    @Test
    public void Run1() throws InterruptedException {
        timKiem("Mũ");

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[3]/div/div/div/div/div[2]/div/div/p"));
        String actual = tag.getText();
        String expected ="Hiển thị .* kết quả";
        Assert.assertTrue(actual.matches(expected));
    }

    @Test
    public void Run2() throws InterruptedException {
        timKiem("");

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[3]/div/div/div/div/div[2]/div/div/p"));
        String actual = tag.getText();
        String expected ="";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void Run3() throws InterruptedException {
        timKiem("M# bao h/iem");

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[3]/div/div/div/div/div[2]/div/div"));
        String actual = tag.getText();
        String expected ="Không có sản phầm nào";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void Run4() throws InterruptedException {
        timKiem("Mũ bảo hiểm đẹp chất lượng cao, bền, giá cả hợp lý, được ưa chuộng người dùng, được mua nhiều nhất");

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[3]/div/div/div/div/div[2]/div/div"));
        String actual = tag.getText();
        String expected ="Tên sản phẩm không quá 50 ký tự .* Không có sản phẩm nào";
        Assert.assertTrue(actual.matches(expected));
    }

    @Test
    public void Run5() throws InterruptedException {
        timKiem("123456789");

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[3]/div/div/div/div/div[2]/div/div"));
        String actual = tag.getText();
        String expected ="Không có sản phầm nào";
        Assert.assertEquals(actual, expected);
    }

    public void timKiem(String data) throws InterruptedException {
        try{
            chromeDriver.get("https://gsports.vn/");

            WebElement inputTimKiem = chromeDriver.findElement(By.id("woocommerce-product-search-field-0"));
            inputTimKiem.sendKeys(data);

            WebElement btnTimKiem = chromeDriver.findElement(By.xpath("/html/body/div[1]/div/section[3]/div[2]/div/div/div/div/div[3]/div/div/form/button"));
            btnTimKiem.click();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        Thread.sleep(3000);
    }
}
