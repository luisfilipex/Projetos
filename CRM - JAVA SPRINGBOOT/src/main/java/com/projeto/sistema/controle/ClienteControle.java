package com.projeto.sistema.controle;

import com.projeto.sistema.modelos.Cliente;  // Altere para Cliente
import com.projeto.sistema.repositorios.CidadeRepositorio;
import com.projeto.sistema.repositorios.EstadoRepositorio;
import com.projeto.sistema.repositorios.ClienteRepositorio;  // Altere para ClienteRepositorio
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
public class ClienteControle {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private EstadoRepositorio estadoRepositorio;

    @Autowired
    private CidadeRepositorio cidadeRepositorio;

    @GetMapping("/cadastroCliente")
    public ModelAndView cadastrar(Cliente cliente) {
        ModelAndView mv = new ModelAndView("administrativo/clientes/cadastro");
        mv.addObject("cliente", cliente);
        mv.addObject("listaEstados", estadoRepositorio.findAll());
        mv.addObject("listaCidades", cidadeRepositorio.findAll());
        return mv;
    }

    @GetMapping("/editarCliente/{id}")
    public String editarCliente(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Cliente> cliente = clienteRepositorio.findById(id);
        if (cliente.isPresent()) {
            model.addAttribute("cliente", cliente.get());
            model.addAttribute("listaEstados", estadoRepositorio.findAll());
            model.addAttribute("listaCidades", cidadeRepositorio.findAll());
            return "administrativo/clientes/cadastro";
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Cliente não encontrado!");
            return "redirect:/listarCliente";
        }
    }

    @GetMapping("/listarCliente")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/clientes/lista");
        mv.addObject("listarCliente", clienteRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerCliente/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Cliente> cliente = clienteRepositorio.findById(id);
        if (cliente.isPresent()) {
            clienteRepositorio.delete(cliente.get());
            redirectAttributes.addFlashAttribute("mensagem", "Cliente removido com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Cliente não encontrado!");
        }
        return listar();
    }

    @PostMapping("/salvarCliente")
    public ModelAndView salvar(@ModelAttribute Cliente cliente, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return cadastrar(cliente);
        }
        clienteRepositorio.save(cliente);
        redirectAttributes.addFlashAttribute("mensagem", "Cliente salvo com sucesso!");
        return new ModelAndView("redirect:/listarCliente");
    }
}
