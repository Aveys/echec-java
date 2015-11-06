package model;

import tools.ObserverObservable.ObservableCustom;
import tools.ObserverObservable.ObserverCustom;

import java.util.ArrayList;
import java.util.List;

/**
 *Classe permettant de rendre observable le modèle
 */
public class ChessGame extends ObservableCustom{

    private Echiquier echiquier;

    public ChessGame(){

        this.echiquier = new Echiquier();
    }

    /**
     * Permet de déplacer une piéce connaissant ses coordonnées initiales vers ses
     * coordonnées finales en vérifiant si le déplacement est possible puis change de joueur.
     * @param xInit position initiale X
     * @param yInit position initiale Y
     * @param xFinal position finale X
     * @param yFinal position finale Y
     * @return true si le déplacement est effectué, false sinon
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
     * Notifie les observer pour qu'ils initialisent leur données
     */
    public void init(){
        this.notifyAllObserver();
    }

    public boolean isEnd(){
        return echiquier.isEnd();
    }

    /***
     * Message de l'échiquier suite à une action sur celui-ci
     * @return Message de l'échiquier
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
     * Liste les déplacements qu'une pièce peut effectuer
     * @param xInit position de départ X
     * @param yInit position de départ Y
     * @return Liste de coordonnées possibles
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
     * @param promotion Type de piece à promouvoir
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