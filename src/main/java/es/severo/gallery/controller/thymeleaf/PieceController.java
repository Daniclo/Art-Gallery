package es.severo.gallery.controller.thymeleaf;

import es.severo.gallery.entity.Piece;
import es.severo.gallery.service.PieceService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view")
public class PieceController {
    private final PieceService pieceService;

    public PieceController(PieceService pieceService){
         this.pieceService = pieceService;
     }
     @GetMapping
     public String homePage(){
         return "index";
     }

     @GetMapping("/pieces")
    public String getPieces(Model model){
        model.addAttribute("pieces",pieceService.findAll());
        return "pieces";
    }

    @GetMapping("/new")
    public String newPiece(Model model){
        model.addAttribute("p",new Piece());
        return "add";
    }

    @PostMapping("/save")
    public String savePiece(@ModelAttribute("p")Piece piece){
        pieceService.save(piece);
        return "redirect:/view/pieces";
    }

    @GetMapping("/update/{id}")
    public String updatePiece(@PathVariable("id") Long id, Model model){
        Piece piece = pieceService.findById(id);
        model.addAttribute("piece",piece);
        return "update";
    }

    @GetMapping("/delete/{id}")
    public String deletePiece(@PathVariable("id") Long id){
        pieceService.deleteById(id);
        return "redirect:/view/pieces";
    }
}