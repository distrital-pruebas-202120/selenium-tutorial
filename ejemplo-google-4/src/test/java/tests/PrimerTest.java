package tests;

import java.util.List;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class PrimerTest {

    private static WebDriver webDriver;

    @BeforeAll
    public static void preparaFixture() {
        // crea un navegador con soporte a Javascript
        webDriver = new HtmlUnitDriver(true);
    }

    @Test
    public void cargaPagina() {
        webDriver.get("https://www.google.com");
        Assertions.assertEquals("Google", webDriver.getTitle());
    }

    @Test
    public void ingresaDatoEnCampoDeTexto() {
        
        webDriver.get("https://www.google.com");
        
        WebElement elemento = webDriver.findElement(
              By.cssSelector("input[name=q]")
            );

        System.out.println(elemento.getClass().getName());

        elemento.sendKeys("Selenium");
        Assertions.assertEquals("Selenium", 
            elemento.getAttribute("value"));
    }


    @Test
    public void realizaBusqueda() {
        
        // va a Google
        webDriver.get("https://www.google.com");
        
        // localiza el campo de texto
        WebElement elemento = webDriver.findElement(
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
      
        // revisa que haya cambiado de página
        Assertions.assertNotEquals("https://www.google.com", webDriver.getCurrentUrl());

        // localiza el primero de los resultados
        WebElement tituloPrimerResultado = webDriver.findElement(
              By.cssSelector(".g h3")
            );
        
        // Revisa que el título tenga el texto
        String texto = tituloPrimerResultado.getText();
        Assertions.assertTrue(texto.contains("Selenium"));

    }



    @AfterAll
    public static void liberaRecursos() {
        webDriver.close();
    }
   
}