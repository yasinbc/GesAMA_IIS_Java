import java.util.*;

public class FunGesAMA
{
    public String TipoGBA;
    public String MiniString;
    public String string;
    public final float latitudMin = 36.0;
    public final float latitudMax = 43.5;
    public final float longitudMin = -9.5;
    public final float longitudMax = 4.5;
    
    public void TraducirTipo(char t, TipoGBA tipo){}
    
    public void RellenaEspacios(int largo, int ancho){
        for (int i=largo; i<ancho; i++){
            System.out.printf (" ");
        }
    }
    

    public void DameMes(int id, String mesImpreso){
        switch (TipoMes(id)) {
            case Enero:
                mesImpreso = "ENERO";
                break;
            case Febrero:
                mesImpreso = "FEBRERO";
                break;
            case Marzo:
                mesImpreso = "MARZO";
                break;
            case Abril:
        	mesImpreso = "ABRIL";
        	break;
            case Mayo:
                mesImpreso = "MAYO";
                break;
            case Junio:
        	mesImpreso = "JUNIO";
                break;
            case Julio:
        	mesImpreso = "JULIO";
        	break;
            case Agosto:
        	mesImpreso = "AGOSTO";
        	break;
            case Septiembre:
        	mesImpreso = "SEPTIEMBRE";
        	break;
            case Octubre:
        	mesImpreso = "OCTUBRE";
        	break;
            case Noviembre:
        	mesImpreso = "NOVIEMBRE";
        	break;
            case Diciembre:
        	mesImpreso = "DICIEMBRE";
        	break;
	  }
    }
    

    public char DameSemilla(){
        int semilla;
        char sem = 'G';
        Random random = new Random();

        //srand(time(NULL));

        //semilla=1+(rand()%3);
        
        semilla = 1+ random.nextInt(3);
        
        switch(semilla){
            case 1:
                sem = 'G';
                break;
            case 2:
                sem = 'A';
                break;
            case 3:
                sem = 'U';
                break;
        }
        return sem;
    }
    

    public float DameCoordenada(int tipo){
        int signo;
        float coordenada=0;
        Random random = new Random();

        if (tipo==0) {
            do{coordenada=(longitudMin + /*rand()%9*/random.nextInt(9)) + ((/*rand() % 1001*/random.nextInt(1001)/110)*0.938;}while(coordenada>longitudMax);
        } else if (tipo==1) {
            do{coordenada=latitudMin + ((rand() % 1900)/220)*0.871;}while(coordenada>latitudMax);
        }

        return coordenada;
    }
}
