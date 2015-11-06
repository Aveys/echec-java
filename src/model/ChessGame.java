package model;

import tools.ObserverObservable.ObservableCustom;
import tools.ObserverObservable.ObserverCustom;

import java.util.ArrayList;
import java.util.List;

/**
 *Classe permettant de rendre observable le mod�le
 */
public class ChessGame extends ObservableCustom{

    private Echiquier echiquier;

    public ChessGame(){

        this.echiquier = new Echiquier();
    }

    /**
     * Permet de d�placer une pi�ce connaissant ses coordonn�es initiales vers ses
     * coordonn�es finales en v�rifiant si le d�placement est possible puis change de joueur.
     * @param xInit position initiale X
     * @param yInit position initiale Y
     * @param xFinal position finale X
     * @param yFinal position finale Y
     * @return true si le d�placement est effectu�, false sinon
     *
     */
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

    /***
     * Notifie les observer pour qu'ils initialisent leur donn�es
     */
    public void init(){
        this.notifyAllObserver();
    }

    public boolean isEnd(){
        return echiquier.isEnd();
    }

    /***
     * Message de l'�chiquier suite � une action sur celui-ci
     * @return Message de l'�chiquier
     */
    public String getMessage(){
        return echiquier.getMessage();
    }

    /***
     * Couleur du joueur courant
     * @return couleur (BLANC/NOIR)
     */
    public Couleur getColorCurrentPlayer(){
        return echiquier.getColorCurrentPlayer();
    }

    public String toString(){
        return echiquier.toString();
    }

    /***
     * Liste les pieces disponibles du plateau
     * @return liste de pieces
     */
    public List<PieceIHM> getPiecesIHM(){
        return echiquier.getPiecesIHM();
    }

    /***
     * Liste les d�placements qu'une pi�ce peut effectuer
     * @param xInit position de d�part X
     * @param yInit position de d�part Y
     * @return Liste de coordonn�es possibles
     */
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

    /***
     * Transforme une piece en une autre
     * @param x position X de la piece
     * @param y position Y de la piece
     * @param promotion Type de piece � promouvoir
     */
    public void promotePiece(int x, int y, String promotion){

        echiquier.promotePiece(x,y,promotion);
    }

    @Override
    public void notifyAllObserver() {
        for(ObserverCustom obs : this.listObserver){
            obs.update(getPiecesIHM());
        }
    }
}