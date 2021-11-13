import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class PrimerTest {

    // atributo con una referencia al WebDriver
    private static WebDriver webDriver;

    // antes de ejecutar todas las pruebas
    @BeforeAll
    public static void preparaFixture() {
        // crea un navegador con soporte a Javascript
        webDriver = new HtmlUnitDriver(true);
    }

    @Test
    public void cargaPagina() {
        // carga la página de Google
        webDriver.get("https://www.google.com");
        // revisa que el título de la página sea "Google"
        Assertions.assertEquals("Google", webDriver.getTitle());
    }

    // prueba que se ingresa un valor en el campo de texto
    @Test
    public void ingresaDatoEnCampoDeTexto() {
        
        // va a la página de Google
        webDriver.get("https://www.google.com");
        
        // busca el la caja de texto con name=q        
        WebElement elemento = webDriver.findElement(
                By.cssSelector("input[name=q]")
            );

        // escribe en la caja de texto
        elemento.sendKeys("Selenium");

        // revisa que el valor del campo coincida con el texto
        Assertions.assertEquals(
                "Selenium", 
                elemento.getAttribute("value")
            );
    }

    // luego de ejecutar todas las pruebas
    @AfterAll
    public static void liberaRecursos() {
        // cierra el navegador
        webDriver.close();
    }
   
}