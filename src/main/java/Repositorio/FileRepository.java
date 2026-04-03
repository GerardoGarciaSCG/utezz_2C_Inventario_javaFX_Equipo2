package Repositorio;

import Modelo.Producto;

import java.io.*;
import java.nio.Buffer;
import java.security.ProtectionDomain;
import java.util.ArrayList;
import java.util.List;

public class FileRepository {
    private static final String FILE_PATH = "Productos.csv";


    public void guardar(List<Producto> productos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for(Producto p : productos){
                bw.write(p.toString());
                bw.newLine();
            }

        }catch (IOException e ){
            System.err.println("Error critico al guardar el archivo" + e.getMessage());
        }
    }

    public List<Producto> leer(){
        List<Producto> productos = new ArrayList<>();
        File file = new File(FILE_PATH);

        if (!file.exists()){
            return productos;
        }
        try(BufferedReader br = new BufferedReader(new FileReader(file))){
            String linea;

            while ((linea = br.readLine()) !=null){
                String [] datos= linea.split(",");
                if(datos.length==5){
                    String codigo = datos [0];
                    String nombre = datos [1];
                    double precio = Double.parseDouble(datos[2]);
                    int stock = Integer.parseInt(datos [3]);
                    String categoria = datos[4];



                    Producto p = new Producto(codigo,nombre , precio, stock,categoria);
                    productos.add(p);
                }
            }
        }catch (IOException | NumberFormatException e){
            System.err.println("Error al leer el archivo o formato " + e.getMessage());

        }
        return productos;
    }
}
