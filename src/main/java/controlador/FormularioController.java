package controlador;

import Modelo.Producto;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FormularioController {
    @FXML private TextField txtCodigo, txtNombre, txtPrecio, txtStock, txtCategoria;
    @FXML private Button btnGuardar, btnCancelar;

    private Producto producto;
    private boolean guardado = false;

    @FXML
    public void initialize() {
        btnCancelar.setOnAction(e -> ((Stage) btnCancelar.getScene().getWindow()).close());
        btnGuardar.setOnAction(e -> guardar());
    }

    private void guardar() {
        try {
            // Creamos el objeto con los datos de los campos
            producto = new Producto(
                    txtCodigo.getText(),
                    txtNombre.getText(),
                    Double.parseDouble(txtPrecio.getText()),
                    Integer.parseInt(txtStock.getText()),
                    txtCategoria.getText()
            );
            guardado = true;
            ((Stage) btnGuardar.getScene().getWindow()).close();
        } catch (NumberFormatException ex) {
            System.out.println("Error: Precio o Stock deben ser números.");
        }
    }

    public void setProductoEdicion(Producto p) {
        this.producto = p; // Guardamos la referencia
        // Rellenamos los campos con la información actual
        txtCodigo.setText(p.getCodigo());
        txtNombre.setText(p.getNombre());
        txtPrecio.setText(String.valueOf(p.getPrecio()));
        txtStock.setText(String.valueOf(p.getStock()));
        txtCategoria.setText(p.getCategoria());

        // El código no debería editarse (opcional, por seguridad)
        txtCodigo.setEditable(false);
    }

    public Producto getProducto() { return producto; }
    public boolean isGuardado() { return guardado; }
}