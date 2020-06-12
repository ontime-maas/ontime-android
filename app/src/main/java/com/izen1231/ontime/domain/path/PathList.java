package com.izen1231.ontime.domain.path;

import java.util.List;

public class PathList {
    int pathType;
    int firstPub;
    List<SubPathList> subPath;
    Info info;

    public int getPathType() {
        return pathType;
    }

    public int getFirstPub() {
        return firstPub;
    }

    public List<SubPathList> getSubPath() {
        return subPath;
    }

    public Info getInfo() {
        return info;
    }

    @Override
    public String toString() {
        return "PathList{" +
                "pathType=" + pathType +
                ", firstPub=" + firstPub +
                ", subPath=" + subPath +
                ", info=" + info +
                '}';
    }
}
