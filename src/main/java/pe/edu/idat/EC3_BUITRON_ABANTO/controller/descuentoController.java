package pe.edu.idat.EC3_BUITRON_ABANTO.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.EC3_BUITRON_ABANTO.model.comisionModel;
import pe.edu.idat.EC3_BUITRON_ABANTO.model.descuentoModel;

@Controller
public class descuentoController {


    @GetMapping("/descuento")
    public String formulariodescuento(Model model){
        model.addAttribute("descuentomodel", new descuentoModel());
        model.addAttribute("visualizar alerta",false);
        return "descuento";
    }
    @PostMapping("/calculardescuento")
    public  String calculardescuento(@ModelAttribute("descuentomodel") descuentoModel descuento, Model model) {


        Integer antiguedad = descuento.getAntiguedad();
        double ventas= descuento.getVenta();

        double descuentototal = 0 ;
        String estilodescuento = "alert-primary";


        if (antiguedad <= 1) {
            descuentototal = ventas * 2 / 100;

        } else if (antiguedad <= 3) {
            descuentototal = ventas * 5 / 100;;

        } else if (antiguedad <= 5) {
            descuentototal = ventas * 8 / 100;;

        } else if (antiguedad > 6) {
            descuentototal = ventas * 12 / 100;;

        }


        model.addAttribute("resultado",
                "SU DESCUENTO ES " + descuentototal  + " SU TOTAL A PAGAR  ES  " + (ventas - descuentototal));

        model.addAttribute("visualizaralerta", true);
        model.addAttribute("estilodescuento", estilodescuento);
        return "descuento";


    }
    }
