package model;

import tools.ObserverObservable.ObservableCustom;
import tools.ObserverObservable.ObserverCustom;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by arthurveys on 02/11/15 for ProjetDP2.
 */
public class ChessGame extends ObservableCustom{

    private Echiquier echiquier;

    public ChessGame(){

        this.echiquier = new Echiquier();
    }

    public boolean move (int xInit, int yInit, int xFinal, int yFinal){

        System.out.println(xInit +":"+yInit+":"+xFinal+":"+yFinal);

        boolean moved = false;

        if(echiquier.isMoveOk(xInit,yInit,xFinal,yFinal)){
            moved = echiquier.move(xInit,yInit,xFinal,yFinal);
        }

        if(moved) echiquier.switchJoueur();

        this.notifyAllObserver();

        return moved;
    }

    public void init(){
        this.notifyAllObserver();
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

    public List<Coord> getListDep(int xInit, int yInit){

        List<Coord>  listCases = new ArrayList<>();
        int x,y;

        for(y=0;y<8;y++){
            for(x=0;x<8;x++){
                if(echiquier.isMoveOk(xInit,yInit,x,y)){
                    listCases.add(new Coord(x,y));
                }
            }
        }

        return listCases;
    }

    @Override
    public void notifyAllObserver() {
        for(ObserverCustom obs : this.listObserver){
            obs.update(getPiecesIHM());
        }
    }
}