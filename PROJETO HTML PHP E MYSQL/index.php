<?php
    include_once("Controller/conexao.php");
?>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Controle de Finanças PHP e MYSQL</title>
    <link rel="stylesheet" href="style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col">
                <h2>Contas a pagar</h2>
            </div>
            <div class="row">
                <div class="col-4">
                    <img src="mysql-php-logos.png" class="img-php">
                </div>
                <div class="col-8">
                <form action="Controller/salvar.php" method="POST"> 
    <div class="mt-3 form-floating">
        <input type="number" class="form-control" id="codigo" name="codigo" readonly> 
        <label for="codigo" class="form-label">Código</label>
    </div>
    <div class="mt-3 form-floating">
        <input type="text" class="form-control" id="cliente" name="cliente">
        <label for="cliente" class="form-label">Cliente</label>
    </div>
    <div class="mt-3 form-floating">
        <input type="date" class="form-control" id="data" name="data">
        <label for="data" class="form-label">Data</label>
    </div>
    <div class="mt-3 form-floating">
        <input type="text" class="form-control" id="valor" name="valor"> 
        <label for="valor" class="form-label">Valor</label>
    </div>
    <input type="hidden" id="id_empresa" name="id_empresa" value="1">
    <div class="mt-3">
        <button type="submit" class="btn btn-primary">Salvar</button> 
    </div>
</form><br>
<div>
    <h5>Atenção.</h5>
    Toda conta será cadastrada como não paga, deve o usuario confirmar se foi paga ou não.
</div>

                </div>
            </div>
        </div>
    </div>
    <div class="container mt-3">
        <div class="row">
            <div class="col">
                <h2>Contas cadastradas</h2>
            </div>
        </div>
        <div class="row">
            <table class="table table-light table-hover">
                <thead>
                    <tr>
                        <th>Código</th>
                        <th>Nome do Cliente</th>
                        <th>Data do Pagamento</th>
                        <th>Valor</th>
                        <th>Status</th>
                        <th>Editar</th>
                        <th>Excluir</th>
                        <th>Marcar como Paga</th>
                    </tr>
                </thead>
                <tbody>
                    <?php
                    $sql = "SELECT * FROM tbl_conta_pagar";
                    $result = mysqli_query($conn, $sql);
                    if ($result && mysqli_num_rows($result) > 0) {
                        while ($linha = mysqli_fetch_assoc($result)) {
                            echo "<tr>";
                            echo "<td>{$linha['id_conta_pagar']}</td>";
                            echo "<td>{$linha['cliente']}</td>"; 
                            echo "<td>{$linha['data_pagar']}</td>";
                            echo "<td>{$linha['valor']}</td>";
                            echo "<td>{$linha['pago']}</td>";
                            echo "<td><a href='Controller/editar.php?id={$linha['id_conta_pagar']}'>Editar</a></td>";
                            echo "<td><a href='Controller/excluir.php?id={$linha['id_conta_pagar']}'>Excluir</a></td>";
                            echo "<td><a href='../controller/marcar_pago.php?id={$linha['id_conta_pagar']}'>Marcar como Paga</a></td>";
                            echo "</tr>";
                        }
                    } else {
                        echo "<tr><td colspan='8'>Nenhum registro encontrado.</td></tr>";
                    }
                    ?>
                </tbody>
            </table>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
</body>
</html>
