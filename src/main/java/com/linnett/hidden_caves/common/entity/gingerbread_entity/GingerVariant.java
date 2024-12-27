package com.linnett.hidden_caves.common.entity.gingerbread_entity;

import java.util.Arrays;
import java.util.Comparator;

public enum GingerVariant {
    NORMAL(0),
    HAPPY(1),
    ;

    private static final GingerVariant[ ] BY_ID = Arrays.stream(values()).sorted(
            Comparator.comparingInt( GingerVariant::getId)).toArray(GingerVariant[]::new);

    private final int id;
    GingerVariant(int id) {
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public static GingerVariant byId(int id){
        return BY_ID[id % BY_ID.length];
    }
}
