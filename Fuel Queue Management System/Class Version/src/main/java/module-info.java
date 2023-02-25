module com.example.task_4_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.prog_cw to javafx.fxml;
    exports com.example.prog_cw;
}