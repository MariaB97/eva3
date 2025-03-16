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
        Integer diasPago = calcbonif.getDiasPago(); Double descuento = 0.0;
        Double credito = calcbonif.getcredito(), deuda = 0.0;
        String diagnostico = "Usted tiene descuento: ", estiloDiagnostico= "alert-primary";

        if (diasPago < 7){
            descuento = 10.0;
            diagnostico += String.format("%.0f",descuento) + "%";
        } else if (diasPago < 15){
            descuento = 5.0;
            diagnostico += String.format("%.0f",descuento) + "%";
        }else {
            diagnostico = "Usted no tiene descuento.";
            estiloDiagnostico = "alert-danger";
        }
        deuda = credito * (1 - descuento/100);

        model.addAttribute("Resultado","Situación: " + diagnostico + " Su deuda final es = S/" + String.format("%.2f",deuda));
        model.addAttribute("visualizarboni", true);
        model.addAttribute("estilodiagnostico", estiloDiagnostico);
        return "bonificacion";
    }
}
