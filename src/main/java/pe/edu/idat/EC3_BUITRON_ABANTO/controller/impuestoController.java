package pe.edu.idat.EC3_BUITRON_ABANTO.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.EC3_BUITRON_ABANTO.model.impuestoModel;

@Controller
public class impuestoController {    
    @GetMapping("/impuesto")
    public String formularioImpuesto(Model model){
    model.addAttribute("impuestoModel", new impuestoModel());
    model.addAttribute("visualizarimp", false);
    return "impuesto";
    }
    @PostMapping("/calcularimp")
    public String calcuarImp(@ModelAttribute("impuestoModel") impuestoModel calcimp, Model model){
        Double precio = calcimp.getPrecio(), total = 0.0;
        Double imp = 0.0;
        String categoria = calcimp.getCategoria();
        String estiloDiagnostico= "alert-primary";

        switch (categoria){
            case "Electronica":
                imp = 15.0;
                break;
            case "Alimentos":
                imp = 5.0;
                break;
            case "Ropa":
                imp = 8.0;
                break;
            case "Muebles":
                imp = 10.0;
                break;
        }
        total = precio * (1+imp/100);

        model.addAttribute("Resultado","El impuesto de este producto es: " + imp + "% Su precio total es = S/" + String.format("%.2f",total));
        model.addAttribute("visualizarimp", true);
        model.addAttribute("estilodiagnostico", estiloDiagnostico);
        return "impuesto";
    }
}
