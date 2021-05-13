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
class Case {

    private int x, y;
    private Nature caseNature;

    Case(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setNature(Nature nature) {
        caseNature = nature;
    }

    public int getCaseX() {
        return x;
    }

    public int getCaseY() {
        return y;
    }

    public Nature getNature() {
        return caseNature;
    }
    
}
