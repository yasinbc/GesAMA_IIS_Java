
public class CalendarioMes
{
    public String DiasCalendario;
    public String Fincas;

    /** devuelve el día de la semana */
    public TipoDia SumarDias(TipoDia dia, int N){
        final int DIAS_SEMANA = 7;
        int aux;

        aux = ((int)dia + N ) % DIAS_SEMANA;

        return (TipoDia)aux;
    }
    
    public TipoDia DiaDeLaSemana(TipoFecha fecha){
       TipoMes M = fecha.leerMes();
       int anyo = fecha.leerAnyo();
       int increDias;                        /* Incremento en días de la semana */
       int increAnyos;                         /* Incremento en días por años enteros */
       int increBisiesto;                       /* Incremento en días por años bisiestos */
       final int AnyoReferencia = 1601;            /* Año a partir del cual determinamos el día */
       final TipoDia DiaReferencia = Domingo;    /* Día a partir del cual determinamos el día*/
        
       if (M == Enero) {increDias = 0;}
           else if (M == Febrero ){increDias =3;}
           else if (M == Marzo )  {increDias =3;}
           else if (M == Abril )  {increDias =6;}
           else if (M == Mayo)    {increDias =1;}
           else if (M == Junio)   {increDias =4;}
           else if (M == Julio)   {increDias =6;}
           else if (M == Agosto)  {increDias =2;}
           else if (M == Septiembre) {increDias =5;}
           else if (M == Octubre) {increDias =0;}
           else if (M == Noviembre) {increDias =3;}
           else {increDias = 5;}
           
       /* Cálculo de incremento de días por años completos desde año referencia */
       increAnyos = anyo - AnyoReferencia;
       /* Añadir dias por años bisiestos */
       increBisiesto = 0;
       
       for (int N=1602; N<anyo; N++) {
           if (bisiesto (N)) {
               increBisiesto ++;
           }
       }
       
       /* añadir un día si la fecha a evaluar cae en un ano bisiesto despues del día 29 de febrero */
       if( bisiesto (anyo) &&    ( M > Febrero )) {
           increDias ++;
       }
    
       /* Cálculo de incremento total de días */
       increDias = increDias + increAnyos + increBisiesto + fecha.leerDia();

       return SumarDias (DiaReferencia, increDias);
    }
    
    /** devuelve el nº de dias de un mes */
    public int DiasDelMes(TipoFecha fecha){
        switch (fecha.leerMes()) {
           case Febrero:
               if (bisiesto (fecha.leerAnyo())) {
                   return (29);
               }else{
                   return (28);
               };
               break;
            case Abril:
            case Junio:
            case Septiembre:
              return (30);
              break;
            default:
              return (31);
       }
    }
    
    /** comprueba si el año es bisiesto */
    public boolean bisiesto(int anyo){
        return ((anyo % 4 == 0) && (anyo % 100 != 0)) || (anyo % 400 == 0);
    }
    
    public void crearCalendario(TipoAlquileres alquileres, int mes, int anyo, int idMaquina){
        DiasCalendario dias;
        String leyenda;
        String nombre;
        Fincas fincas;
        char c = 'C';
        int diaInicio, diaFin, duraAlquiler, mesAnterior, mesPosterior;
        int alquilada, diasTraslado, diasEspera, diasCosechados;
        int tope = alquileres.leerNumAlq();
        TipoFecha fechaAlquiler, fechaFinAlquiler, fechaAux;
        TipoObjeto fincaAlquilada;
        
        diaInicio=0;
        diaFin=0;
        
        //Cargamos los días, luego sobreescribimos con CX
        for (int i=0; i<31; i++){
            System.out.printf(leyenda, "%d", i+1);
            strcpy(dias[i],leyenda);
        }
        
        //ponemos variables a 0
        diasTraslado=0;
        diasCosechados=0;
        diasEspera=0;
        alquilada=0;
        mesAnterior=mes-1;
        mesPosterior=mes+1;
        
        if (mesAnterior==0){
            mesAnterior=12;
        }

        if (mesPosterior>12){
            mesPosterior=1;
        }

        fechaAux.inicializa(1,mes, anyo);
        
        for (int i=0; i<tope; i++){
            if (alquileres.leerMaquinaAlquilada(i).leerId()== idMaquina){//si encontramos la máquina
                //guardo fecha, calculo duracion alquiler, calculo fecha finalizacion y obtengo la finca
                fechaAlquiler.insertaFecha(alquileres.leerFechaAlquilada(i));
                duraAlquiler=alquileres.calcularDuracion(alquileres.leerMaquinaAlquilada(i), alquileres.leerFincaAlquilada(i));
                fechaFinAlquiler=fechaFinAlquiler.calcularNuevaFecha(fechaAlquiler, false, duraAlquiler);
                fincaAlquilada.insertaObjeto(alquileres.leerFincaAlquilada(i));

                //si el alquiler es del mes anterior o del mes solicitado
                if ((fechaAlquiler.leerMesNum()==mesAnterior) || (fechaAlquiler.leerMesNum()==mes)){
                    alquilada++;//aumentamos los alquileres
                    //si el inicio es del mes anterior, pero finaliza en este
                    if ((fechaAlquiler.leerMesNum()==mesAnterior) && (fechaFinAlquiler.leerMesNum() == mes)) {
                        diaInicio=0;
                        fincaAlquilada.leerNombre(nombre);
                        strcpy(fincas[alquilada-1], nombre );//guardamos el nombre de la finca para la leyenda
                    } else {//si es de este mes
                        diaInicio=fechaAlquiler.leerDia()-1;//A REVISAR, XQ RESTO 1??
                        fincaAlquilada.leerNombre(nombre);
                        strcpy(fincas[alquilada-1], nombre );//guardamos el nombre de la finca para la leyenda
                  
                        if (diaInicio>0){//si empezamos el alquiler un dia que no sea uno
                        strcpy(dias[diaInicio-1], "Tr");
                        diasTraslado++;//aumentamos los días de traslado
                        }
                      }
              
                    if ((fechaAlquiler.leerMesNum()==mes) && (fechaFinAlquiler.leerMesNum() > mes)){//si el alquiler es de este mes pero termina el siguiente ponemos que el úlitmo día es el último día del mes que buscamos
                        diaFin=DiasDelMes(fechaAlquiler);
                    } else {//si no termina el mes que viene, ponemos que es el último día del alquiler
                        diaFin=fechaFinAlquiler.leerDia();
                    }
                    
                    //si un alquiler empieza el 1 del mes posterior, en el de la consulta tendré que poner el traslado
                }else if ((fechaAlquiler.leerMesNum()==mesPosterior) && (fechaAlquiler.leerDia()==1)){
                    diaInicio=DiasDelMes(fechaAux);
                    diaFin=DiasDelMes(fechaAux);
                    strcpy(dias[diaInicio-1], "Tr");
                    diasTraslado++;//aumentamos los días de traslado
              }

              for (int j=diaInicio; j<diaFin; j++){//recorremos el array de dias para marcar los días alquilados
                  System.out.printf(leyenda, "%c%d", c, alquilada); //alquileres.leerFincaAlquilada(i).leerId());
                  strcpy(dias[j],leyenda);
                  diasCosechados++;//aumentamos los días cosechados
              }
            }
        }
    
        diasEspera= DiasDelMes(fechaAlquiler)-diasCosechados-diasTraslado;
        pintarCalendario(dias, mes, anyo);
        mostrarLeyenda(fincas, alquilada, diasTraslado, diasCosechados, diasEspera);    
    }
    
    /** imprime la ocupación de un mes */
    public void pintarCalendario(DiasCalendario calendario, int mes, int anyo){
        int ind = 0;
        TipoFecha fecha;
        TipoDia PrimerDia;

        fecha.inicializa(1, mes, anyo);

        PrimerDia=DiaDeLaSemana(fecha);
        
        for (int k = (int)PrimerDia; k >= 1; k --) {                /* imprimir blancos de principio de mes */
            if (ind % 7 == 5) {                        /* imprimir barra de fín de semana */
                System.out.printf ("| ");
            }
            
            if ( ind %7 != 0 && ind % 7 != 5 ) {    /* imprimir blanco entre campos si no es lunes o viernes */
                System.out.printf (" ");
            }
            System.out.printf (" . ");
            ind ++;
    }
    
    for (int k = 0; k < DiasDelMes (fecha); k ++) {            /* imprimir todos los días del mes*/
            if (ind != 0) {
                if (ind % 7 == 0) {                    /* cambio de semana (linea ) si es necesario */
                    System.out.printf ("\n");
                }
            }
            if (ind % 7 == 5) {                        /* imprimir barra de fín de semana */
                System.out.printf ("| ");
            }
            if ( ind %7 != 0 && ind % 7 != 5 ) {        /* imprimir blanco entre campos si no es lunes o viernes */
                System.out.printf (" ");
            }
            printf ("%2s ", calendario[k]);
            ind++;
    }
        
    while ( ind % 7 != 0 ) {
            if (ind % 7 == 5) {                        /* imprimir barra de fín de semana */
                System.out.printf ("| ");
            }
            if ( ind %7 != 0 && ind % 7 != 5 ) {    /* imprimir blanco entre campos si no es lunes o viernes */
                System.out.printf (" ");
            }
                System.out.printf (" . ");
                ind++;
    }
    
    System.out.printf ("\n");
    }
    
    /** imprime la leyenda que hay debajo del calendario */
    public void mostrarLeyenda(Fincas fincas, int alquilada, int diasTraslado, int diasCosechados, int diasEspera){
        System.out.printf("Tiempo de traslados (Tr): %d días\n", diasTraslado);
        System.out.printf("Tiempo de espera: %d días\n", diasEspera);
        for (int i=0; i<alquilada; i++){
            printf("Cosecha C%d: finca %s\n", i+1, fincas[i]);
        }
        System.out.printf("Tiempo total de cosechas (C#): %d días\n", diasCosechados);
    }
}
