package tools.ObserverObservable;

import model.PieceIHM;

import java.util.List;

/**
 * Interface permettant d'implémenter le pattern Observer/Observable
 */
public interface ObserverCustom {

    void update(List<PieceIHM> listPieces);

}
