import React, { createContext, useContext, useState, useEffect } from 'react';
import { decisionApi } from '../api/decisionApi';
import { memberApi } from '../api/memberApi';

const DecisionContext = createContext();

export const useDecisions = () => {
    const context = useContext(DecisionContext);
    return context;
};

export const DecisionProvider = ({ children }) => {
    const [decisions, setDecisions] = useState([]);
    const [currentUser, setCurrentUser] = useState(() => {
        const savedUser = localStorage.getItem('currentUser');
        return savedUser ? JSON.parse(savedUser) : null;
    });
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(null);

    useEffect(() => {
        if (currentUser) {
            localStorage.setItem('currentUser', JSON.stringify(currentUser));
        } else {
            localStorage.removeItem('currentUser');
        }
    }, [currentUser]);

    // 의사결정 목록 로드 - 전체 목록 가져오기 (memberId 파라미터 없이)
    const loadDecisions = async () => {
        setLoading(true);
        setError(null);
        try {
            // memberId 없이 호출하여 모든 의사결정 가져오기
            const data = await decisionApi.getDecisions();
            setDecisions(data);
        } catch (err) {
            setError(err.message);
            console.error('의사결정 목록 로드 실패:', err);
        } finally {
            setLoading(false);
        }
    };

    // 로그인 시 의사결정 목록 로드
    useEffect(() => {
        if (currentUser) {
            loadDecisions();
        }
    }, [currentUser]);

    // 회원가입
    const register = async (email, name) => {
        try {
            const user = await memberApi.register(email, name);
            setCurrentUser(user);
            return user;
        } catch (err) {
            setError(err.message);
            throw err;
        }
    };

    // 로그인
    const login = async (email) => {
        try {
            const user = await memberApi.login(email);
            setCurrentUser(user);
            return user;
        } catch (err) {
            setError(err.message);
            throw err;
        }
    };

    // 로그아웃
    const logout = () => {
        setCurrentUser(null);
        setDecisions([]);
        localStorage.removeItem('currentUser');
    };

    // 의사결정 생성
    const addDecision = async (decisionData) => {
        if (!currentUser) {
            throw new Error('로그인이 필요합니다.');
        }

        try {
            const newDecision = await decisionApi.createDecision({
                ...decisionData,
                memberId: currentUser.id,
            });
            
            setDecisions(prev => [newDecision, ...prev]);
            return newDecision;
        } catch (err) {
            setError(err.message);
            throw err;
        }
    };

    // 의사결정 삭제 - 본인 것만 가능
    const deleteDecision = async (id) => {
        const decision = decisions.find(d => d.id === id);
        
        if (!decision) {
            throw new Error('의사결정을 찾을 수 없습니다.');
        }
        
        if (decision.memberId !== currentUser?.id) {
            throw new Error('본인의 의사결정만 삭제할 수 있습니다.');
        }

        try {
            await decisionApi.deleteDecision(id);
            setDecisions(prev => prev.filter(decision => decision.id !== id));
        } catch (err) {
            setError(err.message);
            throw err;
        }
    };

    // 회고 추가/수정 - 본인 것만 가능
    const addRetrospective = async (id, retrospectiveData) => {
        const decision = decisions.find(d => d.id === id);
        
        if (!decision) {
            throw new Error('의사결정을 찾을 수 없습니다.');
        }
        
        if (decision.memberId !== currentUser?.id) {
            throw new Error('본인의 의사결정만 회고를 작성할 수 있습니다.');
        }

        try {
            const retrospective = await decisionApi.addRetrospective(id, retrospectiveData);
            
            setDecisions(prev =>
                prev.map(decision =>
                    decision.id === id
                        ? { ...decision, retrospective }
                        : decision
                )
            );
        } catch (err) {
            setError(err.message);
            throw err;
        }
    };

    // ID로 의사결정 찾기
    const getDecisionById = (id) => {
        return decisions.find(decision => decision.id === parseInt(id));
    };

    // 본인 의사결정만 필터링
    const getMyDecisions = () => {
        if (!currentUser) return [];
        return decisions.filter(d => d.memberId === currentUser.id);
    };

    // 통계 가져오기 - 전체 통계
    const getStats = () => {
        const myDecisions = getMyDecisions();
        const total = myDecisions.length;
        const personal = myDecisions.filter(d => d.type === '개인').length;
        const team = myDecisions.filter(d => d.type === '팀').length;
        const withRetrospective = myDecisions.filter(d => d.retrospective !== null).length;

        return { total, personal, team, withRetrospective };
    };

    // 의사결정이 본인 것인지 확인
    const isMyDecision = (decisionId) => {
        const decision = decisions.find(d => d.id === decisionId);
        return decision && decision.memberId === currentUser?.id;
    };

    const value = {
        decisions,              // 전체 의사결정 목록
        currentUser,
        loading,
        error,
        register,
        login,
        logout,
        addDecision,
        deleteDecision,
        addRetrospective,
        getDecisionById,
        getMyDecisions,         // 내 의사결정만
        getStats,
        loadDecisions,
        isMyDecision,           // 권한 체크용
    };

    return (
        <DecisionContext.Provider value={value}>
            {children}
        </DecisionContext.Provider>
    );
};