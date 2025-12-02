import React, { useState } from 'react';
import styled from 'styled-components';
import { useDecisions } from '../context/DecisionContext';
import { Link } from 'react-router-dom';
import { ROUTES } from '../routes/routePaths';

const Container = styled.div`
    max-width: 1000px;
    margin: 0 auto;
`;

const Title = styled.h1`
    font-size: 42px;
    font-weight: bold;
    margin-bottom: 24px;
    color: #333;
`;

const ProfileSection = styled.div`
    background: white;
    padding: 32px;
    border-radius: 12px;
    margin-bottom: 24px;
    display: flex;
    align-items: center;
    gap: 24px;
`;

const Avatar = styled.div`
    width: 100px;
    height: 100px;
    border-radius: 50%;
    background: linear-gradient(135deg, #5833ffff 0%, #8b5cf6 100%);
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 40px;
    color: white;
    font-weight: bold;
`;

const ProfileInfo = styled.div`
    flex: 1;
`;

const ProfileName = styled.h2`
    font-size: 28px;
    font-weight: bold;
    color: #333;
    margin-bottom: 8px;
`;

const ProfileEmail = styled.div`
    font-size: 16px;
    color: #666;
    margin-bottom: 16px;
`;

const EditButton = styled.button`
    padding: 8px 20px;
    background: #f0f0f0;
    border: none;
    border-radius: 6px;
    cursor: pointer;
    font-weight: 600;
    color: #333;
    transition: all 0.2s;

    &:hover {
        background: #e2e2e2;
        scale: 0.98;
    }
`;

const StatsGrid = styled.div`
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 16px;
    margin-bottom: 24px;
`;

const StatCard = styled.div`
    background: white;
    padding: 24px;
    border-radius: 12px;
    text-align: center;
`;

const StatNumber = styled.div`
    font-size: 36px;
    font-weight: bold;
    color: #5833ffff;
    margin-bottom: 8px;
`;

const StatLabel = styled.div`
    font-size: 14px;
    color: #666;
`;

const Section = styled.div`
    background: white;
    padding: 24px;
    border-radius: 12px;
    margin-bottom: 24px;
`;

const SectionTitle = styled.h2`
    font-size: 24px;
    font-weight: 600;
    color: #333;
    margin-bottom: 20px;
    padding-bottom: 12px;
    border-bottom: 2px solid #f0f0f0;
`;

const ChartContainer = styled.div`
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
    gap: 24px;
    margin-bottom: 24px;
`;

const ChartCard = styled.div`
    background: #f9f9f9;
    padding: 20px;
    border-radius: 8px;
`;

const ChartTitle = styled.div`
    font-size: 16px;
    font-weight: 600;
    color: #333;
    margin-bottom: 16px;
`;

const PieChart = styled.div`
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 20px;
`;

const PieVisual = styled.div`
    width: 120px;
    height: 120px;
    border-radius: 50%;
    background: conic-gradient(
        #5833ffff 0deg ${props => props.$teamDeg}deg,
        #24854cff ${props => props.$teamDeg}deg 360deg
    );
`;

const PieLegend = styled.div`
    display: flex;
    flex-direction: column;
    gap: 8px;
`;

const LegendItem = styled.div`
    display: flex;
    align-items: center;
    gap: 8px;
    font-size: 14px;
`;

const LegendColor = styled.div`
    width: 16px;
    height: 16px;
    border-radius: 4px;
    background: ${props => props.$color};
`;

const BarChart = styled.div`
    display: flex;
    flex-direction: column;
    gap: 12px;
`;

const BarItem = styled.div`
    display: flex;
    flex-direction: column;
    gap: 4px;
`;

const BarLabel = styled.div`
    font-size: 13px;
    color: #666;
    display: flex;
    justify-content: space-between;
`;

const BarTrack = styled.div`
    height: 24px;
    background: #e2e2e2;
    border-radius: 4px;
    overflow: hidden;
`;

const BarFill = styled.div`
    height: 100%;
    width: ${props => props.$percentage}%;
    background: #5833ffff;
    transition: width 0.3s;
`;

const RecentList = styled.div`
    display: flex;
    flex-direction: column;
    gap: 12px;
`;

const RecentItem = styled(Link)`
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 16px;
    background: #f9f9f9;
    border-radius: 8px;
    text-decoration: none;
    color: inherit;
    transition: all 0.2s;
    border: 2px solid transparent;

    &:hover {
        border-color: #5833ffff;
        background: white;
    }
`;

const RecentTitle = styled.div`
    font-size: 16px;
    font-weight: 600;
    color: #333;
    margin-bottom: 4px;
`;

const RecentDate = styled.div`
    font-size: 13px;
    color: #999;
`;

const RecentBadge = styled.span`
    background: ${props => props.$type === '팀' ? '#5833ffff' : '#24854cff'};
    color: white;
    padding: 4px 12px;
    border-radius: 12px;
    font-size: 12px;
    font-weight: 600;
`;

const EmptyMessage = styled.div`
    text-align: center;
    padding: 48px;
    color: #999;
`;

const MyPage = () => {
    const { decisions, getStats } = useDecisions();
    const stats = getStats();
    
    // 프로필 정보 (나중에 Context나 로컬스토리지로 관리 가능)
    const [profile] = useState({
        name: '사용자',
        email: 'user@example.com',
        avatar: 'U'
    });

    // 최근 5개 결정
    const recentDecisions = decisions.slice(0, 5);

    // 회고 완료율 계산
    const retrospectiveRate = stats.total > 0 
        ? Math.round((stats.withRetrospective / stats.total) * 100) 
        : 0;

    // 팀/개인 비율 계산 (파이차트용)
    const teamPercentage = stats.total > 0
        ? (stats.team / stats.total) * 100
        : 0;
    const teamDeg = (360 * teamPercentage) / 100;

    // 결정 기준 평균 계산
    const criteriaAverages = decisions.reduce((acc, decision) => {
        acc.speed += decision.criteria.speed;
        acc.cost += decision.criteria.cost;
        acc.scalability += decision.criteria.scalability;
        acc.teamCapability += decision.criteria.teamCapability;
        return acc;
    }, { speed: 0, cost: 0, scalability: 0, teamCapability: 0 });

    if (stats.total > 0) {
        criteriaAverages.speed = Math.round(criteriaAverages.speed / stats.total);
        criteriaAverages.cost = Math.round(criteriaAverages.cost / stats.total);
        criteriaAverages.scalability = Math.round(criteriaAverages.scalability / stats.total);
        criteriaAverages.teamCapability = Math.round(criteriaAverages.teamCapability / stats.total);
    }

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleDateString('ko-KR', {
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });
    };

    return (
        <Container>
            <Title>마이페이지</Title>

            <ProfileSection>
                <Avatar>{profile.avatar}</Avatar>
                <ProfileInfo>
                    <ProfileName>{profile.name}</ProfileName>
                    <ProfileEmail>{profile.email}</ProfileEmail>
                    <EditButton>프로필 수정</EditButton>
                </ProfileInfo>
            </ProfileSection>

            <StatsGrid>
                <StatCard>
                    <StatNumber>{stats.total}</StatNumber>
                    <StatLabel>전체 결정</StatLabel>
                </StatCard>
                <StatCard>
                    <StatNumber>{retrospectiveRate}%</StatNumber>
                    <StatLabel>회고 완료율</StatLabel>
                </StatCard>
                <StatCard>
                    <StatNumber>{stats.personal}</StatNumber>
                    <StatLabel>개인 결정</StatLabel>
                </StatCard>
                <StatCard>
                    <StatNumber>{stats.team}</StatNumber>
                    <StatLabel>팀 결정</StatLabel>
                </StatCard>
            </StatsGrid>

            <Section>
                <SectionTitle>📊 의사결정 분석</SectionTitle>
                <ChartContainer>
                    <ChartCard>
                        <ChartTitle>결정 유형 분포</ChartTitle>
                        {stats.total > 0 ? (
                            <PieChart>
                                <PieVisual $teamDeg={teamDeg} />
                                <PieLegend>
                                    <LegendItem>
                                        <LegendColor $color="#5833ffff" />
                                        <span>팀 ({stats.team})</span>
                                    </LegendItem>
                                    <LegendItem>
                                        <LegendColor $color="#24854cff" />
                                        <span>개인 ({stats.personal})</span>
                                    </LegendItem>
                                </PieLegend>
                            </PieChart>
                        ) : (
                            <EmptyMessage>데이터가 없습니다</EmptyMessage>
                        )}
                    </ChartCard>

                    <ChartCard>
                        <ChartTitle>주요 결정 기준 (평균)</ChartTitle>
                        {stats.total > 0 ? (
                            <BarChart>
                                <BarItem>
                                    <BarLabel>
                                        <span>속도</span>
                                        <span>{criteriaAverages.speed}/5</span>
                                    </BarLabel>
                                    <BarTrack>
                                        <BarFill $percentage={(criteriaAverages.speed / 5) * 100} />
                                    </BarTrack>
                                </BarItem>
                                <BarItem>
                                    <BarLabel>
                                        <span>비용</span>
                                        <span>{criteriaAverages.cost}/5</span>
                                    </BarLabel>
                                    <BarTrack>
                                        <BarFill $percentage={(criteriaAverages.cost / 5) * 100} />
                                    </BarTrack>
                                </BarItem>
                                <BarItem>
                                    <BarLabel>
                                        <span>확장성</span>
                                        <span>{criteriaAverages.scalability}/5</span>
                                    </BarLabel>
                                    <BarTrack>
                                        <BarFill $percentage={(criteriaAverages.scalability / 5) * 100} />
                                    </BarTrack>
                                </BarItem>
                                <BarItem>
                                    <BarLabel>
                                        <span>팀 역량</span>
                                        <span>{criteriaAverages.teamCapability}/5</span>
                                    </BarLabel>
                                    <BarTrack>
                                        <BarFill $percentage={(criteriaAverages.teamCapability / 5) * 100} />
                                    </BarTrack>
                                </BarItem>
                            </BarChart>
                        ) : (
                            <EmptyMessage>데이터가 없습니다</EmptyMessage>
                        )}
                    </ChartCard>
                </ChartContainer>
            </Section>

            <Section>
                <SectionTitle>🕒 최근 활동</SectionTitle>
                {recentDecisions.length > 0 ? (
                    <RecentList>
                        {recentDecisions.map(decision => (
                            <RecentItem key={decision.id} to={ROUTES.DECISION_DETAIL(decision.id)}>
                                <div>
                                    <RecentTitle>{decision.title}</RecentTitle>
                                    <RecentDate>{formatDate(decision.decisionDate)}</RecentDate>
                                </div>
                                <RecentBadge $type={decision.type}>{decision.type}</RecentBadge>
                            </RecentItem>
                        ))}
                    </RecentList>
                ) : (
                    <EmptyMessage>아직 기록된 결정이 없습니다</EmptyMessage>
                )}
            </Section>
        </Container>
    );
};

export default MyPage;