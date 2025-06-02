package com.test.api.marvel_challenge.service;

import java.util.Map;

public interface IHttpClientService {
    <T> T doGet(
            String endpoint,
            Map<String,String> queryParams,
            Class<T> responseType);

    <T,R> T doPost(
            String endpoint,
            Map<String,String> queryParams,
            Class<T> responseType,
            R bodyRequest);

    <T,R> T doPut(
            String endpoint,
            Map<String,String> queryParams,
            Class<T> responseType,
            R bodyRequest);

    <T> T doDelete(
            String endpoint,
            Map<String,String> queryParams,
            Class<T> responseType);
}
