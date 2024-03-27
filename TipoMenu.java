import java.util.*;
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
  
    public void guardarFinca(TipoFincas listadoFincas, TipoAlquileres listadoAlquileres){
        int id;
        String nombre; 
        char tipo; // G - Grano, U - Uva, A - Aceituna
        int capacidad; //hectareas de la finca o capacidad de una maquina
        float lat, lon; //coordenadas de ubicacion

        TipoObjeto finca;
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

        System.out.printf("Editar Finca:\n\n");
        System.out.printf("  Identificador (número entre 1 y 20)? ");
        do{
            //scanf("%d", &id);
            // Leemos el número entero ingresado por el usuario
            id = scanner.nextInt();
        }while((id<1) && (id>20));
        //fflush(stdin);
        
        //nombre de la finca
        /**
        System.out.printf("  Nombre (entre 1 y 20 caracteres)? ");
        do{
            //gets(nombre);
        } while (strlen(nombre)>20);
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
        
        //tipo cosecha o borrado
        System.out.printf("  Tipo (G-Grano, U-Uva, A-Aceituna, B-Borrar)? ");
        do{
            //scanf("%c", &tipo);
            tipo = scanner.next().charAt(0);
        }while(Character.toUpperCase(tipo) != 'G' && Character.toUpperCase(tipo) != 'U' && Character.toUpperCase(tipo) != 'A' && Character.toUpperCase(tipo) != 'B');
  
        //tamaño finca
        System.out.printf("  Tamaño (hectáreas)? ");
        //scanf("%d", &capacidad);
        capacidad = scanner.nextInt();
        
        //ubicacion
        System.out.printf("  Ubiación inicial (Latitud)? ");
        //scanf("%f", &lat);
        //fflush(stdin);
        lat = scanner.nextFloat();
        
        System.out.printf("  Ubiación inicial (Longitud)? ");
        //scanf("%f", &lon);
        //fflush(stdin);
        lon = scanner.nextFloat();

        //pregunta si guardamos los valores
        do{
            //scanf("%c",&guardar);
            guardar = scanner.next().charAt(0);
        }while (Character.toUpperCase(guardar) != 'S' && Character.toUpperCase(guardar) != 'N');
        
        
        
        ubicacion.inicializa(lat, lon);
        finca.inicializa(id, nombre, tipo, capacidad, ubicacion);
        //comprobar si quiere guardar o no
        if (Character.toUpperCase(guardar) == 'S'){
            // si guarda y en tipo de grano guardado es B, se borrará
            if (Character.toUpperCase(tipo) == 'B'){
                listadoAlquileres.borrarAlquiler(finca,'f');
                listadoFincas.borrarFinca(id);
            }else{//si han puesto cualquier tipo de grano, lo guardamos/sobrescribimos
                listadoFincas.insertarFinca(finca);
            }
        }
        
        //system("pause");
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }
  
    public void listarMaquinas(TipoMaquinas listadoMaquinas){
        FunGesAMA funGesAMA;
        char opc;
        
        // Creamos un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        
        // Creamos un objeto BufferedReader para leer la entrada del usuario
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        //borra lo que haya en pantalla
        //system("cls");
        // Limpia la pantalla de la consola
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        //Pide el tipo de fincas a listar
        System.out.printf("Tipo de Máquina a listar (G-Grano, U-Uva, A-Aceituna, T-Todas)? ");
        //fflush(stdin);
        //scanf("%c", &opc);
        
        opc = scanner.next().charAt(0);
        //Character.toUpperCase(opc);

        //encabezamientos
        System.out.printf("Id");
        funGesAMA.RellenaEspacios("Id".length(), 20);
        System.out.printf("Nombre");
        funGesAMA.RellenaEspacios("Nombre".length(), 20);
        System.out.printf("Tipo");
        funGesAMA.RellenaEspacios("Tipo".length(), 20);
        System.out.printf("Tamaño");
        funGesAMA.RellenaEspacios("Tamaño".length(), 20);
        System.out.println();

        listadoMaquinas.listarMaquinas(opc);

        //system("pause");
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }
  
    public void listarFincas(TipoFincas listadoFincas){
        FunGesAMA funGesAMA;
        char opc;
        
        // Creamos un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        
        // Creamos un objeto BufferedReader para leer la entrada del usuario
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        
        //borra lo que haya en pantalla
        //system("cls");
        // Limpia la pantalla de la consola
        System.out.print("\033[H\033[2J");
        System.out.flush();
        
        System.out.printf("Tipo de Fincas a listar (G-Grano, U-Uva, A-Aceituna, T-Todas)? ");
        //fflush(stdin);
        //scanf("%c", &opc);
        opc = scanner.next().charAt(0);
        //Character.toUpperCase(opc);
        

        //encabezamientos
        System.out.printf("Id");
        funGesAMA.RellenaEspacios("Id".length(), 20);
        System.out.printf("Nombre");
        funGesAMA.RellenaEspacios("Nombre".length(), 20);
        System.out.printf("Tipo");
        funGesAMA.RellenaEspacios("Tipo".length(), 20);
        System.out.printf("Tamaño");
        funGesAMA.RellenaEspacios("Tamaño".length(), 20);
        System.out.printf("Latitud");
        funGesAMA.RellenaEspacios("Latitud".length(), 20);
        System.out.printf("Longitud");
        funGesAMA.RellenaEspacios("Longitud".length(), 20);
        System.out.printf("\n");

        listadoFincas.listarFincas(opc);

        //system("pause");
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
        
    }
  
    public void pedirDatosAlquiler(TipoMaquinas listadoMaquinas, TipoFincas listadoFincas, TipoAlquileres listadoAlquileres){
        TipoObjeto maquina; //guardo la maquina para luego insertarla en el alquiler
        TipoObjeto finca; //guardo la finca para luego insertarla en el alquiler
        TipoFecha fecha;

        int dia, mes, anyo;
        int idMaquina, idFinca; //guardo el id de la máquina/finca
        int posMaq, posFinca;//guardo la posicion de la maquina/finca
        boolean findMaq, findFinca; //para comprobar si se dan las condiciones
  
        // objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        
        // Creamos un objeto BufferedReader para leer la entrada del usuario
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
  
        do{
            //borra lo que haya en pantalla
            //system("cls");
            //Limpia la pantalla de la consola
            System.out.print("\033[H\033[2J");
            System.out.flush();

            //datos del alquiler
            System.out.printf("Alquiler Máquina\n\n");
            //fflush(stdin);
          //fecha
            System.out.printf("Fecha comienzo cosecha: Día? ");
            //scanf("%d", &dia);
            dia = scanner.nextInt();
            System.out.printf("Fecha comienzo cosecha: Mes? ");
            //scanf("%d", &mes);
            mes = scanner.nextInt();
            System.out.printf("Fecha comienzo cosecha: Año? ");
            //scanf("%d", &anyo);
            anyo = scanner.nextInt();
        }while(!fecha.comprobarFecha(dia, mes, anyo)); //pide fecha mientras no sea correcta

        //Finca/Maquina
        System.out.printf("Identificador de Finca (número entre 1 y 20)? ");
        //scanf("%d", &idFinca);
        idFinca = scanner.nextInt();
        posFinca=listadoFincas.encontrarFinca(idFinca, findFinca);

        System.out.printf("Identificador de Máquina (número entre 1 y 10)? ");
        //scanf("%d", &idMaquina);
        idMaquina = scanner.nextInt();
        posMaq=listadoMaquinas.encontrarMaquina(idMaquina, findMaq);


        //obtiene posición de la máquina y de la finca

        if ((findMaq) && (findFinca)){

            maquina.insertaObjeto(listadoMaquinas.leerMaquina(posMaq));
            finca.insertaObjeto(listadoFincas.leerFinca(posFinca));
            fecha.inicializa(dia, mes, anyo);

            if ((Character.toUpperCase(maquina.leerTipo()) > Character.toUpperCase(finca.leerTipo())) || (Character.toUpperCase(maquina.leerTipo()) < Character.toUpperCase(finca.leerTipo()))){
                System.out.printf("El grano de la máquina y el tipo de cosecha de la finca son incompatibles\n");
            } else{
                mostrarAlquiler(maquina,finca,fecha,listadoAlquileres);
            }
        } else {
            System.out.printf("Máquina/Finca no encontrada\n");
        }
  
        //system("pause");
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }
  
    public void mostrarAlquiler(TipoObjeto maquina,TipoObjeto finca, TipoFecha fecha, TipoAlquileres listadoAlquileres){
        char opc;
        String nombreFinca, nombreFincaAnterior, nombreMaquina;
        
        // Creamos un objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);

        TipoFecha fechaTraslado, fechaFinalizacion;
        TipoObjeto fincaAnterior;

        TipoCoordenadas distancia;//calcular la distancia entre dos puntos

        finca.leerNombre(nombreFinca);
        maquina.leerNombre(nombreMaquina);

        fechaTraslado = fecha.calcularNuevaFecha(fecha, true, 0);
        fechaFinalizacion = fecha.calcularNuevaFecha(fecha, false, listadoAlquileres.calcularDuracion(maquina, finca));

        fincaAnterior=listadoAlquileres.buscarFincaUltimoAlquiler(listadoAlquileres, maquina, fecha);
        fincaAnterior.leerNombre(nombreFincaAnterior);

        System.out.printf("      Resumen alquiler:\n");
        System.out.printf("Maquina alquilada: %s (Id = %d)\n", nombreMaquina, maquina.leerId());
        System.out.printf("Finca a cosechar: %s (Id = %d)\n", nombreFinca, finca.leerId());
        System.out.printf("Traslado desde finca %s (Id = %d)\n", nombreFincaAnterior, fincaAnterior.leerId());
        System.out.printf("Distancia entre fincas: %dkm en línea recta\n", distancia.calcularDistancia(maquina.leerUbicacion(), finca.leerUbicacion()));
        System.out.printf("Tiempo de traslado: %d/%d/%d (1 día)\n", fechaTraslado.leerDia(), fechaTraslado.leerMesNum(), fechaTraslado.leerAnyo());
        System.out.printf("Duración cosecha: %d días\n", listadoAlquileres.calcularDuracion(maquina, finca));
        System.out.printf("Fecha finalizacion: %d/%d/%d\n", fechaFinalizacion.leerDia(), fechaFinalizacion.leerMesNum(), fechaFinalizacion.leerAnyo());
        System.out.printf("Es correcta la operación (S/N)? ");
        do{
            //scanf("%c",&opc);
            opc = scanner.next().charAt(0);
        }while (Character.toUpperCase(opc) != 'S' && Character.toUpperCase(opc) != 'N');

        //comprobar si quiere guardar o no
        if (Character.toUpperCase(opc) == 'S'){
            listadoAlquileres.guardarAlquiler(maquina,finca, fecha);
        } else {
            System.out.printf("No se ha guardado el alquiler\n");
        }
    }
  
    public void mostrarCalendario(TipoMaquinas listadoMaquinas, TipoAlquileres listadoAlquileres){
        TipoFecha fecha;
        TipoObjeto maquina;
        CalendarioMes cal;
        FunGesAMA funGesAma;

        int idMaquina, mes, anyo, posicion;
        boolean encontrado;
        char opc;
        String nombre, mesImpreso;
        
        // objeto Scanner para leer la entrada del usuario
        Scanner scanner = new Scanner(System.in);
        
        // objeto BufferedReader para leer la entrada del usuario
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        do{
            System.out.printf("Plan mensual Máquina:\n");
            System.out.printf("Identificador máquina? ");
            //scanf("%d", &idMaquina);
            idMaquina = scanner.nextInt();
            System.out.printf("Selección Mes? ");
            //scanf("%d", &mes);
            System.out.printf("Seleccióon Año? ");
            //scanf("%d", &anyo);
            anyo = scanner.nextInt();
            System.out.printf("\n\n");

            //si el mes está dentro de los 12 habituales se imprime el calendario pintamos
            if (mes>0 && mes<13){

                //Obtiene la posición de la maquina seleccionada
                posicion=listadoMaquinas.encontrarMaquina(idMaquina, encontrado);

                //carga la maquina seleccionada y lee el nombre para luego mostrarlo
                maquina.insertaObjeto(listadoMaquinas.leerMaquina(posicion));
                maquina.leerNombre(nombre);

                //carga una fecha con el mes y año
                fecha.inicializa(1, mes, anyo);
                //Obteiene el mes para imprimirlo
                funGesAma.DameMes(mes-1, mesImpreso);

                //cabecera del calendario con el nombre, el mes y el año
                System.out.printf("Plan Máquina: %s\n\n", nombre);

                System.out.printf ("%s", mesImpreso);
                funGesAma.RellenaEspacios(mesImpreso.length(), 23);

                System.out.printf ("%d\n", anyo);
                System.out.printf ("===========================\n");
                System.out.printf ("LU  MA  MI  JU  VI | SA  DO\n");
                System.out.printf ("===========================\n");

                cal.crearCalendario(listadoAlquileres, mes, anyo, idMaquina);

            } else{
                System.out.printf("Mes incorrecto");
            }
            System.out.printf("Mostrar otro mes (S/N) ");
            //scanf("%s", &opc);
            opc = scanner.next().charAt(0);
        }while (Character.toUpperCase(opc) == 'S' /*toupper(opc=='S')*/);
        
        //system("pause");
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }
  
    public void rellenoRapido(TipoMaquinas listadoMaquinas, TipoFincas listadoFincas){
        TipoObjeto finca, maquina;
        TipoCoordenadas ubica;
        String nombre;
        StringBuilder builder = new StringBuilder(nombre);
        FunGesAMA funGesAMA;
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        
        
        //Sleep(500);
        //srand(time(NULL));
        //Thread.sleep(500);
        
        /*
        // Obten el tiempo actual en milisegundos
        long tiempoActual = System.currentTimeMillis();
        
        // Crear un generador de números pseudoaleatorios (RNG) con la semilla basada en el tiempo actual
        Random rng = new Random(tiempoActual);
        
        // Ahora puedes usar el generador de números pseudoaleatorios (RNG) para generar números aleatorios
        int numeroAleatorio = rng.nextInt();
        */

        for(int i=0; i<10;i++){
            ubica.inicializa(-0.600,39.633);
            nombre = "maquina ";
            //sprintf(nombre + strlen(nombre), "%d", i+1);
            builder.append(i + 1); // Agregar el entero i+1 al final del StringBuilder
            nombre = builder.toString(); // Convertir StringBuilder a String si es necesario
            maquina.inicializa(i+1, nombre, funGesAMA.DameSemilla(), 1 + /*(rand()%100)*/ random.nextInt(100), ubica);
            listadoMaquinas.insertarMaquina(maquina);
        }



        for(int i=0; i<20;i++){
            ubica.inicializa(DameCoordenada(0),DameCoordenada(1));
            nombre = "finca ";
            //sprintf(nombre + nombre.length(), "%d", i+1);
            builder.append(i + 1); // Agregar el entero i+1 al final del StringBuilder
            nombre = builder.toString(); // Convertir StringBuilder a String si es necesario
            finca.inicializa(i+1, nombre,funGesAMA.DameSemilla(), 1 + /*(rand()%100)*/ random.nextInt(100), ubica);
            listadoFincas.insertarFinca(finca);
        }


        System.out.printf("datos test cargados\n");

        //system("pause");
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }
  
    public void listarAlquileres(TipoAlquileres listadoAlquileres){
        int duracion;
        TipoFecha fechaFin;
        FunGesAMA funGesAMA;
        Scanner scanner = new Scanner(System.in);
        
        System.out.printf("LISTADO ALQUILERES\n\n");

        System.out.printf("Id Maquina");
        funGesAMA.RellenaEspacios("Id Maquina".length(),20);
        System.out.printf("Id Finca");
        funGesAMA.RellenaEspacios("Id Finca".length(),20);
        System.out.printf("Fecha Incio");
        funGesAMA.RellenaEspacios("Fecha Incio".length(),20);
        System.out.printf("Fecha Fin\n");

        for (int i = 0; i < listadoAlquileres.leerNumAlq(); i++){
            fechaFin.insertaFecha(listadoAlquileres.leerFechaAlquilada(i));
            duracion=listadoAlquileres.calcularDuracion(listadoAlquileres.leerMaquinaAlquilada(i), listadoAlquileres.leerFincaAlquilada(i));
            System.out.printf("%-20d", listadoAlquileres.leerMaquinaAlquilada(i).leerId());
            System.out.printf("%-20d", listadoAlquileres.leerFincaAlquilada(i).leerId());
            System.out.printf("%d/%d/%d", listadoAlquileres.leerFechaAlquilada(i).leerDia(), listadoAlquileres.leerFechaAlquilada(i).leerMesNum(), listadoAlquileres.leerFechaAlquilada(i).leerAnyo());
            funGesAMA.RellenaEspacios(8,20);
            System.out.printf("%d/%d/%d\n", listadoAlquileres.leerFechaAlquilada(i).calcularNuevaFecha(fechaFin, false, duracion).leerDia(), listadoAlquileres.leerFechaAlquilada(i).calcularNuevaFecha(fechaFin, false, duracion).leerMesNum(), listadoAlquileres.leerFechaAlquilada(i).calcularNuevaFecha(fechaFin, false, duracion).leerAnyo());
        }
        
        //system("pause");
        System.out.println("Presiona Enter para continuar...");
        scanner.nextLine();
    }
}
