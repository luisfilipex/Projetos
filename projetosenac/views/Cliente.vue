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
        <div class="body1"><div class="gallery-container">
            <div class="gallery">
              <a target="_blank" href="src\assets\0.png">
                <img src="src\assets\0.png" alt="SOLIC">
              </a>
              <div class="desc">SOLICITAR SERVI�OS</div>
            </div>
            <div class="gallery">
              <a target="_blank" href="src\assets\01.png">
                <img src="src\assets\01.png" alt="MONITORAMENTO">
              </a>
              <div class="desc">MONITORAMENTO 24 HORAS</div>
            </div>
            <div class="gallery">
              <a target="_blank" href="src\assets\02.png">
                <img src="src\assets\02.png" alt="ALTERAR DADOS">
              </a>
              <div class="desc">ALTERAR DADOS</div>
            </div>
            <div class="gallery">
              <a target="_blank" href="src\assets\03.png">
                <img src="src\assets\03.png" alt="ESTATISCAS">
              </a>
              <div class="desc">ESTATISTICAS</div>
            </div>
            <div class="gallery">
              <a target="_blank" href="src\assets\04.png">
                <img src="src\assets\04.png" alt="CONTROLE">
              </a>
              <div class="desc">CONTROLE DE DADOS</div>
            </div>
            <div class="gallery">
              <a target="_blank" href="src\assets\05.png">
                <img src="src\assets\05.png" alt="SEGDADOS">
              </a>
              <div class="desc">SEGURAN�A DE DADOS/REDE</div>
            </div>
            <div class="gallery">
              <a target="_blank" href="src\assets\06.png">
                <img src="src\assets\06.png" alt="SUPORTE">
              </a>
              <div class="desc">SUPORTE</div>
            </div>
            <div class="gallery">
              <a target="_blank" href="src\assets\07.png">
                <img src="src\assets\07.png" alt="SEGTRAB">
              </a>
              <div class="desc">SEGURAN�A DO TRABALHO</div>
            </div>
            <div class="gallery">
              <a target="_blank" href="src\assets\08.png">
                <img src="src\assets\08.png" alt="SEGPATRI">
              </a>
              <div class="desc">SEGURAN�A DE PATRIMONIAL</div>
            </div>
            <div class="gallery">
              <a target="_blank" href="src\assets\09.png">
                <img src="src\assets\09.png" alt="PAGAM">
              </a>
              <div class="desc">FINANCEIRO</div>
            </div>
            <div class="gallery">
              <a target="_blank" href="src\assets\10.png">
                <img src="src\assets\10.png" alt="SEGTRAB">
              </a>
              <div class="desc">AVALIAR E VER AVALIA��ES</div>
            </div>
            <div class="gallery">
              <a target="_blank" href="src\assets\11.png">
                <img src="src\assets\11.png" alt="FALE">
              </a>
              <div class="desc">FALE CONOSCO</div>
            </div>
            <div class="gallery">
              <a target="_blank" href="src\assets\12.png">
                <img src="src\assets\12.png" alt="FALE">
              </a>
              <div class="desc">FAZER ANOTA��ES</div>
            </div>
            <div class="gallery">
              <a target="_blank" href="src\assets\13.png">
                <img src="src\assets\13.png" alt="ADDUSER">
              </a>
              <div class="desc">ADICIONAR USUARIO</div>
            </div>
            <div class="gallery">
              <a target="_blank" href="src\assets\14.png">
                <img src="src\assets\14.png" alt="SEGARMADA">
              </a>
              <div class="desc">SEGURAN�A ARMADA</div>
            </div>
           <div class="gallery">
              <a target="_blank" href="src\assets\15.png">
                <img src="src\assets\15.png" alt="ESCOLT">
              </a>
              <div class="desc">ESCOLTA ARMADA</div>
            </div>
           <div class="gallery">
              <a target="_blank" href="src\assets\16.png">
                <img src="src\assets\16.png" alt="DESLOG">
              </a>
              <div class="desc">DESLOGAR</div>
            </div>
          </div>
        </div>
    </div>
    </header>
    <div class="meio"></div>
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
</style>
