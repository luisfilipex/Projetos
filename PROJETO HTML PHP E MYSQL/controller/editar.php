<?php
include_once("../Controller/conexao.php"); 

if ($_SERVER["REQUEST_METHOD"] == "POST") {
    // Pega os dados do formulário de edição
    $id_conta_pagar = $_POST['id_conta_pagar'];
    $cliente = $_POST['cliente'];
    $data = $_POST['data'];
    $valor = $_POST['valor'];

    //atualizar os dados
    $sql = "UPDATE tbl_conta_pagar 
            SET cliente = '$cliente', data_pagar = '$data', valor = '$valor'
            WHERE id_conta_pagar = $id_conta_pagar";

    if ($conn->query($sql) === TRUE) {
        echo "<script>
                alert('Registro atualizado com sucesso!');
                window.location.href = '../index.php';
              </script>";
    } else {
        echo "<script>
                alert('Erro ao atualizar registro: " . $conn->error . "');
                window.location.href = '../index.php';
              </script>";
    }
} elseif ($_SERVER["REQUEST_METHOD"] == "GET" && isset($_GET['id'])) {
    // Carrega os dados do registro para edição
    $id_conta_pagar = $_GET['id'];

    $sql = "SELECT * FROM tbl_conta_pagar WHERE id_conta_pagar = $id_conta_pagar";
    $result = $conn->query($sql);

    if ($result->num_rows > 0) {
        $row = $result->fetch_assoc();
        $cliente = $row['cliente'];
        $data = $row['data_pagar'];
        $valor = $row['valor'];

        // Formulário de edição
        ?>
        <!DOCTYPE html>
        <html lang='pt-br'>
        <head>
            <meta charset='UTF-8'>
            <meta name='viewport' content='width=device-width, initial-scale=1.0'>
            <title>Editar Conta a Pagar</title>
            <link rel='stylesheet' href='../style.css'>
            <link href='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH' crossorigin='anonymous'>
        </head>
        <body>
            <div class='container'>
                <div class='row'>
                    <div class='col'>
                        <h2>Editar Conta a Pagar</h2>
                    </div>
                </div>
                <div class='row'>
                    <div class='col-8'>
                        <form action='editar.php' method='POST'> 
                            <input type='hidden' name='id_conta_pagar' value='<?php echo $id_conta_pagar; ?>'>
                            <div class='mt-3 form-floating'>
                                <input type='text' class='form-control' id='cliente' name='cliente' value='<?php echo $cliente; ?>'> 
                                <label for='cliente' class='form-label'>Cliente</label>
                            </div>
                            <div class='mt-3 form-floating'>
                                <input type='date' class='form-control' id='data' name='data' value='<?php echo $data; ?>'>
                                <label for='data' class='form-label'>Data</label>
                            </div>
                            <div class='mt-3 form-floating'>
                                <input type='text' class='form-control' id='valor' name='valor' value='<?php echo $valor; ?>'> 
                                <label for='valor' class='form-label'>Valor</label>
                            </div>
                            <div class='mt-3'>
                                <button type='submit' class='btn btn-primary'>Atualizar</button> 
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <script src='https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js' integrity='sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz' crossorigin='anonymous'></script>
        </body>
        </html>
        <?php
    } else {
        echo "<script>
                alert('Registro não encontrado.');
                window.location.href = '../index.php';
              </script>";
    }
} else {
    echo "Método não suportado ou ID não fornecido.";
}

$conn->close();
?>
