package GUI.Utils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class SymbolTableView {
    private SimpleIntegerProperty value;
    private SimpleStringProperty variableName;

    public SymbolTableView(SimpleIntegerProperty value, SimpleStringProperty variableName) {
        this.value = value;
        this.variableName = variableName;
    }

    public SymbolTableView(String valueName, int value){
        this.value = new SimpleIntegerProperty(value);
        this.variableName = new SimpleStringProperty(valueName);
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

    public String getVariableName() {
        return variableName.get();
    }

    public SimpleStringProperty variableNameProperty() {
        return variableName;
    }

    public void setVariableName(String variableName) {
        this.variableName.set(variableName);
    }
}
