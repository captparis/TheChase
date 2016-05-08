package memento;

public class Caretaker {

    private GameMemento memento;

    public GameMemento getMemento() {
        return memento;
    }

    public void setMemento(GameMemento memento) {
        this.memento = memento;
    }
}
