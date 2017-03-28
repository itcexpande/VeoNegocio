package com.expandenegocio.veonegocio.activities;


import java.util.HashMap;

/**
 * Created by Jesus on 27/03/2017.
 */
public class Cliente extends HashMap<String, Object> implements Comparable<Cliente> {
    private String nombre;
    private String apellidos;
    private String telefono;
    private String mail;
    private String otros;


    public Cliente(String nombre, String apellidos, String telefono, String mail, String otros) {
        this.put(ClavesClientes.NOMBRE.toString(), nombre);
        this.put(ClavesClientes.APELLIDOS.toString(), apellidos);
        this.put(ClavesClientes.TELEFONO.toString(), telefono);
        this.put(ClavesClientes.MAIL.toString(), mail);
        this.put(ClavesClientes.OTROS.toString(), otros);
    }

    public String getNombre() {
        return (String) this.get(ClavesClientes.NOMBRE.toString());
    }

    public void setNombre(String nombre) {
        this.put(ClavesClientes.NOMBRE.toString(), nombre);
    }

    public String getApellidos() {
        return (String) this.get(ClavesClientes.APELLIDOS.toString());
    }

    public void setApellidos(String apellidos) {
        this.put(ClavesClientes.APELLIDOS.toString(), apellidos);
    }

    public String getTelefono() {
        return (String) this.get(ClavesClientes.TELEFONO.toString());
    }

    public void setTelefono(String telefono) {
        this.put(ClavesClientes.TELEFONO.toString(), telefono);
    }

    public String getMail() {
        return (String) this.get(ClavesClientes.MAIL.toString());
    }

    public void setMail(String mail) {
        this.put(ClavesClientes.MAIL.toString(), mail);
    }

    public String getOtros() {
        return (String) this.get(ClavesClientes.OTROS.toString());
    }

    public void setOtros(String otros) {
        this.put(ClavesClientes.OTROS.toString(), otros);
    }

    @Override
    public int compareTo(Cliente o) {
        String nombre1 = (String) this.get(ClavesClientes.NOMBRE.toString()).toString();
        String nombre2 = (String) o.get(ClavesClientes.NOMBRE.toString()).toString();

        String apellidos1 = (String) this.get(ClavesClientes.APELLIDOS.toString()).toString();
        String apellidos2 = (String) o.get(ClavesClientes.APELLIDOS.toString()).toString();

        String telefono1 = (String) this.get(ClavesClientes.TELEFONO.toString()).toString();
        String telefono2 = (String) o.get(ClavesClientes.TELEFONO.toString()).toString();

        int[] criterios = {
                nombre1.compareTo(nombre2),
                apellidos1.compareTo(apellidos2),
                telefono1.compareTo(telefono2)
        };

        for (int uno : criterios) {
            if (uno != 0) {
                return uno;
            }
        }

        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Cliente) {
            Cliente elCliente = (Cliente) o;
            if (this.compareTo(elCliente) == 0) {
                return true;
            } else {
                return false;
            }
        }
        return super.equals(o);
    }
}