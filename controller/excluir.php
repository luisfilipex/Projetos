<?php
include_once("../Controller/conexao.php"); 

if ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET['id'])) {
    $id_conta_pagar = $_GET['id'];

    //excluir o registro
    $sql = "DELETE FROM tbl_conta_pagar WHERE id_conta_pagar = $id_conta_pagar";

    if ($conn->query($sql) === TRUE) {
        echo "<script>
                alert('Registro excluído com sucesso!');
                window.location.href = '../index.php';
              </script>";
    } else {
        echo "<script>
                alert('Erro ao excluir registro: " . $conn->error . "');
                window.location.href = '../index.php';
              </script>";
    }
} else {
    echo "Método não suportado ou ID não fornecido.";
}

$conn->close();
?>
