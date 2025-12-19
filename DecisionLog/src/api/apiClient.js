const API_BASE = '/api';

export const apiClient = {
  // GET 요청
  get: async (url) => {
    const response = await fetch(`${API_BASE}${url}`);
    if (!response.ok) {
      const error = await response.json();
      throw new Error(error.message || '요청 실패');
    }
    return response.json();
  },

  // POST 요청
  post: async (url, data) => {
    const response = await fetch(`${API_BASE}${url}`, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(data),
    });
    
    if (!response.ok) {
      const error = await response.json();
      throw new Error(error.message || '요청 실패');
    }
    return response.json();
  },

  // DELETE 요청
  delete: async (url) => {
    const response = await fetch(`${API_BASE}${url}`, {
      method: 'DELETE',
    });
    
    if (!response.ok) {
      const error = await response.json();
      throw new Error(error.message || '요청 실패');
    }
    return response.json();
  },
};