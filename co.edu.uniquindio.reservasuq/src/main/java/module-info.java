module co.edu.uniquindio.reservasuq.co_edu_uniquindio_reservasuq {
    requires javafx.controls;
    requires javafx.fxml;


    opens co.edu.uniquindio.reservasuq.co_edu_uniquindio_reservasuq to javafx.fxml;
    exports co.edu.uniquindio.reservasuq.co_edu_uniquindio_reservasuq;
}