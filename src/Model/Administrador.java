package Model;

public class Administrador extends Pessoa {
    private boolean canGerUser;

    public boolean isCanGerUser() {
        return canGerUser;
    }

    public void setCanGerUser(boolean canGerUser) {
        this.canGerUser = canGerUser;
    }
}

