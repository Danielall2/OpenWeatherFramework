package com.spartaglobal.eng76.framework.exceptions;

import com.spartaglobal.eng76.framework.connectionmanager.ConnectionManager;

public class FailedHttpConnectionException extends Exception{

    private final ConnectionManager connectionManager;

    public FailedHttpConnectionException(ConnectionManager connectionManager) {
        super();
        this.connectionManager = connectionManager;
    }

    @Override
    public String getMessage() {
        return connectionManager.getHttpResponse().body();
    }

    public int getStatusCode(){
        return connectionManager.getStatusCode();
    }
}
