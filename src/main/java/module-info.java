module com.abc.demo_mini_proyecto {
    requires javafx.controls;
    requires javafx.fxml;


    // Abre todos los paquetes necesarios a javafx.fxml
    opens com.abc.demo_mini_proyecto to javafx.fxml;


    // Exporta los paquetes para que puedan ser usados externamente
    exports com.abc.demo_mini_proyecto;
    exports com.abc.demo_mini_proyecto.controllers;
    opens com.abc.demo_mini_proyecto.controllers to javafx.fxml;

}
