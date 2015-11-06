package tools.ObserverObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * Class permettant d'implémenter le pattern Observer/Observable
 */
public abstract class ObservableCustom {

    public List<ObserverCustom> listObserver = new ArrayList<>();


    public void addObserver(ObserverCustom obs){
        listObserver.add(obs);
    }

    public abstract void notifyAllObserver();

}
