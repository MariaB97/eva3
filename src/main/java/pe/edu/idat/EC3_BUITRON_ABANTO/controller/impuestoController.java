package pe.edu.idat.EC3_BUITRON_ABANTO.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pe.edu.idat.EC3_BUITRON_ABANTO.model.impuestoModel;

public class impuestoController {    
    @GetMapping("/impuesto")
    public String formularioImpuesto(Model model){
    model.addAttribute("impuestoModel", new impuestoModel());
    model.addAttribute("visualizarboni", false);
    return "bonificacion";
    }
    @PostMapping("/calcularimp")
    public String calcuarImp(@ModelAttribute("impuestoModel") impuestoModel impuesto, Model model){
        Double precio = impuesto.getPrecio(), total = 0.0;
        int imp = 0;
        String categoria = impuesto.getCategoria();
        String estiloDiagnostico= "alert-primary";

        switch (categoria){
            case "Electronica":
                imp = 15;
                total = precio * (1+imp/100);
                break;
            case "Alimentos":
                imp = 5;
                total = precio * (1+imp/100);
                break;
            case "Ropa":
                imp = 8;
                total = precio * (1+imp/100);
                break;
            case "Muebles":
                imp = 10;
                total = precio * (1+imp/100);
                break;
        }

        model.addAttribute("Resultado","El impuesto de este producto es: " + imp + "% Su precio total es = S/" + String.format("%.2f",total));
        model.addAttribute("visualizarboni", true);
        model.addAttribute("estilodiagnostico", estiloDiagnostico);
        return "bonificacion";
    }
}
