import React from 'react'
import { Link } from 'react-router-dom'

const UserList = ({ users }) => {
  return (
    <div style={{ padding: '20px' }}>
      <h2>유저 목록</h2>
      <Link to="/user">
        <button>유저 등록</button>
      </Link>
      <div style={{ marginTop: '20px' }}>
        {users.length === 0 ? (
          <p>등록된 유저가 없습니다.</p>
        ) : (
          users.map(user => (
            <Link to={`/user/${user.id}`} key={user.id}>
              <div>
                <h3>{user.name}</h3>
                <p>나이: {user.age}세</p>
                <p>온라인여부 : {user.online}</p>
              </div>
            </Link>
          ))
        )}
      </div>
    </div>
  )
}

export default UserList