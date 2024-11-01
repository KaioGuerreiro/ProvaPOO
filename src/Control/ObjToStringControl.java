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

                result.append(tmpPr.nomeCategoria + ";" + tmpPr.prodCod + ";" + tmpPr.nomeProd + ";" + tmpPr.qntEstoque + ";" + tmpPr.qntMinEstoque +
                        ";" + Utilities.fToBRL(tmpPr.preco) + ";" + tmpPr.nomeFornecedor + ";" + tmpPr.contatoFornecedor + ";" +
                        (tmpPr.qntEstoque < tmpPr.qntMinEstoque ? "Sim" : "Nao") +
                        "\n");

            });
        } else if (obj instanceof Venda) {
            /* RESULTADO TXT
                IDVendedor | nomeVendedor | IDCliente | nomeCliente | destinoCliente | data
                    categoria | cod | nome | preco | qntVendida | subTotal
                    [ . . . ]
                totalVenda
            */

            float totalGeral = 0f;

            for (Object v : objects) {
                float totalVenda = 0;
                int totProdutos = 0;
                Venda tmpV = (Venda) v; //Converte os Objects em Venda

                //Cabecalho
                result.append("Vendedor: " + tmpV.getVendedor().getId() + ";" + tmpV.getVendedor().getNome() + ";Cliente: " + tmpV.getCliente().getId() +
                        ";" + tmpV.getCliente().getNome() + ";Destino: " + tmpV.getCliente().getDestino() + ";" + tmpV.getData() + "\n");

                //Cabeçalho do produto
                result.append("Categoria;Codigo;Produto;Unitario;Quantidade;SubTotal\n");

                //Produtos
                StringBuilder strProds = new StringBuilder();

                for (ProdutoVenda c : tmpV.getCarrinho()) {
                    float subTotal = c.getPreco() * c.getQntVendida();
                    totalVenda += subTotal;
                    totProdutos += c.getQntVendida();

                    ;
                    strProds.append(c.getCategoria() + ";" + c.getCodigo() + ";" + c.getNome() + ";" +
                            Utilities.fToBRL(c.getPreco()) + ";" + c.getQntVendida() + ";" + Utilities.fToBRL(subTotal) + "\n");
                }

                result.append(strProds + "Totais Venda;;;;" + totProdutos + ";" + Utilities.fToBRL(totalVenda) + "\n\n");
                totalGeral += totalVenda;
            }

            result.append("TotalGeral:;;;;;" + Utilities.fToBRL(totalGeral) + "\n");


        } else throw new Exception("O tipo de Object não foi reconhecido!");

        return result.toString();
    }
}
