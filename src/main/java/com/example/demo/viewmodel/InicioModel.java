package com.example.demo.viewmodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties({"marca", "modelo", "nroAsientos", "precio", "color"})
public record InicioModel(String codigo, String mensaje, String placa, String marca, String modelo, String nroAsientos, String precio, String color) {
}
