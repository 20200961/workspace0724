import { useState } from 'react'
import JavaScript from './components/JavaScript'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
      <JavaScript></JavaScript>
    </>
  )
}

export default App
