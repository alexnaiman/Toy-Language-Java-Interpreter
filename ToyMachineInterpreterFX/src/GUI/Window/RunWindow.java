package GUI.Window;

import Controller.Controller;
import GUI.Utils.BarrierTableView;
import GUI.Utils.FileTableView;
import GUI.Utils.HeapTableView;
import GUI.Utils.SymbolTableView;
import Model.FileStatement.FileData;
import Model.FileStatement.FileTable;
import Model.FileStatement.IFileTable;
import Model.GeneralStatement.IStatement;
import Model.ProgramState;
import Model.Threads.CyclicBarrier.BarrierData;
import Model.Threads.CyclicBarrier.BarrierTable;
import Model.Threads.CyclicBarrier.IBarrierTable;
import Model.Utils.*;
import Repository.Repository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class RunWindow {
    // symbol table
    @FXML
    private TableView<SymbolTableView> symbolTableView;

    @FXML
    private TableColumn<SymbolTableView, String> symbolTableVariableNameColumn;
    @FXML
    private TableColumn<SymbolTableView, Integer> symbolTableValueColumn;
    // heap
    @FXML
    private TableView<HeapTableView> heapView;
    @FXML
    private TableColumn<HeapTableView, Integer> heapAddressColumn;
    @FXML
    private TableColumn<HeapTableView, Integer> heapValueColumn;
    // file table
    @FXML
    private TableView<FileTableView> fileTableView;
    @FXML
    private TableColumn<FileTableView, Integer> fileTableIdentifierColumn;
    @FXML
    private TableColumn<FileTableView, String> fileTableNameColumn;

    // barrier table
    @FXML
    private TableView<BarrierTableView> barrierTableView;
    @FXML
    private TableColumn<BarrierTableView, Integer> barrierTableViewCapacityColumn;
    @FXML
    private TableColumn<BarrierTableView, Integer> barrierTableViewIndexColumn;
    @FXML
    private TableColumn<BarrierTableView, String> barrierTableViewListColumn;

    @FXML
    private ListView<String> outView;
    @FXML
    private ListView<String> programStateIdentifiers;
    @FXML
    private ListView<String> executionStackView;
    @FXML
    private TextField noOfProgramStates;


    private Controller controller;
    private IStatement statement;

    public RunWindow(IStatement statement) {
        this.statement = statement;
    }

    @FXML
    public void initialize() {
        IStack<IStatement> executionStack = new CustomStack<>();
        IDictionary<String, Integer> symbolTable = new CustomDictionary<>();
        IList<Integer> out = new CustomList<>();
        IFileTable<Integer, FileData> fileTable = new FileTable<>();
        IHeap<Integer, Integer> heap = new CustomHeap<>(new IntHeapKeyGenerator());
        executionStack.push(statement);

        ProgramState programState = new ProgramState(statement, executionStack, symbolTable, out, fileTable, heap, IntForkGenerator.generate(), new BarrierTable<>());

        Repository repository = new Repository("");

        repository.add(programState);
        controller = new Controller(repository);

        heapAddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        heapAddressColumn.setCellValueFactory(new PropertyValueFactory<>("value"));

        fileTableIdentifierColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fileTableNameColumn.setCellValueFactory(new PropertyValueFactory<>("fileName"));

        barrierTableViewCapacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        barrierTableViewIndexColumn.setCellValueFactory(new PropertyValueFactory<>("index"));
        barrierTableViewListColumn.setCellValueFactory(new PropertyValueFactory<>("list"));

        symbolTableValueColumn.setCellValueFactory(new PropertyValueFactory<>("value"));
        symbolTableVariableNameColumn.setCellValueFactory(new PropertyValueFactory<>("variableName"));

        setNoOfProgramStates();
        setProgramStateIdentifiers();
        programStateIdentifiers.getSelectionModel().selectFirst();
        setExecutionStackView();
    }

    @FXML
    public void oneStepGUI(ActionEvent actionEvent) {
        try {
            if (controller.oneStepGUI()) {
                setAll();
            } else {
                setNoOfProgramStates();
                setProgramStateIdentifiers();
                symbolTableView.getItems().clear();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Program finished");
                alert.show();
                Node source = (Node) actionEvent.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();
            }
        } catch (RuntimeException e) {
            Node source = (Node) actionEvent.getSource();
            Stage stage = (Stage) source.getScene().getWindow();
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());
            alert.show();
            stage.close();

        }
    }

    @FXML
    public void selectProgramState(MouseEvent mouseEvent) {
        if (controller.noOfProgramStates() > 0) {
            setAll();
        }
    }

    private void setNoOfProgramStates() {
        int no = controller.noOfProgramStates();
        noOfProgramStates.setText("" + no);
    }

    private void setProgramStateIdentifiers() {
        programStateIdentifiers.setItems(controller.getProgramStatesID());
    }

    private ProgramState getCurrentProgramState() {
        int index = programStateIdentifiers.getSelectionModel().getSelectedIndex();
        if (index == -1)
            index = 0;
        return controller.getProgramStateByIndex(index);
    }

    private void setExecutionStackView() {
        ObservableList<String> list = FXCollections.observableArrayList();
        ProgramState state = getCurrentProgramState();

        for (IStatement statement : state.getExecutionStack().getAll()) {
            list.add("" + statement);
        }
        executionStackView.setItems(list);
    }

    private void setSymbolTableView() {
        ObservableList<SymbolTableView> list = FXCollections.observableArrayList();
        ProgramState state = getCurrentProgramState();
        IDictionary<String, Integer> dictionary = state.getSymTable();

        for (String key : dictionary.getAll()) {
            list.add(new SymbolTableView(key, dictionary.getValue(key)));
        }

        symbolTableView.setItems(list);
    }

    //trimiti mail pe mircea.suceveanu@siemens.com
    private void setHeapView() {
        ObservableList<HeapTableView> list = FXCollections.observableArrayList();
        ProgramState state = getCurrentProgramState();
        IHeap<Integer, Integer> heap = state.getHeap();

        for (Integer key : heap.getAll()) {
            list.add(new HeapTableView(key, heap.get(key)));
        }
        heapView.setItems(list);
    }

    private void setFileTableView() {
        ObservableList<FileTableView> list = FXCollections.observableArrayList();
        ProgramState state = getCurrentProgramState();
        IFileTable<Integer, FileData> fileTable = state.getFileTable();

        for (Integer key : fileTable.getAllKeys()) {
            list.add(new FileTableView(key, fileTable.get(key).getFileName()));
        }
        fileTableView.setItems(list);
    }

    private void setBarrierTable() {
        ObservableList<BarrierTableView> list = FXCollections.observableArrayList();
        ProgramState state = getCurrentProgramState();
        IBarrierTable<Integer, BarrierData> barrierTable = state.getBarrierTable();

        for (Integer key : barrierTable.getAllKeys()) {
            list.add(new BarrierTableView(key, barrierTable.get(key).getKey(), barrierTable.get(key).toString()));
        }
        barrierTableView.setItems(list);
    }


    private void setOutView() {
        ProgramState state = getCurrentProgramState();
        ObservableList<String> list = FXCollections.observableArrayList();

        for (Integer i : state.getOut().getAll()) {
            list.add("" + i);
        }

        outView.setItems(list);
    }

    private void setAll() {
        setExecutionStackView();
        setFileTableView();
        setBarrierTable();
        setHeapView();
        setOutView();
        setNoOfProgramStates();
        setSymbolTableView();
        setProgramStateIdentifiers();
    }


}
