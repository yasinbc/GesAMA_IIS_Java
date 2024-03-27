
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
    

    public void DameMes(int id, String mesImpreso){}
    

    public char DameSemilla(){}
    

    public float DameCoordenada(int tipo){}
}
