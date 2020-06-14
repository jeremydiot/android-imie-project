package com.jdiot.projetfinal.pojo;

public class Equipement {
    public int id;
    public String nom;

    public Equipement(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public String toString(){
        return this.nom+"\n";
    }

}
