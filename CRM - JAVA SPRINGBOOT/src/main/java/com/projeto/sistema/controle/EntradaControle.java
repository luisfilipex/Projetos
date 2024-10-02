package com.projeto.sistema.controle;

import com.projeto.sistema.modelos.Entrada;
import com.projeto.sistema.modelos.Funcionario;
import com.projeto.sistema.modelos.ItemEntrada;
import com.projeto.sistema.modelos.Produto;
import com.projeto.sistema.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class EntradaControle {

    @Autowired
    private EntradaRepositorio entradaRepositorio;
    @Autowired
    private EstadoRepositorio estadoRepositorio;
    @Autowired
    private ProdutoRepositorio produtoRepositorio;
    @Autowired
    private FuncionarioRepositorio funcionarioRepositorio;
    @Autowired
    private FornecedorRepositorio fornecedorRepositorio;

    private List<ItemEntrada> listaItemEntrada = new ArrayList<>();

    // Método para carregar a página de cadastro de entradas
    @GetMapping("/cadastroEntrada")
    public ModelAndView cadastrar(Entrada entrada, ItemEntrada itemEntrada) {
        ModelAndView mv = new ModelAndView("administrativo/entradas/cadastro");


        if (this.listaItemEntrada == null || this.listaItemEntrada.isEmpty()) {
            this.listaItemEntrada = new ArrayList<>();
        }

        mv.addObject("entrada", entrada);
        mv.addObject("itemEntrada", itemEntrada);
        mv.addObject("listaItemEntrada", this.listaItemEntrada);
        mv.addObject("listarEntrada", entradaRepositorio.findAll());

        // Adicionando listas de funcionários, produtos e fornecedores à view
        mv.addObject("funcionarios", funcionarioRepositorio.findAll());
        mv.addObject("produtos", produtoRepositorio.findAll());
        mv.addObject("fornecedores", fornecedorRepositorio.findAll());

        return mv;
    }

    @GetMapping("/listarEntrada")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/entradas/lista");
        mv.addObject("listarEntrada", entradaRepositorio.findAll());
        return mv;
    }

    // Método para salvar nova entrada
    @PostMapping("/salvarEntrada")
    public ModelAndView salvar(Entrada entrada, RedirectAttributes redirectAttributes) {
        // Verifica se o produto está associado
        if (entrada.getProduto() == null || entrada.getProduto().getId() == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Produto deve ser selecionado.");
            return new ModelAndView("redirect:/cadastroEntrada");
        }

        // Obtenha o produto do repositório usando o ID
        Optional<Produto> produtoOpt = produtoRepositorio.findById(entrada.getProduto().getId());
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            entrada.setValorTotal(produto.getPrecoVenda() * entrada.getQuantidadeTotal());
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Produto não encontrado.");
            return new ModelAndView("redirect:/cadastroEntrada");
        }

        // Salva a entrada no repositório
        entradaRepositorio.save(entrada);
        this.listaItemEntrada.clear();
        redirectAttributes.addFlashAttribute("mensagem", "Entrada salva com sucesso!");
        return new ModelAndView("redirect:/listarEntrada");
    }

    // Método para editar a entrada
    @GetMapping("/editarEntrada/{id}")
    public String editarEntrada(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Entrada> entradaOpt = entradaRepositorio.findById(id);
        if (entradaOpt.isPresent()) {
            Entrada entrada = entradaOpt.get();
            model.addAttribute("entrada", entrada);
            model.addAttribute("listaItemEntrada", this.listaItemEntrada);
            model.addAttribute("funcionarios", funcionarioRepositorio.findAll());
            model.addAttribute("produtos", produtoRepositorio.findAll());
            model.addAttribute("fornecedores", fornecedorRepositorio.findAll());
            return "administrativo/entradas/cadastro";
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Entrada não encontrada!");
            return "redirect:/listarEntrada";
        }
    }

    // Método para atualizar a entrada
    @PostMapping("/atualizarEntrada")
    public ModelAndView atualizar(Entrada entrada, RedirectAttributes redirectAttributes) {
        // Verifica se o produto está associado
        if (entrada.getProduto() == null || entrada.getProduto().getId() == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Produto deve ser selecionado.");
            return new ModelAndView("redirect:/cadastroEntrada");
        }

        // Obtenha o produto do repositório usando o ID
        Optional<Produto> produtoOpt = produtoRepositorio.findById(entrada.getProduto().getId());
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            entrada.setValorTotal(produto.getPrecoVenda() * entrada.getQuantidadeTotal());
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Produto não encontrado.");
            return new ModelAndView("redirect:/cadastroEntrada");
        }

        // Atualiza a entrada no repositório
        entradaRepositorio.save(entrada);
        this.listaItemEntrada.clear();
        redirectAttributes.addFlashAttribute("mensagem", "Entrada atualizada com sucesso!");
        return new ModelAndView("redirect:/listarEntrada");
    }

    // Método para remover uma entrada pelo ID
    @GetMapping("/removerEntrada/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Entrada> entrada = entradaRepositorio.findById(id);
        if (entrada.isPresent()) {
            entradaRepositorio.delete(entrada.get());
            redirectAttributes.addFlashAttribute("mensagem", "Entrada removida com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Entrada não encontrada!");
        }
        return listar();
    }

    // Getter e Setter da lista de itens de entrada
    public List<ItemEntrada> getListaItemEntrada() {
        return listaItemEntrada;
    }

    public void setListaItemEntrada(List<ItemEntrada> listaItemEntrada) {
        this.listaItemEntrada = listaItemEntrada;
    }
}
