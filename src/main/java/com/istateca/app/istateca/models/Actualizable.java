package com.istateca.app.istateca.models;

public interface Actualizable<T> {
    void actualizarDatos(T nuevaEntidad);
}
