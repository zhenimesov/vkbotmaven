package org.example.command.RPcommand;

import org.example.gson.parser.modules.People;

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
