package sokobanPlayer;

/**
 *
 * @author Clément Bonnot
 */
public class Case {

    // Coordonnées de la case
    public int x, y;
    //Nature de la case
    private Nature caseNature;

    public Case(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Setter de la nature
     *
     * @param nature la nature à initialiser
     */
    public void setNature(Nature nature) {
        caseNature = nature;
    }

    /**
     * Guetter de la coordonée X d'une case
     *
     * @return la coordonnée X de la case
     */
    public int getCaseX() {
        return x;
    }

    /**
     * Guetter de la coordonée Y d'une case
     *
     * @return la coordonnée Y de la case
     */
    public int getCaseY() {
        return y;
    }

    /**
     * Guetter de la nature d'une cases
     *
     * @return la nature de la case
     */
    public Nature getNature() {
        return caseNature;
    }
}
