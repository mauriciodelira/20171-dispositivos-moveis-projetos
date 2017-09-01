package com.mdelira.monsterdex.models;

/**
 * Created by jaeni on 31/08/2017.
 */

public class PokemonEntry {
    private String url;
    private String nome;
    private int numero;

    public PokemonEntry(String url, String nome, int numero) {
        this.url = url;
        this.nome = nome;
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "PokemonEntry{" +
                "url='" + url + '\'' +
                ", nome='" + nome + '\'' +
                ", number=" + numero +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getNumero() {
        String[] urlPartes = url.split("/");
        return Integer.parseInt(urlPartes[urlPartes.length - 1]);
    }

    public void setNumero(int number) {
        this.numero = number;
    }
}
