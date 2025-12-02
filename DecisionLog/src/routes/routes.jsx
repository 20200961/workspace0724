import React from 'react'
import { BrowserRouter, Route, Routes } from 'react-router-dom'
import Layout from '../components/Layout/Layout'
import DecisionListPage from '../pages/DecisionListPage'
import DecisionDetailPage from '../pages/DecisionDetailPage'
import MyPage from '../pages/MyPage'
import { ROUTES } from './routePaths'

const AppRoutes = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={ROUTES.HOME} element={<Layout />}>
          <Route index element={<DecisionListPage />} />
          <Route path="decisions/:id" element={<DecisionDetailPage />} />
          <Route path="decisions" element={<DecisionListPage />} />
          <Route path="mypage" element={<MyPage />} />
          <Route path="login" element={<LoGin />} />
        </Route>
      </Routes>
    </BrowserRouter>
  )
}

export default AppRoutes