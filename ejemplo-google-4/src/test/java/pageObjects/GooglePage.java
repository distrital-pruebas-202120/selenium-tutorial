package pageObjects;

import java.util.List;
import java.util.ArrayList;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GooglePage {

    WebDriver driver;

    public GooglePage(WebDriver driver) {
        this.driver = driver;
        this.driver.get("https://www.google.com");
    }

    public List<Resultado> buscar(String texto) {

        List<Resultado> resultados = new ArrayList<>();

        // va a Google
        driver.get("https://www.google.com");
        
        // localiza el campo de texto
        WebElement elemento = driver.findElement(
            By.cssSelector("input[name=q]")
            );
        // e ingresa el texto "Selenium"
        elemento.sendKeys("Selenium");

        // envia el formulario
        try {
            elemento.submit();            
        } catch (Exception e) {
            // ignora todo posible error de Javascript
        }

        // localiza todos los resultados
        List<WebElement> elementos = driver.findElements(
            By.cssSelector(".g")
            );
        
        // crea el listado de resultados
        for (WebElement item : elementos) {
            WebElement titulo = item.findElement(By.cssSelector("h3"));
            WebElement enlace = item.findElement(By.cssSelector("a"));

            if (enlace != null) {
                resultados.add (new Resultado(
                    titulo.getText(),
                    enlace.getAttribute("href")
                ));
            }

        }

        return resultados;

    }
}