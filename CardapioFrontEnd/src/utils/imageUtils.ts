import axios from 'axios';

// Função para upload da imagem
export const uploadImage = async (file: File): Promise<string> => {
  const formData = new FormData();
  formData.append('file', file);

  try {
    const response = await axios.post<{ url: string }>(
      'http://localhost:8080/upload', // Substitua pela URL real de upload
      formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      }
    );

    return response.data.url; // Ajuste conforme o formato da resposta da sua API
  } catch (error) {
    console.error("Erro ao fazer upload da imagem:", error);
    throw error; // Repassa o erro para que possa ser tratado em outro lugar
  }
};
