import java.util.Scanner;
import java.io.*;

public class TipoMenu 
{
    public void pintarMenu(TipoFincas listadoFincas, TipoMaquinas listadoMaquinas, TipoAlquileres listadoAlquileres){
        //guardo la opción del menu
        char opc = 'N';
        
        // Creamos un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        
        FunGesAMA funGesAMA;

        //Mostrar el menu hasta que se pulse la S
        while(Character.toUpperCase(opc) != 'S'){
            //system("cls");
            // Limpia la pantalla de la consola
            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            System.out.printf("GesAMA: Gestión de Alquiler de Máquinas Agrícolas\n");
            System.out.println();
            funGesAMA.RellenaEspacios(0,4);System.out.printf("Editar Máquina");funGesAMA.RellenaEspacios(0,23);System.out.printf("(Pulsar M)\n");
            funGesAMA.RellenaEspacios(0,4);System.out.printf("Editar Finca");funGesAMA.RellenaEspacios(0,25);System.out.printf("(Pulsar F)\n");
            funGesAMA.RellenaEspacios(0,4);System.out.printf("Listar Máquina");funGesAMA.RellenaEspacios(0,23);System.out.printf("(Pulsar L)\n");
            funGesAMA.RellenaEspacios(0,4);System.out.printf("Estado Fincas");funGesAMA.RellenaEspacios(0,24);System.out.printf("(Pulsar E)\n");
            funGesAMA.RellenaEspacios(0,4);System.out.printf("Alquilar Mácquina");funGesAMA.RellenaEspacios(0,21);System.out.printf("(Pulsar A)\n");
            funGesAMA.RellenaEspacios(0,4);System.out.printf("Plan Mensual Máquina");funGesAMA.RellenaEspacios(0,17);System.out.printf("(Pulsar P)\n");
            //RellenaEspacios(0,4);System.out.printf("Generar Máquinas/Fincas para test");RellenaEspacios(0,4);System.out.printf("(Pulsar Q)\n");
            funGesAMA.RellenaEspacios(0,4);System.out.printf("Listar todos los Alquileres");funGesAMA.RellenaEspacios(0,10);System.out.printf("(Pulsar W)\n");
            funGesAMA.RellenaEspacios(0,4);System.out.printf("Salir");funGesAMA.RellenaEspacios(0,32);System.out.printf("(Pulsar S)\n");
            //System.out.printf("Teclear una opción válida (M|F|L|E|A|P|Q|W|S)? ");
            System.out.printf("Teclear una opción válida (M|F|L|E|A|P|W|S)? ");
            //fflush(stdin);
            //scanf("%c", &opc);
            opc = scanner.next().charAt(0);

            switch(Character.toUpperCase(opc)){
                case 'M':guardarMaquina(listadoMaquinas, listadoAlquileres);break; //Guardamos la máquina

                case 'F':guardarFinca(listadoFincas, listadoAlquileres);break; //Guardamos la finca

                case 'L':listarMaquinas(listadoMaquinas);break; //Listamos las máquinas

                case 'E':listarFincas(listadoFincas);break; //Listamos las fincas

                case 'A':pedirDatosAlquiler(listadoMaquinas, listadoFincas, listadoAlquileres);break; //Alquilamos una máquina para una finca

                case 'P':mostrarCalendario(listadoMaquinas, listadoAlquileres);break; //Mostramos el calendario de una máquina

                case 'S':System.out.printf("Saliendo de la aplicación");break; //Alquilamos una máquina para una finca

                case 'Q':rellenoRapido(listadoMaquinas, listadoFincas);break;

                case 'W':listarAlquileres(listadoAlquileres);break;

                default: System.out.printf("Selecci%cn no válida\n");
            }
        };
    }
    
    public void guardarMaquina(TipoMaquinas listadoMaquinas, TipoAlquileres listadoAlquileres){
        int id;
        String nombre;
        char tipo; // G - Grano, U - Uva, A - Aceituna
        int capacidad; //capacidad de una maquina
        float lat, lon; //coordenadas de ubicacion

        TipoObjeto maquina;
        TipoCoordenadas ubicacion;

        char guardar;
        
        // Creamos un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        
        // Creamos un objeto BufferedReader para leer la entrada del usuario
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        //borra lo que haya en pantalla
        //system("cls");
        // Limpia la pantalla de la consola
        System.out.print("\033[H\033[2J");
        System.out.flush();

        //mostramos titulo
        System.out.printf("Editar Maquina:\n\n");

        //pide el id de la maquina
        System.out.printf("  Identificador (número entre 1 y 10)? ");
        do{
            //scanf("%d", &id);
            // Leemos el número entero ingresado por el usuario
            id = scanner.nextInt();
        }while((id<1) && (id>10));
        //fflush(stdin);
        
        //pide el nombre de la maquina
        /***************************************************************/
        /** Si persiste el error escribimos un condicional
        /***************************************************************/
        /**
        System.out.printf("  Nombre(entre 1 y 20 caracteres)? ");
        do{
            //gets(nombre);
            nombre = reader.readLine();
        } while (nombre.length()>20);
        */
        try {
            System.out.printf("Nombre (entre 1 y 20 caracteres)? ");
            do {
                nombre = reader.readLine();
                } while (nombre.length() > 20);
        } catch (IOException e) {
            System.out.println("Error de entrada/salida: " + e.getMessage());
        } finally {
            try {
                reader.close(); // Cierra el BufferedReader al finalizar
            } catch (IOException e) {
                System.out.println("Error al cerrar el BufferedReader: " + e.getMessage());
            }
        }
        
        //pedimos tipo cosecha o borrado
        System.out.printf("  Tipo (G-Grano, U-Uva, A-Aceituna, B-Borrar)? ");
        do{
            //scanf("%c", &tipo);
            tipo = scanner.next().charAt(0);
        }while(Character.toUpperCase(tipo) != 'G' && Character.toUpperCase(tipo) != 'U' && Character.toUpperCase(tipo) != 'A' && Character.toUpperCase(tipo) != 'B');
  
        //pide capacidad maquina
        System.out.printf("  Capacidad (hectáreas/día)? ");
        //scanf("%d", &capacidad);
        capacidad = scanner.nextInt();
        
        //pide ubicacion
        System.out.printf("  Ubiación inicial (Latitud)? ");
        //scanf("%f", &lat);
        //fflush(stdin);
        lat = scanner.nextFloat();
        
        System.out.printf("  Ubiación inicial (Longitud)? ");
        //scanf("%f", &lon);
        //fflush(stdin);
        lon = scanner.nextFloat();

        //pregunta si guarda los valores
        System.out.printf("IMPORTANTE: Esta opción borra los datos anteriores. Son correctos los nuevos datos (S/N)? ");
        do{
            //scanf("%c",&guardar);
            guardar = scanner.next().charAt(0);
        }while (Character.toUpperCase(guardar) != 'S' && Character.toUpperCase(guardar) != 'N');

        ubicacion.inicializa(lat, lon);
        maquina.inicializa(id, nombre, tipo, capacidad, ubicacion);
        //comprobar si quiere guardar o no
        if (Character.toUpperCase(guardar) == 'S'){
            // si guarda y en tipo de grano han guardado la B, lo borraremos
            if (Character.toUpperCase(tipo) == 'B'){
                listadoAlquileres.borrarAlquiler(maquina, 'm');
                listadoMaquinas.borrarMaquina(id);
            }else{//si se ha puesto cualquier tipo de grano, se guarda/sobrescribe
                listadoMaquinas.insertarMaquina(maquina);
            }
        }
        
        //system("pause");
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }
  
    public void guardarFinca(TipoFincas listadoFincas, TipoAlquileres listadoAlquileres){}
  
    public void listarMaquinas(TipoMaquinas listadoMaquinas){}
  
    public void listarFincas(TipoFincas listadoFincas){}
  
    public void pedirDatosAlquiler(TipoMaquinas listadoMaquinas, TipoFincas listadoFincas, TipoAlquileres listadoAlquileres){}
  
    public void mostrarAlquiler(TipoObjeto maquina,TipoObjeto finca, TipoFecha fecha, TipoAlquileres listadoAlquileres){}
  
    public void mostrarCalendario(TipoMaquinas listadoMaquinas, TipoAlquileres listadoAlquileres){}
  
    public void rellenoRapido(TipoMaquinas listadoMaquinas, TipoFincas listadoFincas){}
  
    public void listarAlquileres(TipoAlquileres listadoAlquileres){}
}
