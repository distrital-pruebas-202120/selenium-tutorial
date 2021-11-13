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

    // realiza una búsqueda en Google
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
        Assertions.assertNotEquals(
                "https://www.google.com", 
                webDriver.getCurrentUrl()
            );

        // localiza el primero de los resultados
        WebElement tituloPrimerResultado = webDriver.findElement(
              By.cssSelector(".g h3")
            );
        
        // Revisa que el título tenga el texto
        String texto = tituloPrimerResultado.getText();
        Assertions.assertTrue(texto.contains("Selenium"));

    }

    // luego de ejecutar todas las pruebas
    @AfterAll
    public static void liberaRecursos() {
        // cierra el navegador
        webDriver.close();
    }
   
}