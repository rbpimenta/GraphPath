package br.com.rodrigo.model;

public class Item {
    private Repo atual;
    private Repo proximo;
    private Item proximoItem;

    public Item(Repo atual, Repo proximo) {
        this.atual = atual;
        this.proximo = proximo;
    }

    public Repo getProximo() {
        return proximo;
    }

    public void setProximo(Repo proximo) {
        this.proximo = proximo;
    }

    public Repo getAtual() {
        return atual;
    }

    public void setAtual(Repo atual) {
        this.atual = atual;
    }

    @Override public String toString() {
        return "{ " +  atual + " -> " + proximo + " } | " + proximoItem;
    }

    public void setProximoItem(Item item) {
        this.proximoItem = item;
    }
}
