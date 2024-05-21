package com.vozhov.caesarapi.entity;

import lombok.Data;

import java.util.List;

@Data
public class Dataset {
    String label;
    String backgroundColor;
    List<Integer> data;
}
