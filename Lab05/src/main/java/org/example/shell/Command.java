package org.example.shell;

public abstract class Command {
    protected String[] args;

    public Command(String[] args) {
        this.args = args;
    }

    public void setArgs(String[] args) {
        this.args = args;
    }

    public abstract void execute();
}