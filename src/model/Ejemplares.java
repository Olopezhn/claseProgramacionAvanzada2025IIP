
package model;

import java.sql.Date;
import java.util.logging.Logger;

/**
 *
 * @author Novitus
 */
public class Ejemplares {

    private int id_Ejemplar;
    private int id_Libro;
    private int Piso;
    private int Pasillo;
    private int Existencia;
  

    private static final Logger LOG = Logger.getLogger(Ejemplares.class.getName());

    public Ejemplares(int id_Ejemplar, int id_Libro, int Piso,
                     int Pasillo, int Existencia) {
        LOG.info("Se creó un objeto de la clase Ejemplares");
        this.id_Ejemplar = id_Ejemplar;
        this.id_Libro = id_Libro;
        this.Piso = Piso;
        this.Pasillo = Pasillo;
        this.Existencia = Existencia;
      
    }

    public Ejemplares() {
    }

    public int getId_Ejemplar() {
        return id_Ejemplar;
    }

    public void setId_Ejemplar(int id_Ejemplar) {
        this.id_Ejemplar = id_Ejemplar;
    }

    public int getId_Libro() {
        return id_Libro;
    }

    public void setId_Libro(int id_Libro) {
        this.id_Libro = id_Libro;
    }

    public int getPiso() {
        return Piso;
    }

    public void setPiso(int Piso) {
        this.Piso = Piso;
    }

    public int getPasillo() {
        return Pasillo;
    }

    public void setPasillo(int Pasillo) {
        this.Pasillo = Pasillo;
    }

    public int getExistencia() {
        return Existencia;
    }

    public void setExistencia(int Existencia) {
        this.Existencia = Existencia;
    }

 
}