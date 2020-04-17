package com.kadir.inventory.model;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.kadir.inventory.enumeration.Status;

public class Response implements Serializable {

    private int status;
    private Error error;
    private String tokenId;
    private Object response;

    public int getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status.getCode();
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }

    public Response setSuccessResponse(Status status, String tokenId, Map responseObj) {
        Response response = new Response();
        response.setStatus(status);
        response.setError(new Error());
        response.setTokenId(tokenId);
        response.setResponse(responseObj);
        return response;
    }

    public static Response setSuccessResponse(Status status, String tokenId, List responseObj) {
        Response response = new Response();
        response.setStatus(status);
        response.setError(new Error());
        response.setTokenId(tokenId);
        response.setResponse(responseObj);
        return response;
    }
}