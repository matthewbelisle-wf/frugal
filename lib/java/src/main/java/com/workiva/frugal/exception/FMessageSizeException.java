package com.workiva.frugal.exception;

import org.apache.thrift.transport.TTransportException;

/**
 * TTransportException which indicates a message was too large for a transport to handle.
 */
public class FMessageSizeException extends TTransportException {

    /**
     * TTransportException code which indicates the request was too large for the transport.
     */
    public static final int REQUEST_TOO_LARGE = 100;

    /**
     * TTransportException code which indicates the response was too large for the transport.
     */
    public static final int RESPONSE_TOO_LARGE = 101;

    private FMessageSizeException(int type, String message) {
        super(type, message);
    }

    /**
     * Creates a new FMessageSizeException for an oversized request.
     *
     * @param message exception message
     * @return FMessageSizeException
     */
    public static FMessageSizeException forRequest(String message) {
        return new FMessageSizeException(REQUEST_TOO_LARGE, message);
    }

    /**
     * Creates a new FMessageSizeException for an oversized response.
     *
     * @param message exception message
     * @return FMessageSizeException
     */
    public static FMessageSizeException forResponse(String message) {
        return new FMessageSizeException(RESPONSE_TOO_LARGE, message);
    }
}
