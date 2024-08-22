import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestGioHang {
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
        GioHang("5");

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[2]/div/div/div/div/div[2]/div/div/div/div/section/div[2]/div/div/div/div/div[2]/div/div/div/div[1]/div"));
        String actual = tag.getText();
        String expected ="Giỏ hàng đã được cập nhật.";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void Run2() throws InterruptedException {
        GioHang("-5");

        try{
            WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[2]/div/div/div/div/div[2]/div/div/div/div/section/div[2]/div/div/div/div/div[2]/div/div/div/div[1]/div"));
            String actual = tag.getText();
            String expected ="Value must be greater than or equal to 0.";
            Assert.assertEquals(actual, expected);
        }catch (Exception ex){

        }
    }

    @Test
    public void Run3() throws InterruptedException {
        GioHang("0");

        WebElement tag1 = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[2]/div/div/div/div/div[2]/div/div/div/div/section/div[2]/div/div/div/div/div[2]/div/div/div/div[1]/div"));
        String actual1 = tag1.getText();
        String expected1 ="Giỏ hàng đã được cập nhật.";
        Assert.assertEquals(actual1, expected1);

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[2]/div/div/div/div/div[2]/div/div/div/div/section/div[2]/div/div/div/div/div[2]/div/div/div/div/p"));
        String actual = tag.getText();
        String expected ="Chưa có sản phẩm nào trong giỏ hàng.";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void Run4() throws InterruptedException {
        GioHang("");

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[2]/div/div/div/div/div[2]/div/div/div/div/section/div[2]/div/div/div/div/div[2]/div/div/div/div[1]/div"));
        String actual = tag.getText();
        String expected ="Số lượng không được để trống.";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void Run5() throws InterruptedException {
        GioHang("1000");

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[2]/div/div/div/div/div[2]/div/div/div/div/section/div[2]/div/div/div/div/div[2]/div/div/div/div[1]/div"));
        String actual = tag.getText();
        String expected ="Số lượng không hợp lệ";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void Run6() throws InterruptedException {
        GioHang("e");

        try{
            WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[2]/div/div/div/div/div[2]/div/div/div/div/section/div[2]/div/div/div/div/div[2]/div/div/div/div[1]/div"));
            String actual = tag.getText();
            String expected ="Please enter a number";
            Assert.assertEquals(actual, expected);
        }catch (Exception ex){

        }
    }

    public void GioHang(String data) throws InterruptedException {
        try{
            chromeDriver.get("https://gsports.vn/mua/mu-bao-hiem-nua-dau-carbon-x-gangster/");

            WebElement btnThem = chromeDriver.findElement(By.xpath("/html/body/div[3]/div/section[2]/div/div/div[1]/div/div/section[1]/div/div/div[2]/div/div/div[4]/div/div/form/button"));
            btnThem.click();

            chromeDriver.get("https://gsports.vn/gio-hang/");
            Thread.sleep(1000);

            WebElement inputSoLuong = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div/div/div/div/div/div[2]/div/div/div/div/section/div/div/div/div/div/div[2]/div/div/div/form/table/tbody/tr[1]/td[5]/div/input"));
            inputSoLuong.clear();
            inputSoLuong.sendKeys(data);

            WebElement btnCapNhat = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/section[2]/div[2]/div/div/div/div/div[2]/div/div/div/div/section/div[2]/div/div/div/div/div[2]/div/div/div/form/table/tbody/tr[2]/td/p[1]/button"));
            btnCapNhat.click();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        Thread.sleep(5000);
    }
}
