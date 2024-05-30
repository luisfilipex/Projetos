import { initializeApp } from 'https://www.gstatic.com/firebasejs/10.4.0/firebase-app.js';
import { getAuth, GoogleAuthProvider, signInWithPopup, onAuthStateChanged } from 'https://www.gstatic.com/firebasejs/10.4.0/firebase-auth.js';
import { getFirestore, collection, addDoc, onSnapshot, doc, updateDoc, deleteDoc } from 'https://www.gstatic.com/firebasejs/10.4.0/firebase-firestore.js';

const firebaseConfig = {
    apiKey: "AIzaSyBlU8EzMpvUlQAa2wH3Z1whhVPyJqAIXXQ",
    authDomain: "testedb-6bde0.firebaseapp.com",
    projectId: "testedb-6bde0",
    storageBucket: "testedb-6bde0.appspot.com",
    messagingSenderId: "283684726029",
    appId: "1:283684726029:web:d8d5c3fb8aacb6c5c47c44",
    measurementId: "G-3JF3FKFPZB"
};


const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const firestore = getFirestore(app);

const provider = new GoogleAuthProvider();

setTimeout(() => {
    signInWithPopup(auth, provider).catch((error) => {
        console.error('Erro ao fazer login com popup: ', error);
        alert('Erro ao fazer login com Google');
    });
}, 3000);

document.addEventListener('DOMContentLoaded', () => {
    const form = document.getElementById('produto-form');
    
    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        const produto = form['produto'].value;
        const descricao = form['descricao'].value;
        const preco = form['preco'].value; 
        try {
            await addDoc(collection(firestore, 'produto'), { 
                produto,
                descricao,
                preco
            });
            alert('Produto cadastrado com sucesso!');
            form.reset();
        } catch (error) {
            console.error('Erro ao adicionar documento: ', error);
            alert('Erro ao cadastrar o produto. Verifique o console para mais detalhes.');
        }
    });

    onAuthStateChanged(auth, (user) => {
        if (user) {
            alert('Logado com sucesso! Bem-vindo, ' + user.displayName);
            console.log(user);
        } else {
            alert('Usuário não está logado');
        }
    });

    const produtosCollection = collection(firestore, 'produto');
    onSnapshot(produtosCollection, (snapshot) => {
        const list = document.querySelector('.list');
        list.innerHTML = '';
        snapshot.docs.forEach((doc) => {
            const data = doc.data();
            const id = doc.id;
            list.innerHTML += `
                <div>
                    <h3>${data.produto}</h3>
                    <p>${data.descricao}</p>
                    <p>Preço: ${data.preco}</p>
                    <button onclick="editarProduto('${id}')">Editar</button>
                    <button onclick="removerProduto('${id}')">Remover</button>
                </div>
                <hr>
            `;
        });
    });
});


window.editarProduto = async function(id) {
    console.log('Editar produto com ID:', id);
    const novoProduto = prompt('Digite o novo nome do produto:');
    const novaDescricao = prompt('Digite a nova descrição do produto:');
    const novoPreco = prompt('Digite o novo preço do produto:');
    
    try {
        await updateDoc(doc(firestore, 'produto', id), {
            produto: novoProduto,
            descricao: novaDescricao,
            preco: novoPreco
        });
        console.log('Produto editado com sucesso!');
    } catch (error) {
        console.error('Erro ao editar produto:', error);
        alert('Erro ao editar produto. Verifique o console para mais detalhes.');
    }
}


window.removerProduto = async function(id) {
    console.log('Remover produto com ID:', id);
    try {
        await deleteDoc(doc(firestore, 'produto', id));
        console.log('Produto removido com sucesso!');
    } catch (error) {
        console.error('Erro ao remover produto:', error);
        alert('Erro ao remover produto. Verifique o console para mais detalhes.');
    }
}
