import { create } from 'zustand';
import { persist } from 'zustand/middleware';

export const useAuthStore = create(
  persist(
    (set, get) => ({
      user: null,
      accessToken: null,
      isAuthenticated: false,

      // 로그인
      login: (user, token) => {
        // 로컬스토리지에 토큰 저장
        localStorage.setItem('accessToken', token);
        
        set({ 
          user, 
          accessToken: token,
          isAuthenticated: true 
        });
      },

      // 로그아웃
      logout: () => {
        // 로컬스토리지에서 토큰 제거
        localStorage.removeItem('accessToken');
        
        set({ 
          user: null, 
          accessToken: null,
          isAuthenticated: false 
        });
      },

      // 토큰 업데이트
      setToken: (token) => {
        localStorage.setItem('accessToken', token);
        set({ accessToken: token });
      },

      // 인증 상태 확인
      checkAuth: () => {
        const token = localStorage.getItem('accessToken');
        const state = get();
        
        if (token && !state.isAuthenticated) {
          set({ 
            accessToken: token,
            isAuthenticated: true 
          });
          return true;
        }
        
        return state.isAuthenticated;
      },
    }),
    {
      name: 'auth-storage',
      // user와 isAuthenticated만 persist (토큰은 localStorage에서 직접 관리)
      partialize: (state) => ({ 
        user: state.user,
        isAuthenticated: state.isAuthenticated 
      }),
    }
  )
);