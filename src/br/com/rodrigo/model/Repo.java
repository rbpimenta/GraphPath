package br.com.rodrigo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Repo {
    private String name;
    private List<Repo> outputs;
    private List<Repo> inputs;

    public Repo(String name) {
        this.name = name;
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Repo> getOutputs() {
        return outputs;
    }

    public void setOutputs(List<Repo> outputs) {
        this.outputs = outputs;
    }

    public List<Repo> getInputs() {
        return inputs;
    }

    public void setInputs(List<Repo> inputs) {
        this.inputs = inputs;
    }

    @Override public String toString() {
        return name;
    }

    @Override public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Repo repo = (Repo) o;
        return name.equals(repo.name);
    }

    @Override public int hashCode() {
        return Objects.hash(name);
    }
}
