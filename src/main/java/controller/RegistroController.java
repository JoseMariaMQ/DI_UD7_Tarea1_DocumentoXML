package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import model.Registro;
import model.RegistroXML;

import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistroController {

    //Variables que referencian a los elementos de la interfaz gráfica
    public TextField codPostal;
    public TextField dniCliente;
    public TextField idRepartidor;
    public DatePicker fecha;
    public Button btnRegistrar;
    public Label mensajeError;

    //Método para inicializar el botón y los eventos al hacer click en los campos de entrada
    public void initialize() {
        //Creamos la acción del botón y lo referenciamos al método registrar
        btnRegistrar.setOnAction(this::registrar);

        //Creamos los eventos de escucha al hacer clic para cada campo de entrada donde borraremos el mensaje de error
        fecha.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mensajeError.setText(null);
            }
        });
        codPostal.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mensajeError.setText(null);
            }
        });
        dniCliente.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mensajeError.setText(null);
            }
        });
        idRepartidor.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                mensajeError.setText(null);
            }
        });
    }

    //Método que se ejecuta al pulsar el botón registrar
    private void registrar(ActionEvent actionEvent) {
        //Comprobamos que se pulsa el botón
        if (actionEvent.getSource() == btnRegistrar) {
            //Comprobamos que ningún campo de entrada este vacío
            if (codPostal.getText().equals("") || dniCliente.getText().equals("") || idRepartidor.getText().equals("") || fecha.getValue()==null) {
                //Cambiamos color de mensaje a rojo para que de la sensación de error
                mensajeError.setTextFill(Color.web("red"));
                //Mostramos mensaje de error
                mensajeError.setText("Tiene que rellenar todos los campos");
            } else if (fecha.getValue().isAfter(LocalDate.now())) { //Comprobamos la fecha
                //Cambiamos color de mensaje a rojo para que de la sensación de error
                mensajeError.setTextFill(Color.web("red"));
                //Mostramos mensaje de error
                mensajeError.setText("La fecha de entrega no puede ser posterior a hoy.");
            } else {
                //Creamos un patrón para cada campo que queremos verificar
                Pattern patronCOD = Pattern.compile("[0-9]{5}");
                Pattern patronDNI = Pattern.compile("[0-9]{8}[A-Z a-z]");
                Pattern patronId = Pattern.compile("[0-9]{1,4}");
                //Lo referenciamos con el campo de entrada
                Matcher matCOD = patronCOD.matcher(codPostal.getText());
                Matcher matDNI = patronDNI.matcher(dniCliente.getText());
                Matcher matId = patronId.matcher(idRepartidor.getText());

                //Comprobamos que los campos se han introducido o no correctamente
                if (!matCOD.matches()) {
                    //Mostramos mensaje de error en rojo
                    mensajeError.setTextFill(Color.web("red"));
                    mensajeError.setText("El CP es erróneo, debe contener 5 números.");
                } else if (!matDNI.matches()) {
                    //Mostramos mensaje de error en rojo
                    mensajeError.setTextFill(Color.web("red"));
                    mensajeError.setText("El DNI introducido es incorrecto.");
                } else if (!matId.matches()) {
                    //Mostramos mensaje de error en rojo
                    mensajeError.setTextFill(Color.web("red"));
                    mensajeError.setText("El 'id' debe contener un número entero");
                } else { //Si todo esta correcto
                    //Eliminamos mensaje de error
                    mensajeError.setText(null);
                    //Creamos alerta para que el usuario compruebe los datos introducidos
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("¿Los datos son correctos?\n" +
                            "Fecha de entrega: " + fecha.getValue() + "\n" +
                            "Código Postal: " + codPostal.getText() + "\n" +
                            "DNI cliente: " + dniCliente.getText() + "\n" +
                            "Id del repartidor: " + idRepartidor.getText());
                    alert.initStyle(StageStyle.UTILITY);
                    alert.showAndWait().ifPresent(response -> {
                        if(response == ButtonType.OK) { //Si acepta
                            //Creamos objeto de tipo Registro con los datos introducidos
                            Registro registro = new Registro(fecha.getValue(), Integer.parseInt(codPostal.getText()), dniCliente.getText(), Integer.parseInt(idRepartidor.getText()));
                            //Instanciamos la clase que crea y modifica el XML
                            RegistroXML registroXML = new RegistroXML();
                            //LLamamos al método que realiza el XML
                            registroXML.documentoXML(registro);

                            //Mostramos mensaje satisfactorio de color verde
                            mensajeError.setTextFill(Color.web("green"));
                            mensajeError.setText("Datos registrados correctamente");

                            //Limpiamos los campos de entrada
                            fecha.setValue(null);
                            codPostal.setText(null);
                            dniCliente.setText(null);
                            idRepartidor.setText(null);
                        }
                    });
                }
            }
        }
    }
}
