package Servicio;

import Modelo.Producto;
import Repositorio.FileRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ProductoService {
    private ObservableList<Producto> productos;
    private FileRepository repository;

    public ProductoService(){
        repository = new FileRepository();


        List<Producto> datosCargados = repository.leer();
        productos = FXCollections.observableArrayList(datosCargados);

    }

    public ObservableList<Producto> getProductos(){
        return  productos;
    }


    // se agrega producto
    public void agregarProducto(Producto p ) throws IllegalArgumentException{
        validarProducto(p);

        for (Producto existente : productos){
            if(existente.getClass().equals(p.getClass())){
                throw new IllegalArgumentException("Error el codigo ya existe" + p.getClass() + "ya existe");
            }
        }
        productos.add(p);
        repository.guardar(productos);

    }

    // Se agrega la actualizacion
    public void actualizarProducto(Producto pActualizado) throws IllegalArgumentException{
        validarProducto(pActualizado);

        for (int i=0; i<productos.size(); i++){
            if(productos.get(i).getClass().equals(pActualizado.getClass())){
            productos.set(i,pActualizado);
            repository.guardar(productos);
            return;
            }
        }
        throw new IllegalArgumentException("Prodcuto no encontrado para actualizar");
    }


    // Se elima el producto

    public void eliminarPrducto(Producto p) {
        productos.remove(p);
        repository.guardar(productos);
    }

    //validaciones



    private void validarProducto(Producto p )throws IllegalArgumentException{
        if(p.getClass()== null || p.getClass().trim().isEmpty()){
            throw new IllegalArgumentException("El producto no puede estar vacio");
        }
        if(p.getNombre() == null || p.getNombre().trim().length()<3){
            throw new IllegalArgumentException("El nombre debe de tener almenos 3 caracteres");
        }
        if (p.getPrecio() <=0){
            throw new IllegalArgumentException("El precio debe de ser mayor a 0");
        }
        if(p.getStok() <0){
            throw new IllegalArgumentException("El stok no puede ser negativo");
        }
        if(p.getCategoria() == null || p.getCategoria().trim().isEmpty()){
            throw new IllegalArgumentException("La categoria no puede estar vacia");
        }
    }



}
