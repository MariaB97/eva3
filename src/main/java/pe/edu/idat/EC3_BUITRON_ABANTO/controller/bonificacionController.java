package pe.edu.idat.EC3_BUITRON_ABANTO.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.EC3_BUITRON_ABANTO.model.bonificacionModel;

@Controller
public class bonificacionController {
    @GetMapping("/bonificacion")
    public String formularioBonificacion(Model model){
        model.addAttribute("bonificacionModel", new bonificacionModel());
        model.addAttribute("visualizarboni", false);
        return "bonificacion";
    }
    @PostMapping("/calcularbonif")
    public String calcuarBonif(@ModelAttribute("bonificacionModel") bonificacionModel calcbonif, Model model){
        Integer diasPago = calcbonif.getDiasPago(); int descuento = 0;
        Double credito = calcbonif.getcredito();
        String diagnostico = "Usted tiene descuento: ", estiloDiagnostico= "alert-primary";

        if (diasPago < 7){
            descuento = 10;
            credito = credito * (1 - descuento/100);
            diagnostico += descuento + "%";
        } else if (diasPago>=7 & diasPago < 15){
            descuento = 5;
            credito = credito * (1 - descuento/100);
            diagnostico += descuento + "%";
        }else {
            diagnostico = "Usted no tiene descuento.";
            estiloDiagnostico = "alert-danger";
        }
        model.addAttribute("Resultado","Situación: " + diagnostico + " Su credito final es = S/" + String.format("%.2f",credito));
        model.addAttribute("visualizarboni", true);
        model.addAttribute("estilodiagnostico", estiloDiagnostico);
        return "bonificacion";
    }
}
