module code.binarymatrixmultiplication {
    requires javafx.controls;
    requires javafx.fxml;

    opens code.binarymatrixmultiplication to javafx.fxml;
    exports code.binarymatrixmultiplication;
}
