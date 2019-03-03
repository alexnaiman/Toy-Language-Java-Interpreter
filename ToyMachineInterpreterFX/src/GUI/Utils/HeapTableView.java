package GUI.Utils;

import javafx.beans.property.SimpleIntegerProperty;

public class HeapTableView {

    private SimpleIntegerProperty address, value;

    public HeapTableView(SimpleIntegerProperty address, SimpleIntegerProperty value) {
        this.address = address;
        this.value = value;
    }

    public HeapTableView(int address, int value) {
        this.value = new SimpleIntegerProperty(value);
        this.address = new SimpleIntegerProperty(address);

    }

    public int getAddress() {
        return address.get();
    }

    public SimpleIntegerProperty addressProperty() {
        return address;
    }

    public void setAddress(int address) {
        this.address.set(address);
    }

    public int getValue() {
        return value.get();
    }

    public SimpleIntegerProperty valueProperty() {
        return value;
    }

    public void setValue(int value) {
        this.value.set(value);
    }

}
