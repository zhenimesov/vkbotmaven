package kz.tamoha.vkbotmaven.command.RPcommand;

public enum enumOfRp {
    уебать(" Уебал игрока"), поцеловать(" Поцеловал игрока"), обнять(" Обнял игрока");

    private String doIt;
    enumOfRp(String doIt){
        this.doIt = doIt;
    }

    public String getDoIt() {
        return doIt;
    }
}
