module com.psu.matrixmultiplication {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.psu.matrixmultiplication to javafx.fxml;
    exports com.psu.matrixmultiplication;
}
