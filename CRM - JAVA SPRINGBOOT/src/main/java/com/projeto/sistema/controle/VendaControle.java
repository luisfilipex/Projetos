package com.projeto.sistema.controle;

import com.projeto.sistema.modelos.Venda; // Alterado de Entrada para Venda
import com.projeto.sistema.modelos.ItemVenda; // Alterado de ItemEntrada para ItemVenda
import com.projeto.sistema.modelos.Produto;
import com.projeto.sistema.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class VendaControle {

    @Autowired
    private VendaRepositorio vendaRepositorio;
    @Autowired
    private EstadoRepositorio estadoRepositorio;
    @Autowired
    private ProdutoRepositorio produtoRepositorio;
    @Autowired
    private FuncionarioRepositorio funcionarioRepositorio;
    @Autowired
    private ClienteRepositorio clienteRepositorio;


    private List<ItemVenda> listaItemVenda = new ArrayList<>();

    // Método para carregar a página de cadastro de vendas
    @GetMapping("/cadastroVenda")
    public ModelAndView cadastrar(Venda venda, ItemVenda itemVenda) {
        ModelAndView mv = new ModelAndView("administrativo/vendas/cadastro");

        // Inicializa a lista de itens de venda se necessário
        if (this.listaItemVenda == null || this.listaItemVenda.isEmpty()) {
            this.listaItemVenda = new ArrayList<>();
        }

        mv.addObject("venda", venda);
        mv.addObject("itemVenda", itemVenda);
        mv.addObject("listaItemVenda", this.listaItemVenda);
        mv.addObject("listarVenda", vendaRepositorio.findAll());

        // Adicionando listas de funcionários, produtos e fornecedores à view
        mv.addObject("funcionarios", funcionarioRepositorio.findAll());
        mv.addObject("cliente", clienteRepositorio.findAll());
        mv.addObject("produtos", produtoRepositorio.findAll());


        return mv;
    }

    @GetMapping("/listarVenda")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/vendas/lista");
        mv.addObject("listarVenda", vendaRepositorio.findAll());
        return mv;
    }

    // Método para salvar nova venda
    @PostMapping("/salvarVenda")
    public ModelAndView salvar(Venda venda, RedirectAttributes redirectAttributes) {
        if (venda.getProduto() == null || venda.getProduto().getId() == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Produto deve ser selecionado.");
            return new ModelAndView("redirect:/cadastroVenda");
        }

        // Obtenha o produto do repositório usando o ID
        Optional<Produto> produtoOpt = produtoRepositorio.findById(venda.getProduto().getId());
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            venda.setValorTotal(produto.getPrecoVenda() * venda.getQuantidadeTotal());
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Produto não encontrado.");
            return new ModelAndView("redirect:/cadastroVenda");
        }

        // Salva a venda no repositório
        vendaRepositorio.save(venda);
        this.listaItemVenda.clear();
        redirectAttributes.addFlashAttribute("mensagem", "Venda salva com sucesso!");
        return new ModelAndView("redirect:/listarVenda");
    }

    // Método para editar a venda
    @GetMapping("/editarVenda/{id}")
    public String editarVenda(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Venda> vendaOpt = vendaRepositorio.findById(id);
        if (vendaOpt.isPresent()) {
            Venda venda = vendaOpt.get();
            model.addAttribute("venda", venda);
            model.addAttribute("listaItemVenda", this.listaItemVenda);
            model.addAttribute("funcionarios", funcionarioRepositorio.findAll());
            model.addAttribute("cliente", clienteRepositorio.findAll());
            model.addAttribute("produtos", produtoRepositorio.findAll());

            return "administrativo/vendas/cadastro";
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Venda não encontrada!");
            return "redirect:/listarVenda";
        }
    }

    // Método para atualizar a venda
    @PostMapping("/atualizarVenda")
    public ModelAndView atualizar(Venda venda, RedirectAttributes redirectAttributes) {
        // Verifica se o produto está associado
        if (venda.getProduto() == null || venda.getProduto().getId() == null) {
            redirectAttributes.addFlashAttribute("mensagem", "Produto deve ser selecionado.");
            return new ModelAndView("redirect:/cadastroVenda");
        }

        // Obtenha o produto do repositório usando o ID
        Optional<Produto> produtoOpt = produtoRepositorio.findById(venda.getProduto().getId());
        if (produtoOpt.isPresent()) {
            Produto produto = produtoOpt.get();
            venda.setValorTotal(produto.getPrecoVenda() * venda.getQuantidadeTotal());
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Produto não encontrado.");
            return new ModelAndView("redirect:/cadastroVenda");
        }

        // Atualiza a venda no repositório
        vendaRepositorio.save(venda);
        this.listaItemVenda.clear();
        redirectAttributes.addFlashAttribute("mensagem", "Venda atualizada com sucesso!");
        return new ModelAndView("redirect:/listarVenda");
    }

    // Método para remover uma venda pelo ID
    @GetMapping("/removerVenda/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Venda> venda = vendaRepositorio.findById(id);
        if (venda.isPresent()) {
            vendaRepositorio.delete(venda.get());
            redirectAttributes.addFlashAttribute("mensagem", "Venda removida com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Venda não encontrada!");
        }
        return listar();
    }

    // Getter e Setter da lista de itens de venda
    public List<ItemVenda> getListaItemVenda() {
        return listaItemVenda;
    }

    public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
        this.listaItemVenda = listaItemVenda;
    }
}