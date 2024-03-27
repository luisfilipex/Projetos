<script setup lang="ts">
import WelcomeItem from './WelcomeItem.vue'
import DocumentationIcon from './icons/IconDocumentation.vue'
import ToolingIcon from './icons/IconTooling.vue'
import EcosystemIcon from './icons/IconEcosystem.vue'
import CommunityIcon from './icons/IconCommunity.vue'
import SupportIcon from './icons/IconSupport.vue'
</script>

<style>
 
</style>
<template>
   <header class="topo">
        <nav class="menu">
            <li>Inicio</li>
            <li>Quem Somos</li>
            <li>Parceiros</li>
            <li>Serviços</li>
            <li>Área de Clientes</li>
            <li>Contato</li>
        </nav>
        <div class="faca">
        <div class="faca1"><P>Faça seu Orçamento Clique aqui!></P>
        </div>
    </div>
    </header>
    <div class="meio">

        <div class="orcam">
            <img class="imgtec" src="img/imgmeio.png" alt="Whatsapp" width="840" height="240">
        </div>
        <div class="cont">
            <img class="imgcentro" src="img/img01.jpg" alt="Whatsapp" width="140" height="140"><br>
            Vigilância privada: proteção dinâmica e eficiente para sua segurança<br>
            A vigilância privada é uma solução eficiente para a proteção através de uma abordagem dinâmica, ágil e adaptável às necessidades específicas de cada pessoa.<br>
            <button class="btn">Detalhes</button>
        </div>
        <div class="cont">
            <img class="imgcentro" src="img/segtrab.jpg" alt="Whatsapp" width="140" height="140"><br>
            Segurança do trabalho: Segurança do trabalho de sua empresa e muito importante faça sua consultoria ou orcamento<br>
            Nos contamos com uma grande equipe de segurança do trabalho muito capacitada para suprir suas necessidades<br>
            <button class="btn">Detalhes</button>
        </div>
        <div class="cont">
            <img class="imgcentro" src="img/segweb.jpg" alt="Whatsapp" width="140" height="140"><br>
            Cyber security: proteção de sistemas de computador contra roubo ou danos ao hardware, software ou dados eletrônicos, controle de acesso a banco de dados, rotas<br>
            Para proteção de seus dados contamos com uma equipe especializada.<br>
            <button class="btn">Detalhes</button>
        </div>
        <div class="cont">
            <img class="imgcentro" src="img/monito.jpg" alt="Whatsapp" width="140" height="140"><br>
            Monitoramento 24horas:<br>
            Oferecemos o serviço de monitoramento 24/7 para que você não se preocupe com sua segurança aonde oferecemos total controle e de imagens de video e instalação de sistema de cameras e alarmes de acordo com sua necessidade.<br>
            <button class="btn">Detalhes</button>
        </div>
    </div>
    <article class="fotos">
    </article>
    <footer class="rodape">SEGURANÇA PRIVADA LTDA<br>
        <div class="contato">
            <img src="img/wpp.png" alt="Whatsapp" width="35" height="35">
            <img src="img/insta.png" alt="Instagram" width="35" height="35">
            <img src="img/email.png" alt="E-mail" width="35" height="35">
            <img src="img/tel.png" alt="Telefone" width="35" height="35">
        </div>
    </footer>
</template>

