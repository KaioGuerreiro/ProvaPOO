package Control;

import Model.Venda;
import Model.ProdRelatorio;

import java.util.ArrayList;

public class ObjToStringControl {

    static String relatorioObj(ArrayList<Object> objects){
        ArrayList<Venda> tmpVenda = new ArrayList<Venda>();

        if(objects.isEmpty()) {
            return null;
        }

        Object obj = objects.getFirst();

        //Verifica se todo o conteudo do vetor é igual.
        for (Object o : objects){
            if (!obj.getClass().equals(o.getClass())){
                return null;
            }

        }

        if (obj instanceof ProdRelatorio){
            StringBuilder result = new StringBuilder();

            //Para cada um do vetor, criar uma linha.
            objects.forEach(pr -> {
                ProdRelatorio tmpPr = (ProdRelatorio) pr; //Converte os Objects em ProdRelatorio.

                result.append(tmpPr.nomeCategoria + ";" + tmpPr.prodCod + ";" + tmpPr.qntEstoque + ";" + tmpPr.qntMinEstoque +
                        ";" + tmpPr.preco + ";" + tmpPr.nomeFornecedor + ";" + tmpPr.contatoFornecedor + ";" +
                        (tmpPr.qntEstoque < tmpPr.qntMinEstoque ? "BAIXO ESTOQUE" : null) +
                        "\n");

            });

            return result.toString();
        }
        else if (obj instanceof Venda){
            objects.forEach(v -> {
                Venda tmpV = (Venda) v; //Converte os Objects em Venda.

                String IdVendedor = Integer.toString(tmpV.getVendedor().getId());
                String nomeVendedr = tmpV.getVendedor().toString();
            });
        }

        return "nothing";
    }
}
