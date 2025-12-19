import React, { useEffect } from 'react'
import { HeaderContainer, Logo, Nav, NavLink, NavLinks } from './Layout.styled'
import { ROUTES } from '../../routes/routePaths'
import { useLocation, useNavigate } from 'react-router-dom'
import { useDecisions } from '../../context/DecisionContext'
import styled from 'styled-components'

const LogoutButton = styled.button`
    padding: 8px 16px;
    background: #ff4444;
    color: white;
    border: none;
    border-radius: 4px;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.2s;

    &:hover {
        background: #cc0000;
        scale: 0.98;
    }
`

const UserInfo = styled.span`
    color: #666;
    font-size: 14px;
    margin-right: 12px;
`

const Header = () => {
  const location = useLocation();
  const navigate = useNavigate();
  const { currentUser, logout } = useDecisions();

  useEffect(() => {
    console.log(location.pathname);
  })
  
  const isActive = (path) => {
    return location.pathname === path ? 'active' : '';
  }

  const handleLogout = () => {
    logout();
    navigate(ROUTES.LOGIN);
  }
  
  return (
    <HeaderContainer>
      <Nav>
        <Logo to={ROUTES.HOME}>Decision Log</Logo>
        <NavLinks>
          {currentUser ? (
            <>
              <NavLink to={ROUTES.HOME} className={isActive(ROUTES.HOME)}>홈</NavLink>
              <NavLink to={ROUTES.DECISION} className={isActive(ROUTES.DECISION)}>의사결정</NavLink>
              <NavLink to={ROUTES.MYPAGE} className={isActive(ROUTES.MYPAGE)}>마이페이지</NavLink>
              <UserInfo>{currentUser.name}님</UserInfo>
              <LogoutButton onClick={handleLogout}>로그아웃</LogoutButton>
            </>
          ) : (
            <NavLink to={ROUTES.LOGIN}>로그인</NavLink>
          )}
        </NavLinks>
      </Nav>
    </HeaderContainer>
  )
}

export default Header