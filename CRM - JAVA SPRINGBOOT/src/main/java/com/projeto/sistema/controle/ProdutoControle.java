package com.projeto.sistema.controle;

import com.projeto.sistema.modelos.Produto;
import com.projeto.sistema.repositorios.ProdutoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
public class ProdutoControle {

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @GetMapping("/cadastroProduto")
    public ModelAndView cadastrar(Produto produto) {
        ModelAndView mv = new ModelAndView("administrativo/produtos/cadastro");
        mv.addObject("produto", produto);
        return mv;
    }

    @GetMapping("/editarProduto/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Produto> produto = produtoRepositorio.findById(id);
        if (produto.isPresent()) {
            return cadastrar(produto.get());
        } else {
            return listar();
        }
    }

    @GetMapping("/listarProduto")
    public ModelAndView listar() {
        ModelAndView mv = new ModelAndView("administrativo/produtos/lista");
        mv.addObject("listarProduto", produtoRepositorio.findAll());
        return mv;
    }

    @GetMapping("/removerProduto/{id}")
    public ModelAndView remover(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Optional<Produto> produto = produtoRepositorio.findById(id);
        if (produto.isPresent()) {
            produtoRepositorio.delete(produto.get());
            redirectAttributes.addFlashAttribute("mensagem", "Produto removido com sucesso!");
        } else {
            redirectAttributes.addFlashAttribute("mensagem", "Produto não encontrado!");
        }
        return listar();
    }

    @PostMapping("/salvarProduto")
    public ModelAndView salvar(@Valid @ModelAttribute("produto") Produto produto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            System.out.println("Erros de validação: " + result.getAllErrors());
            ModelAndView mv = new ModelAndView("administrativo/produtos/cadastro");
            mv.addObject("produto", produto);
            return mv;
        }

        produtoRepositorio.save(produto);
        redirectAttributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
        return new ModelAndView("redirect:/listarProduto");
    }



    @GetMapping("/precoProduto/{id}")
    @ResponseBody
    public Map<String, Object> getPrecoProduto(@PathVariable Long id) {
        Produto produto = produtoRepositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto não encontrado"));
        Map<String, Object> response = new HashMap<>();
        response.put("preco", produto.getPreco());
        return response;
    }
}
