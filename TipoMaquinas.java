
public class TipoMaquinas
{
    private ListaMaquinas maquinas;
    private int numeroMaquinas;
    public TipoObjeto [] ListaMaquinas = new TipoObjeto[10];
    
    /**inicializa numeroMaquinas a 0 */
    public void inicializa(){
        this.numeroMaquinas=0;
    }

    /** Si en GuardarMaquina indicamos B en Tipo, la borramos */
    public void borrarMaquina(int id){
        //guarda la posición en la que he encontrado el id de la Maquina a borrar
        int posicion;
        boolean encontrado;

        posicion = encontrarMaquina(id, encontrado);

        if (encontrado){
            //recorre el array buscando la Maquina que nos han pasado
            for (int i= posicion; i<numeroMaquinas;i++){
                maquinas[i].insertaObjeto(maquinas[i+1]);
            }
            //decrementamos el número de Maquinas
            numeroMaquinas--;
        } else {
            System.out.printf("No se ha encontrado el id de la Maquina\n");
        }
    }
    
    /** Guardamos la Maquina */
    public void insertarMaquina(TipoObjeto maquina){
        int posicion;
        boolean encontrado;
        //comprueba si ya tenía la Maquina
        posicion = encontrarMaquina(maquina.leerId(), encontrado);

        //si la posicion es 0, no la ha encontrado, por lo que inserta la Maquina y suma 1 a numeroMaquinas
        if (posicion==0 || (!encontrado)){
            maquinas[numeroMaquinas].insertaObjeto(maquina);
            //incrementa el número de Maquinas almacenadas
            numeroMaquinas++;
        } else if (posicion>=0 && (encontrado))  {//si lo encontro, lo inserta en la posición que ha devuelto encontrarMaquina
            maquinas[posicion].insertaObjeto(maquina);
        }
    }
    
    /** lista maquinas */
    public void listarMaquinas(char opc){
        TipoGBA tipo;
        String nombre;

        for (int i=0; i<numeroMaquinas; i++){
            //seleccion listar todas las máquinas
            if (Character.toUpperCase(opc) == 'T'){
                //convertir en palabra
                TraducirTipo(maquinas[i].leerTipo(), tipo);
                //una vez convertido, imprime la línea
                System.out.printf("%-20d", maquinas[i].leerId());
                maquinas[i].leerNombre(nombre);
                System.out.printf("%-20s", nombre);
                System.out.printf("%-20s", tipo);
                System.out.printf("%d hectáreas",maquinas[i].leerCapacidad());
                System.out.printf("\n");
            }else{
                if (maquinas[i].leerTipo()==opc){
                    //convertir en palabra
                    TraducirTipo(maquinas[i].leerTipo(), tipo);
                    System.out.printf("%-20d", maquinas[i].leerId());
                    maquinas[i].leerNombre(nombre);
                    System.out.printf("%-20s", nombre);
                    System.out.printf("%-20s", tipo);
                    System.out.printf("%d hectáreas",maquinas[i].leerCapacidad());
                    System.out.printf("\n");
                }
            }
        }
    }
    
    /** indica si ha encontrado el id de la máquina */
    public int encontrarMaquina(int id, boolean encontrado){
        //guarda la posicion en la que se encuentra
        int posicion;

        posicion = 0;
        encontrado = false;
        
        do{
            if (maquinas[posicion].leerId() == id){
            encontrado=true;
        }
        posicion++;
        }while (posicion<numeroMaquinas && (!encontrado));
        
        //devuelve la posición, si no la ha encontrado será 0
        if (encontrado){
            return posicion-1;
        } else {
            return 0;
        }
    }
    
    /** leem un TipoObjeto del array de maquinas */
    public TipoObjeto leerMaquina(int posicion){
        TipoObjeto maquina;
        maquina.insertaObjeto(maquinas[posicion]);
        return maquina;
    }
    
    /** devuelve el número de máquinas guardadas */
    public int leerNumMaq(){
        return this.numeroMaquinas;
    }
}
