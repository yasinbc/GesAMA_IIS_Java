

public class TipoFecha
{
    private int dia;
    private int mes;
    private int anyo;
    public enum TipoDia{Lunes, Martes, Miercoles, Jueves, Viernes, Sabado, Domingo};
    public enum TipoMes {Enero, Febrero, Marzo, Abril, Mayo, Junio, Julio, Agosto, Septiembre, Octubre, Noviembre, Diciembre};
    
    
    /** devuelve una nueva fecha en función si es un traslado o no */
    public TipoFecha calcularNuevaFecha(TipoFecha fecha, boolean traslado, int duracion){
        int diaNuevo, mesNuevo, anyoNuevo;
        TipoFecha fechaNueva, fechaAux;
        CalendarioMes cal;
        
        //si es por el traslado
        if (traslado){
            diaNuevo = fecha.leerDia()-1;//leemos el dia
            if (diaNuevo == 0){//si el nuevo día es 0
                mesNuevo = fecha.leerMesNum()-1;
                if (mesNuevo == 0){//como no hay mes 0, pasamos a diciembre y decrementamos el año
                    mesNuevo=12; //como el mes es 0, pasamos a diciembre
                    anyoNuevo = fecha.leerAnyo()-1; // como hemos retrocedido a diciembre, pasamos al año anterior
                } else{
                    anyoNuevo = fecha.leerAnyo();
                }
                fechaNueva.inicializa(0,mesNuevo,anyoNuevo);//esto lo hacemos para tener el último día del nuevo mes
                diaNuevo = cal.DiasDelMes(fechaNueva); //ponemos el último día del mes
            } else {//al restar 1 a la fecha no es 0, por lo tanto mantenemos el mes y el año
                mesNuevo = fecha.leerMesNum();
                anyoNuevo = fecha.leerAnyo();
            }
        } else { //si es para mostrar la fecha de finalización
            diaNuevo=fecha.leerDia()+duracion;
            mesNuevo = fecha.leerMesNum();
            anyoNuevo = fecha.leerAnyo();

            fechaAux.inicializa(1, mesNuevo, anyoNuevo);

            while(diaNuevo>cal.DiasDelMes(fechaAux)){
                diaNuevo=diaNuevo-cal.DiasDelMes(fechaAux);
                mesNuevo++;
                
                if (mesNuevo>12){
                    mesNuevo=1;
                    anyoNuevo++;
                }
                fechaAux.updMes(mesNuevo);
                fechaAux.updAnyo(anyoNuevo);
            }

        }

        //guardamos la nueva fecha
        fechaNueva.inicializa(diaNuevo, mesNuevo, anyoNuevo);
        
        return fechaNueva;
    }
    
    /** comprueba que la fecha introducida sea correcta */
    public boolean comprobarFecha(int dia, int mes, int anyo){
        boolean diaOK, mesOK, anyoOK;
        int diasMes;
        TipoFecha fecha;
        CalendarioMes cal;

        diaOK = false;
        mesOK = false;
        anyoOK = false;
        
        fecha.inicializa(dia, mes, anyo);
        diasMes=cal.DiasDelMes(fecha);

        if (fecha.leerAnyo()>1601 && fecha.leerAnyo()<=3000){
            anyoOK=true;
        } else {
            anyoOK=false;
        }
        if (fecha.leerMesNum()>0 && fecha.leerMesNum()<13){
            mesOK=true;
            if (fecha.leerDia()>0 && fecha.leerDia()<=diasMes){
                diaOK=true;
            } else {
                diaOK =false;
            }
        } else {
            mesOK=false;
        }

        if ((anyoOK) && (mesOK) && (diaOK)){
            return true;
        } else {
            return false;
        }
    }
    
    /** guardar una fecha con variables */
    public void inicializa(int dia, int mes, int anyo){
        this.dia = dia;
        this.mes = mes;
        this.anyo = anyo;
    }
    
    /** guardar una fecha con un objeto TipoFecha */
    public void insertaFecha(TipoFecha fecha){
        this.dia = fecha.leerDia();
        this.mes = fecha.leerMesNum();
        this.anyo = fecha.leerAnyo();
    }
    
    /** lee el día */
    public int leerDia(){
        return this.dia;
    }
    
    /** lee el mes como texto */
    public TipoMes leerMes(){
        return (TipoMes)this.mes-1;
    }
    
    /**  lee el mes como número */
    public int leerMesNum(){
        return this.mes;
    }
    
    /** lee el año */
    public int leerAnyo(){
        return this.anyo;
    }
    
    /** actualiza el día */
    public void updDia(int dia){
        this.dia=dia;
    }
    
    /** actualiza el mes */
    public void updMes(int mes){
        this.mes=mes;
    }
    
    /** actualiza el año */
    public void updAnyo(int anyo){
        this.anyo=anyo;
    }
    
    /** lee la fecha como un objeto TipoFecha */
    public TipoFecha leerFecha(){
        TipoFecha fecha;
        fecha.inicializa(this.dia, this.mes, this.anyo);
        return fecha;        
    }
}