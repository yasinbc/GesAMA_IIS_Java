
public class TipoFincas
{
    private ListaFincas fincas;
    private int numeroFincas;
    public TipoObjeto[] ListaFincas = new TipoObjeto[20] ;
    
    /** borrar una Finca */
    public void borrarFinca(int id){
        //guarda la posición en la que se encuentra el id de la finca a borrar
        int posicion;
        boolean encontrado;
        
        posicion = encontrarFinca(id, encontrado);
        
        if (encontrado){
        //recorremos el array buscando la finca que nos han pasado
        for (int i= posicion; i<numeroFincas;i++){
            fincas[i]=fincas[i+1];
        }
        //decrementamos el número de fincas
        numeroFincas--;
        } else {
            System.out.printf("No se ha encontrado el id de la Finca");
        }
    }
    
    /** lista las Fincas de un array */
    public void listarFincas(char opc){
        TipoGBA tipo;
        String nombre;
        
        for (int i=0; i<numeroFincas; i++){
            //Seleccionamos pintar todas las máquinas
            if (Character.toUpperCase(opc) /*toupper(opc)*/ == 'T'){
                //Como solo guardamos la incial, tenemos que hacer convertirlo en palabra
                TraducirTipo(fincas[i].leerTipo(), tipo);
                //una vez convertido, pintamos la línea
                System.out.printf("%-20d", fincas[i].leerId());
                fincas[i].leerNombre(nombre);
                System.out.printf("%-20s", nombre);
                System.out.printf("%-20s", tipo);
                printf("%3d hectáreas",fincas[i].leerCapacidad());
                System.out.printf("%*.3f", 13, fincas[i].leerUbicaLat());
                System.out.printf("%*.3f", 20, fincas[i].leerUbicaLon());
                System.out.printf("\n");
            }else{
                if (fincas[i].leerTipo()==opc){
                    //Como solo guardamos la incial, tenemos que hacer convertirlo en palabra
                    TraducirTipo(fincas[i].leerTipo(), tipo);
                    //una vez convertido, pintamos la línea
                    System.out.printf("%-20d", fincas[i].leerId());
                    fincas[i].leerNombre(nombre);
                    System.out.printf("%-20s", nombre);
                    System.out.printf("%-20s", tipo);
                    System.out.printf("%3d hectáreas",fincas[i].leerCapacidad());
                    System.out.printf("%*.3f", -10, fincas[i].leerUbicaLat());
                    System.out.printf("%*.3f", -10, fincas[i].leerUbicaLon());
                    System.out.printf("\n");
                }
            }
        }
    } 
    
    /** devuelve la posición de una Finca */
    public int encontrarFinca(int id, boolean encontrado){
        //guarda la posicion en la que se encuentra
        int posicion;

        posicion = 0;
        encontrado = false;
        
        do{
            if (fincas[posicion].leerId() == id){
            encontrado=true;
          }
          posicion++;
        }while (posicion<numeroFincas && (!encontrado));
        
        //devolvemos la posición, si no la ha encontrado será 0
        if (encontrado){
            return posicion-1;
        } else {
            return 0;
        }
    }
    
    /** pone a 0 los nº de Finca */
    public void inicializa(){
        this.numeroFincas = 0;
    }
    
    /** insertar una Finca en el array */
    public void insertarFinca(TipoObjeto finca){
        int posicion;
        boolean encontrado;
        //se comprueba si ya informó la finca previamente
        posicion = encontrarFinca(finca.leerId(), encontrado);
        
        //si la posicion es 0 no la ha encontrado, por lo que se inserta la finca y se añade 1 a numeroFincas
        if (posicion==0 && (!encontrado)){
            fincas[numeroFincas].insertaObjeto(finca);
            //incrementa el número de fincas almacenadas
            numeroFincas++;
        } else if (posicion>=0 && (encontrado))  {//si lo ecuentra, lo insertara en la posición que ha devuelto encontrarMaquina
            fincas[posicion].insertaObjeto(finca);
        }
    }
    /** lee los datos de una finca y los devuelve en un TipoObjeto */
    public TipoObjeto leerFinca(int posicion){
        TipoObjeto finca;
        finca.insertaObjeto(fincas[posicion]);
        return finca;
    }
}
