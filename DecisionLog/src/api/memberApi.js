import { apiClient } from './apiClient';

export const memberApi = {
  // 회원가입
  register: async (email, name) => {
    return apiClient.post('/members', { email, name });
  },

  // 로그인
  login: async (email) => {
    return apiClient.post('/members/login', { email });
  },

  // 회원 정보 조회
  getMember: async (memberId) => {
    return apiClient.get(`/members/${memberId}`);
  },

  // 회원 통계 조회
  getStats: async (memberId) => {
    return apiClient.get(`/members/${memberId}/stats`);
  },
};