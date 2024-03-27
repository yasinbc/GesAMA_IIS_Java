
public class TipoObjeto
{
    private int id;
    private String nombre;
    private char tipo;
    private int capacidad;
    private TipoCoordenadas ubica;
    
    /** guarda los datos desde variables */
    public void inicializa(int id, String nombre, char tipo, int capacidad, TipoCoordenadas ubica){
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.ubica.inicializa(ubica.leerLat(),ubica.leerLon());
    }
    
    /** guarda los datos desde un objeto TipoObjeto */
    public void insertaObjeto(TipoObjeto objeto){
        String nombre;

        objeto.leerNombre(nombre);

        this.id = objeto.leerId();
        this.nombre = nombre;
        this.tipo = objeto.leerTipo();
        this.capacidad = objeto.leerCapacidad();
        this.ubica.inicializa(objeto.leerUbicaLat(), objeto.leerUbicaLon());
    }
    
    /** lee el id de la máquina/finca */
    public int leerId(){
        return this.id;
    }
    
    /** lee el nombre de la máquina/finca */
    public void leerNombre(String nombre){
        this.nombre = nombre;
    }
    
    /** lee el tipo de grano de la máquina/finca */
    public char leerTipo(){
        return this.tipo;
    }
    
    /** lee la capacidad/tamaño de la máquina/finca */
    public int leerCapacidad(){
        return this.capacidad;
    }
    
    /** lee la Latitud de la máquina/finca */
    public float leerUbicaLat(){
        return this.ubica.leerLat();
    }
    
    /** lee la Longitud de la máquina/finca */
    public float leerUbicaLon(){
        return this.ubica.leerLon();
    }
    
    /** lee las Coordenadas de la máquina/finca y las devuelve en un TipoCoordenada */
    public TipoCoordenadas leerUbicacion(){
        TipoCoordenadas ubicacion;
        ubicacion.inicializa(this.ubica.leerLat(), this.ubica.leerLon());
        return ubicacion;
    }
}
