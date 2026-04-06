package controlador;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import Modelo.Producto;
import Servicio.ProductoService;

public class MainController {

    @FXML private TextField txtBuscar;
    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, String> colCodigo;
    @FXML private TableColumn<Producto, String> colNombre;
    @FXML private TableColumn<Producto, Double> colPrecio;
    @FXML private TableColumn<Producto, Integer> colStock;
    @FXML private TableColumn<Producto, String> colCategoria;

    @FXML private Button btnNuevo;
    @FXML private Button btnEditar;
    @FXML private Button btnEliminar;

    private ProductoService productoService;

    @FXML
    public void initialize() {
        productoService = new ProductoService();

        colCodigo.setCellValueFactory(new PropertyValueFactory<>("codigo"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<>("categoria"));

        tablaProductos.setItems(productoService.getProductos());

        configurarBuscador();

        btnEliminar.setOnAction(event -> eliminarProducto());
        btnNuevo.setOnAction(event -> abrirFormulario(null));
        btnEditar.setOnAction(event -> abrirFormulario(tablaProductos.getSelectionModel().getSelectedItem()));
    }

    private void configurarBuscador() {
        FilteredList<Producto> datosFiltrados = new FilteredList<>(productoService.getProductos(), p -> true);

        txtBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            datosFiltrados.setPredicate(producto -> {
                if (newValue == null || newValue.trim().isEmpty()) return true;
                String filtro = newValue.toLowerCase();
                return producto.getNombre().toLowerCase().contains(filtro) ||
                        producto.getCodigo().toLowerCase().contains(filtro);
            });
        });

        SortedList<Producto> datosOrdenados = new SortedList<>(datosFiltrados);
        datosOrdenados.comparatorProperty().bind(tablaProductos.comparatorProperty());
        tablaProductos.setItems(datosOrdenados);
    }

    private void eliminarProducto() {
        Producto seleccionado = tablaProductos.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            Alert alerta = new Alert(Alert.AlertType.CONFIRMATION, "¿Seguro que deseas eliminar: " + seleccionado.getNombre() + "?", ButtonType.YES, ButtonType.NO);
            alerta.showAndWait().ifPresent(respuesta -> {
                if (respuesta == ButtonType.YES) productoService.eliminarProducto(seleccionado);
            });
        } else {
            Alert alerta = new Alert(Alert.AlertType.WARNING, "Debes seleccionar un producto de la tabla.");
            alerta.showAndWait();
        }
    }

    private void abrirFormulario(Producto producto) {
        try {
            javafx.fxml.FXMLLoader loader = new javafx.fxml.FXMLLoader(getClass().getResource("/FormularioView.fxml"));
            javafx.scene.Parent root = loader.load();

            FormularioController controller = loader.getController();

            // Si vamos a EDITAR, le pasamos los datos actuales al formulario
            if (producto != null) {
                controller.setProductoEdicion(producto);
            }

            javafx.stage.Stage stage = new javafx.stage.Stage();
            stage.setTitle(producto == null ? "Nuevo Producto" : "Editar Producto");
            stage.setScene(new javafx.scene.Scene(root));
            stage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            stage.showAndWait();

            if (controller.isGuardado()) {
                if (producto == null) {
                    // ES NUEVO: Se añade a la lista
                    productoService.getProductos().add(controller.getProducto());
                } else {
                    // ES EDICIÓN: Buscamos el índice y reemplazamos el viejo por el nuevo
                    int indice = productoService.getProductos().indexOf(producto);
                    productoService.getProductos().set(indice, controller.getProducto());
                }
                tablaProductos.refresh();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}