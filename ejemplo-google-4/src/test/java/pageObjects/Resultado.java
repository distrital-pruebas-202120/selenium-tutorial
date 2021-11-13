package pageObjects;

public class Resultado {

    private String titulo;
    private String url;

    public Resultado(String titulo, String url) {
        this.titulo = titulo;
        this.url = url;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getUrl() {
        return url;
    }

}