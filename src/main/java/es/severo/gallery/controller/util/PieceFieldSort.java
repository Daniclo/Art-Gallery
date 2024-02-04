package es.severo.gallery.controller.util;

public enum PieceFieldSort {
    ID("id"),
    NAME("name"),
    PRICE("price");

    private String field;
    PieceFieldSort(String field) {
        this.field = field;
    }
    public String getField(){
        return field;
    }
}
