package inventoryData;


import fileOperations.Observer;

public interface Subject {

    void registerObserver(Observer observer);

}
