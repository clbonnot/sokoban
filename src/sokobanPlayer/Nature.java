package sokobanPlayer;

/**
 *
 * @author Clément Bonnot
 */
public enum Nature {

    WALL(1, "Mur", "#"),
    VOID(2, "Vide", "."),
    BOX(3, "Caisse", "C"),
    PERSON(4, "Joueur", "P"),
    TARGET(5, "Cible", "X");

    final private int ID;
    final private String OBJECT;
    final private String SYMBOL;

    Nature(int id, String objet, String symbol) {
        this.ID = id;
        this.OBJECT = objet;
        this.SYMBOL = symbol;
    }

    /**
     * Guetter de l'id d'une nature
     *
     * @return l'id correspondant à la nature
     */
    public int getId() {
        return ID;
    }
    
    /**
     * Guetter de l'objet correspondant à la nature
     *
     * @return l'objet correspondant à la nature
     */
    public String getObjet() {
        return OBJECT;
    }
    /**
     * Guetter du symbol d'une nature
     *
     * @return le symbol correspondant à la nature
     */
    public String getSymbol() {
        return SYMBOL;
    }
}
