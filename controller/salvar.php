<?php
include_once("conexao.php");

// verifica o metodo post
if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // pega os dados digitado no formulario
    $codigo = $_POST['codigo'];
    $cliente = $_POST['cliente'];
    $data = $_POST['data'];
    $valor = $_POST['valor'];
    $id_empresa = $_POST['id_empresa'];

    // Insere os dados na tabela tbl_conta_pagar
    $sql = "INSERT INTO tbl_conta_pagar (valor, data_pagar, id_empresa, cliente) 
            VALUES ('$valor', '$data', '$id_empresa', '$cliente')";

    if ($conn->query($sql) === TRUE) {
        echo "<script>
                alert('Registro inserido com sucesso!');
                window.location.href = '../index.php';
              </script>";
    } else {
        echo "<script>
                alert('Erro ao inserir registro: " . $conn->error . "');
                window.location.href = '../index.php';
              </script>";
    }
} else {
    echo "Método não suportado.";
}

$conn->close();
?>
