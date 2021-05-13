/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sokobanPlayer;

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
    
    final private int ID;
    final private String OBJECT;
    final private String SYMBOL;

    Nature(int id, String objet, String symbol) {
        this.ID = id;
        this.OBJECT = objet;
        this.SYMBOL = symbol;
    }

    public int getId() {
        return ID;
    }
    public String getObjet() {
        return OBJECT;
    }
    public String getSymbol() {
        return SYMBOL; 
    }
}
