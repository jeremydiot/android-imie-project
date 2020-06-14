package com.jdiot.projetfinal.pojo;

public class Option {
    public int id;
    public String nom;
    public int prix;

    public Option(int id, String nom, int prix) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
    }

    public String toString(String moneySymbol){
        return this.nom+" "+Integer.toString(this.prix)+moneySymbol+"\n";
    }

}
