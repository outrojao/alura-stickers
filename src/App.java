import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {
        API api = API.NASA_APOD;

        String url = api.getUrl();
        ExtratorDeConteudo extrator = api.getExtrator();

        var http = new ClientHttp();
        String json = http.buscaDados(url);
        
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        var diretorio = new File("figurinhas/");
        diretorio.mkdir();

        for (Conteudo conteudo : conteudos) {

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "figurinhas/" + conteudo.titulo() + ".png";

            geradora.criar(inputStream, nomeArquivo);

            System.out.println("\u001b[1m Título: \u001b[m" + conteudo.titulo());
            System.out.println();
        }
    }
}
