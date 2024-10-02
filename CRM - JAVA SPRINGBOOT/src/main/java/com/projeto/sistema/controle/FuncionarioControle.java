package com.projeto.sistema.controle;

import com.projeto.sistema.modelos.Funcionario;
import com.projeto.sistema.repositorios.FuncionarioRepositorio;
import com.projeto.sistema.repositorios.EstadoRepositorio;
import com.projeto.sistema.repositorios.CidadeRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class FuncionarioControle {

    @Autowired
    private FuncionarioRepositorio funcionarioRepositorio;

    @Autowired
    private EstadoRepositorio estadoRepositorio;

    @Autowired
    private CidadeRepositorio cidadeRepositorio;

    @GetMapping("/cadastroFuncionario")
    public ModelAndView cadastrar(Funcionario funcionario) {
        ModelAndView mv = new ModelAndView("administrativo/funcionarios/cadastro");
        mv.addObject("funcionario", funcionario);
        mv.addObject("listaEstados", estadoRepositorio.findAll());
        mv.addObject("listaCidades", cidadeRepositorio.findAll());
        return mv;
    }

    @GetMapping("/editarFuncionario/{id}")
    public String editarFuncionario(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Funcionario> funcionario = funcionarioRepositorio.findById(id);
        if (funcionario.isPresent()) {
            model.addAttribute("funcionario", funcionario.get());
            model.addAttribute("listaEstados", estadoRepositorio.findAll());
            model.addAttribute("listaCidades", cidadeRepositorio.findAll());
            return "administrativo/funcionarios/cadastro";
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Funcionário não encontrado!");
            return "redirect:/listarFuncionario";
        }
    }

    @GetMapping("/listarFuncionario")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/funcionarios/lista");
        mv.addObject("listarFuncionario", funcionarioRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerFuncionario/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Funcionario> funcionario = funcionarioRepositorio.findById(id);
        if (funcionario.isPresent()) {
            funcionarioRepositorio.delete(funcionario.get());
            redirectAttributes.addFlashAttribute("mensagem", "Funcionário removido com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Funcionário não encontrado!");
        }
        return listar();
    }

    @PostMapping("/salvarFuncionario")
    public ModelAndView salvar(@ModelAttribute Funcionario funcionario, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return cadastrar(funcionario);
        }
        funcionarioRepositorio.save(funcionario);  // Salva o funcionário
        redirectAttributes.addFlashAttribute("mensagem", "Funcionário salvo com sucesso!");
        return new ModelAndView("redirect:/listarFuncionario");
    }
}
