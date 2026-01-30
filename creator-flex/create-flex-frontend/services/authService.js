import api from './api';

export const authAPI = {
  // 로그인
  login: async (memberAccount, password) => {
    const response = await api.post('/auth/login', {
      memberAccount,
      password,
    });
    return response.data;
  },

  // 로그아웃
  logout: async () => {
    const response = await api.post('/auth/logout');
    return response.data;
  },

  // 내 프로필 조회
  getMyProfile: async () => {
    const response = await api.get('/members/me');
    return response.data;
  },
};