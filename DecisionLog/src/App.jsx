import './App.css'
import AppRoutes from './routes/routes'
import { DecisionProvider } from './context/DecisionContext'

function App() {
  return (
    <DecisionProvider>
      <AppRoutes />
    </DecisionProvider>
  )
}

export default App