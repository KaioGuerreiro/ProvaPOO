package Control;

import Model.Venda;

import java.util.ArrayList;
import java.util.Objects;

public class ObjToStringControl {
    static String relatorioObj(ArrayList<Objects> objects){
        Venda venda = new Venda();
        if(objects.isEmpty()) {
            return null;
        }


        return "teste";
    }
}
