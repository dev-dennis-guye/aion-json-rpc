package org.aion.rpcgenerator;

public enum  ExitCodes {
    Normal(0), Error(-1);
    public final int code;
    ExitCodes(int code) {
        this.code = code;
    }
}
