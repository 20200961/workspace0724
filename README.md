# Decision Log

의사결정 과정을 체계적으로 기록하고 회고하는 웹 애플리케이션입니다.

## 프로젝트 소개

Decision Log는 개인과 팀의 중요한 의사결정 과정을 문서화하고, 결정의 배경과 결과를 추적하여 더 나은 의사결정을 돕는 서비스입니다.

### 주요 기능

- **의사결정 기록**: 상황, 선택지, 기준을 체계적으로 문서화
- **선택지 비교**: 각 옵션의 장단점과 위험 요소 분석
- **결정 기준 설정**: 속도, 비용, 확장성, 팀 역량 등 맞춤 기준
- **회고 작성**: 결정 후 실제 결과와 개선점 기록
- **통계 대시보드**: 의사결정 패턴과 트렌드 분석
- **팀/개인 구분**: 결정 유형별 관리

## 기술 스택

### Frontend
- **React 18** - UI 라이브러리
- **React Router v6** - 클라이언트 사이드 라우팅
- **Styled Components** - CSS-in-JS 스타일링
- **Context API** - 전역 상태 관리

### Backend (별도 서버 필요)
- **REST API** - HTTP 기반 API 통신
- 예상 엔드포인트:
  - `/api/members` - 회원 관리
  - `/api/decisions` - 의사결정 CRUD
  - `/api/decisions/:id/retrospective` - 회고 작성

## 설치 및 실행

### 사전 요구사항
- Node.js 16.x 이상
- npm 또는 yarn

### 프론트엔드 실행

```bash
# 저장소 클론
git clone [repository-url]
cd decision-log

# 의존성 설치
npm install

# 개발 서버 실행
npm run dev

# 프로덕션 빌드
npm run build
```

### 환경 변수 설정

프로젝트 루트에 `.env` 파일 생성:

```env
VITE_API_BASE_URL=http://localhost:8080/api
```

## 프로젝트 구조

```
src/
├── api/                    # API 통신 모듈
│   ├── apiClient.js       # HTTP 클라이언트
│   ├── decisionApi.js     # 의사결정 API
│   └── memberApi.js       # 회원 API
├── components/            # 재사용 컴포넌트
│   ├── Decision/
│   │   └── DecisionForm.jsx
│   └── Layout/
│       ├── Header.jsx
│       └── Layout.jsx
├── context/              # Context API
│   ├── AuthContext.jsx
│   └── DecisionContext.jsx
├── pages/                # 페이지 컴포넌트
│   ├── DecisionDetailPage.jsx
│   ├── DecisionListPage.jsx
│   ├── Login.jsx
│   ├── MyPage.jsx
│   └── NotFound.jsx
├── routes/               # 라우팅 설정
│   ├── routes.jsx
│   └── routePaths.js
├── App.jsx
└── main.jsx
```

## API 명세

### 회원 API

#### 회원가입
```http
POST /api/members
Content-Type: application/json

{
  "email": "user@example.com",
  "name": "홍길동"
}
```

#### 로그인
```http
POST /api/members/login
Content-Type: application/json

{
  "email": "user@example.com"
}
```

#### 회원 통계 조회
```http
GET /api/members/{memberId}/stats
```

### 의사결정 API

#### 의사결정 생성
```http
POST /api/decisions
Content-Type: application/json

{
  "memberId": 1,
  "title": "프론트엔드 프레임워크 선택",
  "type": "팀",
  "situation": "새 프로젝트를 시작하면서...",
  "options": [
    {
      "name": "React",
      "pros": "풍부한 생태계",
      "cons": "러닝커브",
      "risks": "버전 업데이트"
    }
  ],
  "finalChoice": "React",
  "criteria": {
    "speed": 4,
    "cost": 3,
    "scalability": 5,
    "teamCapability": 4
  }
}
```

#### 의사결정 목록 조회
```http
GET /api/decisions
GET /api/decisions?memberId={memberId}
```

#### 의사결정 상세 조회
```http
GET /api/decisions/{decisionId}
```

#### 의사결정 삭제
```http
DELETE /api/decisions/{decisionId}
```

#### 회고 작성
```http
POST /api/decisions/{decisionId}/retrospective
Content-Type: application/json

{
  "actualResult": "예상대로 개발 속도가 빨라졌습니다",
  "wasCorrect": "yes",
  "improvements": "초기 학습 기간을 더 고려했어야 했습니다"
}
```

## 주요 기능 설명

### 1. 의사결정 기록
- 결정의 배경과 상황 문서화
- 여러 선택지의 장단점 비교
- 결정 기준별 가중치 설정
- 최종 선택 사항 기록

### 2. 회고 시스템
- 결정 후 실제 결과 기록
- 판단의 적절성 평가
- 다음 의사결정을 위한 개선점 도출

### 3. 통계 대시보드
- 전체/개인/팀 결정 수 집계
- 회고 완료율 추적
- 결정 기준별 평균값 시각화
- 최근 활동 내역

### 4. 권한 관리
- 본인의 의사결정만 수정/삭제 가능
- 타인의 의사결정은 조회만 가능

## 개발 가이드

### 컴포넌트 작성 규칙

```jsx
// Styled Components 사용
const Container = styled.div`
  max-width: 1000px;
  margin: 0 auto;
`;

// Context 사용
const { decisions, addDecision } = useDecisions();
const { currentUser } = useAuth();
```

### API 호출 패턴

```javascript
// Try-catch로 에러 처리
try {
  const data = await decisionApi.getDecisions();
  setDecisions(data);
} catch (err) {
  console.error('API 호출 실패:', err);
  alert(err.message);
}
```

### 라우트 보호

```jsx
// ProtectedRoute로 인증 필요한 페이지 보호
<Route path="/mypage" element={
  <ProtectedRoute>
    <MyPage />
  </ProtectedRoute>
} />
```

## 향후 개발 계획

- [ ] 의사결정 검색 및 필터링
- [ ] 태그 시스템
- [ ] 의사결정 템플릿 기능
- [ ] 팀원과 공유 기능
- [ ] 이메일 알림
- [ ] 데이터 내보내기 (PDF, CSV)
- [ ] 다크 모드
- [ ] 모바일 앱

## 라이센스

이 프로젝트는 MIT 라이센스를 따릅니다.

## 기여하기

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 문의

프로젝트에 대한 문의사항이나 버그 리포트는 Issues를 통해 남겨주세요.
