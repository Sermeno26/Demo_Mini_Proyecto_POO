module com.abc.demo_mini_proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    // Abre todos los paquetes necesarios a javafx.fxml
    opens com.abc.demo_mini_proyecto to javafx.fxml;


    // Exporta los paquetes para que puedan ser usados externamente
    exports com.abc.demo_mini_proyecto;
    exports com.abc.demo_mini_proyecto.controllers;
    opens com.abc.demo_mini_proyecto.controllers to javafx.fxml;
    exports com.abc.demo_mini_proyecto.Modelos;
    opens com.abc.demo_mini_proyecto.Modelos to javafx.fxml;
    exports com.abc.demo_mini_proyecto.Util;
    opens com.abc.demo_mini_proyecto.Util to javafx.fxml;

}
