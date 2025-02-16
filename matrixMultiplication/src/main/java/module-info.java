module com.psu.matrixmultiplication {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.psu.matrixmultiplication to javafx.fxml;
    exports com.psu.matrixmultiplication;
}
