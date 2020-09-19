package com.example.revistascientficasdelauteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.revistascientficasdelauteq.WebServices.Asynchtask;
import com.example.revistascientficasdelauteq.WebServices.WebService;
import com.example.revistascientficasdelauteq.adaptador.AdaptadorSeccion;
import com.example.revistascientficasdelauteq.modelo.Articulo;
import com.example.revistascientficasdelauteq.modelo.Seccion2;

import org.json.JSONArray;
import org.json.JSONException;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SeccionArticulosActivity extends AppCompatActivity implements Asynchtask {
    private RequestQueue queue;
    ArrayList<Articulo> articulos;
    ArrayList<Seccion2> seccion2Articulo;
    RecyclerView recyclerView;
    //AdaptadorEdicion adapator;
    Bundle parametros;
    String id_revista,id_edicion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_session_articulos);
        parametros = this.getIntent().getExtras();
        queue= Volley.newRequestQueue(SeccionArticulosActivity.this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_final);
        recyclerView.setLayoutManager(new LinearLayoutManager(SeccionArticulosActivity.this));
        handleSSLHandshake();
        consultarSeccionesArticulos();
    }

    private void consultarSeccionesArticulos() {
        id_revista= parametros.getString("id_revista");
        id_edicion=parametros.getString("id_edicion");
        //String url1="https://revistas.uteq.edu.ec/ws/pubssections.php?i_id="+id_edicion;
        //String url="https://revistas.uteq.edu.ec/ws/pubs.php?i_id=78&section=2";
        String url="https://revistas.uteq.edu.ec/ws/pubs.php?i_id="+id_edicion;
        //String url ="https://revistas.uteq.edu.ec/ws/pubs.php?i_id="+id_revista+"&section="+id_edicion;
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(url,
                datos, SeccionArticulosActivity.this, SeccionArticulosActivity.this);
        ws.execute("GET");
    }

    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};
            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
    @Override
    public void processFinish(String result) throws JSONException {
        JSONArray jsonlista= new JSONArray(result);
        articulos = Articulo.JsonObjectsBuild(jsonlista);
        seccion2Articulo = Seccion2.JsonObjectsBuild(articulos);
        AdaptadorSeccion adaptadorSeccion= new AdaptadorSeccion(getApplicationContext(), seccion2Articulo);
        recyclerView.setAdapter(adaptadorSeccion);
    }
}