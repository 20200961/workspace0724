# Decision Log

의사결정 과정을 체계적으로 기록하고 회고하는 웹 애플리케이션

## 프로젝트 소개

개인과 팀의 중요한 의사결정을 문서화하고, 결정의 배경과 결과를 추적하여 더 나은 의사결정을 돕는 서비스입니다.

### 주요 기능

- 📝 의사결정 기록 (상황, 선택지, 기준)
- 🔍 선택지 비교 및 분석
- 🎯 결과 회고 작성
- 📊 통계 대시보드
- 👥 팀/개인 결정 구분

## 기술 스택

**Frontend**
- React 18 + React Router v6
- Styled Components
- Context API

**Backend (필수)**
- REST API 서버 (Spring Boot, Express 등)
- Database (MySQL, PostgreSQL 등)

> ⚠️ **백엔드 API 서버가 반드시 필요합니다**

## 빠른 시작

### 1. 백엔드 서버 실행
```bash
# 백엔드 서버를 먼저 실행하세요 (예: http://localhost:8080)
```

### 2. 프론트엔드 실행
```bash
npm install
npm run dev
```

### 3. 프록시 설정 (vite.config.js)
```javascript
export default defineConfig({
  server: {
    proxy: {
      '/api': 'http://localhost:8080'
    }
  }
})
```

## API 엔드포인트

### 회원
- `POST /api/members` - 회원가입
- `POST /api/members/login` - 로그인
- `GET /api/members/:id/stats` - 통계 조회

### 의사결정
- `GET /api/decisions` - 목록 조회
- `GET /api/decisions/:id` - 상세 조회
- `POST /api/decisions` - 생성
- `DELETE /api/decisions/:id` - 삭제
- `POST /api/decisions/:id/retrospective` - 회고 작성

## 프로젝트 구조

```
src/
├── api/                 # API 통신
├── components/          # 재사용 컴포넌트
├── context/            # Context API (Auth, Decision)
├── pages/              # 페이지 컴포넌트
└── routes/             # 라우팅 설정
```

## 데이터베이스 스키마

```sql
members (id, email, name, created_at)
decisions (id, member_id, title, type, situation, final_choice, decision_date)
options (id, decision_id, name, pros, cons, risks)
criteria (id, decision_id, speed, cost, scalability, team_capability)
retrospectives (id, decision_id, actual_result, was_correct, improvements)
```
