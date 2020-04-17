package com.kadir.inventory.enumeration;

public enum Status {
    OK(200),
    NOT_ACCEPTABLE(406),
    BAD_REQUEST(400);
	

    private final int code;

    Status(final int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String toString() {
        return String.valueOf(code);
    }

    public static Status getStatus(int code) throws IllegalAccessError {
        if (code == 200)
            return Status.OK;
        else if (code == 406)
            return Status.NOT_ACCEPTABLE;
        else if (code == 400)
            return Status.BAD_REQUEST;
        else
            throw new IllegalAccessError("Status not found in Status Enum");
    }
}
