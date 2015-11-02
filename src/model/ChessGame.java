package model;

import java.util.Observable;

/**
 * Created by arthurveys on 02/11/15 for ProjetDP2.
 */
public class ChessGame extends Observable{

    private Echiquier echiquier;

    public ChessGame(){

        this.echiquier = new Echiquier();
    }

    public boolean move (int xInit, int yInit, int xFinal, int yFinal){

        boolean moved = false;

        if(echiquier.isMoveOk(xInit,yInit,xFinal,yFinal)){
            moved = echiquier.move(xInit,yInit,xFinal,yFinal);
        }
        else{
            System.out.println("Mouvement impossible");
        }
        return moved;
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


}