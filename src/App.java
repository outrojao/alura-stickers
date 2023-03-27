import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        
       for (Map<String,String> filme : listaDeFilmes) {
        System.out.println("\u001b[1m Título: \u001b[m" + filme.get("title"));
        System.out.println("\u001b[1m Imagem: \u001b[m" + filme.get("image"));
        System.out.println("\u001b[30;1m \u001b[43;1m Nota: \u001b[m " + filme.get("imDbRating"));

        double classificacao = Double.parseDouble(filme.get("imDbRating"));
        int numeroDeEstrelas = (int) classificacao;
        for (int s = 1; s <= numeroDeEstrelas; s++) {
            System.out.print("⭐");
        }
        System.out.println("\n");
       }
    }
}
