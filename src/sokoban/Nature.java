/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokoban;

/**
 *
 * @author clbonnot
 */
enum Nature {

    WALL(1, "Mur", "#"),
    VOID(2, "Vide", "."),
    BOX(3, "Caisse", "C"),
    PERSON(4, "Joueur", "P"),
    TARGET(5,"Cible","X");
    
    private int id;
    private String object;
    private String symbol;

    Nature(int id, String objet, String symbol) {
        this.id = id;
        this.object = objet;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }
    public String getObjet() {
        return object;
    }
    public String getSymbol() {
        return symbol; 
    }
}
