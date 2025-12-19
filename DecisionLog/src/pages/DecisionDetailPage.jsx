import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { useDecisions } from '../context/DecisionContext';
import { ROUTES } from '../routes/routePaths';
import { decisionApi } from '../api/decisionApi';

const Container = styled.div`
    max-width: 900px;
    margin: 0 auto;
`;

const BackButton = styled.button`
    padding: 8px 16px;
    border: 1px solid #e2e2e2;
    background: white;
    border-radius: 4px;
    cursor: pointer;
    margin-bottom: 24px;
    color: #666;

    &:hover {
        background: #f9f9f9;
    }
`;

const Header = styled.div`
    background: white;
    padding: 32px;
    border-radius: 12px;
    margin-bottom: 24px;
`;

const Title = styled.h1`
    font-size: 36px;
    font-weight: bold;
    color: #333;
    margin-bottom: 16px;
`;

const Meta = styled.div`
    display: flex;
    gap: 16px;
    margin-bottom: 16px;
`;

const Badge = styled.span`
    background: ${props => props.color || '#e2e2e2'};
    color: ${props => props.textColor || '#333'};
    padding: 6px 16px;
    border-radius: 12px;
    font-size: 14px;
    font-weight: 600;
`;

const DateText = styled.div`
    color: #999;
    font-size: 14px;
`;

const Section = styled.div`
    background: white;
    padding: 24px;
    border-radius: 12px;
    margin-bottom: 24px;
`;

const SectionTitle = styled.h2`
    font-size: 20px;
    font-weight: 600;
    color: #333;
    margin-bottom: 16px;
    padding-bottom: 12px;
    border-bottom: 2px solid #f0f0f0;
`;

const Situation = styled.p`
    line-height: 1.8;
    color: #666;
    font-size: 15px;
`;

const OptionsGrid = styled.div`
    display: grid;
    gap: 16px;
`;

const OptionCard = styled.div`
    border: 2px solid ${props => props.isSelected ? '#5833ffff' : '#e2e2e2'};
    background: ${props => props.isSelected ? '#f3effdff' : 'white'};
    padding: 20px;
    border-radius: 8px;
`;

const OptionHeader = styled.div`
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 12px;
`;

const OptionName = styled.h3`
    font-size: 18px;
    font-weight: 600;
    color: #333;
`;

const SelectedBadge = styled.span`
    background: #5833ffff;
    color: white;
    padding: 4px 12px;
    border-radius: 12px;
    font-size: 12px;
    font-weight: 600;
`;

const OptionDetail = styled.div`
    margin-bottom: 12px;
`;

const DetailLabel = styled.div`
    font-size: 13px;
    font-weight: 600;
    color: #999;
    margin-bottom: 4px;
`;

const DetailContent = styled.div`
    font-size: 14px;
    color: #666;
    line-height: 1.6;
`;

const CriteriaGrid = styled.div`
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
    gap: 16px;
`;

const CriteriaItem = styled.div`
    padding: 16px;
    background: #f9f9f9;
    border-radius: 8px;
`;

const CriteriaName = styled.div`
    font-size: 14px;
    color: #666;
    margin-bottom: 8px;
`;

const CriteriaValue = styled.div`
    display: flex;
    align-items: center;
    gap: 8px;
`;

const CriteriaBar = styled.div`
    flex: 1;
    height: 8px;
    background: #e2e2e2;
    border-radius: 4px;
    overflow: hidden;
`;

const CriteriaFill = styled.div`
    height: 100%;
    width: ${props => (props.value / 5) * 100}%;
    background: #5833ffff;
    transition: width 0.3s;
`;

const CriteriaScore = styled.span`
    font-size: 16px;
    font-weight: 600;
    color: #5833ffff;
`;

const RetrospectiveForm = styled.div`
    margin-top: 16px;
`;

const TextArea = styled.textarea`
    width: 100%;
    padding: 12px;
    border: 1px solid #e2e2e2;
    border-radius: 4px;
    margin-bottom: 12px;
    min-height: 80px;
    resize: vertical;
    outline: none;

    &:focus {
        border-color: #5833ffff;
    }
`;

const RadioGroup = styled.div`
    display: flex;
    gap: 16px;
    margin-bottom: 12px;
`;

const RadioLabel = styled.label`
    display: flex;
    align-items: center;
    gap: 8px;
    cursor: pointer;
`;

const Button = styled.button`
    padding: 12px 24px;
    border: none;
    background: #5833ffff;
    color: white;
    border-radius: 4px;
    cursor: pointer;
    font-weight: 600;

    &:hover {
        scale: 0.98;
    }
`;

const RetrospectiveContent = styled.div`
    background: #f9f9f9;
    padding: 20px;
    border-radius: 8px;
    margin-top: 16px;
`;

const RetrospectiveItem = styled.div`
    margin-bottom: 16px;

    &:last-child {
        margin-bottom: 0;
    }
`;

const LoadingMessage = styled.div`
    text-align: center;
    padding: 48px;
    color: #666;
`;

const DecisionDetailPage = () => {
    const { id } = useParams();
    const navigate = useNavigate();
    const { addRetrospective, currentUser, isMyDecision } = useDecisions();
    const [decision, setDecision] = useState(null);
    const [loading, setLoading] = useState(true);
    const [showRetrospectiveForm, setShowRetrospectiveForm] = useState(false);
    const [retrospectiveData, setRetrospectiveData] = useState({
        actualResult: '',
        wasCorrect: 'yes',
        improvements: ''
    });

    useEffect(() => {
        const loadDecision = async () => {
            try {
                const data = await decisionApi.getDecision(id);
                setDecision(data);
            } catch (err) {
                alert('의사결정을 불러오는데 실패했습니다: ' + err.message);
                navigate(ROUTES.HOME);
            } finally {
                setLoading(false);
            }
        };

        loadDecision();
    }, [id, navigate]);

    const formatDate = (dateString) => {
        const date = new Date(dateString);
        return date.toLocaleDateString('ko-KR', {
            year: 'numeric',
            month: 'long',
            day: 'numeric'
        });
    };

    const handleAddRetrospective = async () => {
        if (retrospectiveData.actualResult.trim() === '') {
            alert('실제 결과를 입력해주세요.');
            return;
        }
        
        try {
            const retrospective = await addRetrospective(decision.id, retrospectiveData);
            setDecision(prev => ({ ...prev, retrospective }));
            setShowRetrospectiveForm(false);
            alert('회고가 저장되었습니다.');
        } catch (err) {
            alert('회고 저장 실패: ' + err.message);
        }
    };

    if (loading) {
        return (
            <Container>
                <LoadingMessage>로딩 중...</LoadingMessage>
            </Container>
        );
    }

    if (!decision) {
        return (
            <Container>
                <p>결정을 찾을 수 없습니다.</p>
                <BackButton onClick={() => navigate(ROUTES.HOME)}>목록으로</BackButton>
            </Container>
        );
    }

    const canEdit = decision && currentUser && decision.memberId === currentUser.id;

    return (
        <Container>
            <BackButton onClick={() => navigate(ROUTES.HOME)}>← 목록으로</BackButton>

            <Header>
                <Title>{decision.title}</Title>
                <Meta>
                    <Badge
                        color={decision.type === '팀' ? '#5833ffff' : '#24854cff'}
                        textColor="white"
                    >
                        {decision.type}
                    </Badge>
                    <DateText>{formatDate(decision.decisionDate)}</DateText>
                </Meta>
            </Header>

            <Section>
                <SectionTitle>상황 설명</SectionTitle>
                <Situation>{decision.situation}</Situation>
            </Section>

            <Section>
                <SectionTitle>선택지 비교</SectionTitle>
                <OptionsGrid>
                    {decision.options.map((option, index) => (
                        <OptionCard
                            key={option.id}
                            isSelected={option.name === decision.finalChoice}
                        >
                            <OptionHeader>
                                <OptionName>
                                    {option.name || `선택지 ${String.fromCharCode(65 + index)}`}
                                </OptionName>
                                {option.name === decision.finalChoice && (
                                    <SelectedBadge>최종 선택</SelectedBadge>
                                )}
                            </OptionHeader>

                            <OptionDetail>
                                <DetailLabel>장점</DetailLabel>
                                <DetailContent>{option.pros || '없음'}</DetailContent>
                            </OptionDetail>

                            <OptionDetail>
                                <DetailLabel>단점</DetailLabel>
                                <DetailContent>{option.cons || '없음'}</DetailContent>
                            </OptionDetail>

                            <OptionDetail>
                                <DetailLabel>위험 요소</DetailLabel>
                                <DetailContent>{option.risks || '없음'}</DetailContent>
                            </OptionDetail>
                        </OptionCard>
                    ))}
                </OptionsGrid>
            </Section>

            <Section>
                <SectionTitle>결정 기준</SectionTitle>
                <CriteriaGrid>
                    <CriteriaItem>
                        <CriteriaName>속도</CriteriaName>
                        <CriteriaValue>
                            <CriteriaBar>
                                <CriteriaFill value={decision.criteria.speed} />
                            </CriteriaBar>
                            <CriteriaScore>{decision.criteria.speed}/5</CriteriaScore>
                        </CriteriaValue>
                    </CriteriaItem>

                    <CriteriaItem>
                        <CriteriaName>비용</CriteriaName>
                        <CriteriaValue>
                            <CriteriaBar>
                                <CriteriaFill value={decision.criteria.cost} />
                            </CriteriaBar>
                            <CriteriaScore>{decision.criteria.cost}/5</CriteriaScore>
                        </CriteriaValue>
                    </CriteriaItem>

                    <CriteriaItem>
                        <CriteriaName>확장성</CriteriaName>
                        <CriteriaValue>
                            <CriteriaBar>
                                <CriteriaFill value={decision.criteria.scalability} />
                            </CriteriaBar>
                            <CriteriaScore>{decision.criteria.scalability}/5</CriteriaScore>
                        </CriteriaValue>
                    </CriteriaItem>

                    <CriteriaItem>
                        <CriteriaName>팀 역량</CriteriaName>
                        <CriteriaValue>
                            <CriteriaBar>
                                <CriteriaFill value={decision.criteria.teamCapability} />
                            </CriteriaBar>
                            <CriteriaScore>{decision.criteria.teamCapability}/5</CriteriaScore>
                        </CriteriaValue>
                    </CriteriaItem>
                </CriteriaGrid>
            </Section>

            <Section>
                <SectionTitle>결과 회고</SectionTitle>
                {!decision.retrospective && !showRetrospectiveForm && (
                    <div>
                        {canEdit ? (
                            <>
                                <p style={{ color: '#999', marginBottom: '16px' }}>
                                    아직 회고가 작성되지 않았습니다.
                                </p>
                                <Button onClick={() => setShowRetrospectiveForm(true)}>
                                    회고 작성하기
                                </Button>
                            </>
                        ) : (
                            <p style={{ color: '#999', marginBottom: '16px' }}>
                                아직 회고가 작성되지 않았습니다.
                            </p>
                        )}
                    </div>
                )}

                {showRetrospectiveForm && canEdit && (
                    <RetrospectiveForm>
                        <DetailLabel>실제 결과는 어땠나요?</DetailLabel>
                        <TextArea
                            value={retrospectiveData.actualResult}
                            onChange={(e) => setRetrospectiveData(prev => ({
                                ...prev,
                                actualResult: e.target.value
                            }))}
                            placeholder="결정 후 실제로 어떤 일이 일어났는지 작성해주세요..."
                        />

                        <DetailLabel>판단이 맞았나요?</DetailLabel>
                        <RadioGroup>
                            <RadioLabel>
                                <input
                                    type="radio"
                                    name="wasCorrect"
                                    value="yes"
                                    checked={retrospectiveData.wasCorrect === 'yes'}
                                    onChange={(e) => setRetrospectiveData(prev => ({
                                        ...prev,
                                        wasCorrect: e.target.value
                                    }))}
                                />
                                예, 좋은 결정이었습니다
                            </RadioLabel>
                            <RadioLabel>
                                <input
                                    type="radio"
                                    name="wasCorrect"
                                    value="no"
                                    checked={retrospectiveData.wasCorrect === 'no'}
                                    onChange={(e) => setRetrospectiveData(prev => ({
                                        ...prev,
                                        wasCorrect: e.target.value
                                    }))}
                                />
                                아니요, 아쉬운 점이 있습니다
                            </RadioLabel>
                        </RadioGroup>

                        <DetailLabel>다음에 바꿀 점은?</DetailLabel>
                        <TextArea
                            value={retrospectiveData.improvements}
                            onChange={(e) => setRetrospectiveData(prev => ({
                                ...prev,
                                improvements: e.target.value
                            }))}
                            placeholder="다음에 비슷한 상황이 온다면 어떻게 하시겠습니까?"
                        />

                        <Button onClick={handleAddRetrospective}>회고 저장</Button>
                    </RetrospectiveForm>
                )}

                {decision.retrospective && (
                    <RetrospectiveContent>
                        <RetrospectiveItem>
                            <DetailLabel>실제 결과</DetailLabel>
                            <DetailContent>{decision.retrospective.actualResult}</DetailContent>
                        </RetrospectiveItem>

                        <RetrospectiveItem>
                            <DetailLabel>판단 평가</DetailLabel>
                            <DetailContent>
                                {decision.retrospective.wasCorrect === 'yes'
                                    ? '좋은 결정이었습니다'
                                    : '아쉬운 점이 있습니다'}
                            </DetailContent>
                        </RetrospectiveItem>

                        <RetrospectiveItem>
                            <DetailLabel>개선 사항</DetailLabel>
                            <DetailContent>{decision.retrospective.improvements}</DetailContent>
                        </RetrospectiveItem>

                        <RetrospectiveItem>
                            <DetailLabel>작성 날짜</DetailLabel>
                            <DetailContent>
                                {formatDate(decision.retrospective.updatedAt)}
                            </DetailContent>
                        </RetrospectiveItem>
                    </RetrospectiveContent>
                )}
            </Section>
        </Container>
    );
};

export default DecisionDetailPage;