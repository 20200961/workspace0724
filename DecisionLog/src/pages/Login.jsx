import React, { useState } from 'react';
import styled from 'styled-components';
import { useDecisions } from '../context/DecisionContext';
import { useNavigate } from 'react-router-dom';
import { ROUTES } from '../routes/routePaths';

const Container = styled.div`
    max-width: 400px;
    margin: 100px auto;
    padding: 40px;
    background: white;
    border-radius: 12px;
    box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
`;

const Title = styled.h1`
    font-size: 32px;
    font-weight: bold;
    color: #5833ffff;
    text-align: center;
    margin-bottom: 40px;
`;

const Form = styled.form`
    display: flex;
    flex-direction: column;
    gap: 16px;
`;

const Input = styled.input`
    padding: 12px;
    border: 1px solid #e2e2e2;
    border-radius: 4px;
    font-size: 16px;
    outline: none;

    &:focus {
        border-color: #5833ffff;
    }
`;

const Button = styled.button`
    padding: 12px;
    background: #5833ffff;
    color: white;
    border: none;
    border-radius: 4px;
    font-size: 16px;
    font-weight: 600;
    cursor: pointer;

    &:hover {
        scale: 0.98;
    }

    &:disabled {
        background: #ccc;
        cursor: not-allowed;
    }
`;

const TabContainer = styled.div`
    display: flex;
    margin-bottom: 24px;
    border-bottom: 2px solid #e2e2e2;
`;

const Tab = styled.button`
    flex: 1;
    padding: 12px;
    background: none;
    border: none;
    font-size: 16px;
    font-weight: 600;
    color: ${props => props.$active ? '#5833ffff' : '#999'};
    border-bottom: 2px solid ${props => props.$active ? '#5833ffff' : 'transparent'};
    margin-bottom: -2px;
    cursor: pointer;

    &:hover {
        color: #5833ffff;
    }
`;

const ErrorMessage = styled.div`
    color: #ff4444;
    font-size: 14px;
    text-align: center;
`;

const Login = () => {
    const [isLogin, setIsLogin] = useState(true);
    const [email, setEmail] = useState('');
    const [name, setName] = useState('');
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);
    
    const { login, register } = useDecisions();
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setLoading(true);

        try {
            if (isLogin) {
                await login(email);
            } else {
                if (!name.trim()) {
                    setError('이름을 입력해주세요.');
                    setLoading(false);
                    return;
                }
                await register(email, name);
            }
            navigate(ROUTES.HOME);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };

    return (
        <Container>
            <Title>Decision Log</Title>
            
            <TabContainer>
                <Tab 
                    type="button"
                    $active={isLogin} 
                    onClick={() => setIsLogin(true)}
                >
                    로그인
                </Tab>
                <Tab 
                    type="button"
                    $active={!isLogin} 
                    onClick={() => setIsLogin(false)}
                >
                    회원가입
                </Tab>
            </TabContainer>

            <Form onSubmit={handleSubmit}>
                <Input
                    type="email"
                    placeholder="이메일"
                    value={email}
                    onChange={(e) => setEmail(e.target.value)}
                    required
                />
                
                {!isLogin && (
                    <Input
                        type="text"
                        placeholder="이름"
                        value={name}
                        onChange={(e) => setName(e.target.value)}
                        required
                    />
                )}

                {error && <ErrorMessage>{error}</ErrorMessage>}

                <Button type="submit" disabled={loading}>
                    {loading ? '처리 중...' : (isLogin ? '로그인' : '회원가입')}
                </Button>
            </Form>
        </Container>
    );
};

export default Login;