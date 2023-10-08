package com.helpfox.main.Repository;

import com.google.gson.Gson;
import com.helpfox.main.Model.Vehicle.Vehicle;
import com.helpfox.main.Resource.Resource;
import com.helpfox.main.Resource.VehicleResource;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.util.List;

import static com.helpfox.main.Utils.Http.apiUrl;

public class VehicleRepository implements RepositoryResource {
    private String baseUri = "vehicles/";

    public VehicleRepository() throws UnirestException {
    }

    @Override
    public void insert() throws UnirestException {

    }

    @Override
    public void update() throws UnirestException {

    }

    @Override
    public void delete() throws UnirestException {

    }

    @Override
    public Object find() throws UnirestException {
        return null;
    }

    @Override
    public List<Vehicle> all() throws UnirestException {
        HttpResponse<JsonNode> apiResponse = Unirest.get(apiUrl + baseUri).asJson();
        String responseJsonAsString = apiResponse.getBody().toString();

        Resource response = new Gson().fromJson(responseJsonAsString, Resource.class);
        return null;
    }
}
