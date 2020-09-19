package com.example.revistascientficasdelauteq.modelo;

import java.util.ArrayList;
import java.util.List;

public class Seccion2 {
    String section;
    List<SeccionArticulo> articulos;
    public Seccion2(String section, List<SeccionArticulo> articulos) {
        this.section = section;
        this.articulos = articulos;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public List<SeccionArticulo> getArticulos() {
        return articulos;
    }

    public void setArticulos(List<SeccionArticulo> articulos) {
        this.articulos = articulos;
    }


    public static ArrayList<Seccion2> JsonObjectsBuild(List<Articulo> articulos){
        ArrayList<Seccion2> seccion2Revistas = new ArrayList<>();
        ArrayList<String> secciones= new ArrayList<>();
        String seccion="";
        String aux_seccion="";
        for (int i = 0; i < articulos.size() ; i++) {
            seccion=articulos.get(i).getSection();
            if(!seccion.equals(aux_seccion)){
                secciones.add(seccion);
            }
            aux_seccion=seccion;
        }
        for (int i=0;i< secciones.size();i++){
            List<SeccionArticulo> aux_articulos= new ArrayList<>();
            for (int j = 0; j < articulos.size() ; j++) {
                if(secciones.get(i).equals(articulos.get(j).getSection())){
                    aux_articulos.add(new SeccionArticulo(
                            articulos.get(j).getPublicationId(),
                            articulos.get(j).getTitle(),
                            articulos.get(j).getDoi(),
                            articulos.get(j).getAbstract(),
                            articulos.get(j).getDatePublished(),
                            articulos.get(j).getSubmissionId(),
                            articulos.get(j).getSectionId(),
                            articulos.get(j).getSeq(),
                            articulos.get(j).getGaleys(),
                            articulos.get(j).getKeywords(),
                            articulos.get(j).getAuthors()
                    ));
                }
            }
            seccion2Revistas.add(new Seccion2(secciones.get(i), aux_articulos));
        }
        return seccion2Revistas;
    }
}
