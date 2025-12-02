import React, { useState } from 'react'
import { useNavigate } from 'react-router-dom'

const UserRegister = ({ users, addUser }) => {
  const [name, setName] = useState("");
  const [age, setAge] = useState("");
  const [online, setOnline] = useState("Offline");
  const navigate = useNavigate();

  const handleChangeName = (ev) => {
    setName(ev.target.value);
  }

  const handleChangeAge = (ev) => {
    setAge(ev.target.value);
  }

  const handleChangeOnline = (ev) => {
    setOnline(ev.target.value);
  }

  const handleSubmit = (ev) => {
    ev.preventDefault(); 
    
    if (!name.trim()) {
      alert('이름을 입력해주세요!');
      return;
    }
    if (!age || age <= 0) {
      alert('올바른 나이를 입력해주세요!');
      return;
    }
    
    const newUser = {
      id: users.length > 0 ? users[users.length - 1].id + 1 : 1,
      name: name,
      age: parseInt(age),
      online: online
    };
    
    addUser(newUser);
    navigate('/');
  }

  return (
    <form onSubmit={handleSubmit}>
      <label>
        이름 : <input type='text' value={name} onChange={handleChangeName} />
      </label>
      <br /><br />
      <label>
        나이 : <input type="number" value={age} onChange={handleChangeAge} />
      </label>
      <br /><br />
        온라인 여부 : 
      <select value={online} onChange={handleChangeOnline}>
            <option value="Online">온라인</option>
            <option value="Offline">오프라인</option>
        </select>
      <br /><br />
      <button type="submit">제출</button>
    </form>
  )
}

export default UserRegister