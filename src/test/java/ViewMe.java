import net.lightbody.bmp.proxy.ProxyServer;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ViewMe {

    private ProxyServer server;
    private WebDriver driver;

    String user = "denisv";
    String pass = "";
    String url = "e-apps.playtech.corp/MyTimesheet/Pages/ViewSingle.aspx";
    String project = "Client Mobile Platform";

    @Test
    public void testik1(){

        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("network.http.phishy-userpass-length", 255);
        profile.setPreference("network.automatic-ntlm-auth.trusted-uris", "playtech.corp");
        WebDriver driver = new FirefoxDriver(profile);
        driver.manage().timeouts().implicitlyWait(5 , TimeUnit.SECONDS);

        driver.get("http://"+user+":"+pass+"@"+url+"");

        List<WebElement> listReportedTimes = driver.findElements(By.xpath("//*[contains(@id, 'Total')]"));

        List<WebElement> listInputTimes = driver.findElements(By.xpath("//*[contains(@id, 'Day')]"));

        driver.findElement(By.xpath("//button[@title='Show All Items']")).click();
        WebElement selectProjectField = driver.findElement(By.xpath("//*[@class='ddlGridProjects']/following-sibling::input"));
        selectProjectField.sendKeys(project);


        for (int i = 0; i < listInputTimes.size(); i++ ) {
            if (listReportedTimes.get(i).getText().length() > 6) {
                listInputTimes.get(i).sendKeys(listReportedTimes.get(i).getText().substring(6));
            }
        }
        driver.findElement(By.xpath("//input[@text='Insert']")).click();
        System.out.println("Done!");
    }
}