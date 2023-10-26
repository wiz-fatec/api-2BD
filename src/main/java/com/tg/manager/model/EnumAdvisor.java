package com.tg.manager.model;

public enum EnumAdvisor {
    GERSON("GERSON DA PENHA NETO"),
    MASANORI("FERNANDO MASANORI ASHIKAGA"),
    JEAN("JEAN CARLOS LOURENÇA COSTA"),
    SAKUE("EDUARDO SAKAUE"),
    MINEDA("EMANUEL MINEDA CARNEIRO"),
    WALMIR("JOSE WALMIR GONCALVES DUQUE");

    private String description;

    EnumAdvisor(String s) {
        this.description = s;
    }

    public String getDescription() {
        return description;
    }

    public static String validatorNameEnum(String name){
        if(name.contains("GERSON")){
            return EnumAdvisor.GERSON.getDescription();
        } else if (name.contains("MASANORI")) {
            return EnumAdvisor.MASANORI.getDescription();
        } else if (name.contains("JEAN")) {
            return EnumAdvisor.JEAN.getDescription();
        } else if (name.contains("SAKAUE")) {
            return EnumAdvisor.SAKUE.getDescription();
        } else if (name.contains("MINEDA")) {
            return EnumAdvisor.MINEDA.getDescription();
        } else if (name.contains("WALMIR")){
            return EnumAdvisor.WALMIR.getDescription();
        }
        throw new RuntimeException("Advisor not Found");

    }
}
