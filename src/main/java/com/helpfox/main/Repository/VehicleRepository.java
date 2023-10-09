package com.helpfox.main.Repository;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.helpfox.main.Model.Vehicle.Vehicle;
import com.helpfox.main.Resource.Resource;
import com.helpfox.main.Resource.VehicleResource;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static com.helpfox.main.Utils.Http.apiUrl;

public class VehicleRepository implements RepositoryResource {
    private String baseUri = "vehicles";

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
    public List<VehicleResource> all() throws UnirestException {
        HttpResponse<JsonNode> apiResponse = Unirest.get(apiUrl + baseUri).asJson();
        String responseJsonAsString = apiResponse.getBody().toString();
        System.out.println("Response as string: " + responseJsonAsString);
        Type listType = new TypeToken<ArrayList<VehicleResource>>(){}.getType();
        List<VehicleResource> response =  new Gson().fromJson(responseJsonAsString, listType);
        System.out.println("Response: " + response );
        return response;
    }
}
