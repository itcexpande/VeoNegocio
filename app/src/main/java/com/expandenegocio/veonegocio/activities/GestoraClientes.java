package com.expandenegocio.veonegocio.activities;

import java.util.ArrayList;

/**
 * Created by jesus on 28/03/2017.
 */

public class GestoraClientes extends ArrayList<Cliente> {
/*
    public static final GestoraClientes INSTANCE = new GestoraClientes();

    public static GestoraClientes getINSTANCE() {
        return INSTANCE;
    }
*/
    public GestoraClientes() {
        Cliente nuevoCliente = new Cliente("Juan", "Carlos De Borbón y Grecia", "666666666", "sudireccionmail@gmail.com", "Campechano I");
        this.agregarCliente(nuevoCliente);

        nuevoCliente = new Cliente("Maria", "López Lopez", "983359746", "marialopezlopez@gmail.com", "nada");
        this.agregarCliente(nuevoCliente);

        nuevoCliente = new Cliente("Maria", "Lópz López", "983359747", "marialopezlopez@hotmail.com", "nada");
        this.agregarCliente(nuevoCliente);

        nuevoCliente = new Cliente("Blanca", "De La Rosa", "606358845", "blanca@hotmail.com", "nada");
        this.agregarCliente(nuevoCliente);

        nuevoCliente = new Cliente("Jesus", "Aguado ", "666358845", "jesus@hotmail.com", "nada");
        this.agregarCliente(nuevoCliente);

        nuevoCliente = new Cliente("Azucena", "De La Rosa", "606358845", "azucena@hotmail.com", "nada");
        this.agregarCliente(nuevoCliente);

        nuevoCliente = new Cliente("Rafael", "Nadal Menorca", "654123456", "rnm@hotmail.com", "nada");
        this.agregarCliente(nuevoCliente);

        nuevoCliente = new Cliente("Pablo", "Iglesias I", "608354123", "", "number one");
        this.agregarCliente(nuevoCliente);

    }


    private void agregarCliente(Cliente nuevoCliente) {
        if (!existeCliente(nuevoCliente)) {
            this.add(nuevoCliente);
        }
    }

    private boolean existeCliente(Cliente nuevoCliente) {
        if (this.contains(nuevoCliente)) {
            return true;
        }
        return false;
    }


}


