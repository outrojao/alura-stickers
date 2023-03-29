import java.util.List;
import java.util.Map;

public class ExtratorDeConteudoDoIMDB implements ExtratorDeConteudo {
    public List<Conteudo> extraiConteudos(String json){
        //? Extrai somente os dados desejados
        var parser = new JsonParser();
        List<Map<String, String>> listaDeAtributos
         = parser.parse(json);

        return listaDeAtributos.stream()
        .map(atributo -> new Conteudo(atributo.get("title").replace(":", " -"), atributo.get("image")))
        .toList();
    }
}
