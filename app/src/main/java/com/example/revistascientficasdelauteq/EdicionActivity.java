package com.example.revistascientficasdelauteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.revistascientficasdelauteq.WebServices.Asynchtask;
import com.example.revistascientficasdelauteq.WebServices.WebService;
import com.example.revistascientficasdelauteq.adaptador.AdaptadorEdicion;
import com.example.revistascientficasdelauteq.adaptador.AdaptadorRevista;
import com.example.revistascientficasdelauteq.modelo.Edicion;
import com.example.revistascientficasdelauteq.modelo.Revista;

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

public class EdicionActivity extends AppCompatActivity implements Asynchtask {
    private RequestQueue queue;
    ArrayList<Edicion> ediciones;
    RecyclerView recyclerView;
    AdaptadorEdicion adapator;
    Bundle parametros;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edicion);
        parametros = this.getIntent().getExtras();
        queue= Volley.newRequestQueue(EdicionActivity.this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_ediciones);
        recyclerView.setLayoutManager(new LinearLayoutManager(EdicionActivity.this));
        handleSSLHandshake();
        consultarRevistas();
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
    private void consultarRevistas(){
        String id = parametros.getString("id_revista");
        String url ="https://revistas.uteq.edu.ec/ws/issues.php?j_id="+id;
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(url,
                datos, EdicionActivity.this, EdicionActivity.this);
        ws.execute("GET");
    }
    @Override
    public void processFinish(String result) throws JSONException {
        try {
            JSONArray jsonlista= new JSONArray(result);
            ediciones = Edicion.JsonObjectsBuild(jsonlista);
            adapator= new AdaptadorEdicion(getApplicationContext(), ediciones);
            adapator.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"Selecciono :" + ediciones.get(recyclerView.getChildAdapterPosition(view)).getIssueId(),Toast.LENGTH_SHORT).show();
                }
            });
            recyclerView.setAdapter(adapator);
        }catch (JSONException e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
        }

    }
}