package com.projeto.sistema.controle;

import com.projeto.sistema.modelos.Fornecedor;  // Altere para Fornecedor
import com.projeto.sistema.repositorios.CidadeRepositorio;
import com.projeto.sistema.repositorios.FornecedorRepositorio;  // Altere para FornecedorRepositorio
import com.projeto.sistema.repositorios.EstadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class FornecedorControle {

    @Autowired
    private FornecedorRepositorio fornecedorRepositorio;

    @Autowired
    private EstadoRepositorio estadoRepositorio;

    @Autowired
    private CidadeRepositorio cidadeRepositorio;

    @GetMapping("/cadastroFornecedor")
    public ModelAndView cadastrar(Fornecedor fornecedor) {
        ModelAndView mv = new ModelAndView("administrativo/fornecedores/cadastro");
        mv.addObject("fornecedor", fornecedor);
        mv.addObject("listaEstados", estadoRepositorio.findAll());
        mv.addObject("listaCidades", cidadeRepositorio.findAll());
        return mv;
    }

    @GetMapping("/editarFornecedor/{id}")
    public String editarFornecedor(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Fornecedor> fornecedor = fornecedorRepositorio.findById(id);
        if (fornecedor.isPresent()) {
            model.addAttribute("fornecedor", fornecedor.get());
            model.addAttribute("listaEstados", estadoRepositorio.findAll());
            model.addAttribute("listaCidades", cidadeRepositorio.findAll());
            return "administrativo/fornecedores/cadastro";
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Fornecedor não encontrado!");
            return "redirect:/listarFornecedor";
        }
    }

    @GetMapping("/listarFornecedor")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/fornecedores/lista");
        mv.addObject("listarFornecedor", fornecedorRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerFornecedor/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Fornecedor> fornecedor = fornecedorRepositorio.findById(id);
        if (fornecedor.isPresent()) {
            fornecedorRepositorio.delete(fornecedor.get());
            redirectAttributes.addFlashAttribute("mensagem", "Fornecedor removido com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Fornecedor não encontrado!");
        }
        return listar();
    }

    @PostMapping("/salvarFornecedor")
    public ModelAndView salvar(@ModelAttribute Fornecedor fornecedor, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return cadastrar(fornecedor);
        }
        fornecedorRepositorio.save(fornecedor);
        redirectAttributes.addFlashAttribute("mensagem", "Fornecedor salvo com sucesso!");
        return new ModelAndView("redirect:/listarFornecedor");
    }
}
