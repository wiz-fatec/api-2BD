package com.tg.manager.utils;

public enum ModelTGEnum {
    PORTIFOLIO("Portfólio (Exige participação em todos os 6 APIs)"),
    ARTICLE("Artigo Tecnológico ou Científico"),
    DISCIPLINE("Relatório Técnico - Disciplina (Somente para quem não pode participar de 6 APIs)"),
    INTERNSHIP("Relatório Técnico - Estágio (Somente para quem não pode participar de 6 APIs. Autorizado pela empresa)");

    private String description;

     ModelTGEnum(String description){
        this.description = description;
    }
    public String getDescription(){
         return description;
    }
}
