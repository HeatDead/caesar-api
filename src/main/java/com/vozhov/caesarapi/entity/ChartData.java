package com.vozhov.caesarapi.entity;

import lombok.Data;

import java.util.List;

@Data
public class ChartData {
    List<String> labels;
    List<Dataset> datasets;
}
