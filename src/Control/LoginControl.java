package Control;

import Model.Cliente;
import Model.Pessoa;
import Persistence.Dados;

public class LoginControl {
    public static Integer autLogin(Integer codUser){
        if (Dados.getPessoas().isEmpty()) // Verifica se a lista de pessoas esta vazia.
            return null;

        for(Pessoa p : Dados.getPessoas()){
            if (p.getId().equals(codUser)){
                if (p instanceof Cliente){ // Verifica se for cliente ja pula.
                    continue;
                }

                return p.getId(); // Retorna se for um Vendedor ou Administrador.
            }

        }

        return -1; // Retorna quando só tem clientes na lista;
    }
}
