
public class TipoCoordenadas
{
    private float lat;
    private float lon;
    
    /** calcula la distancia entre dos puntos */
    public int calcularDistancia(TipoCoordenadas CoordMaq, TipoCoordenadas CoordFinca){
        //float lat, lon;
        double lat, lon;
        int dist;

        //lon = pow(CoordFinca.lon - CoordMaq.lon, 2);
        //lat = pow(CoordFinca.lat - CoordMaq.lat, 2);
        lon = Math.pow(CoordFinca.lon - CoordMaq.lon, 2);
        lat = Math.pow(CoordFinca.lat - CoordMaq.lat, 2);

        

        //dist = int(ceil(sqrt(lon+lat)*110));
        dist = (int) Math.ceil(Math.sqrt((lon + lat) * 110));


        if (dist<0){
            dist=dist*-1;
        }
        
        return dist;
    }
    
    /** lee Latitud */
    public float leerLat(){
        return this.lat;
    }
    
    /** lee Longitud */
    public float leerLon(){
        return this.lon;
    }
    
    /** guarda la longitud/latitud */
    public void inicializa(float x, float y){
        this.lon = x;
        this.lat = y;
    }
}
