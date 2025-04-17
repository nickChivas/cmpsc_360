module code.truthtablebuilder {
    requires javafx.controls;
    requires javafx.fxml;

    opens code.truthtablebuilder to javafx.fxml;
    exports code.truthtablebuilder;
}
