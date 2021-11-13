package tests;

import java.util.List;
import org.junit.jupiter.api.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import pageObjects.GooglePage;
import pageObjects.Resultado;

public class SegundoTest {
   
    private static WebDriver webDriver;

    // antes de ejecutar todas las pruebas
    @BeforeAll
    public static void preparaFixture() {
        // crea un navegador con soporte a Javascript
        webDriver = new HtmlUnitDriver(true);
    }
    
    @Test
    public void pruebaGoogle() {

        // carga la página de Google
        GooglePage google = new GooglePage(webDriver);

        // obtiene listado de resultados
        List<Resultado> resultados = google.buscar("Selenium");
        Assertions.assertNotEquals(0, resultados.size());
        
        // revisar el primer resultado
        Resultado resultado = resultados.get(0);
        Assertions.assertTrue(resultado.getTitulo().contains("Selenium"));

    }

    // luego de ejecutar todas las pruebas
    @AfterAll
    public static void liberaRecursos() {
        webDriver.close();
    }

}
