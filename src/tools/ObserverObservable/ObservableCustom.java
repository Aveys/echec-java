package tools.ObserverObservable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lbl on 04/11/2015.
 */
public abstract class ObservableCustom {

    public List<ObserverCustom> listObserver = new ArrayList<>();


    public void addObserver(ObserverCustom obs){
        listObserver.add(obs);
    }

    public abstract void notifyAllObserver();

}
