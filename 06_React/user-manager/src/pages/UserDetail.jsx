import React from 'react'
import { useParams, useNavigate } from 'react-router-dom'

const UserDetail = ({ users, deleteUser }) => {
  const { id } = useParams();
  const navigate = useNavigate();
  const user = users.find(u => u.id === parseInt(id));

  if (!user) {
    return (
      <div style={{ padding: '20px' }}>
        <h2>유저를 찾을 수 없습니다.</h2>
        <button onClick={() => navigate('/')}>목록으로 돌아가기</button>
      </div>
    );
  }


  return (
    <div style={{ padding: '20px' }}>
      <h2>유저 상세 정보</h2>
      <div style={{ border: '1px solid #ddd', padding: '20px', borderRadius: '5px' }}>
        <p><strong>이름:</strong> {user.name}</p>
        <p><strong>나이:</strong> {user.age}세</p>
        <p><strong>온라인 여부 :</strong> {user.online || '없음'}</p>
      </div>
      <div style={{ marginTop: '20px' }}>
        <button onClick={() => navigate('/')}>뒤로 가기</button>
      </div>
    </div>
  )
}

export default UserDetail