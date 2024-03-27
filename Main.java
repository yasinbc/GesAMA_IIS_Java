

public class Main
{
    public static void main(String [] args){
        TipoFincas listadoFincas; //donde guardo las fincas
        TipoMaquinas listadoMaquinas; //donde guardo las máquinas
        TipoAlquileres listadoAlquileres;
    
        TipoMenu menu;

        listadoFincas.inicializa();
        listadoMaquinas.inicializa();
        listadoAlquileres.inicializa();

        menu.pintarMenu(listadoFincas, listadoMaquinas, listadoAlquileres);  
    }
}
