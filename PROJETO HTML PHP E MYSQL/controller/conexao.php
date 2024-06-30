<?php
//arquivo de configuração do db
include_once("config.php");

// conexao do banco de dados
$conn = new mysqli($servidor, $usuario, $senha, $banco);

// verifica caso nao conecta apresenta o erro
if ($conn->connect_error) {
    die("Falha ao conectar ao banco de dados: " . $conn->connect_error);
}
?>
