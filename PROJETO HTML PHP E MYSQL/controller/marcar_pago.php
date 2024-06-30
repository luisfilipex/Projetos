<?php
include_once("../Controller/conexao.php"); 

if ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET['id'])) {
    $id_conta_pagar = $_GET['id'];

    // atualizar o status para 'pago'
    $sql = "UPDATE tbl_conta_pagar SET pago = 1 WHERE id_conta_pagar = $id_conta_pagar";

    if ($conn->query($sql) === TRUE) {
        echo "<script>
                alert('Conta marcada como paga!');
                window.location.href = '../index.php';
              </script>";
    } else {
        echo "<script>
                alert('Erro ao marcar conta como paga: " . $conn->error . "');
                window.location.href = '../index.php';
              </script>";
    }
} else {
    echo "Método não suportado ou ID não fornecido.";
}

$conn->close();
?>
