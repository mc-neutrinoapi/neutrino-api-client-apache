/*
 * The MIT License
 *
 * Copyright 2022 Neutrino API.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.neutrinoapi.client;

import com.google.gson.JsonObject;
import java.nio.file.Path;
import java.util.Optional;

/**
 * API response object for success or failure
 */
public class APIResponse {

    public static final int NO_STATUS = 0;
    public static final String NO_CONTENT_TYPE = "";
    
    private static final int NO_ERROR_CODE = 0;
    private static final String NO_ERROR_MSG = "";

    private final JsonObject data;
    private final Path file;

    private final String contentType;
    private final int httpStatusCode;
    private final int errorCode;
    private final String errorMessage;
    private final Throwable errorCause;

    public APIResponse(JsonObject data, Path file, String contentType, int httpStatusCode, int errorCode, String errorMessage, Throwable errorCause) {
        this.data = data;
        this.file = file;
        this.contentType = contentType;
        this.httpStatusCode = httpStatusCode;
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorCause = errorCause;
    }

    /**
     * Response body
     */
    public Optional<JsonObject> getData() {
        return Optional.ofNullable(data);
    }

    /**
     * Local file path storing response body
     */
    public Optional<Path> getFile() {
        return Optional.ofNullable(file);
    }

    /**
     * Response body's media type
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Response's HTTP status code
     */
    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * Error code for request/response error
     */
    public int getErrorCode() {
        return errorCode;
    }

    /**
     * Error message for request/response error
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Cause of error e.g exception instance or stack trace
     */
    public Optional<Throwable> getErrorCause() {
        return Optional.ofNullable(errorCause);
    }

    /**
     * Create API response for response data
     */
    public static APIResponse of(int statusCode, String contentType, JsonObject data) {
        return new APIResponse(data, null, contentType, statusCode, NO_ERROR_CODE, NO_ERROR_MSG, null);
    }

    /**
     * Create API response for response file
     */
    public static APIResponse of(int statusCode, String contentType, Path outputFilePath) {
        return new APIResponse(null, outputFilePath, contentType, statusCode, NO_ERROR_CODE, NO_ERROR_MSG, null);
    }

    /**
     * Create API response for error code
     */
    public static APIResponse of(int statusCode, String contentType, int errorCode) {
        String errorMessage = APIErrorCode.getErrorMessage(errorCode);
        return new APIResponse(null, null, contentType, statusCode, errorCode, errorMessage, null);
    }

    /**
     * Create API response for HTTP status code
     */
    public static APIResponse of(int statusCode, String contentType, int errorCode, String errorMessage) {
        return new APIResponse(null, null, contentType, statusCode, errorCode, errorMessage, null);
    }

    /**
     * Create API response for error cause
     */
    public static APIResponse of(int errorCode, Throwable errorCause) {
        String errorMessage = APIErrorCode.getErrorMessage(errorCode);
        return new APIResponse(null, null, NO_CONTENT_TYPE, NO_STATUS, errorCode, errorMessage, errorCause);
    }
}
