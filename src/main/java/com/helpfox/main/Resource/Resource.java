package com.helpfox.main.Resource;

import java.util.List;

public class Resource {

    //add paginate, it needs to response the json
    public List<?> data;
    public Object links;
    public Object meta;

    public Resource(List<?> data, Object links, Object meta) {
        this.data = data;
        this.links = links;
        this.meta = meta;
    }

    public List<?> getData() {
        return data;
    }

    public Object getLinks() {
        return links;
    }

    public Object getMeta() {
        return meta;
    }
}
