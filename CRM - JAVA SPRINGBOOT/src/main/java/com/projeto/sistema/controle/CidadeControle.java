package com.projeto.sistema.controle;

import com.projeto.sistema.modelos.Cidade;
import com.projeto.sistema.repositorios.CidadeRepositorio;
import com.projeto.sistema.repositorios.EstadoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class CidadeControle {

    @Autowired
    private CidadeRepositorio cidadeRepositorio;
    @Autowired
    private EstadoRepositorio estadoRepositorio;

    @GetMapping("/cadastroCidade")
    public ModelAndView cadastrar(Cidade cidade) {
        ModelAndView mv = new ModelAndView("administrativo/cidades/cadastro");
        mv.addObject("cidade", cidade);
        mv.addObject("listaCidade", cidadeRepositorio.findAll());
        mv.addObject("listaEstado", estadoRepositorio.findAll());
        return mv;
    }


    @GetMapping("/editarCidade/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = cidadeRepositorio.findById(id);
        if (cidade.isPresent()) {
            return cadastrar(cidade.get());
        } else {
            return listar();
        }
    }

    @GetMapping("/listarCidade")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/cidades/lista");
        mv.addObject("listarCidade", cidadeRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerCidade/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Cidade> cidade = cidadeRepositorio.findById(id);
        if (cidade.isPresent()) {
            cidadeRepositorio.delete(cidade.get());
            redirectAttributes.addFlashAttribute("mensagem", "Cidade removida com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Cidade não encontrada!");
        }
        return listar();
    }

    @PostMapping("/salvarCidade")
    public ModelAndView salvar(Cidade cidade, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return cadastrar(cidade);
        }
        cidadeRepositorio.save(cidade);
        redirectAttributes.addFlashAttribute("mensagem", "Cadastro realizado com sucesso!");
        return new ModelAndView("redirect:/listarCidade");
    }
}
