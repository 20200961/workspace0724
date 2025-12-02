import React, { createContext, useContext, useState, useEffect } from 'react';

const DecisionContext = createContext();

export const useDecisions = () => {
    const context = useContext(DecisionContext);
    return context;
};

export const DecisionProvider = ({ children }) => {
    const [decisions, setDecisions] = useState(() => {
        const savedDecisions = localStorage.getItem('decisions');
        return savedDecisions ? JSON.parse(savedDecisions) : [];
    });

    useEffect(() => {
        localStorage.setItem('decisions', JSON.stringify(decisions));
    }, [decisions]);

    // 의사결정 생성
    const addDecision = (decisionData) => {
        const newDecision = {
            id: Date.now(),
            title: decisionData.title,
            decisionDate: new Date().toISOString(),
            type: decisionData.type, // '개인' 또는 '팀'
            situation: decisionData.situation,
            options: decisionData.options, // [{ name, pros, cons, risks }]
            finalChoice: decisionData.finalChoice,
            criteria: decisionData.criteria, // { speed, cost, scalability, teamCapability }
            retrospective: null, // 나중에 추가
            createdAt: new Date().toISOString(),
        };

        setDecisions(prev => [newDecision, ...prev]);
        return newDecision;
    };

    // 의사결정 삭제
    const deleteDecision = (id) => {
        setDecisions(prev => prev.filter(decision => decision.id !== id));
    };

    // 의사결정 수정
    const updateDecision = (id, updateData) => {
        setDecisions(prev =>
            prev.map(decision =>
                decision.id === id ? { ...decision, ...updateData } : decision
            )
        );
    };

    // 회고 추가/수정
    const addRetrospective = (id, retrospectiveData) => {
        setDecisions(prev =>
            prev.map(decision =>
                decision.id === id
                    ? {
                        ...decision,
                        retrospective: {
                            actualResult: retrospectiveData.actualResult,
                            wasCorrect: retrospectiveData.wasCorrect,
                            improvements: retrospectiveData.improvements,
                            updatedAt: new Date().toISOString(),
                        }
                    }
                    : decision
            )
        );
    };

    // ID로 의사결정 찾기
    const getDecisionById = (id) => {
        return decisions.find(decision => decision.id === parseInt(id));
    };

    // 통계 가져오기
    const getStats = () => {
        const total = decisions.length;
        const personal = decisions.filter(d => d.type === '개인').length;
        const team = decisions.filter(d => d.type === '팀').length;
        const withRetrospective = decisions.filter(d => d.retrospective !== null).length;

        return { total, personal, team, withRetrospective };
    };

    const value = {
        decisions,
        addDecision,
        deleteDecision,
        updateDecision,
        addRetrospective,
        getDecisionById,
        getStats,
    };

    return (
        <DecisionContext.Provider value={value}>
            {children}
        </DecisionContext.Provider>
    );
};