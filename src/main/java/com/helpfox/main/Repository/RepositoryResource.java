package com.helpfox.main.Repository;

import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

public interface RepositoryResource {
    public void insert() throws UnirestException;
    public void update() throws UnirestException;
    public void delete() throws UnirestException;

    public Object find() throws UnirestException;
    public List<?> all() throws UnirestException;
}
