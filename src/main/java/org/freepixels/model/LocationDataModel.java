package org.freepixels.model;

public class LocationDataModel {
    private String name;
    private String dimension;
    private String holder;
    private String holderuuid;
    private String time;
    private int posx;
    private int posy;
    private int posz;

    // 构造器
    public LocationDataModel(String name, String dimension, String holder, String holderuuid, String time, int posx, int posy, int posz) {
        this.name = name;
        this.dimension = dimension;
        this.holder = holder;
        this.holderuuid = holderuuid;
        this.time = time;
        this.posx = posx;
        this.posy = posy;
        this.posz = posz;
    }

    // Getter和Setter方法
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getHolderuuid() {
        return holderuuid;
    }

    public void setHolderuuid(String holderuuid) {
        this.holderuuid = holderuuid;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPosx() {
        return posx;
    }

    public void setPosx(int posx) {
        this.posx = posx;
    }

    public int getPosy() {
        return posy;
    }

    public void setPosy(int posy) {
        this.posy = posy;
    }

    public int getPosz() {
        return posz;
    }

    public void setPosz(int posz) {
        this.posz = posz;
    }
}

