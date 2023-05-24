package tests.day15_POM;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.QualitydemyPage;
import utilities.ConfigReader;
import utilities.Driver;

public class C06_QualitydemyNegatifLoginTesti {

    @Test
    public void gecersizIsimSifreTesti(){
        // qualitydemy ana sayfaya gidin
        Driver.getDriver().get(ConfigReader.getProperty("qdUrl"));

        // login linkine tiklayin
        QualitydemyPage qualitydemyPage =new QualitydemyPage();
        qualitydemyPage.ilkLoginLinki.click();

        // gecersiz username ve sifre yi ilgili kutulara yazin
        qualitydemyPage.emailKutusu.sendKeys("qdGecersizUsename");
        qualitydemyPage.passwordKutusu.sendKeys("qdGecersizPassword");

        // login butonuna basin
        qualitydemyPage.loginButonu.click();

        // basarili olarak giris yapilamadigini test edin
        Assert.assertTrue(qualitydemyPage.emailKutusu.isEnabled());

        // sayfayi kapatin
        Driver.closeDriver();
    }

    @Test(groups = "smoke")
    public void gecersizIsimTesti(){
        // qualitydemy ana sayfaya gidin
        Driver.getDriver().get(ConfigReader.getProperty("qdUrl"));

        // login linkine tiklayin
        QualitydemyPage qualitydemyPage =new QualitydemyPage();
        qualitydemyPage.ilkLoginLinki.click();

        // gecersiz username ve gecerli sifre yi ilgili kutulara yazin
        qualitydemyPage.emailKutusu.sendKeys("qdGecersizUsename");
        qualitydemyPage.passwordKutusu.sendKeys("qdGecerliPassword");

        // login butonuna basin
        qualitydemyPage.loginButonu.click();

        // basarili olarak giris yapilamadigini test edin
        Assert.assertTrue(qualitydemyPage.emailKutusu.isEnabled());

        // sayfayi kapatin
        Driver.closeDriver();
    }

    @Test
    public void gecersizSifreTesti(){
        // qualitydemy ana sayfaya gidin
        Driver.getDriver().get(ConfigReader.getProperty("qdUrl"));

        // login linkine tiklayin
        QualitydemyPage qualitydemyPage =new QualitydemyPage();
        qualitydemyPage.ilkLoginLinki.click();

        // gecerli username ve gecersiz sifre yi ilgili kutulara yazin
        qualitydemyPage.emailKutusu.sendKeys("qdGecerliUsename");
        qualitydemyPage.passwordKutusu.sendKeys("qdGecersizPassword");

        // login butonuna basin
        qualitydemyPage.loginButonu.click();

        // basarili olarak giris yapilamadigini test edin
        Assert.assertTrue(qualitydemyPage.emailKutusu.isEnabled());

        // sayfayi kapatin
        Driver.closeDriver();
    }
}
