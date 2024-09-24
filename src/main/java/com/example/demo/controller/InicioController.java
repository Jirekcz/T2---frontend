package com.example.demo.controller;

import org.springframework.web.client.RestTemplate;
import com.example.demo.dto.InicioRequestDTO;
import com.example.demo.dto.InicioResponseDTO;
import com.example.demo.viewmodel.InicioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
public class InicioController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/inicio")
    public String inicio(Model model) {
        InicioModel inicioModel = new InicioModel("00", "", "");
        model.addAttribute("inicioModel", inicioModel);
        return "inicio";
    }

    @PostMapping("/buscar")
    public String buscar(@RequestParam("placa") String placa, Model model) {
        // Validamos los campos de entrada
        if (placa == null || placa.trim().length() == 0) {
            InicioModel inicioModel = new InicioModel("01", "ERROR: Completar los campos", "");
            model.addAttribute("inicioModel", inicioModel);
            return "inicio";
        }

        try {
            // Invocamos al API
            String endpoint = "";
            InicioRequestDTO inicioRequestDTO = new InicioRequestDTO(placa);
            InicioResponseDTO inicioResponseDTO = restTemplate.postForObject(endpoint, inicioRequestDTO, InicioResponseDTO.class);

            // Validar respuesta
            if (inicioResponseDTO.codigo().equals("00")) {
                InicioModel inicioModel = new InicioModel("00", "", inicioResponseDTO.marca());
                model.addAttribute("inicioModel", inicioModel);
                return "principal";
            } else {
                InicioModel inicioModel = new InicioModel("02", "ERROR: Busqueda fallida", "");
                model.addAttribute("inicioModel", inicioModel);
                return "inicio";
            }

        } catch (Exception e) {
            InicioModel inicioModel = new InicioModel("01", "", "");
            model.addAttribute("inicioModel", inicioModel);
            System.out.println(e.getMessage());
            return "inicio";
        }
    }
}
