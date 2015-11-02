package model;

import java.util.List;
import java.util.Observable;

/**
 * Created by arthurveys on 02/11/15 for ProjetDP2.
 */
public class ChessGame extends Observable{

    private Echiquier echiquier;

    public ChessGame(){

        this.echiquier = new Echiquier();
        this.setChanged();
        this.notifyObservers();
    }

    public boolean move (int xInit, int yInit, int xFinal, int yFinal){

        boolean moved = false;

        if(echiquier.isMoveOk(xInit,yInit,xFinal,yFinal)){
            moved = echiquier.move(xInit,yInit,xFinal,yFinal);
        }

        if(moved) echiquier.switchJoueur();

        this.setChanged();
        this.notifyObservers();

        return moved;
    }

    public void init(){
        this.setChanged();
        this.notifyObservers();
    }

    public boolean isEnd(){
        return echiquier.isEnd();
    }

    public String getMessage(){
        return echiquier.getMessage();
    }

    public Couleur getColorCurrentPlayer(){
        return echiquier.getColorCurrentPlayer();
    }

    public String toString(){
        return echiquier.toString();
    }

    public List<PieceIHM> getPiecesIHM(){
        return echiquier.getPiecesIHM();
    }


}