import React from 'react'
import { BrowserRouter, Route, Routes, Navigate } from 'react-router-dom'
import Layout from '../components/Layout/Layout'
import DecisionListPage from '../pages/DecisionListPage'
import DecisionDetailPage from '../pages/DecisionDetailPage'
import MyPage from '../pages/MyPage'
import Login from '../pages/Login'
import { ROUTES } from './routePaths'
import { useDecisions } from '../context/DecisionContext'

// 로그인 필요한 라우트 보호
const ProtectedRoute = ({ children }) => {
  const { currentUser } = useDecisions();
  
  if (!currentUser) {
    return <Navigate to={ROUTES.LOGIN} replace />;
  }
  
  return children;
}

const AppRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={ROUTES.LOGIN} element={<Login />} />
        
        <Route path={ROUTES.HOME} element={<Layout />}>
          <Route index element={
            <ProtectedRoute>
              <DecisionListPage />
            </ProtectedRoute>
          } />
          <Route path="decisions/:id" element={
            <ProtectedRoute>
              <DecisionDetailPage />
            </ProtectedRoute>
          } />
          <Route path="decisions" element={
            <ProtectedRoute>
              <DecisionListPage />
            </ProtectedRoute>
          } />
          <Route path="mypage" element={
            <ProtectedRoute>
              <MyPage />
            </ProtectedRoute>
          } />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default AppRoutes