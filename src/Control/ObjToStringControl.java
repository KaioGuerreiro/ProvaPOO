package Control;

import Model.ProdRelatorio;
import Model.ProdutoVenda;
import Model.Venda;

import java.util.ArrayList;

public class ObjToStringControl {

    static String relatorioObj(ArrayList<Object> objects) throws Exception {
        ArrayList<Venda> tmpVenda = new ArrayList<Venda>();

        if (objects.isEmpty()) {
            throw new Exception("Vetor de Object não pode ser vazio!");
        }

        Object obj = objects.getFirst();

        //Verifica se todo o conteudo do vetor é igual.
        for (Object o : objects) {
            if (!obj.getClass().equals(o.getClass())) {
                throw new Exception("Todos os Objects do vetor devem ser do mesmo tipo!");
            }
        }

        StringBuilder result = new StringBuilder();

        if (obj instanceof ProdRelatorio) {
            //Para cada um do vetor, criar uma linha.
            objects.forEach(pr -> {
                ProdRelatorio tmpPr = (ProdRelatorio) pr; //Converte os Objects em ProdRelatorio.

                result.append(tmpPr.nomeCategoria + ";" + tmpPr.prodCod + ";" + tmpPr.qntEstoque + ";" + tmpPr.qntMinEstoque +
                        ";" + tmpPr.preco + ";" + tmpPr.nomeFornecedor + ";" + tmpPr.contatoFornecedor + ";" +
                        (tmpPr.qntEstoque < tmpPr.qntMinEstoque ? "BAIXO ESTOQUE" : null) +
                        "\n");

            });
        } else if (obj instanceof Venda) {
            /* RESULTADO TXT
                IDVendedor | nomeVendedor | IDCliente | nomeCliente | destinoCliente | data
                    cod | nome | preco | qntVendida | subTotal
                    [ . . . ]
                totalVenda
            */

            float totalVenda = 0;

            for (Object v : objects) {
                Venda tmpV = (Venda) v; //Converte os Objects em Venda.

                //Cabecalho
                result.append(tmpV.getVendedor().getId() + ";" + tmpV.getVendedor().getNome() + ";" + tmpV.getCliente().getId() +
                        ";" + tmpV.getCliente().getNome() + ";" + tmpV.getCliente().getDestino() + ";" + tmpV.getData() + "\n");

                //Produtos
                StringBuilder strProds = new StringBuilder();

                for (ProdutoVenda c : tmpV.getCarrinho()) {
                    float subTotal = c.getPreco() * c.getQntVendida();
                    totalVenda += subTotal;

                    strProds.append(c.getCodigo() + ";" + c.getNome() + ";" + c.getPreco() + ";" + c.getQntVendida() + ";" + subTotal + "\n");
                }
            }
            ;

            result.append(totalVenda + "\n");

        } else throw new Exception("O tipo de Object não foi reconhecido!");

        return result.toString();
    }
}
