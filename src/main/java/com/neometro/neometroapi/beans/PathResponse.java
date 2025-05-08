package com.neometro.neometroapi.beans;

import java.util.List;
import lombok.ToString;

@ToString
public class PathResponse {
    private List<String> path;

    public PathResponse(List<String> path) {
        this.path = path;
    }

    public List<String> getPath() {
        return path;
    }

    public void setPath(List<String> path) {
        this.path = path;
    }
}
