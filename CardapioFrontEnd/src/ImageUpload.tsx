import React, { useState } from 'react';
import axios from 'axios';

const ImageUpload = () => {
  const [imageBase64, setImageBase64] = useState('');

  // Função para converter a imagem em Base64
  const convertToBase64 = (file: File): Promise<string> => {
    return new Promise((resolve, reject) => {
      const reader = new FileReader();
      reader.readAsDataURL(file);
      reader.onload = () => resolve(reader.result as string);
      reader.onerror = (error) => reject(error);
    });
  };

  // Manipulador de mudanças no arquivo
  const handleFileChange = async (event: React.ChangeEvent<HTMLInputElement>) => {
    if (event.target.files) {
      const file = event.target.files[0];
      const base64 = await convertToBase64(file);
      setImageBase64(base64); // Armazena a imagem em Base64 no estado
      console.log(base64); // Aqui você pode ver o Base64 no console
    }
  };

  // Manipulador de envio do formulário
  const handleFormSubmit = async (event: React.FormEvent) => {
    event.preventDefault();
    // Enviar a imagem Base64 para o servidor
    try {
      await axios.post('http://localhost:8080/upload', { image: imageBase64 });
      console.log("Imagem enviada com sucesso!");
    } catch (error) {
      console.error("Erro ao enviar a imagem:", error);
    }
  };

  return (
    <form onSubmit={handleFormSubmit}>
      <input type="file" accept="image/*" onChange={handleFileChange} />
      <button type="submit">Enviar Imagem</button>
    </form>
  );
};

export default ImageUpload;
