<script>
import { ref } from 'vue';

    let nome = ref('');
    let email = ref('');
    let senha = ref('');

    function cadastrar() {
      localStorage.setItem('nome', nome._value);
      localStorage.setItem('email', email._value);
      localStorage.setItem('confirma', senha._value);
    }
</script>
<template>
    <header class="topo">
        <div class="ilogo1">
          <div class="imglogo">
        <img class="imglogo" src="src\assets\logo.jpg" alt="Whatsapp" width="100" height="100">
    </div>
    </div>
        <nav class="menu">
            <li><router-link to="/home">Inicio</router-link></li>
            <li>Quem Somos</li>
            <li>Parceiros</li>
            <li>Serviços</li>
            <li><router-link to="/login">Área de Clientes</router-link></li>
            <li>Contato</li>
        </nav>
        <div class="linha"></div>
        <div class="faca">
          <div class="form">
                <h3>Cadastro de usuários</h3>
      
                <div>
                <input type="text"  placeholder="Nome" v-model="nome"/>
                </div><br>
                <div >
                <input type="text"  placeholder="Usuário" v-model="emails"/>
                </div><br>
                <div>
                <input type="password"  placeholder="Senha" v-model="password"/>
                </div><br>
                <input class="botao1" type="submit" id="salvar" value="Cadastrar" />
                </div>
        </div>
    </header>
    <div class="meio">
        </div>
    <article class="fotos">
    </article>
    <footer class="rodape">SEGURANÇA PRIVADA LTDA<br>
        <div class="contato">
            <img src="src\assets\wpp.png" alt="Whatsapp" width="35" height="35">
            <img src="src\assets\insta.png" alt="Instagram" width="35" height="35">
            <img src="src\assets\email.png" alt="E-mail" width="35" height="35">
            <img src="src\assets\tel.png" alt="Telefone" width="35" height="35">
        </div>
    </footer>
    </template>

<style>
 body {
       margin: auto;
        color: #fff;
        font-family: 'Times New Roman', Times, serif;
        font-size: 16px;
    }
    .topo {
        background-color: rgb(9, 4, 83);
        width: 100%;
        height: 120px;
        text-align: center;
        display: inline-block;
    }
    .linha{
        margin-top: 89px;
        background-color: rgb(236, 221, 6);
        width: 100%;
        height: 20px;
        text-align: center;
    }
    .menu {
        width: 100%;
        height: 50px;
        margin-top: 50px;
        display: inline;
        text-align: center;
        margin-right: 570px;
        border-radius: 5px;
    }
    .menu li{
        display: inline; 
        padding: 14px 16px;
        text-decoration: none;
        cursor: pointer;
        margin-top: 20px;
    }
    .meio {
        background-color: #fff;
        width: 100%;
        height: 880px;
        margin-top: 5px;
        text-align: center;
        display: flex; 
        justify-content: center;
        float: left;
    }
    .orcam{
        color: #000;
        width: 50px;
        height: 80px;
        border-radius: 5px;
       text-align: center;
    }
    .cont{
        background-color: #fff;
        float: left;
        width: 200px;
        height: 350px;
        border-radius: 5px;
        box-shadow: 1px 1px 1px 1px #0e0d0d;
        margin: 5px;
        margin-top: 520px;
        color:rgb(9, 4, 83);
    
    }
    .btn{
        color:#fff;
        cursor: pointer;
        border: none;
        border-radius: 5px;
        background-color: rgb(9, 4, 83);
        width: 100px;
        height: 30px;
    }
    .fotos {
        display: flex;
        margin: auto;
        text-align: center;
        align-items: center;
    }
    .divfoto {
        background-color: rgb(109, 108, 108);
        width: 100px;
        height: 200px;
        margin-left: 10px;
        box-shadow: 2px 2px 2px;
        text-align: center;
        margin-top: 5px;
    }
    .rodape{
        background-color: rgb(9, 4, 83);
        width: 100%;
        height: 180px;
        text-align: center;
        float: left;
    }
    .contato{
        width: 20px;
        height: 20px;
        text-align: center;
        display: inline;
        margin-top: 20px;
        cursor: pointer;
    }
    .imgcentro{
        border-radius: 5px;
        margin-top: 8px;
        box-shadow: 1px 1px 1px 1px #0e0d0d;
    }
    .imgtec{
        margin-left: 50px;
        margin-top: 270px;
        border-radius: 5px;

    }
    .faca{
        width: 100%;
        float:left;
    }
    .faca1{
        background-color: rgb(9, 4, 83);
        width: 840px;
        height: 50px;
        margin: 0 auto;
        border-radius: 5px;
        box-shadow: 2px 2px 2px 2px #d4d3d3;
        margin-right: 510px;
    }
    .faca2{
        width: 100%;
        float:left;
    }
    .faca3{
        background-color: rgb(9, 4, 83);
        width: 840px;
        height: 170px;
        margin: 0 auto;
        border-radius: 5px;
        box-shadow: 2px 2px 2px 2px #d4d3d3;
        margin-right: 510px;
    }
    p{
       padding: 2%;
       font-size: 16;
       text-indent: 50px;
       text-align: justify;
       letter-spacing: 1px;
    }
    p span{
        color: rgb(236, 221, 6);
        cursor: pointer;
    }
    .imglogo{
    float: left;
    margin: 0 auto;
    margin-left: 270px;
        
    }
    .ilogo1{
        width: 100%;
    }
    @media (max-width: 1024px){
    }
    .body1 {
    display: flex;
    justify-content: center;
    align-items: center;
    min-height: 100vh;
    background-color: #f0f0f0;
    margin: 0;
    flex-wrap: wrap;
  }

  .gallery-container {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    gap: 10px;
    width: 100%;
    max-width: 800px;
    margin: 0 auto;
  }

  .gallery {
    flex-basis: calc(33.33% - 20px);
    border: 1px solid #ccc;
    padding: 10px;
    text-align: center;
    box-sizing: border-box;
  }

  .gallery:hover {
    border: 1px solid #777;
  }

  .gallery img {
    width: 60px;
    height: 60px;
  }

  .desc {
    margin-top: 10px;
    text-align: center;
    color:#000;
  }
  .form{
        background-color: rgb(9, 4, 83);
        width: 400px;
        height: 250px;
        border-color: #080808;
        align-items: center;
        margin: auto;
        border-radius: 3px;
        font-family: sans-serif;
        font-size: 14px;
        text-align: center;
        border: 2px 2px 2px 2px;
        box-shadow: 1px 1px 1px 1px rgb(236, 221, 6);
        padding: 2px;
        color: #fff;
        margin-top: 150px;
    }
    .botao1{
        box-shadow: 2px 2px 2px #080808;
        cursor:pointer;
        margin-left: 1px;
        margin-top: 3px;
        background-color: rgb(236, 221, 6);
        width: 120px;
        height: 25px;
        border-radius: 5px;
        border:none;
        text-align: center;
        color: #fff;
    }
</style>
