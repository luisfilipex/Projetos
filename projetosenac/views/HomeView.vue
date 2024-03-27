<script>

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
          <div class="faca1"><p>Faça seu Orçamento ou se Cadastre para as ofertas<span><a href="About"> Clique aqui!</a> </span></p>
          </div>
          <div class="faca3"><h2><span>Seja Bem vindo a Protect</span><br></h2>
              <p> Seu mais novo portal de segurança aonde você vai encontrar vários tipos de serviços na área de segurança privada, você poderá fazer orçamentos e ver os nossos parceiros que prestam serviços e suas avaliações deixada por clientes, faça orçamento, agendamento e mantenha-se seguro.
              </p>
          </div>
      </div>
      </header>
      <div class="meio">
  
          <div class="orcam">
              <img class="imgtec" src="src\assets\imgmeio.png" alt="Whatsapp" width="840" height="240">
          </div>
          <div class="cont">
              <img class="imgcentro" src="src\assets\img01.jpg" alt="Whatsapp" width="140" height="140"><br>
              Vigilância privada: proteção dinâmica e eficiente para sua segurança<br>
              A vigilância privada é uma solução eficiente para a proteção através de uma abordagem dinâmica, ágil e adaptável às necessidades específicas de cada pessoa.<br>
              <button class="btn">Detalhes</button>
          </div>
          <div class="cont">
              <img class="imgcentro" src="src\assets\segtrab.jpg" alt="Whatsapp" width="140" height="140"><br>
              Segurança do trabalho: Segurança do trabalho de sua empresa e muito importante faça sua consultoria ou orcamento<br>
              Nos contamos com uma grande equipe de segurança do trabalho muito capacitada para suprir suas necessidades<br>
              <button class="btn">Detalhes</button>
          </div>
          <div class="cont">
              <img class="imgcentro" src="src\assets\segweb.jpg" alt="Whatsapp" width="140" height="140"><br>
              Cyber security: proteção de sistemas de computador contra roubo ou danos ao hardware, software ou dados eletrônicos, controle de acesso a banco de dados, rotas<br>
              Para proteção de seus dados contamos com uma equipe especializada.<br>
              <button class="btn">Detalhes</button>
          </div>
          <div class="cont">
              <img class="imgcentro" src="src\assets\monito.jpg" alt="Whatsapp" width="140" height="140"><br>
              Monitoramento 24horas:<br>
              Oferecemos o serviço de monitoramento 24/7 para que você não se preocupe com sua segurança aonde oferecemos total controle e de imagens de video e instalação de sistema de cameras e alarmes de acordo com sua necessidade.<br>
              <button class="btn">Detalhes</button>
          </div>
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
    h2 span{
        color:rgb(236, 221, 6) ;
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
</style>
