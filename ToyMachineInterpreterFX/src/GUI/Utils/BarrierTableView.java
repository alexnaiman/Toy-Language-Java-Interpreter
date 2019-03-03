package GUI.Utils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class BarrierTableView {
    private SimpleIntegerProperty index;
    private SimpleIntegerProperty capacity;
    private SimpleStringProperty list;

    public BarrierTableView(SimpleIntegerProperty index, SimpleIntegerProperty capacity, SimpleStringProperty list) {
        this.index = index;
        this.capacity = capacity;
        this.list = list;
    }
    public BarrierTableView(int index, int capacity, String list) {
        this.index = new SimpleIntegerProperty(index);
        this.capacity = new SimpleIntegerProperty(capacity);
        this.list = new SimpleStringProperty(list);
    }

    public int getIndex() {
        return index.get();
    }

    public SimpleIntegerProperty indexProperty() {
        return index;
    }

    public void setIndex(int index) {
        this.index.set(index);
    }

    public int getCapacity() {
        return capacity.get();
    }

    public SimpleIntegerProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }

    public String getList() {
        return list.get();
    }

    public SimpleStringProperty listProperty() {
        return list;
    }

    public void setList(String list) {
        this.list.set(list);
    }
}
