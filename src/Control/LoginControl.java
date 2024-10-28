package Control;

import Model.Cliente;
import Model.Pessoa;
import Persistence.Dados;

import java.util.ArrayList;

public class LoginControl {
    public static Integer autLogin(Integer codUser) {
        if (Dados.getPessoas().isEmpty()) // Verifica se a lista de pessoas esta vazia.
            return -1;

        ArrayList<Pessoa> ps = Dados.getPessoas();

        for (int i = 0; i < ps.size(); i++) {
            if (ps.get(i).getId().equals(codUser)) {
                if (ps.get(i) instanceof Cliente) { // Verifica se for cliente ja pula.
                    continue;
                }

                return i; // Retorna se for um Vendedor ou Administrador.
            }
        }

        return -1; // Retorna quando só tem clientes na lista;
    }


    public static Integer loginCod() {
        Integer codL = SafeInputControl.sInteger("Login", "Infome seu Codigo");
        if (codL == null) return null;

        Integer indexUser = LoginControl.autLogin(codL);

        if (indexUser > -1) {
            Dados.setUserLogged(indexUser);
            return indexUser;
        }

        return null;
    }
}
