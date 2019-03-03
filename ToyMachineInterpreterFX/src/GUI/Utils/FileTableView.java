package GUI.Utils;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FileTableView {
    private SimpleIntegerProperty id;
    private SimpleStringProperty fileName;

    public FileTableView(SimpleIntegerProperty id, SimpleStringProperty fileName) {
        this.id = id;
        this.fileName = fileName;
    }

    public FileTableView(int id, String fileName) {
        this.id = new SimpleIntegerProperty(id);
        this.fileName = new SimpleStringProperty(fileName);
    }
    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getFileName() {
        return fileName.get();
    }

    public SimpleStringProperty fileNameProperty() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName.set(fileName);
    }
}
