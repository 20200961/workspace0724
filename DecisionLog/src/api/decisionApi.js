import { apiClient } from './apiClient';

export const decisionApi = {
  // 의사결정 생성
  createDecision: async (decisionData) => {
    return apiClient.post('/decisions', decisionData);
  },

  // 의사결정 목록 조회
  getDecisions: async (memberId) => {
    const url = memberId ? `/decisions?memberId=${memberId}` : '/decisions';
    return apiClient.get(url);
  },

  // 의사결정 상세 조회
  getDecision: async (decisionId) => {
    return apiClient.get(`/decisions/${decisionId}`);
  },

  // 의사결정 삭제
  deleteDecision: async (decisionId) => {
    return apiClient.delete(`/decisions/${decisionId}`);
  },

  // 회고 추가
  addRetrospective: async (decisionId, retrospectiveData) => {
    return apiClient.post(`/decisions/${decisionId}/retrospective`, retrospectiveData);
  },
};