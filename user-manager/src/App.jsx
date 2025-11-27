import { useState } from 'react'
import { BrowserRouter, Routes, Route, Link } from 'react-router-dom'
import NotFound from './pages/NotFound'
import UserList from './pages/UserList'
import UserDetail from './pages/UserDetail'
import UserRegister from './pages/UserRegister'
import './App.css'

function App() {
  const [users, setUsers] = useState([]);

  const addUser = (newUser) => {
    setUsers([...users, newUser]);
  };

  return (
    <BrowserRouter>
      <nav>
        <Link to="/">유저 목록 페이지</Link>
        <Link to="/user">유저 등록 페이지</Link>
      </nav>
      <Routes>
        <Route path='/' element={<UserList users={users} />}/>
        <Route path='/user/:id' element={<UserDetail users={users} />}/>
        <Route path='/user' element={<UserRegister users={users} addUser={addUser} />}/>
        <Route path='*' element={<NotFound />}/>
      </Routes>
    </BrowserRouter>
  )
}

export default App