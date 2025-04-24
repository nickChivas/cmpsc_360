module code.visualcountingproblems {
    requires javafx.controls;
    requires javafx.fxml;

    opens code.visualcountingproblems to javafx.fxml;
    exports code.visualcountingproblems;
}
