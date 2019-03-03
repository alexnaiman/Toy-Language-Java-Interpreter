package GUI.Window;

import Model.Expression.*;
import Model.FileStatement.CloseRFile;
import Model.FileStatement.OpenRFile;
import Model.FileStatement.ReadFile;
import Model.GeneralStatement.*;
import Model.HeapStatement.NewStatement;
import Model.HeapStatement.WriteHeapStatement;
import Model.Threads.CyclicBarrier.AwaitBarrierStatement;
import Model.Threads.CyclicBarrier.NewBarrierStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectWindow {

    @FXML
    private ListView<String> listView;

    @FXML
    private Button selectButton;

    private List<IStatement> statementList = new ArrayList<>();

    @FXML
    public void initialize() {
        IStatement statement1 = new CompoundStatement(new AssignmentStatement("v", new ConstantExpression(10)), new CompoundStatement(new NewStatement("a", new ConstantExpression(22)), new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteHeapStatement("a", new ConstantExpression(30)), new CompoundStatement(new AssignmentStatement("v", new ConstantExpression(32)), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression("a")))))), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression("a"))))));
        IStatement ex9 = new CompoundStatement(new AssignmentStatement("a", new ConstantExpression(-2)), new WhileStatement(new BooleanExpression(new VariableExpression("a"), new ConstantExpression(0), "<"), new CompoundStatement(new PrintStatement(new VariableExpression("a")), new AssignmentStatement("a", new ArithmeticExpression('+', new VariableExpression("a"), new ConstantExpression(1))))));
        IStatement ex8 = new CompoundStatement(new AssignmentStatement("a", new ConstantExpression(3)), new WhileStatement(new BooleanExpression(new VariableExpression("a"), new ConstantExpression(0), ">"), new CompoundStatement(new PrintStatement(new VariableExpression("a")), new AssignmentStatement("a", new ArithmeticExpression('-', new VariableExpression("a"), new ConstantExpression(1))))));
        IStatement ex7 = new CompoundStatement(new AssignmentStatement("v", new ConstantExpression(10)), new CompoundStatement(new NewStatement("v", new ConstantExpression(20)), new CompoundStatement(new NewStatement("a", new ConstantExpression(22)), new CompoundStatement(new WriteHeapStatement("a", new ConstantExpression(30)), new CompoundStatement(new PrintStatement(new VariableExpression("a")), new CompoundStatement(new PrintStatement(new ReadHeapExpression("a")), new AssignmentStatement("a", new ConstantExpression(0))))))));
        IStatement ex10 = new CompoundStatement(new OpenRFile("test.in", "var_f"),
                new CompoundStatement(new ReadFile(new VariableExpression("var_f"), "var_c"),
                        new CompoundStatement(new PrintStatement(new VariableExpression("var_c")), new CompoundStatement(new IfStatement(new VariableExpression("var_c"), new CompoundStatement(new ReadFile(new VariableExpression("var_f"), "var_c"), new PrintStatement(new VariableExpression("var_c"))), new PrintStatement(new VariableExpression("var_c"))), new CloseRFile(new VariableExpression("var_f"))))));
        IStatement ex11 = new CompoundStatement(new AssignmentStatement("v", new ConstantExpression(10)), new CompoundStatement(new NewStatement("a", new ConstantExpression(22)), new CompoundStatement(new ForkStatement(new CompoundStatement(new WriteHeapStatement("a", new ConstantExpression(30)), new CompoundStatement(new AssignmentStatement("v", new ConstantExpression(32)), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression("a")))))), new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new ReadHeapExpression("a"))))));
        IStatement ex12 = new CompoundStatement(new AssignmentStatement("a", new ConstantExpression(1)), new CompoundStatement(new AssignmentStatement("b", new ConstantExpression(2)), new CompoundStatement(new AssignmentStatement("c", new ConstantExpression(5)), new CompoundStatement(new SwitchStatement(new ArithmeticExpression('*', new VariableExpression("a"), new ConstantExpression(10)), new ArithmeticExpression('*', new VariableExpression("b"), new VariableExpression("c")), new CompoundStatement(new PrintStatement(new VariableExpression("a")), new PrintStatement(new VariableExpression("b"))), new ConstantExpression(10), new CompoundStatement(new PrintStatement(new ConstantExpression(100)), new PrintStatement(new ConstantExpression(200))), new PrintStatement(new ConstantExpression(300))),new PrintStatement(new ConstantExpression(300))))));
        IStatement ex13 = new CompoundStatement(
                new NewStatement("v1", new ConstantExpression(2)), new CompoundStatement(
                new NewStatement("v2", new ConstantExpression(3)), new CompoundStatement(
                new NewStatement("v3", new ConstantExpression(4)), new CompoundStatement(
                new NewBarrierStatement("cnt", new ReadHeapExpression("v2")), new CompoundStatement(
                new ForkStatement(
                        new CompoundStatement(
                                new AwaitBarrierStatement("cnt"), new CompoundStatement(
                                new WriteHeapStatement("v1", new ArithmeticExpression('*', new ReadHeapExpression("v1"), new ConstantExpression(10))), new PrintStatement(new ReadHeapExpression("v1")))
                        )
                ), new CompoundStatement(
                new ForkStatement(
                        new CompoundStatement(
                                new AwaitBarrierStatement("cnt"), new CompoundStatement(
                                new WriteHeapStatement("v2", new ArithmeticExpression('*', new ReadHeapExpression("v2"), new ConstantExpression(10))), new CompoundStatement(
                                new WriteHeapStatement("v2", new ArithmeticExpression('*', new ReadHeapExpression("v2"), new ConstantExpression(10))), new PrintStatement(new ReadHeapExpression("v2"))
                        )
                        )
                        )
                ), new CompoundStatement(
                new AwaitBarrierStatement("cnt"), new PrintStatement(new ReadHeapExpression("v3"))
        )
        )
        )
        )
        )
        )
        );
        statementList.add(statement1);
        statementList.add(ex9);
        statementList.add(ex8);
        statementList.add(ex7);
        statementList.add(ex11);
        statementList.add(ex12);
        statementList.add(ex13);
        ObservableList<String> observableList = FXCollections.observableArrayList();

        for (IStatement statement : statementList) {
            observableList.add("" + statement);
        }

        listView.setItems(observableList);

        listView.getSelectionModel().selectFirst();
    }

    @FXML
    public void buttonRun(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../Scheme/RunWindow.fxml"));
        loader.setController(new RunWindow(statementList.get(listView.getSelectionModel().getSelectedIndex())));

        Stage stage = new Stage();
        Parent root = loader.load();
        stage.setTitle("Running program");
        stage.setScene(new Scene(root, 800, 600));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }
}
