package com.example.revistascientficasdelauteq;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.revistascientficasdelauteq.WebServices.Asynchtask;
import com.example.revistascientficasdelauteq.WebServices.WebService;
import com.example.revistascientficasdelauteq.adaptador.AdaptadorRevista;
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

public class RevistasActivity extends AppCompatActivity implements Asynchtask {
    private RequestQueue queue;
    ArrayList<Revista> revistas;
    RecyclerView recyclerView;
    AdaptadorRevista adapator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revistas);
        queue= Volley.newRequestQueue(RevistasActivity.this);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView_revistas);
        recyclerView.setLayoutManager(new LinearLayoutManager(RevistasActivity.this));
        handleSSLHandshake();
        consultarRevistas();
    }
    private void consultarRevistas(){
        String url ="https://revistas.uteq.edu.ec/ws/journals.php";
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(url,
                datos, RevistasActivity.this, RevistasActivity.this);
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
        try {
            JSONArray jsonlista= new JSONArray(result);
            revistas = Revista.JsonObjectsBuild(jsonlista);
            adapator= new AdaptadorRevista(getApplicationContext(), revistas);
            adapator.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getApplicationContext(),"Selecciono :" + revistas.get(recyclerView.getChildAdapterPosition(view)).getJournalId(),Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(RevistasActivity.this,EdicionActivity.class);
                    intent.putExtra("id_revista",revistas.get(recyclerView.getChildAdapterPosition(view)).getJournalId());
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(adapator);
        }catch (JSONException e)
        {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG);
        }
    }
}