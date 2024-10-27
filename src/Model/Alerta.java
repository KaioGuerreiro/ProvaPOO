package Model;

public class Alerta {
    private String descricao;
    private String dados;
    private boolean resolvido;

    public Alerta(String descricao, String dados, boolean resolvido) {
        this.descricao = descricao;
        this.dados = dados;
        this.resolvido = resolvido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDados() {
        return dados;
    }

    public void setDados(String dados) {
        this.dados = dados;
    }

    public boolean isResolvido() {
        return resolvido ? true : false;
    }

    public void setResolvido(boolean resolvido) {
        this.resolvido = resolvido;
    }
}
