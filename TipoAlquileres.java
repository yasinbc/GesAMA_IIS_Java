import static java.lang.Math.ceil;


public class TipoAlquileres
{
    private int numAlquileres;
    private ListaMaquinasExt maquinasAlquiladas;
    private ListaFincasExt fincasAlquiladas;
    private ListaFechas fechasAlquiladas;
    public TipoObjeto [] ListaMaquinasExt = new TipoObjeto [50];
    public TipoObjeto [] ListaFincasExt = new TipoObjeto [50];
    public TipoFecha [] ListaFechas = new TipoFecha [50];
    
    /** guarda un alquiler */
    public void guardarAlquiler(TipoObjeto maquina, TipoObjeto finca, TipoFecha fecha){
        if(!alquilerSolapado(maquina, finca, fecha)){
            //inserta en cada uno de los arrays de maquinas/fincas alquiladas el TipoObjeto correspondiente
            maquinasAlquiladas[numAlquileres].insertaObjeto(maquina);
            fincasAlquiladas[numAlquileres].insertaObjeto(finca);
            fechasAlquiladas[numAlquileres].insertaFecha(fecha);
            //incrementa el número de alquileres realizados
            numAlquileres++;
            ordenacionAlquileres();
        } else {
            System.out.printf("No se ha podido realizar el alquiler debido a que se solapa con otro\n");
        }
    }
    
    /** calcula cuantos días durará el alquiler */
    public int calcularDuracion(TipoObjeto maq, TipoObjeto finca){
        int dias;

        //dias = int(ceil(f.leerCapacidad() / m.leerCapacidad()));
        dias = (int) ceil(finca.leerCapacidad() / maq.leerCapacidad());

        return dias;
    }
    
    public void borrarAlquiler(TipoObjeto obj, char tipo){}
    
    /** devuelve la posición de un alquiler de una máquina */
    public int buscarAlquiler(TipoObjeto obj, char tipo, boolean encontrada){
        int pos=0;
        encontrada=false;

        if (tipo=='f'){
            do{
                if(fincasAlquiladas[pos].leerId() == obj.leerId()){
                    encontrada=true;
                }
                pos++;
            }while(pos<numAlquileres && (!encontrada));
        } else if (tipo=='m'){
            do{
                if(maquinasAlquiladas[pos].leerId() == obj.leerId()){
                    encontrada=true;
            }
            pos++;
            }while(pos<numAlquileres && (!encontrada));
        }
        
        return pos;
    }
    
    /** se pasa una máquina y una fecha y devuelve la finca donde está alquilada previa a esa fecha */
    public TipoObjeto buscarFincaUltimoAlquiler(TipoAlquileres alquileres, TipoObjeto maquina, TipoFecha fecha){
        TipoObjeto finca, maquinaAlquilada;
        TipoCoordenadas ubica;

        boolean encontrado=false;
        //recorre los alquileres en busca de los que correspondan a la máquina indicada
        for (int i=0; i<numAlquileres;i++){
            maquinaAlquilada.insertaObjeto(alquileres.leerMaquinaAlquilada(i));
            if (maquina.leerId()==maquinaAlquilada.leerId()){
                if (fechasAlquiladas[i].leerAnyo() < fecha.leerAnyo() || (fechasAlquiladas[i].leerMes() < fecha.leerMes() || (fechasAlquiladas[i].leerDia() < fecha.leerDia()))){
                    finca.insertaObjeto(alquileres.fincasAlquiladas[i]);
                    encontrado=true;
                }
            }

            //si no ha encontrado ningún alquiler para esa máquina se traslada desde la base
        }

        if (!encontrado){
            ubica.inicializa(1,1);
            finca.inicializa(0, "Base Empresa", 'g', 0, ubica);
        }

        return finca;
    }
    
    /** comprueba si un nuevo alquiler se solapa con uno ya existente de la misma máquina */
    public boolean alquilerSolapado(TipoObjeto maquinaNueva, TipoObjeto fincaNueva, TipoFecha fechaNueva){
        int pos=0;
        int alquileresEncontrados=0;//para guarda los alquileres que coinciden con la máquina, se usa de break en el while
        int alquileresSolapados;
        TipoFecha fechaAlquilada, fechaFinAlquilada, fechaFinNueva;//guarda las fechas

        fechaFinNueva.insertaFecha(fechaFinNueva.calcularNuevaFecha(fechaNueva,false,calcularDuracion(maquinaNueva, fincaNueva)));//calculamos la fecha de finalización del nuevo alquiler

        //comprueba cuantos alquileres tiene la máquina
        for (int i=0; i<numAlquileres; i++){
            if(maquinasAlquiladas[i].leerId()== maquinaNueva.leerId()){
                alquileresEncontrados++;
            }
        }
        //alquileresSolapados servirá para detectar si se solapa con algún alquiler
        alquileresSolapados=alquileresEncontrados;
        if ((numAlquileres>0) && (alquileresEncontrados>0)){ //si no hay ni alquileres ni encuentra uno para esa maquina no hay solapamiento
            do{
                if(maquinasAlquiladas[pos].leerId()== maquinaNueva.leerId()){//si encontra la máquina alquilada
                    fechaAlquilada.insertaFecha(fechasAlquiladas[pos]);
                    fechaAlquilada.insertaFecha(fechaAlquilada.calcularNuevaFecha(fechaAlquilada,true,calcularDuracion(maquinasAlquiladas[pos], fincasAlquiladas[pos])));
                    fechaFinAlquilada.insertaFecha(fechaFinAlquilada.calcularNuevaFecha(fechaAlquilada,false,calcularDuracion(maquinasAlquiladas[pos], fincasAlquiladas[pos])+1)); //calculamos la fecha de finalización del alquiler encontrado
                  /**1 comproba que el año del nuevo alquiler sea mayor.
                    *2 comproba si el año del nuevo alquiler es igual al de fin del alquiler Y el mes es posterior
                    *3 comproba si el año del nuevo alquiler es igual al de fin del alquiler Y el mes es igual Y el dia es posterior*/
                    if (/*1*/fechaNueva.leerAnyo() > fechaFinAlquilada.leerAnyo() /*1*/ || /*2*/(fechaNueva.leerAnyo() == fechaFinAlquilada.leerAnyo() && (fechaNueva.leerMesNum() > fechaFinAlquilada.leerMesNum()/*2*/ || /*3*/(fechaNueva.leerMesNum() == fechaFinAlquilada.leerMesNum() && fechaNueva.leerDia()-1> fechaFinAlquilada.leerDia()/*3*/)))){
                        alquileresSolapados--;
                    }
                  /**1 comproba que el año del fin del nuevo alquiler sea anterior al inicio del alquiler
                    *2 comproba que el año del fin del nuevo alquiler sea igual al inicio del alquiler Y el mes sea anterior
                    *3 comproba que el año del fin del nuevo alquiler sea igual al inicio del alquiler Y el mes sea igual Y el dia es anterior*/
                    if (/*1*/ fechaFinNueva.leerAnyo() < fechaAlquilada.leerAnyo() /*1*/|| /*2*/(fechaFinNueva.leerAnyo()==fechaAlquilada.leerAnyo() && (fechaFinNueva.leerMesNum() < fechaAlquilada.leerMesNum() /*2*/ ||/*3*/ (fechaFinNueva.leerMesNum()==fechaAlquilada.leerMesNum() && fechaFinNueva.leerDia() < fechaAlquilada.leerDia()/*3*/ )))){
                        alquileresSolapados--;
                    }
                    alquileresEncontrados--;//si ha encontrado una maquina que coincide, decrementa los alquileres encontrados
                }
                pos++;
            //recorre mientras pos sea menor a numAlquileres Y no se haya encontrado ningún solapado Y queden alquileres que buscar
            }while((pos<numAlquileres) /*&& (!solapado)*/ && (alquileresEncontrados>0));
        }
        //si alquileresSolapados vale 0 es que no se han encontrado alquileres que se solapan con el nuevo
        if (alquileresSolapados==0){
            return false;
        } else {
            return true;
        }
    }
    
    /** se le pasa una máquina/finca para borrar todos los alquileres relacionados */
    public void borrarAlquiler(){
        int pos;
        int borrados=0;
        boolean encontrada;

        if (tipo=='f'){
            pos=buscarAlquiler(obj, tipo, encontrada)-1;
            while(pos>=0 && (encontrada)){
                if (encontrada){
                    for (int i = pos; i<numAlquileres;i++){
                        fincasAlquiladas[pos]=fincasAlquiladas[pos+1];
                        maquinasAlquiladas[pos]=maquinasAlquiladas[pos+1];
                        fechasAlquiladas[pos]=fechasAlquiladas[pos+1];
                    }
                    numAlquileres--; //resta una posición del alquiler
                    borrados++;//suma una a los borrados
                    pos=buscarAlquiler(obj, tipo, encontrada)-1;//vuelve a buscar la finca
                }
            }
        } else if (tipo=='m'){
            pos=buscarAlquiler(obj, tipo, encontrada)-1;
            while(pos>=0 && (encontrada)){
                if (encontrada){
                    for (int i = pos; i<numAlquileres;i++){
                        fincasAlquiladas[pos]=fincasAlquiladas[pos+1];
                        maquinasAlquiladas[pos]=maquinasAlquiladas[pos+1];
                        fechasAlquiladas[pos]=fechasAlquiladas[pos+1];
                    }
                    numAlquileres--; //resta una posición el alquiler
                    borrados++;//suma una a los borrados
                    pos=buscarAlquiler(obj, tipo, encontrada)-1;//vuelve a buscar la finca
                }
            }
        }
        if (borrados>0){
            System.out.printf("Se han borrado %d alquileres\n", borrados);
            ordenacionAlquileres();
        }
    }
    
    /** Recorre el array maquinasAlquiladas buscando la máquina, cuando la encuentra la copia en uno auxiliar para ordenarlos, 
      * primero por maquina, luego por fecha */
    public void ordenacionAlquileres(){
        int pos=0;
        TipoObjeto maq, finca;
        TipoFecha fecha;

        ListaMaquinasExt maquinasAlquiladasTmp;
        ListaFincasExt fincasAlquiladasTmp;
        ListaFechas fechasAlquiladasTmp;

        //ORDENA LAS MÁQUINAS
        //recorre las máquinas guardadas para obtener el id
        for (int m=0; m<numAlquileres; m++){
            maq.insertaObjeto(maquinasAlquiladas[m]);
            //recorre las maquinasAlquiladas para comprobar si hay algun alquiler de la máquina seleccionada
            for (int mA=0; mA<numAlquileres; mA++){
                //si la encuentra, la copia al array temporal
                if (maq.leerId() == leerMaquinaAlquilada(mA).leerId()){
                    maquinasAlquiladasTmp[pos] = maquinasAlquiladas[mA];
                    fincasAlquiladasTmp[pos] = fincasAlquiladas[mA];
                    fechasAlquiladasTmp[pos] = fechasAlquiladas[mA];
                    pos++;
                }
            }
        }

        //copia en el array correcto el array temporal
        //memcpy(maquinasAlquiladas, maquinasAlquiladasTmp, 50);
        //memcpy(fincasAlquiladas, fincasAlquiladasTmp, 50);
        //memcpy(fechasAlquiladas, fechasAlquiladasTmp, 50);
        System.arraycopy(maquinasAlquiladasTmp, 0, maquinasAlquiladas, 0, 50);
        System.arraycopy(fincasAlquiladasTmp, 0, fincasAlquiladas, 0, 50);
        System.arraycopy(fechasAlquiladasTmp, 0, fechasAlquiladas, 0, 50);
        
        //ORDENA LAS FECHAS POR MÁQUINA

        for (int i = 0; i<numAlquileres;i++){
            for (int j=0; j<numAlquileres-i-1;j++){
                if(maquinasAlquiladas[j].leerId()==maquinasAlquiladas[j+1].leerId()){
                    if (fechasAlquiladas[j].leerAnyo() > fechasAlquiladas[j+1].leerAnyo() || (fechasAlquiladas[j].leerAnyo() == fechasAlquiladas[j+1].leerAnyo() && fechasAlquiladas[j].leerMes() > fechasAlquiladas[j+1].leerMes() || (fechasAlquiladas[j].leerMes() == fechasAlquiladas[j+1].leerMes() &&fechasAlquiladas[j].leerDia() > fechasAlquiladas[j+1].leerDia()))){
                        //ordena las fechas
                        fecha.insertaFecha(fechasAlquiladas[j]);
                        fechasAlquiladas[j]=fechasAlquiladas[j+1];
                        fechasAlquiladas[j+1]=fecha;
                        //ordena las fincas
                        finca.insertaObjeto(fincasAlquiladas[j]);
                        fincasAlquiladas[j]=fincasAlquiladas[j+1];
                        fincasAlquiladas[j+1]=finca;
                    }
                }
            }
        }
    }
    
    /** inicializa a 0 numAlquileres */
    public void inicializa(){
        this.numAlquileres = 0;
    }
    
    /** lee una Máquina alquilada */
    public TipoObjeto leerMaquinaAlquilada(int pos){
        TipoObjeto maquina;
        maquina.insertaObjeto(maquinasAlquiladas[pos]);
        return maquina;
    }
    
    /** lee una Finca alquilada */
    public TipoObjeto leerFincaAlquilada(int pos){
        TipoObjeto finca;
        finca.insertaObjeto(fincasAlquiladas[pos]);
        return finca;
    }
    
    /** la Fecha de un alquiler */
    public TipoFecha leerFechaAlquilada(int pos){
        TipoFecha fecha;
        fecha.insertaFecha(fechasAlquiladas[pos]);
        return fecha;
    }
    
    /** devuelve los alquileres que se han realizado */
    public int leerNumAlq(){
        return this.numAlquileres;
    }
}
