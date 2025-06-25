module com.example.fdlizaa {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.fdlizaa to javafx.fxml;
    exports com.example.fdlizaa;
}