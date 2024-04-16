package com.example.example4sem6_rikky_and_morty_rest.domain;

import lombok.Data;

import java.util.List;

@Data
public class Characters {
     Info info;
     List<Result> results;
}
