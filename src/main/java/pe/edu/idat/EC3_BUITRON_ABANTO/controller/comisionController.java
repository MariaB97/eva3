package pe.edu.idat.EC3_BUITRON_ABANTO.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.EC3_BUITRON_ABANTO.model.comisionModel;

@Controller
public class comisionController {


    @GetMapping("/comision")
    public String formulariocomision(Model model){
        model.addAttribute("comisionmodel", new comisionModel());
        model.addAttribute("visualizar alerta",false);
        return "comision";
    }
    @PostMapping("/calcularcomision")
    public  String calcularcomision(@ModelAttribute("comisionmodel") comisionModel comision, Model model) {



        Double ventas= comision.getVentas();
        Double comisiontotal = 0.0;
        String estiloComision = "alert-primary";


        if(ventas <1000){
            comisiontotal = ventas * 0.03;
        } else if (ventas <=5000) {
            comisiontotal = ventas * 0.05;

        } else if (ventas <=10000) {
            comisiontotal = ventas * 0.07;

        } else if (ventas >10000) {
            comisiontotal = ventas * 0.10;

        }




        model.addAttribute("resultado",
                "su comision es " + String.format("%.2f", comisiontotal));

        model.addAttribute("visualizaralerta",true);
        model.addAttribute("estilocomision",estiloComision);
        return "comision";

    }






    }
