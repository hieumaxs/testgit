import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestDatHang {

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
        datHang("Nguyễn Thị A", "số 6,ngách 40 ngõ 3 Thái Hà Đống Đa Hà Nội", "0389525230", "nguyenthia@gmail.com");

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div/section/div/div/div/div/div/div/div/div/div/div/p[1]"));
        String actual = tag.getText();
        String expected ="Cảm ơn bạn. Đơn hàng của bạn đã được nhận.";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void Run2() throws InterruptedException {
        datHang("", "", "", "");

        WebElement tag1 = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div/section/div/div/div/div/div/div/div/div/div/form/div[1]/ul/li[1]"));
        String actual1 = tag1.getText();
        String expected1 ="Mục Họ tên: là mục bắt buộc.";
        Assert.assertEquals(actual1, expected1);

        WebElement tag2 = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div/section/div/div/div/div/div/div/div/div/div/form/div[1]/ul/li[2]"));
        String actual2 = tag2.getText();
        String expected2 ="Mục Địa chỉ: là mục bắt buộc.";
        Assert.assertEquals(actual2, expected2);

        WebElement tag3 = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div/section/div/div/div/div/div/div/div/div/div/form/div[1]/ul/li[3]"));
        String actual3 = tag3.getText();
        String expected3 ="Mục Số điện thoại: là mục bắt buộc.";
        Assert.assertEquals(actual3, expected3);

        WebElement tag4 = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div/section/div/div/div/div/div/div/div/div/div/form/div[1]/ul/li[4]"));
        String actual4 = tag4.getText();
        String expected4 ="Mục Địa chỉ email: là mục bắt buộc.";
        Assert.assertEquals(actual4, expected4);
    }

    @Test
    public void Run3() throws InterruptedException {
        datHang("Nguyễn Thị A", "số 6,ngách 40 ngõ 3 Thái Hà Đống Đa Hà Nội", "aaaabbbbbb", "nguyenthia@gmail.com");

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div/section/div/div/div/div/div/div/div/div/div/form/div[1]/ul/li"));
        String actual = tag.getText();
        String expected ="Mục Số điện thoại: không phải là số điện thoại hợp lệ.";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void Run4() throws InterruptedException {
        datHang("Nguyễn Thị A", "số 6,ngách 40 ngõ 3 Thái Hà Đống Đa Hà Nội", "1234567891011", "nguyenthia@gmail.com");

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div/section/div/div/div/div/div/div/div/div/div/div/p[1]"));
        String actual = tag.getText();
        String expected ="Mục Số điện thoại: không phải là số điện thoại hợp lệ.";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void Run5() throws InterruptedException {
        datHang("Nguyễn Thị A", "số 6,ngách 40 ngõ 3 Thái Hà Đống Đa Hà Nội", "0389525230", "nguyenthiagmail.com");

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div/section/div/div/div/div/div/div/div/div/div/form/div[1]/ul/li"));
        String actual = tag.getText();
        String expected ="Địa chỉ email thanh toán không hợp lệ";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void Run6() throws InterruptedException {
        datHang("Nguyễn Thị A", "số 6,ngách 40 ngõ 3 Thái Hà Đống Đa Hà Nội", "0389525230", "nguyenthia@");

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div/section/div/div/div/div/div/div/div/div/div/form/div[1]/ul/li"));
        String actual = tag.getText();
        String expected ="Địa chỉ email thanh toán không hợp lệ";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void Run7() throws InterruptedException {
        datHang("Nguyễn Thị A", "số 6,ngách 40 ngõ 3 Thái Hà Đống Đa Hà Nội", "0389525230", "nguyenthia!@gmail.com");

        WebElement tag = chromeDriver.findElement(By.xpath("/html/body/div[2]/div/div/section/div/div/div/div/div/div/div/div/div/div/p[1]"));
        String actual = tag.getText();
        String expected ="Địa chỉ email thanh toán không hợp lệ";
        Assert.assertEquals(actual, expected);
    }

    public void datHang(String hoTen, String diaChi, String sdt, String email) throws InterruptedException {
        try{
            chromeDriver.get("https://gsports.vn/mua/mu-bao-hiem-nua-dau-carbon-x-gangster/");

            WebElement btnThem = chromeDriver.findElement(By.xpath("/html/body/div[3]/div/section[2]/div/div/div[1]/div/div/section[1]/div/div/div[2]/div/div/div[4]/div/div/form/button"));
            btnThem.click();

            Thread.sleep(3000);

            WebElement inputHoTen = chromeDriver.findElement(By.xpath("//*[@id=\"billing_first_name\"]"));
            inputHoTen.sendKeys(hoTen);

            WebElement inputDiaChi =chromeDriver.findElement(By.xpath("//*[@id=\"billing_address_1\"]"));
            inputDiaChi.sendKeys(diaChi);

            WebElement inputSDT = chromeDriver.findElement(By.xpath("//*[@id=\"billing_phone\"]"));
            inputSDT.sendKeys(sdt);

            WebElement inputEmail = chromeDriver.findElement(By.xpath("//*[@id=\"billing_email\"]"));
            inputEmail.sendKeys(email);

            Thread.sleep(1000);

            WebElement btnDatHang = chromeDriver.findElement(By.xpath("//*[@id=\"place_order\"]"));
            btnDatHang.click();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        Thread.sleep(15000);
    }
}
